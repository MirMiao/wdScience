package com.wd.tech.adapter.myhomepageadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.bean.beanMyHomePage.MyAllResult;

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

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAllViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyAllViewHolder extends RecyclerView.ViewHolder{

        public MyAllViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
