package com.wd.tech.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.FriendChatDialogueRecordBean;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.util.RsaCoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/19 17:49
 * @classname :
 */
public class MyFriendChatDialogueRecordadapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private List<FriendChatDialogueRecordBean.ResultBean> FriendChatDialogueRecordresult=new ArrayList<>();
  private Context context;
    private MyChatDialoguefriendViewHolder myChatDialoguefriendViewHolder;
    private MyChatDialogueminViewHolder myMyChatDialogueminViewHolder;

    public MyFriendChatDialogueRecordadapter(List<FriendChatDialogueRecordBean.ResultBean> friendChatDialogueRecordresult, Context context) {
        FriendChatDialogueRecordresult.addAll(friendChatDialogueRecordresult);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==0){
            View view= LayoutInflater.from(context).inflate(R.layout.chatdialogue_friend,parent,false);
            myChatDialoguefriendViewHolder = new MyChatDialoguefriendViewHolder(view);
            return  myChatDialoguefriendViewHolder;
        }
        else {
            View view= LayoutInflater.from(context).inflate(R.layout.chatdialogue_mine,parent,false);
            myMyChatDialogueminViewHolder = new MyChatDialogueminViewHolder(view);
            return myMyChatDialogueminViewHolder;
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyChatDialoguefriendViewHolder){
         MyChatDialoguefriendViewHolder  myChatDialoguefriendViewHolder=  (MyChatDialoguefriendViewHolder)holder;
         RetrofitUtil.getInstance().getRoundphoto(FriendChatDialogueRecordresult.get(position).getHeadPic(),myChatDialoguefriendViewHolder.friend_head);
         try {
             String content=RsaCoder.decryptByPublicKey(FriendChatDialogueRecordresult.get(position).getContent());
             myChatDialoguefriendViewHolder.friend_content.setText(content);
             notifyDataSetChanged();
         } catch (Exception e) {
             e.printStackTrace();
         }

     }else {
         MyChatDialogueminViewHolder  myChatDialogueminViewHolder=  (MyChatDialogueminViewHolder)holder;
         RetrofitUtil.getInstance().getRoundphoto(FriendChatDialogueRecordresult.get(position).getHeadPic(),myChatDialogueminViewHolder.mine_head);
         try {
             String content=RsaCoder.decryptByPublicKey(FriendChatDialogueRecordresult.get(position).getContent());
             //解密好友发送的信息
             myChatDialogueminViewHolder.mine_content.setText(content);
             notifyDataSetChanged();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
    }

    //刷新消息接口
    public void update(List<FriendChatDialogueRecordBean.ResultBean> FriendChatDialogueRecordresult) {
        this.FriendChatDialogueRecordresult.clear();
        this.FriendChatDialogueRecordresult.addAll(FriendChatDialogueRecordresult);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return FriendChatDialogueRecordresult.size();
    }
    @Override
    public int getItemViewType(int position) {
       int direction= FriendChatDialogueRecordresult.get(position).getDirection();
        if (direction==2) {
            return 0;
        }
        return 1;
    }
    class  MyChatDialoguefriendViewHolder extends RecyclerView.ViewHolder {
        ImageView friend_head;
        TextView friend_content;
        public MyChatDialoguefriendViewHolder(@NonNull View itemView) {
            super(itemView);
            friend_head=itemView.findViewById(R.id.friend_head);
            friend_content=itemView.findViewById(R.id.friend_content);
        }
    }
    class  MyChatDialogueminViewHolder extends RecyclerView.ViewHolder {
        ImageView mine_head;
        TextView mine_content;
        public MyChatDialogueminViewHolder(@NonNull View itemView) {
            super(itemView);
            mine_head=itemView.findViewById(R.id.mine_head);
            mine_content=itemView.findViewById(R.id.mine_content);
        }
    }
}
