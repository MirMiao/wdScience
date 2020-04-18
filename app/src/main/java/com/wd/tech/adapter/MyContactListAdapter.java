package com.wd.tech.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.bean.UserFriendListBean;
import com.wd.tech.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/18 12:10
 * @classname :
 */
public class MyContactListAdapter  extends BaseExpandableListAdapter {
    private List<UserFriendListBean.ResultBean> userfriendlistresult=new ArrayList<>();
    private Context context;
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
        groupholder.group_count.setText(userfriendlistresult.get(i).getCurrentNumber()+"");
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
            view.setTag(childholder);
        }else {
            childholder= (ChildViewHolder) view.getTag();
        }
        List<UserFriendListBean.ResultBean.FriendInfoListBean>   friendInfoList = userfriendlistresult.get(i).getFriendInfoList();
        childholder.child_name.setText(friendInfoList.get(i1).getNickName());
        childholder.child_remarkName.setText(friendInfoList.get(i1).getRemarkName());
        RetrofitUtil.getInstance().getRoundphoto(friendInfoList.get(i1).getHeadPic(),childholder.child_img);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

 //父容器
class  GroupViewHolder {
        TextView group_name,group_count;
 }

    //子容器
    class  ChildViewHolder {
        ImageView child_img;
        TextView child_name,child_remarkName;
    }
}
