package com.wd.tech.adapter.infromation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.MessageList;
import com.wd.tech.R;
import com.wd.tech.bean.messagebean.CrowdInfromBean;
import com.wd.tech.util.TimeformatUtil;
import com.wd.tech.view.messageactivity.CrowdInformActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/22 19:44
 * @classname :
 */
public class MyCrowdInfromadapter  extends  RecyclerView.Adapter<MyCrowdInfromadapter.MycrowdinfromViewHolder>{
    private List<CrowdInfromBean.ResultBean> crowdinfromresult=new ArrayList<>();
    private Context context;
    private MycrowdinfromViewHolder mycrowdinfromViewHolder;

    public MyCrowdInfromadapter(List<CrowdInfromBean.ResultBean> crowdinfromresult, Context context) {
        this.crowdinfromresult.addAll(crowdinfromresult);
        this.context = context;
    }

    @NonNull
    @Override
    public MycrowdinfromViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.fragment_messagelist_item,parent,false);
        mycrowdinfromViewHolder = new MycrowdinfromViewHolder(view);
        return mycrowdinfromViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MycrowdinfromViewHolder holder, int position) {
        mycrowdinfromViewHolder.message_username.setText("群通知");
        mycrowdinfromViewHolder.message_usertime.setText(TimeformatUtil.gettime(crowdinfromresult.get(position).getNoticeTime()));
        mycrowdinfromViewHolder.message_usercontent.setText(crowdinfromresult.get(position).getNickName()+"申请加入你的群");
        crowdinfromresult.get(position).getNoticeId();
        if(crowdinfromresult.get(position).getStatus()==2){
            mycrowdinfromViewHolder.message_contentcount.setVisibility(View.GONE);
        }
        else {
            mycrowdinfromViewHolder.message_contentcount.setText("1");
        }
        mycrowdinfromViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CrowdInformActivity.class);
                context.startActivity(intent);
            }
        });
    }
    public   void update(List<CrowdInfromBean.ResultBean> crowdinfromresult){
        this.crowdinfromresult.clear();
        this.crowdinfromresult.addAll(crowdinfromresult);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return crowdinfromresult.size();
    }

    class MycrowdinfromViewHolder extends RecyclerView.ViewHolder {
        ImageView message_userimg;
        TextView message_username, message_usertime, message_usercontent, message_contentcount;

        public MycrowdinfromViewHolder(@NonNull View itemView) {
            super(itemView);
            message_userimg = itemView.findViewById(R.id.message_userimg);
            message_username = itemView.findViewById(R.id.message_username);
            message_usertime = itemView.findViewById(R.id.message_usertime);
            message_usercontent = itemView.findViewById(R.id.message_usercontent);
            message_contentcount = itemView.findViewById(R.id.message_contentcount);
        }
    }
}
