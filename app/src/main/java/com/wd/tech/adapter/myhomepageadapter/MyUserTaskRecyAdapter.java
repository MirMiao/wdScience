package com.wd.tech.adapter.myhomepageadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.beanMyHomePage.MyUserTaskResult;

import java.util.List;

public class MyUserTaskRecyAdapter extends RecyclerView.Adapter<MyUserTaskRecyAdapter.MyUserTaskViewHolder> {
    private List<MyUserTaskResult> list;
    private Context context;
    public MyUserTaskRecyAdapter(List<MyUserTaskResult> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyUserTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recy_usertask, parent, false);
        MyUserTaskViewHolder myUserTaskViewHolder = new MyUserTaskViewHolder(inflate);
        return myUserTaskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyUserTaskViewHolder holder, int position) {
        MyUserTaskResult myUserTaskResult = list.get(position);
        String taskName = myUserTaskResult.getTaskName();
        int taskType = myUserTaskResult.getTaskType();
        int status = myUserTaskResult.getStatus();
        int taskIntegral = myUserTaskResult.getTaskIntegral();

        holder.userRmingcheng.setText(taskName);
        holder.userJifen.setText(taskIntegral+"分");

        switch (taskType){
            case 1:
                holder.userLeixing.setText("每日任务");
                break;
            case 2:
                holder.userLeixing.setText("一次性任务");
                break;
        }

        switch (status){
            case 1:
                holder.userZhuangtai.setText("已做");
                break;
            case 2:
                holder.userZhuangtai.setText("未做");
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyUserTaskViewHolder extends RecyclerView.ViewHolder {
        private TextView userRmingcheng;
        private TextView userJifen;
        private TextView userZhuangtai;
        private TextView userLeixing;
        public MyUserTaskViewHolder(@NonNull View itemView) {
            super(itemView);
            userRmingcheng = (TextView) itemView.findViewById(R.id.user_rmingcheng);
            userJifen = (TextView) itemView.findViewById(R.id.user_jifen);
            userZhuangtai = (TextView) itemView.findViewById(R.id.user_zhuangtai);
            userLeixing = (TextView) itemView.findViewById(R.id.user_leixing);
        }
    }

}
