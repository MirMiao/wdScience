package com.wd.tech.adapter.informationadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.informationentity.InfoRecommendListEntity;

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
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_inforrecommendlist_layout, null);
        MyViewholder myViewholder = new MyViewholder(inflate);
        return myViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
          holder.rvInformationInfo.setLayoutManager(new LinearLayoutManager(context));

        InfoRecommendListAdapter2 infoRecommendListAdapter2 = new InfoRecommendListAdapter2(context, result);
        holder.rvInformationInfo.setAdapter(infoRecommendListAdapter2);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.rv_InformationInfo)
        RecyclerView rvInformationInfo;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
