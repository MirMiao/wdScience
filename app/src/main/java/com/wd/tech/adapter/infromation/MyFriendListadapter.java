package com.wd.tech.adapter.infromation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.messagebean.FriendListBean;
import com.wd.tech.event.Eventfriend;
import com.wd.tech.util.RetrofitUtil;

import org.greenrobot.eventbus.EventBus;

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
        Eventfriend eventfriend=new Eventfriend();
        myFriendListViewHolder.btn_friendid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              EventBus.getDefault().postSticky(eventfriend);
            }
        });
        myFriendListViewHolder.check_friend.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    int  friendUid = friendlistresult.get(position).getFriendUid();
                    eventfriend.friendid=friendUid;
                }
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
        CheckBox check_friend;
        public MyFriendListViewHolder(@NonNull View itemView) {
            super(itemView);
            frined_head= itemView.findViewById(R.id.frined_head);
            frined_name= itemView.findViewById(R.id.frined_name);
            frined_qianqame= itemView.findViewById(R.id.frined_qianqame);
            btn_friendid= itemView.findViewById(R.id.btn_friendid);
            check_friend= itemView.findViewById(R.id.check_friend);
        }
    }
}
