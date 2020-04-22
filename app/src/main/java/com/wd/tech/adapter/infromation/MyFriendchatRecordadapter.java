package com.wd.tech.adapter.infromation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.messagebean.FriendChatRrecordBean;
import com.wd.tech.util.RsaCoder;
import com.wd.tech.util.TimeformatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/20 15:42
 * @classname :
 */
public class MyFriendchatRecordadapter extends  RecyclerView.Adapter<MyFriendchatRecordadapter.MyFriendchatRecordViewHolder>{
    private  List<FriendChatRrecordBean.ResultBean> result= new ArrayList<>();
    private Context context;
    private MyFriendchatRecordViewHolder myFriendchatRecordViewHolder;
    private String string;

    public MyFriendchatRecordadapter(List<FriendChatRrecordBean.ResultBean> result, Context context) {
        this.result.addAll(result);
        this.context = context;
    }

    @NonNull
    @Override
    public MyFriendchatRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.friend_chatrecord_item,parent ,false);
        myFriendchatRecordViewHolder = new MyFriendchatRecordViewHolder(view);
        return myFriendchatRecordViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyFriendchatRecordViewHolder holder, int position) {
     myFriendchatRecordViewHolder.name.setText(result.get(position).getNickName());
     myFriendchatRecordViewHolder.time.setText(TimeformatUtil.gettime(result.get(position).getChatTime()));
        try {
            string = RsaCoder.decryptByPublicKey(result.get(position).getContent());
            myFriendchatRecordViewHolder.content.setText(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class  MyFriendchatRecordViewHolder extends RecyclerView.ViewHolder {
        TextView name,time,content;
        public MyFriendchatRecordViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            time=itemView.findViewById(R.id.time);
            content=itemView.findViewById(R.id.content);
        }
    }
}
