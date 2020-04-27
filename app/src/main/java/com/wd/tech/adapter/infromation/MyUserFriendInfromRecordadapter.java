package com.wd.tech.adapter.infromation;

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
import com.wd.tech.bean.messagebean.UserFriendInfromRecordBean;
import com.wd.tech.event.Eventstuast;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.util.TimeformatUtil;

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
public class MyUserFriendInfromRecordadapter  extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<UserFriendInfromRecordBean.ResultBean> userfriendinfromrecordresult=new ArrayList<>();
    private Context context;
    private MyUserFriendInfromRecord1ViewHolder myUserFriendInfromRecord1ViewHolder;
    private MyUserFriendInfromRecord2ViewHolder myUserFriendInfromRecord2ViewHolder;
    public MyUserFriendInfromRecordadapter(List<UserFriendInfromRecordBean.ResultBean> userfriendinfromrecordresult, Context context) {
        this.userfriendinfromrecordresult.addAll(userfriendinfromrecordresult);
        this.context = context;
    }
    private  int staust=0;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      if (viewType==0){
          View view= LayoutInflater.from(context).inflate(R.layout.userfriendinfrom_recyclerview_item,parent,false);
          myUserFriendInfromRecord1ViewHolder = new MyUserFriendInfromRecord1ViewHolder(view);
          return myUserFriendInfromRecord1ViewHolder;
      }
      else {
          View view= LayoutInflater.from(context).inflate(R.layout.userfriendinfrom_recyclerview_stuats_item,parent,false);
          myUserFriendInfromRecord2ViewHolder = new MyUserFriendInfromRecord2ViewHolder(view);
          return myUserFriendInfromRecord2ViewHolder;
      }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       if (holder instanceof  MyUserFriendInfromRecord1ViewHolder){
           ((MyUserFriendInfromRecord1ViewHolder) holder).friend_name.setText(userfriendinfromrecordresult.get(position).getFromNickName());
           RetrofitUtil.getInstance().getRoundphoto(userfriendinfromrecordresult.get(position).getFromHeadPic(), ((MyUserFriendInfromRecord1ViewHolder) holder).friend_head);
           ((MyUserFriendInfromRecord1ViewHolder) holder).user_stuast.setVisibility(View.GONE);
           ((MyUserFriendInfromRecord1ViewHolder) holder).time.setText(TimeformatUtil.getall(userfriendinfromrecordresult.get(position).getNoticeTime()));
           ((MyUserFriendInfromRecord1ViewHolder) holder).btn_yes.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   staust=2;
                   Eventstuast eventstuast=new Eventstuast();
                   eventstuast.id=staust;
                   EventBus.getDefault().postSticky(eventstuast);

               }
           });
           ((MyUserFriendInfromRecord1ViewHolder) holder).btn_no.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   staust=3;
                   Eventstuast eventstuast=new Eventstuast();
                   eventstuast.id=staust;
                   EventBus.getDefault().postSticky(eventstuast);

               }
           });
       }
        if (holder instanceof  MyUserFriendInfromRecord2ViewHolder){
            ((MyUserFriendInfromRecord2ViewHolder) holder).friend_name2.setText(userfriendinfromrecordresult.get(position).getFromNickName());
            RetrofitUtil.getInstance().getRoundphoto(userfriendinfromrecordresult.get(position).getFromHeadPic(),((MyUserFriendInfromRecord2ViewHolder) holder).friend_head2);
            ((MyUserFriendInfromRecord2ViewHolder) holder).time2.setText(TimeformatUtil.getall(userfriendinfromrecordresult.get(position).getNoticeTime()));
            if (userfriendinfromrecordresult.get(position).getStatus()==2){
                ((MyUserFriendInfromRecord2ViewHolder) holder).user_stuast2.setText("已同意");

            }
            if (userfriendinfromrecordresult.get(position).getStatus()==3){
                ((MyUserFriendInfromRecord2ViewHolder) holder).user_stuast2.setText("已拒绝");
            }
        }
    }

    @Override
    public int getItemCount() {
        return userfriendinfromrecordresult.size();

    }
    //下拉刷新
    public  void refresh(List<UserFriendInfromRecordBean.ResultBean> userfriendinfromrecordresult){
      this.userfriendinfromrecordresult.clear();
      this.userfriendinfromrecordresult.addAll(userfriendinfromrecordresult);
      notifyDataSetChanged();
    }
    //上拉加载
    public  void loadmore(List<UserFriendInfromRecordBean.ResultBean> userfriendinfromrecordresult) {
        this.userfriendinfromrecordresult.addAll(userfriendinfromrecordresult);
        notifyDataSetChanged();
    }
        @Override
    public int getItemViewType(int position) {
     int  status=     userfriendinfromrecordresult.get(position).getStatus();
       if (status==1){
        return 0;
       }
       return  1;
    }

    class  MyUserFriendInfromRecord1ViewHolder extends RecyclerView.ViewHolder {
        ImageView friend_head;
        TextView friend_name,user_stuast,time;
        Button btn_yes,btn_no;
        public MyUserFriendInfromRecord1ViewHolder(@NonNull View itemView) {
            super(itemView);
            friend_head= itemView.findViewById(R.id.friend_head);
            friend_name= itemView.findViewById(R.id.friend_name);
            user_stuast= itemView.findViewById(R.id.user_stuast);
            btn_yes= itemView.findViewById(R.id.btn_yes);
            btn_no= itemView.findViewById(R.id.btn_no);
            time= itemView.findViewById(R.id.time);
        }
    }
    class  MyUserFriendInfromRecord2ViewHolder extends RecyclerView.ViewHolder {
        ImageView friend_head2;
        TextView friend_name2,user_stuast2,time2;

        public MyUserFriendInfromRecord2ViewHolder(@NonNull View itemView) {
            super(itemView);
            friend_head2= itemView.findViewById(R.id.friend_head2);
            friend_name2= itemView.findViewById(R.id.friend_name2);
            user_stuast2= itemView.findViewById(R.id.user_stuast2);
            time2= itemView.findViewById(R.id.time2);
        }
    }
}
