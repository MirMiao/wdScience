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
import com.wd.tech.bean.beancommunity.UserPostVoList;
import com.wd.tech.util.RetrofitUtil;

import java.util.List;

public class MyUserPostRecyAdapter extends RecyclerView.Adapter<MyUserPostRecyAdapter.MyUserPostViewHolder> {
    private List<UserPostVoList> list;
    private Context context;
    private int sta=0;
    public MyUserPostRecyAdapter(List<UserPostVoList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyUserPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recy_userpost, parent, false);
        MyUserPostViewHolder myUserPostViewHolder = new MyUserPostViewHolder(inflate);
        return myUserPostViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyUserPostViewHolder holder, int position) {
        UserPostVoList userPostVoList = list.get(position);
        String content = userPostVoList.getContent();
        String file = userPostVoList.getFile();
        int praise = userPostVoList.getPraise();
        int comment = userPostVoList.getComment();
        int id = userPostVoList.getId();

        RetrofitUtil instance = RetrofitUtil.getInstance();
        instance.getRoundphoto(file,holder.haoyouTupian);
        holder.haoyouTiezi.setText(content);
        holder.haoyouPraise.setText(praise+"");
        holder.haoyouComment.setText(comment+"");


        holder.haoyouPinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPinglun!=null){
                    mPinglun.click(v,id);
                }
            }
        });

        holder.haoyouZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sta++;
                if(mDianzan!=null){
                    mDianzan.dianzan(sta,praise,id,holder.haoyouPraise);
                }
                if(sta%2==1){
                    holder.haoyouZan.setImageResource(R.mipmap.community_zan2);
                    holder.haoyouPraise.setText("1");
                }else{
                    holder.haoyouZan.setImageResource(R.mipmap.community_zan);
                    holder.haoyouPraise.setText("0");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyUserPostViewHolder extends RecyclerView.ViewHolder {
        private TextView haoyouTiezi;
        private ImageView haoyouTupian;
        private ImageView haoyouPinglun;
        private TextView haoyouComment;
        private ImageView haoyouZan;
        private TextView haoyouPraise;
        public MyUserPostViewHolder(@NonNull View itemView) {
            super(itemView);
            haoyouTiezi = (TextView) itemView.findViewById(R.id.haoyou_tiezi);
            haoyouTupian = (ImageView) itemView.findViewById(R.id.haoyou_tupian);
            haoyouPinglun = (ImageView) itemView.findViewById(R.id.haoyou_pinglun);
            haoyouComment = (TextView) itemView.findViewById(R.id.haoyou_comment);
            haoyouZan = (ImageView) itemView.findViewById(R.id.haoyou_zan);
            haoyouPraise = (TextView) itemView.findViewById(R.id.haoyou_praise);
        }
    }

    private Dianzan mDianzan;

    public void setDianzan(Dianzan dianzan) {
        mDianzan = dianzan;
    }

    public interface Dianzan {
        void dianzan(int st,int num, int id,TextView textView);
    }

    private Pinglun mPinglun;

    public void setPinglun(Pinglun pinglun) {
        mPinglun = pinglun;
    }

    public interface Pinglun {
        void click(View view, int id);
    }

}
