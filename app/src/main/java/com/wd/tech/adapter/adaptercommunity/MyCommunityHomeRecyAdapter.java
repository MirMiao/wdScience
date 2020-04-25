package com.wd.tech.adapter.adaptercommunity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.beancommunity.CommunityResult;
import com.wd.tech.event.CommunityEvent;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.util.TimeformatUtil;
import com.wd.tech.view.communityactivity.PingLunActivity;
import com.wd.tech.view.communityactivity.QuanbupinglunActivity;

import java.util.List;

public class MyCommunityHomeRecyAdapter extends RecyclerView.Adapter<MyCommunityHomeRecyAdapter.MyCommunityHomeViewHolder> {
    private List<CommunityResult> list;
    private Context context;
    public MyCommunityHomeRecyAdapter(List<CommunityResult> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyCommunityHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recy_community, parent, false);
        MyCommunityHomeViewHolder myCommunityHomeViewHolder = new MyCommunityHomeViewHolder(inflate);
        return myCommunityHomeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCommunityHomeViewHolder holder, int position) {
        CommunityResult communityResult = list.get(position);
        int comment = communityResult.getComment();
        String headPic = communityResult.getHeadPic();
        String file = communityResult.getFile();
        String content = communityResult.getContent();
        String nickName = communityResult.getNickName();
        int praise = communityResult.getPraise();
        long publishTime = communityResult.getPublishTime();
        String signature = communityResult.getSignature();
        int id = communityResult.getId();

        RetrofitUtil instance = RetrofitUtil.getInstance();
        instance.getRoundphoto(headPic, holder.communitylistHeadim);
        instance.getRectphoto(file, holder.communitylistTupian);

        holder.communitylistName.setText(nickName);
        String gettime = TimeformatUtil.gettime(publishTime);
        holder.communitylistTime.setText(gettime);
        holder.communitylistNeirong.setText(content);
        holder.communitylistComment.setText(comment + "");
        holder.communitylistPraise.setText(praise + "");
        holder.communitylistqianming.setText(signature);

        holder.communitylistZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.communitylistZan.setImageResource(R.mipmap.community_zan2);
            }
        });

        holder.pinglun.setLayoutManager(new LinearLayoutManager(context));

        MyCommunityPingRecyAdapter myCommunityPingRecyAdapter = new MyCommunityPingRecyAdapter(communityResult.getCommunityCommentVoList(), context);
        holder.pinglun.setAdapter(myCommunityPingRecyAdapter);

        holder.quanbupinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommunityEvent communityEvent = new CommunityEvent();
                communityEvent.id = id;
                communityEvent.nickName = nickName;
                communityEvent.headPic = headPic;
                Intent intentqp = new Intent(context, QuanbupinglunActivity.class);
                context.startActivity(intentqp);
            }
        });
        
        holder.quxieping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpq=new Intent(context, PingLunActivity.class);
                context.startActivity(intentpq);
            }
        });

        holder.communitylistPinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyCommunityHomeViewHolder extends RecyclerView.ViewHolder {
        private ImageView communitylistHeadim;
        private TextView communitylistName;
        private TextView communitylistTime;
        private TextView communitylistNeirong;
        private ImageView communitylistTupian;
        private ImageView communitylistPinglun;
        private TextView communitylistComment;
        private ImageView communitylistZan;
        private TextView communitylistPraise;
        private TextView communitylistqianming;
        private TextView quanbupinglun;
        private RecyclerView pinglun;
        private ImageView quxieping;

        public MyCommunityHomeViewHolder(@NonNull View itemView) {
            super(itemView);
            communitylistHeadim = (ImageView) itemView.findViewById(R.id.communitylist_headim);
            communitylistName = (TextView) itemView.findViewById(R.id.communitylist_name);
            communitylistTime = (TextView) itemView.findViewById(R.id.communitylist_time);
            communitylistNeirong = (TextView) itemView.findViewById(R.id.communitylist_neirong);
            communitylistTupian = (ImageView) itemView.findViewById(R.id.communitylist_tupian);
            communitylistPinglun = (ImageView) itemView.findViewById(R.id.communitylist_pinglun);
            communitylistComment = (TextView) itemView.findViewById(R.id.communitylist_comment);
            communitylistZan = (ImageView) itemView.findViewById(R.id.communitylist_zan);
            communitylistPraise = (TextView) itemView.findViewById(R.id.communitylist_praise);
            communitylistqianming = (TextView) itemView.findViewById(R.id.communitylist_qianming);
            quanbupinglun = (TextView) itemView.findViewById(R.id.quanbupinglun);
            pinglun = (RecyclerView) itemView.findViewById(R.id.pinglun);
            quxieping = (ImageView) itemView.findViewById(R.id.quxieping);
        }
    }

}
