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
 * @date :2020/4/28 20:20
 * @classname :
 */
public class Mycrowdmanageadapter extends RecyclerView.Adapter<Mycrowdmanageadapter.MyguanViewHolder> {
    private List<CrowdGroupAllUserMessageBean.ResultBean> crowdgroupallusermessageresult =new ArrayList<>();
    private Context context;
    private MyguanViewHolder myguanViewHolder;

    public Mycrowdmanageadapter(List<CrowdGroupAllUserMessageBean.ResultBean> crowdgroupallusermessageresult, Context context) {
        this.crowdgroupallusermessageresult.addAll(crowdgroupallusermessageresult);
        this.context = context;
    }

    @NonNull
    @Override
    public MyguanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.crowdmember_item,parent,false);
        myguanViewHolder = new MyguanViewHolder(view);
        return myguanViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyguanViewHolder holder, int position) {
        if (crowdgroupallusermessageresult.get(position).getRole()==2){
            myguanViewHolder.zhu_name.setText(crowdgroupallusermessageresult.get(position).getNickName());
            RetrofitUtil.getInstance().getRoundphoto(crowdgroupallusermessageresult.get(position).getHeadPic(), myguanViewHolder.zhu_head);
        }
        else {
            myguanViewHolder.zhu_head.setVisibility(View.GONE);
            myguanViewHolder.zhu_name.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return crowdgroupallusermessageresult.size();
    }

    class  MyguanViewHolder extends RecyclerView.ViewHolder {
        ImageView zhu_head;
        TextView zhu_name;
        public MyguanViewHolder(@NonNull View itemView) {
            super(itemView);
            zhu_head= itemView.findViewById(R.id.zhu_head);
            zhu_name= itemView.findViewById(R.id.zhu_name);
        }
    }
}
