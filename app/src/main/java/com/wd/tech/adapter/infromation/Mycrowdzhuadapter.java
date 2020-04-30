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
 * @date :2020/4/28 20:17
 * @classname :
 */
public class Mycrowdzhuadapter extends  RecyclerView.Adapter<Mycrowdzhuadapter.MyzhuViewHolder>{
    private List<CrowdGroupAllUserMessageBean.ResultBean> crowdgroupallusermessageresult =new ArrayList<>();
    private Context context;
    private MyzhuViewHolder myzhuViewHolder;

    public Mycrowdzhuadapter(List<CrowdGroupAllUserMessageBean.ResultBean> crowdgroupallusermessageresult, Context context) {
        this.crowdgroupallusermessageresult.addAll(crowdgroupallusermessageresult);
        this.context = context;
    }

    @NonNull
    @Override
    public MyzhuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.crowdmember_item,parent,false);
        myzhuViewHolder = new MyzhuViewHolder(view);
        return myzhuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyzhuViewHolder holder, int position) {
         if (crowdgroupallusermessageresult.get(position).getRole()==3){
             myzhuViewHolder.zhu_name.setText(crowdgroupallusermessageresult.get(position).getNickName());
             RetrofitUtil.getInstance().getRoundphoto(crowdgroupallusermessageresult.get(position).getHeadPic(), myzhuViewHolder.zhu_head);
         }
         else {
             myzhuViewHolder.zhu_head.setVisibility(View.GONE);
             myzhuViewHolder.zhu_name.setVisibility(View.GONE);
         }
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
}
