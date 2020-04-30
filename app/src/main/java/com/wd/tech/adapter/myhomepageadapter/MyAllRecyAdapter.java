package com.wd.tech.adapter.myhomepageadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.wd.tech.bean.beanMyHomePage.MyAllResult;

import com.wd.tech.R;
import com.wd.tech.bean.beanMyHomePage.MyAllResult;
import com.wd.tech.util.RetrofitUtil;


import java.util.List;

public class MyAllRecyAdapter extends RecyclerView.Adapter<MyAllRecyAdapter.MyAllViewHolder> {
    private List<MyAllResult> list;
    private Context context;
    public MyAllRecyAdapter(List<MyAllResult> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.recy_myhomepage_all, parent, false);
        MyAllViewHolder myAllViewHolder = new MyAllViewHolder(inflate);
        return myAllViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyAllViewHolder holder, int position) {

        MyAllResult myAllResult = list.get(position);
        String title = myAllResult.getTitle();
        String thumbnail = myAllResult.getThumbnail();

        RetrofitUtil instance = RetrofitUtil.getInstance();
        instance.getRectphoto(thumbnail,holder.allIm);
        holder.allNeirong.setText(title);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class MyAllViewHolder extends RecyclerView.ViewHolder {
        private ImageView allIm;
        private TextView allNeirong;
        public MyAllViewHolder(@NonNull View itemView) {
            super(itemView);
            allIm = (ImageView) itemView.findViewById(R.id.all_im);
            allNeirong = (TextView) itemView.findViewById(R.id.all_neirong);

        }
    }

}
