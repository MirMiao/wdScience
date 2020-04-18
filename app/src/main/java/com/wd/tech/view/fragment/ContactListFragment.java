package com.wd.tech.view.fragment;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import com.wd.tech.R;
import com.wd.tech.adapter.MyContactListAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.UserFriendListBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.myview.Mysearchview;
import com.wd.tech.presenter.Presenter;
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
    private MyContactListAdapter myContactListAdapter;

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
    }

    @OnClick(R.id.group_crowd)
    public void onViewClicked() {

    }

    @Override
    public void success(Object o) {
        if (o instanceof UserFriendListBean) {
            List<UserFriendListBean.ResultBean> userfriendlistresult = ((UserFriendListBean) o).getResult();
            myContactListAdapter = new MyContactListAdapter(userfriendlistresult, getActivity());
            expandableListView.setAdapter(myContactListAdapter);
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }
}
