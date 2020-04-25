package com.wd.tech.view.messageactivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyCrowdGroupAllUserMessagea2dapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.CrowdGroupAllUserMessageBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.touch.OnItemMoveListener;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

//群管理
public class CrowdManageActivity extends BaseActivity<Presenter> implements IContract.IView {

    @BindView(R.id.btn_applyfriend)
    ImageView btnApplyfriend;
    @BindView(R.id.crowdManagListView)
    SwipeRecyclerView crowdManagListView;


    private int groupIdmanage;
    private List<CrowdGroupAllUserMessageBean.ResultBean> crowdgroupallusermessageresult;
    private MyCrowdGroupAllUserMessagea2dapter myCrowdGroupAllUserMessagea2dapter;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_crowd_manage;
    }

    @Override
    protected void initData() {
        groupIdmanage = getIntent().getIntExtra("groupIdmanage", 0);
        presenter.getCrowdGroupAllUserMessageBeandata(groupIdmanage);
      //  crowdManagListView.setItemViewSwipeEnabled(true);// 开启滑动删除。默认关闭。
        crowdManagListView.setSwipeItemMenuEnabled(true);
        // 设置监听器。
        crowdManagListView.setSwipeMenuCreator(mSwipeMenuCreator);
        crowdManagListView.setOnItemMoveListener(onItemMoveListener);// 监听拖拽，更新UI
    }

    @Override
    protected void initView() {

    }

    // 创建菜单：
 private     SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
            SwipeMenuItem modifyItem = new SwipeMenuItem(CrowdManageActivity.this)
                    .setBackgroundColor(Color.BLUE)
                    .setText("编辑")
                    .setTextColor(Color.BLACK)
                    .setTextSize(15) // 文字大小。
                    .setWidth(200)
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            leftMenu.addMenuItem(modifyItem);
            // 2 删除
            SwipeMenuItem deleteItem = new SwipeMenuItem(CrowdManageActivity.this);
            deleteItem.setText("删除")
                    .setBackgroundColor(Color.BLUE)
                    .setTextColor(Color.WHITE) // 文字颜色。
                    .setTextSize(15) // 文字大小。
                    .setWidth(200)
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

            rightMenu.addMenuItem(deleteItem);

            // 注意：哪边不想要菜单，那么不要添加即可。
        }
    };

    // 菜单点击监听。
    private OnItemMoveListener onItemMoveListener = new OnItemMoveListener() {
        @Override
        public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
            Toast.makeText(App.context, "撤销", Toast.LENGTH_LONG).show();

            return true;
        }

        @Override
        public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
            Toast.makeText(App.context, "移除", Toast.LENGTH_LONG).show();

        }
    };

    public void back(View view) {
        finish();
    }

    @Override
    public void success(Object o) {
        if (o instanceof CrowdGroupAllUserMessageBean) {
            crowdgroupallusermessageresult = ((CrowdGroupAllUserMessageBean) o).getResult();
            myCrowdGroupAllUserMessagea2dapter = new MyCrowdGroupAllUserMessagea2dapter(crowdgroupallusermessageresult, this);
            crowdManagListView.setAdapter(myCrowdGroupAllUserMessagea2dapter);
            crowdManagListView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }


    @OnClick(R.id.btn_applyfriend)
    public void onViewClicked() {
        Intent intentmanage = new Intent(CrowdManageActivity.this, SreachActivity.class);
        startActivity(intentmanage);
    }


}
