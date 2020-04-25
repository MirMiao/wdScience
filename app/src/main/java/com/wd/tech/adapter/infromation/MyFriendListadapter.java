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
import com.wd.tech.bean.messagebean.FriendListBean;
import com.wd.tech.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/23 9:02
 * @classname :
 */
public class MyFriendListadapter extends  RecyclerView.Adapter<MyFriendListadapter.MyFriendListViewHolder>{
    private List<FriendListBean.ResultBean> friendlistresult=new ArrayList<>();
    private Context context;
    private MyFriendListViewHolder myFriendListViewHolder;

    public MyFriendListadapter(List<FriendListBean.ResultBean> friendlistresult, Context context) {
        this.friendlistresult.addAll(friendlistresult);
        this.context = context;
    }

    @NonNull
    @Override
    public MyFriendListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sreachfrendlist_item,parent,false);
        myFriendListViewHolder = new MyFriendListViewHolder(view);
        return myFriendListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyFriendListViewHolder holder, int position) {
     myFriendListViewHolder.frined_name.setText(friendlistresult.get(position).getNickName());
        RetrofitUtil.getInstance().getRectphoto(friendlistresult.get(position).getHeadPic(),myFriendListViewHolder.frined_head);
        myFriendListViewHolder.frined_qianqame.setText(friendlistresult.get(position).getSignature());
        myFriendListViewHolder.btn_friendid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              int  friendUid = friendlistresult.get(position).getFriendUid();
            }
        });
    }

    @Override
    public int getItemCount() {
        return friendlistresult.size();
    }

    class  MyFriendListViewHolder extends RecyclerView.ViewHolder {
        ImageView frined_head;
        TextView frined_name,frined_qianqame;
        Button btn_friendid;
        public MyFriendListViewHolder(@NonNull View itemView) {
            super(itemView);
            frined_head= itemView.findViewById(R.id.frined_head);
            frined_name= itemView.findViewById(R.id.frined_name);
            frined_qianqame= itemView.findViewById(R.id.frined_qianqame);
            btn_friendid= itemView.findViewById(R.id.btn_friendid);
        }
    }
}
