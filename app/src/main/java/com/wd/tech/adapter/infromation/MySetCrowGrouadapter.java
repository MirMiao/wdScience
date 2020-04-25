package com.wd.tech.adapter.infromation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.messagebean.MySetCrowGroupBean;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.view.messageactivity.CrowdHomePageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/23 18:13
 * @classname :
 */
public class MySetCrowGrouadapter extends RecyclerView.Adapter<MySetCrowGrouadapter.MySetCrowGrouaViewHoldel>{
    private List<MySetCrowGroupBean.ResultBean> mysetcrowdresult=new ArrayList<>();
    private Context context;
    private MySetCrowGrouaViewHoldel mySetCrowGrouaViewHoldel;

    public MySetCrowGrouadapter(List<MySetCrowGroupBean.ResultBean> mysetcrowdresult, Context context) {
        this.mysetcrowdresult.addAll(mysetcrowdresult);
        this.context = context;
    }

    @NonNull
    @Override
    public MySetCrowGrouaViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.crowdgroup_item,parent,false);
        mySetCrowGrouaViewHoldel = new MySetCrowGrouaViewHoldel(view);
        return mySetCrowGrouaViewHoldel;
    }

    @Override
    public void onBindViewHolder(@NonNull MySetCrowGrouaViewHoldel holder, int position) {
       mySetCrowGrouaViewHoldel.crowd_name.setText(mysetcrowdresult.get(position).getGroupName());
        RetrofitUtil.getInstance().getRectphoto(mysetcrowdresult.get(position).getGroupImage(),mySetCrowGrouaViewHoldel.crowd_headpic);
        mySetCrowGrouaViewHoldel.btn_crowd_dialt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int groupId=mysetcrowdresult.get(position).getGroupId();
                Intent  intent=new Intent(context, CrowdHomePageActivity.class);
                intent.putExtra("groupId",groupId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mysetcrowdresult.size();
    }

    class  MySetCrowGrouaViewHoldel extends RecyclerView.ViewHolder {
        ImageView crowd_headpic;
        LinearLayout btn_crowd_dialt;
        TextView crowd_name;
        public MySetCrowGrouaViewHoldel(@NonNull View itemView) {
            super(itemView);
            crowd_headpic= itemView.findViewById(R.id.crowd_headpic);
            btn_crowd_dialt= itemView.findViewById(R.id.btn_crowd_dialt);
            crowd_name= itemView.findViewById(R.id.crowd_name);
        }
    }
}
