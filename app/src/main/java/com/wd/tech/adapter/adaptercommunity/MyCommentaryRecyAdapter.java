package com.wd.tech.adapter.adaptercommunity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.beancommunity.Commentaryresult;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.util.TimeformatUtil;
import com.wd.tech.view.communityactivity.MyPostActivity;

import java.util.ArrayList;
import java.util.List;

public class MyCommentaryRecyAdapter extends RecyclerView.Adapter<MyCommentaryRecyAdapter.MyCommentaryViewHolder> {
    private List<Commentaryresult> list=new ArrayList<>();
    private Context context;
    public MyCommentaryRecyAdapter(List<Commentaryresult> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyCommentaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recy_commentary, parent, false);
        MyCommentaryViewHolder myCommentaryViewHolder = new MyCommentaryViewHolder(inflate);
        return myCommentaryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCommentaryViewHolder holder, int position) {
        Commentaryresult commentaryresult = list.get(position);
        String nickName = commentaryresult.getNickName();
        String headPic = commentaryresult.getHeadPic();
        String content = commentaryresult.getContent();
        long commentTime = commentaryresult.getCommentTime();

        RetrofitUtil instance = RetrofitUtil.getInstance();
        instance.getRoundphoto(headPic,holder.commentaryIm);

        String gettime = TimeformatUtil.gettime(commentTime);
        holder.commentaryTime.setText(gettime);

        holder.commentaryName.setText(nickName);
        holder.commentaryNei.setText(content);

        holder.commentaryIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MyPostActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyCommentaryViewHolder extends RecyclerView.ViewHolder {
        private ImageView commentaryIm;
        private TextView commentaryName;
        private TextView commentaryTime;
        private TextView commentaryNei;
        public MyCommentaryViewHolder(@NonNull View itemView) {
            super(itemView);
            commentaryIm = (ImageView) itemView.findViewById(R.id.commentary_im);
            commentaryName = (TextView) itemView.findViewById(R.id.commentary_name);
            commentaryTime = (TextView) itemView.findViewById(R.id.commentary_time);
            commentaryNei = (TextView) itemView.findViewById(R.id.commentary_nei);
        }
    }

}
