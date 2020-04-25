package com.wd.tech.adapter.infromation;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.wd.tech.R;
import com.wd.tech.bean.messagebean.UserFriendInfromRecordBean;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.util.TimeformatUtil;
import com.wd.tech.view.messageactivity.FriendInformActivity;

import java.util.ArrayList;
import java.util.List;
/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/18 9:29
 * @classname :
 */
public class MyMessageListAdapter extends  RecyclerView.Adapter<MyMessageListAdapter.MyMessageListViewHolder> {
    private List<UserFriendInfromRecordBean.ResultBean> userfriendinfromrecordresult = new ArrayList<>();
    private Context context;
    private MyMessageListViewHolder myMessageListViewHolder;

    public MyMessageListAdapter(List<UserFriendInfromRecordBean.ResultBean> userfriendinfromrecordresult, Context context) {
        this.userfriendinfromrecordresult.addAll(userfriendinfromrecordresult);
        this.context = context;
    }

    @NonNull
    @Override
    public MyMessageListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.fragment_messagelist_item,parent,false);
        myMessageListViewHolder = new MyMessageListViewHolder(view);
        return myMessageListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyMessageListViewHolder myMessageListViewHolder, int position) {
     myMessageListViewHolder.message_username.setText("好友通知");
     myMessageListViewHolder.message_usertime.setText(TimeformatUtil.gettime(userfriendinfromrecordresult.get(position).getNoticeTime()));
     myMessageListViewHolder.message_usercontent.setText(userfriendinfromrecordresult.get(position).getFromNickName()+"请求添加你为好友");
     if(userfriendinfromrecordresult.get(position).getStatus()==2){
         myMessageListViewHolder.message_contentcount.setVisibility(View.GONE);
     }
     else {
         myMessageListViewHolder.message_contentcount.setText("1");
     }
        myMessageListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int noticeId = userfriendinfromrecordresult.get(position).getNoticeId();
                Intent intent=new Intent(context, FriendInformActivity.class);
                intent.putExtra("noticeId",noticeId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userfriendinfromrecordresult.size();
    }
    class MyMessageListViewHolder extends RecyclerView.ViewHolder {
        ImageView message_userimg;
        TextView message_username, message_usertime, message_usercontent, message_contentcount;

        public MyMessageListViewHolder(@NonNull View itemView) {
            super(itemView);
            message_userimg = itemView.findViewById(R.id.message_userimg);
            message_username = itemView.findViewById(R.id.message_username);
            message_usertime = itemView.findViewById(R.id.message_usertime);
            message_usercontent = itemView.findViewById(R.id.message_usercontent);
            message_contentcount = itemView.findViewById(R.id.message_contentcount);
        }
    }
}