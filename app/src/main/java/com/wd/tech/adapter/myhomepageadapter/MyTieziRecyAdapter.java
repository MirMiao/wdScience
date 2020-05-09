package com.wd.tech.adapter.myhomepageadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.Event;
import com.wd.tech.R;
import com.wd.tech.bean.beancommunity.MyPostResult;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.util.TimeformatUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MyTieziRecyAdapter extends RecyclerView.Adapter<MyTieziRecyAdapter.MyTieziViewHolder> {
    private List<MyPostResult> list;
    private Context context;
    public MyTieziRecyAdapter(List<MyPostResult> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyTieziViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recy_mytiezi, parent, false);
        MyTieziViewHolder myTieziViewHolder = new MyTieziViewHolder(inflate);
        return myTieziViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyTieziViewHolder holder, int position) {
        MyPostResult myPostResult = list.get(position);
        int comment = myPostResult.getComment();
        String content = myPostResult.getContent();
        String file = myPostResult.getFile();
        int praise = myPostResult.getPraise();
        long publishTime = myPostResult.getPublishTime();
        int id = myPostResult.getId();

        holder.tieziContent.setText(content);
        RetrofitUtil instance = RetrofitUtil.getInstance();
        instance.getRectphoto(file,holder.tieziIm);
        String gettime = TimeformatUtil.gettime(publishTime);
        holder.tieziTime.setText(gettime);
        holder.tieziShan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event=new Event();
                event.tieziid=id;
                EventBus.getDefault().postSticky(event);
            }
        });

        holder.tieziComment.setText(comment+"");
        holder.tieziPraise.setText(praise+"");

        holder.tieziZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tieziZan.setImageResource(R.mipmap.community_zan2);
            }
        });

        holder.tieziPinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyTieziViewHolder extends RecyclerView.ViewHolder {
        private TextView tieziContent;
        private ImageView tieziIm;
        private TextView tieziTime;
        private TextView tieziShan;
        private ImageView tieziPinglun;
        private TextView tieziComment;
        private ImageView tieziZan;
        private TextView tieziPraise;
        public MyTieziViewHolder(@NonNull View itemView) {
            super(itemView);
            tieziContent = (TextView) itemView.findViewById(R.id.tiezi_content);
            tieziIm = (ImageView) itemView.findViewById(R.id.tiezi_im);
            tieziTime = (TextView) itemView.findViewById(R.id.tiezi_time);
            tieziShan = (TextView) itemView.findViewById(R.id.tiezi_shan);
            tieziPinglun = (ImageView) itemView.findViewById(R.id.tiezi_pinglun);
            tieziComment = (TextView) itemView.findViewById(R.id.tiezi_comment);
            tieziZan = (ImageView) itemView.findViewById(R.id.tiezi_zan);
            tieziPraise = (TextView) itemView.findViewById(R.id.tiezi_praise);
        }
    }

}
