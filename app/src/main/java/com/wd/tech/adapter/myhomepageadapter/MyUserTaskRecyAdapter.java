package com.wd.tech.adapter.myhomepageadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.beanMyHomePage.MyUserTaskResult;
import com.wd.tech.view.activity.MainActivity;

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

        holder.userMingcheng.setText(taskName);
        holder.userJifen.setText("+"+taskIntegral);

        switch (status){
            case 1:
                holder.userChakan.setText("已做");
                holder.userChakan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "您已经做了任务了", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 2:
                holder.userChakan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyUserTaskViewHolder extends RecyclerView.ViewHolder {
        private TextView userMingcheng;
        private TextView userJifen;
        private Button userChakan;
        public MyUserTaskViewHolder(@NonNull View itemView) {
            super(itemView);
            userMingcheng = (TextView) itemView.findViewById(R.id.user_mingcheng);
            userJifen = (TextView) itemView.findViewById(R.id.user_jifen);
            userChakan = (Button) itemView.findViewById(R.id.user_chakan);
        }
    }

}
