package com.wd.tech.adapter.myhomepageadapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.beanMyHomePage.MyUserIntegralRecordResult;
import com.wd.tech.util.TimeformatUtil;

import java.util.List;

public class MyIntegralRecordRecyAdapter extends RecyclerView.Adapter<MyIntegralRecordRecyAdapter.MyIntegralRecordViewHolder> {
    private List<MyUserIntegralRecordResult> list;
    private Context context;
    public MyIntegralRecordRecyAdapter(List<MyUserIntegralRecordResult> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyIntegralRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recy_integralrecord, parent, false);
        MyIntegralRecordViewHolder myIntegralRecordViewHolder = new MyIntegralRecordViewHolder(inflate);
        return myIntegralRecordViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyIntegralRecordViewHolder holder, int position) {
        MyUserIntegralRecordResult myUserIntegralRecordResult = list.get(position);
        int amount = myUserIntegralRecordResult.getAmount();
        int type = myUserIntegralRecordResult.getType();
        int direction = myUserIntegralRecordResult.getDirection();
        long createTime = myUserIntegralRecordResult.getCreateTime();

        String gettime = TimeformatUtil.gettime(createTime);
        holder.integralrecordTime.setText(gettime);

        switch (type){
            case 1:
                holder.integralrecordLeixing.setText("签到");
                break;
            case 2:
                holder.integralrecordLeixing.setText("评论");
                break;
            case 3:
                holder.integralrecordLeixing.setText("分享");
                break;
            case 4:
                holder.integralrecordLeixing.setText("发帖");
                break;
            case 5:
                holder.integralrecordLeixing.setText("抽奖收入");
                break;
            case 6:
                holder.integralrecordLeixing.setText("付费资讯");
                break;
            case 7:
                holder.integralrecordLeixing.setText("抽奖支出");
                break;
            case 8:
                holder.integralrecordLeixing.setText("完善明细");
                break;
            case 9:
                holder.integralrecordLeixing.setText("查看广告");
                break;
            case 10:
                holder.integralrecordLeixing.setText("绑定第三方");
                break;
        }

        switch (direction){
            case 1:
                holder.integralrecordFen.setText("+"+amount);
                break;
            case 2:
                holder.integralrecordFen.setTextColor(Color.BLUE);
                holder.integralrecordFen.setText("-"+amount);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyIntegralRecordViewHolder extends RecyclerView.ViewHolder {
        private TextView integralrecordLeixing;
        private TextView integralrecordTime;
        private TextView integralrecordFen;
        public MyIntegralRecordViewHolder(@NonNull View itemView) {
            super(itemView);
            integralrecordLeixing = (TextView) itemView.findViewById(R.id.integralrecord_leixing);
            integralrecordTime = (TextView) itemView.findViewById(R.id.integralrecord_time);
            integralrecordFen = (TextView) itemView.findViewById(R.id.integralrecord_fen);
        }
    }

}
