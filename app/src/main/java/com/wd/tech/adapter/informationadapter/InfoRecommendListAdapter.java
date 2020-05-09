package com.wd.tech.adapter.informationadapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.wd.tech.R;
import com.wd.tech.bean.informationentity.InfoRecommendListEntity;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时间 :2020/4/22  16:40
 * 作者 :苗恒
 * 功能 :
 */
public class InfoRecommendListAdapter extends RecyclerView.Adapter<InfoRecommendListAdapter.MyViewholder> {
    Context context;
    List<InfoRecommendListEntity.ResultBean> result;


    public InfoRecommendListAdapter(Context context, List<InfoRecommendListEntity.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_informationlist_layout, null);
        MyViewholder myViewholder = new MyViewholder(inflate);
        return myViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        Glide.with(context).load(result.get(position).getThumbnail())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(8)))
                .into(holder.ivImage);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long releaseTime = result.get(position).getReleaseTime();
        String format = simpleDateFormat.format(releaseTime);
        holder.tvTitle.setText(result.get(position).getTitle());
        holder.tvSummary.setText(result.get(position).getSummary());
        holder.tvTime.setText(format);
        holder.tvSource.setText(result.get(position).getSource());
        holder.tvAixinNum.setText(result.get(position).getCollection() + "");
        holder.tvSharNum.setText(result.get(position).getShare() + "");
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(result.get(position).getId());
                }
            });
            int whetherCollection = result.get(position).getWhetherCollection();
            if (whetherCollection == 1) {

                holder.cbAixin.setChecked(true);
            } else if (whetherCollection == 2) {
                holder.cbAixin.setChecked(false);
            }
            if (aiXinClickListener != null) {
                holder.cbAixin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        aiXinClickListener.onClick(result.get(position).getId(), result.get(position).getWhetherCollection(),position%result.size());
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb_aixin)
        CheckBox cbAixin;
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_summary)
        TextView tvSummary;
        @BindView(R.id.tv_source)
        TextView tvSource;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_aixinNum)
        TextView tvAixinNum;
        @BindView(R.id.cb_shar)
        CheckBox cbShar;
        @BindView(R.id.tv_sharNum)
        TextView tvSharNum;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private OnItemClickListener onItemClickListener;
    private AiXinClickListener aiXinClickListener;

    public void setAiXinClickListener(AiXinClickListener aiXinClickListener) {
        this.aiXinClickListener = aiXinClickListener;
    }

    public interface OnItemClickListener {
        void onClick(int getInformationId);
    }

    public interface AiXinClickListener {
        void onClick(int getInformationId, int whetherCollection,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
