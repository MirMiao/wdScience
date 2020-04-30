package com.wd.tech.adapter.informationadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.wd.tech.R;
import com.wd.tech.bean.informationentity.FindAllPingLunEntity;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时间 :2020/4/27  22:03
 * 作者 :苗恒
 * 功能 :
 */
public class PingLunAdapter extends RecyclerView.Adapter<PingLunAdapter.MyViewHolder> {
    Context context;
    List<FindAllPingLunEntity.ResultBean> result;


    public PingLunAdapter(Context context, List<FindAllPingLunEntity.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_pinglun_layout, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(result.get(position).getHeadPic())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(holder.ivHeadPic);
        holder.tvUserName.setText(result.get(position).getNickName());
        long commentTime = result.get(position).getCommentTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(commentTime);
        holder.tvTime.setText(format);
        holder.tvMessage.setText(result.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_headPic)
        ImageView ivHeadPic;
        @BindView(R.id.tv_userName)
        TextView tvUserName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_message)
        TextView tvMessage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
