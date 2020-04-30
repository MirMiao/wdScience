package com.wd.tech.adapter.infromation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.messagebean.CrowdChatContentBean;
import com.wd.tech.bean.messagebean.FriendChatDialogueRecordBean;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.util.RsaCoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/25 13:17
 * @classname :
 */
public class MyCrowdChatContentadapter extends  RecyclerView.Adapter<MyCrowdChatContentadapter.MyCrowdChatDialogueminViewHolder>{
    private    List<CrowdChatContentBean.ResultBean> result=new ArrayList<>();
    private Context context;
    private MyCrowdChatDialogueminViewHolder myCrowdChatDialogueminViewHolder;

    public MyCrowdChatContentadapter(List<CrowdChatContentBean.ResultBean> result, Context context) {
        this.result.addAll(result);
        this.context = context;
    }

    @NonNull
    @Override
    public MyCrowdChatDialogueminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.chatdialogue_mine,parent,false);
        myCrowdChatDialogueminViewHolder = new MyCrowdChatDialogueminViewHolder(view);
        return myCrowdChatDialogueminViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCrowdChatDialogueminViewHolder holder, int position) {
        RetrofitUtil.getInstance().getRoundphoto(result.get(position).getHeadPic(),myCrowdChatDialogueminViewHolder.mine_head);
        try {
            String content= RsaCoder.decryptByPublicKey(result.get(position).getChatContent());
            //解密好友发送的信息
            myCrowdChatDialogueminViewHolder.mine_content.setText(content);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    //刷新消息接口
    public void loadmore(List<CrowdChatContentBean.ResultBean> result) {
        this.result.addAll(result);
        notifyDataSetChanged();
    }
    class  MyCrowdChatDialogueminViewHolder extends RecyclerView.ViewHolder {
        ImageView mine_head;
        TextView mine_content;
        public MyCrowdChatDialogueminViewHolder(@NonNull View itemView) {
            super(itemView);
            mine_head=itemView.findViewById(R.id.mine_head);
            mine_content=itemView.findViewById(R.id.mine_content);
        }
    }
}
