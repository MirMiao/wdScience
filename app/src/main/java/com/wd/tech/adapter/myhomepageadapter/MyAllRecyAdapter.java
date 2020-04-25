package com.wd.tech.adapter.myhomepageadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
=======
import android.widget.ImageView;
import android.widget.TextView;
>>>>>>> origin/information-module-branch

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< HEAD
import com.wd.tech.bean.beanMyHomePage.MyAllResult;
=======
import com.wd.tech.R;
import com.wd.tech.bean.beanMyHomePage.MyAllResult;
import com.wd.tech.util.RetrofitUtil;
>>>>>>> origin/information-module-branch

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
<<<<<<< HEAD

        return null;
=======
        View inflate = LayoutInflater.from(context).inflate(R.layout.recy_myhomepage_all, parent, false);
        MyAllViewHolder myAllViewHolder = new MyAllViewHolder(inflate);
        return myAllViewHolder;
>>>>>>> origin/information-module-branch
    }

    @Override
    public void onBindViewHolder(@NonNull MyAllViewHolder holder, int position) {
<<<<<<< HEAD
=======
        MyAllResult myAllResult = list.get(position);
        String title = myAllResult.getTitle();
        String thumbnail = myAllResult.getThumbnail();

        RetrofitUtil instance = RetrofitUtil.getInstance();
        instance.getRectphoto(title,holder.allIm);
        holder.allNeirong.setText(thumbnail);
>>>>>>> origin/information-module-branch

    }

    @Override
    public int getItemCount() {
<<<<<<< HEAD
        return 0;
    }

    class MyAllViewHolder extends RecyclerView.ViewHolder{

        public MyAllViewHolder(@NonNull View itemView) {
            super(itemView);
=======
        return list.size();
    }

    class MyAllViewHolder extends RecyclerView.ViewHolder {
        private ImageView allIm;
        private TextView allNeirong;
        public MyAllViewHolder(@NonNull View itemView) {
            super(itemView);
            allIm = (ImageView) itemView.findViewById(R.id.all_im);
            allNeirong = (TextView) itemView.findViewById(R.id.all_neirong);
>>>>>>> origin/information-module-branch
        }
    }

}
