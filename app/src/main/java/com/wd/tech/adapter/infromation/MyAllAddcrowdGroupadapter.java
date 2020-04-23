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
import com.wd.tech.bean.messagebean.MyAllAddCrowdGroupBean;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.view.messageactivity.AddCrowdActivity;
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
public class MyAllAddcrowdGroupadapter extends  RecyclerView.Adapter<MyAllAddcrowdGroupadapter.MyAllAddcrowdGroupViewHoldel>{
    private List<MyAllAddCrowdGroupBean.ResultBean> myalladdcrowdresult=new ArrayList<>();
    private Context context;
    private MyAllAddcrowdGroupViewHoldel myAllAddcrowdGroupViewHoldel;

    public MyAllAddcrowdGroupadapter(List<MyAllAddCrowdGroupBean.ResultBean> myalladdcrowdresult, Context context) {
        this.myalladdcrowdresult.addAll(myalladdcrowdresult);
        this.context = context;
    }

    @NonNull
    @Override
    public MyAllAddcrowdGroupViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.crowdgroup_item,parent,false);
        myAllAddcrowdGroupViewHoldel = new MyAllAddcrowdGroupViewHoldel(view);
        return myAllAddcrowdGroupViewHoldel;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAllAddcrowdGroupViewHoldel holder, int position) {
        myAllAddcrowdGroupViewHoldel.crowd_name.setText(myalladdcrowdresult.get(position).getGroupName());
        RetrofitUtil.getInstance().getRectphoto(myalladdcrowdresult.get(position).getGroupImage(),myAllAddcrowdGroupViewHoldel.crowd_headpic);
        myAllAddcrowdGroupViewHoldel.btn_crowd_dialt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int groupId=myalladdcrowdresult.get(position).getGroupId();
                Intent intent=new Intent(context, AddCrowdActivity.class);
                intent.putExtra("groupId",groupId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myalladdcrowdresult.size();
    }

    class  MyAllAddcrowdGroupViewHoldel extends RecyclerView.ViewHolder {
         ImageView crowd_headpic;
         LinearLayout btn_crowd_dialt;
         TextView crowd_name;
        public MyAllAddcrowdGroupViewHoldel(@NonNull View itemView) {
            super(itemView);
            crowd_headpic= itemView.findViewById(R.id.crowd_headpic);
            btn_crowd_dialt= itemView.findViewById(R.id.btn_crowd_dialt);
            crowd_name= itemView.findViewById(R.id.crowd_name);
        }
    }
}
