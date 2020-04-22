package com.wd.tech.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyContactListAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.UserFriendListBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.myview.Mysearchview;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.view.messageactivity.CrowdGroupActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/17 16:24
 * @classname :联系人
 */
public class ContactListFragment extends BaseFragment<Presenter> implements IContract.IView {

    @BindView(R.id.group_crowd)
    LinearLayout groupCrowd;
    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;
    @BindView(R.id.mysearchview)
    Mysearchview mysearchview;
    @BindView(R.id.refresh)
    MaterialRefreshLayout refresh;
    private MyContactListAdapter myContactListAdapter;
    List<UserFriendListBean.ResultBean> userfriendlistresult;

    @Override
    protected int bindLayoutid() {
        return R.layout.fragment_contactlist;
    }

    @Override
    protected void initView(View inflate) {
    }

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected void initData() {
        presenter.getUserFriendListdata();
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                presenter.getUserFriendListdata();
                myContactListAdapter.updade(userfriendlistresult);
                refresh.finishRefresh();
            }
        });

    }

    @OnClick(R.id.group_crowd)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), CrowdGroupActivity.class);
        startActivity(intent);

    }

    @Override
    public void success(Object o) {
        if (o instanceof UserFriendListBean) {
            userfriendlistresult = ((UserFriendListBean) o).getResult();
            myContactListAdapter = new MyContactListAdapter(userfriendlistresult, getActivity());
            expandableListView.setAdapter(myContactListAdapter);
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }
}
