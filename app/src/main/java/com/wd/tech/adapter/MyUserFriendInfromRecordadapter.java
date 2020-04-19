package com.wd.tech.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.UserFriendInfromRecordBean;
import com.wd.tech.event.Eventstuast;
import com.wd.tech.util.RetrofitUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/19 14:22
 * @classname :
 */
public class MyUserFriendInfromRecordadapter  extends  RecyclerView.Adapter<MyUserFriendInfromRecordadapter.MyUserFriendInfromRecordViewHolder>{
    private List<UserFriendInfromRecordBean.ResultBean> userfriendinfromrecordresult=new ArrayList<>();
    private Context context;
    private MyUserFriendInfromRecordViewHolder myUserFriendInfromRecordViewHolder;
    public MyUserFriendInfromRecordadapter(List<UserFriendInfromRecordBean.ResultBean> userfriendinfromrecordresult, Context context) {
        this.userfriendinfromrecordresult.addAll(userfriendinfromrecordresult);
        this.context = context;
    }
    private  int staust=0;
    @NonNull
    @Override
    public MyUserFriendInfromRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.userfriendinfrom_recyclerview_item,parent,false);
        myUserFriendInfromRecordViewHolder = new MyUserFriendInfromRecordViewHolder(view);
        return myUserFriendInfromRecordViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyUserFriendInfromRecordViewHolder holder, int position) {
       myUserFriendInfromRecordViewHolder.friend_name.setText(userfriendinfromrecordresult.get(position).getFromNickName());
        RetrofitUtil.getInstance().getRoundphoto(userfriendinfromrecordresult.get(position).getFromHeadPic(),myUserFriendInfromRecordViewHolder.friend_head);
        if (userfriendinfromrecordresult.get(position).getStatus()==1){
            myUserFriendInfromRecordViewHolder.user_stuast.setVisibility(View.GONE);
            myUserFriendInfromRecordViewHolder.btn_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    staust=2;
                    Eventstuast eventstuast=new Eventstuast();
                    eventstuast.id=staust;
                    EventBus.getDefault().postSticky(eventstuast);

                }
            });
            myUserFriendInfromRecordViewHolder.btn_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    staust=3;
                    Eventstuast eventstuast=new Eventstuast();
                    eventstuast.id=staust;
                    EventBus.getDefault().postSticky(eventstuast);

                }
            });
        }
        if (userfriendinfromrecordresult.get(position).getStatus()==2){
            myUserFriendInfromRecordViewHolder.user_stuast.setText("已同意");
            myUserFriendInfromRecordViewHolder.btn_yes.setVisibility(View.GONE);
            myUserFriendInfromRecordViewHolder.btn_no.setVisibility(View.GONE);
        }
        if (userfriendinfromrecordresult.get(position).getStatus()==3){
            myUserFriendInfromRecordViewHolder.btn_yes.setVisibility(View.GONE);
            myUserFriendInfromRecordViewHolder.btn_no.setVisibility(View.GONE);
            myUserFriendInfromRecordViewHolder.user_stuast.setText("已拒绝");

        }
    }

    @Override
    public int getItemCount() {
        return userfriendinfromrecordresult.size();
    }

    class  MyUserFriendInfromRecordViewHolder extends RecyclerView.ViewHolder {
        ImageView friend_head;
        TextView friend_name,user_stuast;
        Button btn_yes,btn_no;
        public MyUserFriendInfromRecordViewHolder(@NonNull View itemView) {
            super(itemView);

            friend_head= itemView.findViewById(R.id.friend_head);
            friend_name= itemView.findViewById(R.id.friend_name);
            user_stuast= itemView.findViewById(R.id.user_stuast);
            btn_yes= itemView.findViewById(R.id.btn_yes);
            btn_no= itemView.findViewById(R.id.btn_no);
        }
    }
}
