package com.wd.tech.view.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyMessageAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.base.mvp.BasePresenter;
import com.wd.tech.view.messageactivity.AddActivity;
import com.wd.tech.view.messageactivity.SetCrowdActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 时间 :2020/4/17  13:56
 * 作者 :苗恒
 * 功能 :
 */
public class MessageFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tabmessage)
    TabLayout tabmessage;
    @BindView(R.id.btn_add)
    ImageView btnAdd;
    @BindView(R.id.vpmessage)
    ViewPager vpmessage;
    private MyMessageAdapter myMessageAdapter;
    private PopupWindow popupWindow;

    @Override
    protected int bindLayoutid() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(View inflate) {
        myMessageAdapter = new MyMessageAdapter(getChildFragmentManager());
        tabmessage.setupWithViewPager(vpmessage);
        vpmessage.setAdapter(myMessageAdapter);
        vpmessage.setOffscreenPageLimit(2);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btn_add)
    public void onViewClicked() {

        showPopupWindow();


    }
    //添加好友或群 创建群聊 PopupWindow
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow_addfriendcrowd_item, null);
        popupWindow = new PopupWindow(contentView,
                ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(contentView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //设置各个控件的点击响应
        LinearLayout add_friend_crowd = (LinearLayout)contentView.findViewById(R.id.add_friend_crowd);
        LinearLayout add_crowd = (LinearLayout)contentView.findViewById(R.id.add_crowd);
        add_friend_crowd.setOnClickListener(this);
        add_crowd.setOnClickListener(this);
        int xOff;
        int buttonWidth = btnAdd.getWidth();
        int popupwindowWidth = popupWindow.getContentView().getMeasuredWidth();

        xOff = buttonWidth - popupwindowWidth;
        //显示PopupWindow
        popupWindow.showAsDropDown(btnAdd, xOff, 0);
    }

   //选择
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.add_friend_crowd:{
                Intent intentaddfc=new Intent(getActivity(), AddActivity.class);
                getActivity().startActivity(intentaddfc);
                popupWindow.dismiss();
            }
            break;
            case R.id.add_crowd:{
                Intent intentaddc=new Intent(getActivity(), SetCrowdActivity.class);
                getActivity().startActivity(intentaddc);
                popupWindow.dismiss();
            }
            break;
        }
    }
}
