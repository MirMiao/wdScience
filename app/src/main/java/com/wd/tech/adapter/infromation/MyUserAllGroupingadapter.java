package com.wd.tech.adapter.infromation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.messagebean.UserAllGroupingBean;
import com.wd.tech.event.Eventgroupid;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/21 10:10
 * @classname :
 */
public class MyUserAllGroupingadapter extends RecyclerView.Adapter<MyUserAllGroupingadapter.MyUserAllGroupingViewHolder> {
    private List<UserAllGroupingBean.ResultBean> userallgroupingresult=new ArrayList<>();
    private Context context;
    private MyUserAllGroupingViewHolder myUserAllGroupingViewHolder;

    public MyUserAllGroupingadapter(List<UserAllGroupingBean.ResultBean> userallgroupingresult, Context context) {
        this.userallgroupingresult.addAll(userallgroupingresult);
        this.context = context;
    }

    @NonNull
    @Override
    public MyUserAllGroupingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.userallgrouping_item,parent,false);
        myUserAllGroupingViewHolder = new MyUserAllGroupingViewHolder(view);
        return myUserAllGroupingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyUserAllGroupingViewHolder holder, int position) {
      myUserAllGroupingViewHolder.group_name.setText(userallgroupingresult.get(position).getGroupName());
        int groupId = userallgroupingresult.get(position).getGroupId();
      myUserAllGroupingViewHolder.group_name.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Eventgroupid eventgroupid=new Eventgroupid();
              eventgroupid.groupid=groupId;
              EventBus.getDefault().postSticky(eventgroupid);
          }
      });
    }
public  void  update(List<UserAllGroupingBean.ResultBean> userallgroupingresult){
    this.userallgroupingresult.clear();
    this.userallgroupingresult.addAll(userallgroupingresult);
    notifyDataSetChanged();
}
    @Override
    public int getItemCount() {
        return userallgroupingresult.size();
    }

    class  MyUserAllGroupingViewHolder extends RecyclerView.ViewHolder {
     TextView group_name;
        public MyUserAllGroupingViewHolder(@NonNull View itemView) {
         super(itemView);
            group_name= itemView.findViewById(R.id.group_name);
     }
 }

}
