package com.wd.tech.adapter.informationadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.bean.informationentity.InformationInfosEntity;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时间 :2020/4/27  9:57
 * 作者 :苗恒
 * 功能 :
 */
public class InformationInfoAdapter extends RecyclerView.Adapter<InformationInfoAdapter.MyViewHolder> {
    Context context;
    InformationInfosEntity.ResultBean result;


    public InformationInfoAdapter(Context context, InformationInfosEntity.ResultBean result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_informationinfo_layout, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //获取是否已经购买
        int readPower = result.getReadPower();
        if (readPower == 1) {
            String content = result.getContent();
            holder.webContent.getSettings().setJavaScriptEnabled(true);
            holder.webContent.getSettings().setSupportZoom(true);
            holder.webContent.getSettings().setBuiltInZoomControls(true);

            content = content.replaceAll("&", "");

            content = content.replaceAll("“", "\"");

            content = content.replaceAll("<", "<");

            content = content.replaceAll(">", ">");

            content = content.replaceAll("\\n", "<br>");//换行

            content = content.replaceAll("<img", "<img width=\"100%\"");//图片不超出屏幕

            holder.webContent.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
            holder.webContent.setVisibility(View.VISIBLE);
            holder.ivShangweigoumai.setVisibility(View.GONE);
            holder.btShangweigoumai.setVisibility(View.GONE);
        } else {
            //没有阅读权限
            holder.webContent.setVisibility(View.GONE);
            holder.ivShangweigoumai.setVisibility(View.VISIBLE);
            holder.btShangweigoumai.setVisibility(View.VISIBLE);
        }

        holder.tvTitle.setText(result.getTitle());
        long releaseTime = result.getReleaseTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(releaseTime);
        holder.tvTime.setText(format);
        holder.tvSource.setText(result.getSource());
        Glide.with(context)
                .load(result.getThumbnail())
                .into(holder.tvImage);
        if (infoPayByIntegralCallBack != null) {
            holder.btShangweigoumai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    infoPayByIntegralCallBack.getInfoId(result.getId());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_source)
        TextView tvSource;
        @BindView(R.id.tv_image)
        ImageView tvImage;
        @BindView(R.id.web_content)
        WebView webContent;
        @BindView(R.id.iv_shangweigoumai)
        ImageView ivShangweigoumai;
        @BindView(R.id.bt_shangweigoumai)
        Button btShangweigoumai;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    private InfoPayByIntegralCallBack infoPayByIntegralCallBack;

    public void setInfoPayByIntegralCallBack(InfoPayByIntegralCallBack infoPayByIntegralCallBack) {
        this.infoPayByIntegralCallBack = infoPayByIntegralCallBack;
    }

    public interface InfoPayByIntegralCallBack{
        void getInfoId(int id);
  }
}
