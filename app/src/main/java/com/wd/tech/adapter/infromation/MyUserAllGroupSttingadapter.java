package com.wd.tech.adapter.infromation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.UserAllGroupingBean;
import com.wd.tech.event.Eventgroupdeleteid;
import com.wd.tech.event.Eventgroupid;
import com.wd.tech.event.Eventgroupupdateid;
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
        int   groupId = userallgroupresult.get(position).getGroupId();
        myUserAllGroupsttingViewHolder.group.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Eventgroupupdateid eventgroupupdateid =new Eventgroupupdateid();
               eventgroupupdateid.groupupdateid=groupId;
               EventBus.getDefault().postSticky(eventgroupupdateid);
           }
       });

      myUserAllGroupsttingViewHolder.group.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View view) {
               Eventgroupdeleteid eventgroupdeleteid=new Eventgroupdeleteid();
               eventgroupdeleteid.groupdeleteid=groupId;
               EventBus.getDefault().postSticky(eventgroupdeleteid);
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
        LinearLayout group;

        public MyUserAllGroupsttingViewHolder(@NonNull View itemView) {
            super(itemView);
            group_name = itemView.findViewById(R.id.group_name);
            group = itemView.findViewById(R.id.group);
        }
    }
}
