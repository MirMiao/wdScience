package com.wd.tech.adapter.infromation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.messagebean.CrowdInfromBean;
import com.wd.tech.event.Eventstuast;
import com.wd.tech.util.RetrofitUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/23 14:42
 * @classname :
 */
public class MyCrowdInfromactadapter extends  RecyclerView.Adapter<MyCrowdInfromactadapter.MycrowdInfromactViewHolder>{
     private List<CrowdInfromBean.ResultBean> crowdinfromresult=new ArrayList<>();
     private Context context;
    private int stust=0;
    private MycrowdInfromactViewHolder mycrowdInfromactViewHolder;

    public MyCrowdInfromactadapter(List<CrowdInfromBean.ResultBean> crowdinfromresult, Context context) {
        this.crowdinfromresult.addAll(crowdinfromresult);
        this.context = context;
    }

    @NonNull
    @Override
    public MycrowdInfromactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.crowdinfrom_item,parent,false);
        mycrowdInfromactViewHolder = new MycrowdInfromactViewHolder(view);
        return mycrowdInfromactViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MycrowdInfromactViewHolder holder, int position) {
        mycrowdInfromactViewHolder.friend_name.setText(crowdinfromresult.get(position).getNickName());
        RetrofitUtil.getInstance().getRoundphoto(crowdinfromresult.get(position).getHeadPic(),mycrowdInfromactViewHolder.friend_head);
        if (crowdinfromresult.get(position).getStatus()==1){
            mycrowdInfromactViewHolder.user_stuast.setVisibility(View.GONE);
            mycrowdInfromactViewHolder.btn_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stust=1;
                    Eventstuast eventstuast=new Eventstuast();
                    eventstuast.id=stust;
                    EventBus.getDefault().postSticky(eventstuast);

                }
            });
            mycrowdInfromactViewHolder.btn_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stust=2;
                    Eventstuast eventstuast=new Eventstuast();
                    eventstuast.id=stust;
                    EventBus.getDefault().postSticky(eventstuast);

                }
            });
        }
        if (crowdinfromresult.get(position).getStatus()==2){
            mycrowdInfromactViewHolder.user_stuast.setText("已同意");
            mycrowdInfromactViewHolder.btn_yes.setVisibility(View.GONE);
            mycrowdInfromactViewHolder.btn_no.setVisibility(View.GONE);
        }
        if (crowdinfromresult.get(position).getStatus()==3){
            mycrowdInfromactViewHolder.btn_yes.setVisibility(View.GONE);
            mycrowdInfromactViewHolder.btn_no.setVisibility(View.GONE);
            mycrowdInfromactViewHolder.user_stuast.setText("已拒绝");
        }
        if (crowdinfromresult.get(position).getType()==1){
            mycrowdInfromactViewHolder.user_addstuast.setText(crowdinfromresult.get(position).getNickName()+"邀请您加入"+crowdinfromresult.get(position).getGroupName()+"群");
        }
        if (crowdinfromresult.get(position).getType()==2){
            mycrowdInfromactViewHolder.user_addstuast.setText(crowdinfromresult.get(position).getNickName()+"申请加入您的"+crowdinfromresult.get(position).getGroupName()+"群");

        }
    }

    @Override
    public int getItemCount() {
        return crowdinfromresult.size();
    }
//刷新
    public  void update(List<CrowdInfromBean.ResultBean> crowdinfromresult){
        this.crowdinfromresult.clear();
        this.crowdinfromresult.addAll(crowdinfromresult);
        notifyDataSetChanged();
    }
    class  MycrowdInfromactViewHolder extends RecyclerView.ViewHolder {
        ImageView friend_head;
        TextView friend_name,user_stuast,user_addstuast;
        Button btn_yes,btn_no;
        public MycrowdInfromactViewHolder(@NonNull View itemView) {
            super(itemView);
            friend_head= itemView.findViewById(R.id.friend_head);
            friend_name= itemView.findViewById(R.id.friend_name);
            user_stuast= itemView.findViewById(R.id.user_stuast);
            user_addstuast= itemView.findViewById(R.id.user_addstuast);
            btn_yes= itemView.findViewById(R.id.btn_yes);
            btn_no= itemView.findViewById(R.id.btn_no);
        }
    }
}
