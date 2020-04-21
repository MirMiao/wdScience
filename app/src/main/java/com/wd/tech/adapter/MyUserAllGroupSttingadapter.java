package com.wd.tech.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.UserAllGroupingBean;
import com.wd.tech.event.Eventgroupid;
import com.wd.tech.event.Eventstuast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/21 13:17
 * @classname :
 */
public class MyUserAllGroupSttingadapter  extends  RecyclerView.Adapter<MyUserAllGroupSttingadapter.MyUserAllGroupsttingViewHolder>{
    private List<UserAllGroupingBean.ResultBean> userallgroupresult=new ArrayList<>();
    private Context context;
    private MyUserAllGroupsttingViewHolder myUserAllGroupsttingViewHolder;

    public MyUserAllGroupSttingadapter(List<UserAllGroupingBean.ResultBean> userallgroupresult, Context context) {
        this.userallgroupresult.addAll(userallgroupresult);
        this.context = context;
    }

    @NonNull
    @Override
    public MyUserAllGroupsttingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.userallgrouping_item,parent,false);
        myUserAllGroupsttingViewHolder = new MyUserAllGroupsttingViewHolder(view);
        return myUserAllGroupsttingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyUserAllGroupsttingViewHolder holder, int position) {
      myUserAllGroupsttingViewHolder.group_name.setText(userallgroupresult.get(position).getGroupName());
        myUserAllGroupsttingViewHolder.group_name.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Eventgroupid  eventgroupid =new Eventgroupid();
               int   groupId = userallgroupresult.get(position).getGroupId();
               eventgroupid.groupid=groupId;
               EventBus.getDefault().postSticky(eventgroupid);
           }
       });

       myUserAllGroupsttingViewHolder.group_name.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View view) {
               Eventstuast eventstuast=new Eventstuast();
               int   groupId2 = userallgroupresult.get(position).getGroupId();
               eventstuast.id=groupId2;
               EventBus.getDefault().postSticky(eventstuast);
               return true;
           }
       });
    }

  public void update ( List<UserAllGroupingBean.ResultBean> userallgroupresult){
      this.userallgroupresult.clear();
      this.userallgroupresult.addAll(userallgroupresult);
      notifyDataSetChanged();
  }
    @Override
    public int getItemCount() {
        return userallgroupresult.size();
    }

    class  MyUserAllGroupsttingViewHolder extends RecyclerView.ViewHolder {
        TextView group_name;

        public MyUserAllGroupsttingViewHolder(@NonNull View itemView) {
            super(itemView);
            group_name = itemView.findViewById(R.id.group_name);
        }
    }
}
