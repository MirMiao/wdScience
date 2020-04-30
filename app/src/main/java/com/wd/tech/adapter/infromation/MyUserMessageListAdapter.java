package com.wd.tech.adapter.infromation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.MessageList;
import com.wd.tech.R;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.util.RsaCoder;
import com.wd.tech.view.messageactivity.FriendChatActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/25 14:20
 * @classname :
 */
public class MyUserMessageListAdapter  extends  RecyclerView.Adapter<MyUserMessageListAdapter.MyusermessageViewHolder>{
    private    List<MessageList> list=new ArrayList<>();
    private Context context;
    private MyusermessageViewHolder myusermessageViewHolder;

    public MyUserMessageListAdapter(List<MessageList> list, Context context) {
        this.list.addAll(list);
        this.context = context;
    }

    @NonNull
    @Override
    public MyusermessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.fragment_messagelist_item,parent,false);
        myusermessageViewHolder = new MyusermessageViewHolder(view);
        return myusermessageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyusermessageViewHolder holder, int position) {
        myusermessageViewHolder.message_username.setText(list.get(position).name);
        myusermessageViewHolder.message_usertime.setText(list.get(position).time);
        myusermessageViewHolder.message_usercontent.setText(list.get(position).content);
        myusermessageViewHolder.message_userimg.setPadding(0,0,0,0);
        myusermessageViewHolder.message_userimg.setBackgroundColor(Color.WHITE);
        RetrofitUtil.getInstance().getRectphoto(list.get(position).headpic,myusermessageViewHolder.message_userimg);
       myusermessageViewHolder.message_contentcount.setText("1");
        myusermessageViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, FriendChatActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
   public   void update( List<MessageList> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
   }
    class MyusermessageViewHolder extends RecyclerView.ViewHolder {
        ImageView message_userimg;
        TextView message_username, message_usertime, message_usercontent, message_contentcount;

        public MyusermessageViewHolder(@NonNull View itemView) {
            super(itemView);
            message_userimg = itemView.findViewById(R.id.message_userimg);
            message_username = itemView.findViewById(R.id.message_username);
            message_usertime = itemView.findViewById(R.id.message_usertime);
            message_usercontent = itemView.findViewById(R.id.message_usercontent);
            message_contentcount = itemView.findViewById(R.id.message_contentcount);
        }
    }
}
