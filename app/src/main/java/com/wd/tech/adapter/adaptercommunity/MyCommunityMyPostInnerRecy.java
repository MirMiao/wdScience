package com.wd.tech.adapter.adaptercommunity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.beancommunity.MyPostResult;
import com.wd.tech.util.RetrofitUtil;

import java.util.List;

public class MyCommunityMyPostInnerRecy extends RecyclerView.Adapter<MyCommunityMyPostInnerRecy.MyPostInnerViewHolder> {
    private List<MyPostResult> list;
    private Context context;
    public MyCommunityMyPostInnerRecy(List<MyPostResult> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyPostInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recy_mypostinner, parent, false);
        MyPostInnerViewHolder myPostInnerViewHolder = new MyPostInnerViewHolder(inflate);
        return myPostInnerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPostInnerViewHolder holder, int position) {
        MyPostResult myPostResult = list.get(position);
        String content = myPostResult.getContent();
        String file = myPostResult.getFile();

        holder.mypostNeirong.setText(content);

        RetrofitUtil instance = RetrofitUtil.getInstance();
        instance.getRectphoto(file,holder.mypostIm);

        holder.mypostZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mypostZan.setImageResource(R.mipmap.community_zan2);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyPostInnerViewHolder extends RecyclerView.ViewHolder {
        private TextView mypostNeirong;
        private ImageView mypostIm;
        private ImageView mypostPinglun;
        private TextView mypostComment;
        private ImageView mypostZan;
        private TextView mypostPraise;
        public MyPostInnerViewHolder(@NonNull View itemView) {
            super(itemView);
            mypostNeirong = (TextView) itemView.findViewById(R.id.mypost_neirong);
            mypostIm = (ImageView) itemView.findViewById(R.id.mypost_im);
            mypostPinglun = (ImageView) itemView.findViewById(R.id.mypost_pinglun);
            mypostComment = (TextView) itemView.findViewById(R.id.mypost_comment);
            mypostZan = (ImageView) itemView.findViewById(R.id.mypost_zan);
            mypostPraise = (TextView) itemView.findViewById(R.id.mypost_praise);
        }
    }

}
