package com.wd.tech.adapter.infromation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.wd.tech.R;
import com.wd.tech.bean.messagebean.CrowdGroupAllUserMessageBean;
import com.wd.tech.util.RetrofitUtil;
import java.util.ArrayList;
import java.util.List;
/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/24 19:28
 * @classname :
 */
public class MyCrowdGroupAllUserMessageadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<CrowdGroupAllUserMessageBean.ResultBean> crowdgroupallusermessageresult=new ArrayList<>();
    private Context context;
    public MyCrowdGroupAllUserMessageadapter(List<CrowdGroupAllUserMessageBean.ResultBean> crowdgroupallusermessageresult, Context context) {
        this.crowdgroupallusermessageresult.addAll(crowdgroupallusermessageresult);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==0){
            View view= LayoutInflater.from(context).inflate(R.layout.crowdmember_item4,parent,false);
            MyzhuViewHolder  holder=new MyzhuViewHolder(view);
            return holder;
        }
        if (viewType==1){
            View view= LayoutInflater.from(context).inflate(R.layout.crowdmember_item2,parent,false);
            MyguanViewHolder  holder=new MyguanViewHolder(view);
            return holder;
        }
        else {
            View view= LayoutInflater.from(context).inflate(R.layout.crowdmember_item3,parent,false);
            MymemberViewHolder  holder=new MymemberViewHolder(view);
            return holder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof  MyzhuViewHolder){
        ((MyzhuViewHolder) holder).zhu_name.setText(crowdgroupallusermessageresult.get(position).getNickName());
        RetrofitUtil.getInstance().getRoundphoto(crowdgroupallusermessageresult.get(position).getHeadPic(), ((MyzhuViewHolder) holder).zhu_head);
    }
    if (holder instanceof  MyguanViewHolder){
        ((MyguanViewHolder) holder).guan_name.setText(crowdgroupallusermessageresult.get(position).getNickName());
        RetrofitUtil.getInstance().getRoundphoto(crowdgroupallusermessageresult.get(position).getHeadPic(), ((MyguanViewHolder) holder).guan_head);
    }
    if (holder instanceof  MymemberViewHolder){
        ((MymemberViewHolder) holder).member_name.setText(crowdgroupallusermessageresult.get(position).getNickName());
        RetrofitUtil.getInstance().getRoundphoto(crowdgroupallusermessageresult.get(position).getHeadPic(), ((MymemberViewHolder) holder).member_head);
    }
    }

    @Override
    public int getItemViewType(int position) {
     int  role = crowdgroupallusermessageresult.get(position).getRole();
     if (role==3)
     {
         return 0;
     }
        if (role==2)
        {
            return 1;
        }

        return 2;
    }

    @Override
    public int getItemCount() {
        return crowdgroupallusermessageresult.size();
    }

    class  MyzhuViewHolder extends RecyclerView.ViewHolder {
         ImageView zhu_head;
         TextView zhu_name;
        public MyzhuViewHolder(@NonNull View itemView) {
            super(itemView);
            zhu_head= itemView.findViewById(R.id.zhu_head);
            zhu_name= itemView.findViewById(R.id.zhu_name);
        }
    }
    class  MyguanViewHolder extends RecyclerView.ViewHolder {
        ImageView guan_head;
        TextView guan_name;
        public MyguanViewHolder(@NonNull View itemView) {
            super(itemView);
            guan_head= itemView.findViewById(R.id.guan_head);
            guan_name= itemView.findViewById(R.id.guan_name);
        }
    }
    class  MymemberViewHolder extends RecyclerView.ViewHolder {
        ImageView member_head;
        TextView member_name;
        public MymemberViewHolder(@NonNull View itemView) {
            super(itemView);
            member_head= itemView.findViewById(R.id.member_head);
            member_name= itemView.findViewById(R.id.member_name);
        }
    }
}
