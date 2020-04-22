package com.wd.tech.adapter.informationadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.informationentity.SerchInfoByKeyWordEntity;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时间 :2020/4/22  20:09
 * 作者 :苗恒
 * 功能 :
 */
public class SerchAdapter extends RecyclerView.Adapter<SerchAdapter.MyViewHolder> {
    Context context;
    List<SerchInfoByKeyWordEntity.ResultBean> list;

    public SerchAdapter(Context context, List<SerchInfoByKeyWordEntity.ResultBean> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_serch_layout, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        long releaseTime = list.get(position).getReleaseTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(releaseTime);
        holder.tvTime.setText(format);
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvSource.setText(list.get(position).getSource());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_source)
        TextView tvSource;
        @BindView(R.id.tv_time)
        TextView tvTime;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
