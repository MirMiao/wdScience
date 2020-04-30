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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.wd.tech.R;
import com.wd.tech.bean.informationentity.InformationInfosEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时间 :2020/4/27  20:28
 * 作者 :苗恒
 * 功能 :
 */
public class TuiJianAdapter extends RecyclerView.Adapter<TuiJianAdapter.MyViewHolder> {
    Context context;
    List<InformationInfosEntity.ResultBean.InformationListBean> informationList;


    public TuiJianAdapter(Context context, List<InformationInfosEntity.ResultBean.InformationListBean> informationList) {
        this.context = context;
        this.informationList = informationList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_tuijian_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(informationList.get(position).getThumbnail())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(8)))
                .into(holder.ivIamge);
                holder.tvTitle.setText(informationList.get(position).getTitle());
        if (tuiJianOnclick != null) {
             holder.itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     tuiJianOnclick.onClick(informationList.get(position).getId());
                 }
             });
        }
    }

    @Override
    public int getItemCount() {
        return informationList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_iamge)
        ImageView ivIamge;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    private TuiJianOnclick tuiJianOnclick;

    public void setTuiJianOnclick(TuiJianOnclick tuiJianOnclick) {
        this.tuiJianOnclick = tuiJianOnclick;
    }

    public interface TuiJianOnclick{
        void onClick(int id);
    }
}
