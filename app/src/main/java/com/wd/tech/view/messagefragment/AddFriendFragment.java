package com.wd.tech.view.messagefragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.messagebean.PhoneUserMessangeBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.myview.Mysearchview;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.view.messageactivity.FriendMessageActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/18 19:28
 * @classname :加好友
 */
public class AddFriendFragment extends BaseFragment<Presenter> implements IContract.IView {
    @BindView(R.id.frined_head)
    ImageView frinedHead;
    @BindView(R.id.frined_name)
    TextView frinedName;
    @BindView(R.id.btn_addfriend)
    LinearLayout btnAddfriend;
    @BindView(R.id.wudata)
    TextView wudata;
    @BindView(R.id.mysearchview)
    Mysearchview mysearchview;
    private PhoneUserMessangeBean.ResultBean result;


    @Override
    protected int bindLayoutid() {
        return R.layout.fragment_addfriend;
    }

    @Override
    protected void initView(View inflate) {
        wudata.setVisibility(View.GONE);
        btnAddfriend.setVisibility(View.GONE);

    }

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected void initData() {
        mysearchview.setSetContext(new Mysearchview.setContext() {
            @Override
            public void onContent(String text) {

                presenter.getPhoneUserMessangeBeanata(text);
            }
        });
        mysearchview.setSetDelete(new Mysearchview.setDelete() {
            @Override
            public void ondelete(String text) {
                text = "";
            }
        });
    }


    @Override
    public void success(Object o) {
        if (o instanceof PhoneUserMessangeBean) {
            result = ((PhoneUserMessangeBean) o).getResult();
            if (result == null) {
                wudata.setVisibility(View.VISIBLE);
                btnAddfriend.setVisibility(View.GONE);
            } else {
                wudata.setVisibility(View.GONE);
                btnAddfriend.setVisibility(View.VISIBLE);
                RetrofitUtil.getInstance().getRoundphoto(result.getHeadPic(), frinedHead);
                frinedName.setText(result.getNickName());
            }
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }

    @OnClick(R.id.btn_addfriend)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), FriendMessageActivity.class);
        int souserId = result.getUserId();
        intent.putExtra("souserId", souserId);
        startActivity(intent);
    }

}
