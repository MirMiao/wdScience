package com.wd.tech.adapter.infromation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.bean.messagebean.UserFriendListBean;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.view.messageactivity.FriendMessageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/18 12:10
 * @classname :
 */
public class MyContactListAdapter  extends BaseExpandableListAdapter implements View.OnClickListener {
    private List<UserFriendListBean.ResultBean> userfriendlistresult=new ArrayList<>();
    private Context context;
    private PopupWindow popupWindow;

    public MyContactListAdapter(List<UserFriendListBean.ResultBean> userfriendlistresult, Context context) {
        this.userfriendlistresult.addAll(userfriendlistresult);
        this.context = context;
    }


    @Override
    public int getGroupCount() {
        return userfriendlistresult.size();
    }

    @Override
    public int getChildrenCount(int i) {
        List<UserFriendListBean.ResultBean.FriendInfoListBean>   friendInfoList = userfriendlistresult.get(i).getFriendInfoList();
        return friendInfoList.size();
    }

    @Override
    public Object getGroup(int i) {
        return userfriendlistresult.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        List<UserFriendListBean.ResultBean.FriendInfoListBean>   friendInfoList = userfriendlistresult.get(i).getFriendInfoList();

        return friendInfoList.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i+i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        //优化
        GroupViewHolder groupholder;
        if(view==null){
           view= LayoutInflater.from(context).inflate(R.layout.fragment_contactlist_group_item,null);
            groupholder=  new GroupViewHolder();
            groupholder.group_name=view.findViewById(R.id.group_name);
            groupholder.group_count=view.findViewById(R.id.group_count);
            view.setTag(groupholder);
        }else {
            groupholder= (GroupViewHolder) view.getTag();
        }
        groupholder.group_name.setText(userfriendlistresult.get(i).getGroupName());
        List<UserFriendListBean.ResultBean.FriendInfoListBean>   friendInfoList = userfriendlistresult.get(i).getFriendInfoList();
        groupholder.group_count.setText(friendInfoList.size()+"/10");
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        //优化
        ChildViewHolder childholder;
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.fragment_contactlist_child_item,null);
            childholder=  new ChildViewHolder();
            childholder.child_img=view.findViewById(R.id.child_img);
            childholder.child_name=view.findViewById(R.id.child_name);
            childholder.child_remarkName=view.findViewById(R.id.child_remarkName);
            childholder.friend_remove=view.findViewById(R.id.friend_remove);
            view.setTag(childholder);
        }else {
            childholder= (ChildViewHolder) view.getTag();
        }
        List<UserFriendListBean.ResultBean.FriendInfoListBean>   friendInfoList = userfriendlistresult.get(i).getFriendInfoList();
        childholder.child_name.setText(friendInfoList.get(i1).getNickName());
        childholder.child_remarkName.setText(friendInfoList.get(i1).getSignature());
        RetrofitUtil.getInstance().getRoundphoto(friendInfoList.get(i1).getHeadPic(),childholder.child_img);
        childholder.friend_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转传值
                Intent intent=new Intent(context, FriendMessageActivity.class);
                String remarkName = friendInfoList.get(i1).getRemarkName();
                int friendUid = friendInfoList.get(i1).getFriendUid();
                intent.putExtra("friendUid",friendUid);
                intent.putExtra("remark",remarkName);
                context.startActivity(intent);
            }
        });
        childholder.friend_remove.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                childholder.friend_remove.setBackgroundColor(Color.GRAY);
                showPopupWindow();
                return true;
            }
        });
        return view;
    }
    //刷新
    public  void updade(List<UserFriendListBean.ResultBean> userfriendlistresult){
      this.userfriendlistresult.clear();
      this.userfriendlistresult.addAll(userfriendlistresult);
      notifyDataSetChanged();
    }
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(context).inflate(R.layout.popupwindow_removefriendcrowd_item, null);
        popupWindow = new PopupWindow(contentView,
                ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(contentView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //设置各个控件的点击响应
        TextView btn_deletefrrend = (TextView)contentView.findViewById(R.id.btn_deletefrrend);
        TextView btn_heifrrend = (TextView)contentView.findViewById(R.id.btn_heifrrend);
        btn_deletefrrend.setOnClickListener(this);
        btn_heifrrend.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(context).inflate(R.layout.fragment_message, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 500);
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.add_friend_crowd:{
                Toast.makeText(App.context,"clicked financial+删除好友",Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
            break;
            case R.id.add_crowd:{
                Toast.makeText(App.context,"clicked financial+拉黑好友",Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
            break;
        }
    }

    //父容器
class  GroupViewHolder {
        TextView group_name,group_count;
 }

    //子容器
    class  ChildViewHolder {
        ImageView child_img;
        TextView child_name,child_remarkName;
        LinearLayout  friend_remove;
    }
}
