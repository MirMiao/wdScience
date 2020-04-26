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
import com.wd.tech.util.TimeformatUtil;

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
public class MyCrowdInfromactadapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
     private List<CrowdInfromBean.ResultBean> crowdinfromresult=new ArrayList<>();
     private Context context;
    private int stust=0;
    private MycrowdInfromact1ViewHolder mycrowdInfromact1ViewHolder;
    private MycrowdInfromact2ViewHolder mycrowdInfromact2ViewHolder;

    public MyCrowdInfromactadapter(List<CrowdInfromBean.ResultBean> crowdinfromresult, Context context) {
        this.crowdinfromresult.addAll(crowdinfromresult);
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==0){
            View view= LayoutInflater.from(context).inflate(R.layout.crowdinfrom_item,parent,false);
            mycrowdInfromact1ViewHolder = new MycrowdInfromact1ViewHolder(view);
            return mycrowdInfromact1ViewHolder;
        }
        else {
            View view= LayoutInflater.from(context).inflate(R.layout.crowdinfrom_stuats_item,parent,false);
            mycrowdInfromact2ViewHolder = new MycrowdInfromact2ViewHolder(view);
            return mycrowdInfromact2ViewHolder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  MycrowdInfromact1ViewHolder){
            ((MycrowdInfromact1ViewHolder) holder).friend_name.setText(crowdinfromresult.get(position).getNickName());
            RetrofitUtil.getInstance().getRoundphoto(crowdinfromresult.get(position).getHeadPic(), ((MycrowdInfromact1ViewHolder) holder).friend_head);
            ((MycrowdInfromact1ViewHolder) holder).user_stuast.setVisibility(View.GONE);
            ((MycrowdInfromact1ViewHolder) holder).time.setText(TimeformatUtil.getall(crowdinfromresult.get(position).getNoticeTime()));
            ((MycrowdInfromact1ViewHolder) holder).btn_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stust=1;
                    Eventstuast eventstuast=new Eventstuast();
                    eventstuast.id=stust;
                    EventBus.getDefault().postSticky(eventstuast);

                }
            });
            ((MycrowdInfromact1ViewHolder) holder).btn_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stust=2;
                    Eventstuast eventstuast=new Eventstuast();
                    eventstuast.id=stust;
                    EventBus.getDefault().postSticky(eventstuast);
                }
            });
            if (crowdinfromresult.get(position).getType()==1){
                ((MycrowdInfromact1ViewHolder) holder).user_addstuast.setText(crowdinfromresult.get(position).getNickName()+"邀请您加入"+crowdinfromresult.get(position).getGroupName()+"群");
            }
            if (crowdinfromresult.get(position).getType()==2){
                ((MycrowdInfromact1ViewHolder) holder).user_addstuast.setText(crowdinfromresult.get(position).getNickName()+"申请加入您的"+crowdinfromresult.get(position).getGroupName()+"群");
            }

        }
        if (holder instanceof  MycrowdInfromact2ViewHolder){
            ((MycrowdInfromact2ViewHolder) holder).friend_name2.setText(crowdinfromresult.get(position).getNickName());
            RetrofitUtil.getInstance().getRoundphoto(crowdinfromresult.get(position).getHeadPic(),((MycrowdInfromact2ViewHolder) holder).friend_head2);
            ((MycrowdInfromact2ViewHolder) holder).time2.setText(TimeformatUtil.getall(crowdinfromresult.get(position).getNoticeTime()));
            if (crowdinfromresult.get(position).getStatus()==2){
                ((MycrowdInfromact2ViewHolder) holder).user_stuast2.setText("已同意");
            }
            if (crowdinfromresult.get(position).getStatus()==3){
                ((MycrowdInfromact2ViewHolder) holder).user_stuast2.setText("已拒绝");
            }
            if (crowdinfromresult.get(position).getType()==1){
                ((MycrowdInfromact2ViewHolder) holder).user_addstuast2.setText(crowdinfromresult.get(position).getNickName()+"邀请您加入"+crowdinfromresult.get(position).getGroupName()+"群");
            }
            if (crowdinfromresult.get(position).getType()==2){
                ((MycrowdInfromact2ViewHolder) holder).user_addstuast2.setText(crowdinfromresult.get(position).getNickName()+"申请加入您的"+crowdinfromresult.get(position).getGroupName()+"群");
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        int  status= crowdinfromresult.get(position).getStatus();
        if (status==1){
            return 0;
        }
        return  1;
    }

    @Override
    public int getItemCount() {
        return crowdinfromresult.size();
    }
//下拉刷新
    public  void update(List<CrowdInfromBean.ResultBean> crowdinfromresult){
        this.crowdinfromresult.clear();
        this.crowdinfromresult.addAll(crowdinfromresult);
        notifyDataSetChanged();
    }
    //上拉刷新
    public  void loadmore(List<CrowdInfromBean.ResultBean> crowdinfromresult){
        this.crowdinfromresult.addAll(crowdinfromresult);
        notifyDataSetChanged();
    }
    class  MycrowdInfromact1ViewHolder extends RecyclerView.ViewHolder {
        ImageView friend_head;
        TextView friend_name,user_stuast,user_addstuast,time;
        Button btn_yes,btn_no;
        public MycrowdInfromact1ViewHolder(@NonNull View itemView) {
            super(itemView);
            friend_head= itemView.findViewById(R.id.friend_head);
            friend_name= itemView.findViewById(R.id.friend_name);
            user_stuast= itemView.findViewById(R.id.user_stuast);
            user_addstuast= itemView.findViewById(R.id.user_addstuast);
            btn_yes= itemView.findViewById(R.id.btn_yes);
            btn_no= itemView.findViewById(R.id.btn_no);
            time= itemView.findViewById(R.id.time);
        }
    }
    class  MycrowdInfromact2ViewHolder extends RecyclerView.ViewHolder {
        ImageView friend_head2;
        TextView friend_name2,user_stuast2,user_addstuast2,time2;
        public MycrowdInfromact2ViewHolder(@NonNull View itemView) {
            super(itemView);
            friend_head2= itemView.findViewById(R.id.friend_head2);
            friend_name2= itemView.findViewById(R.id.friend_name2);
            user_stuast2= itemView.findViewById(R.id.user_stuast2);
            user_addstuast2= itemView.findViewById(R.id.user_addstuast2);
            time2= itemView.findViewById(R.id.time2);
        }
    }
}
