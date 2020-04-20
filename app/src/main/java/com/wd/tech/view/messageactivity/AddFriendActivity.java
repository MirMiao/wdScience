package com.wd.tech.view.messageactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.mvp.BasePresenter;
import com.wd.tech.bean.AddFriendBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RetrofitUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//添加好友
public class AddFriendActivity extends BaseActivity<Presenter> implements IContract.IView {


    @BindView(R.id.btn_send)
    TextView btnSend;
    @BindView(R.id.friend_head)
    ImageView friendHead;
    @BindView(R.id.friend_name)
    TextView friendName;
    @BindView(R.id.friend_remarkName)
    TextView friendRemarkName;
    @BindView(R.id.apply_content)
    EditText applyContent;
    private int userIdaf;
    private String nickNameaf;
    private String userNameaf;
    private String remarkaf;
    private String headPicaf;
    private String string;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_add_friend;
    }

    @Override
    protected void initData() {
        userIdaf = getIntent().getIntExtra("userIdaf", 0);
        nickNameaf = getIntent().getStringExtra("nickNameaf");
        userNameaf = getIntent().getStringExtra("userNameaf");
        remarkaf = getIntent().getStringExtra("remarkaf");
        headPicaf = getIntent().getStringExtra("headPicaf");

        friendName.setText(nickNameaf);
        friendRemarkName.setText(remarkaf);
        RetrofitUtil.getInstance().getRoundphoto(headPicaf,friendHead);
    }

    @Override
    protected void initView() {

    }

    public void back(View view) {
        finish();
    }


    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        string = applyContent.getText().toString();
        presenter.getAddFriendBeanata(userIdaf,string);
        finish();
    }

    @Override
    public void success(Object o) {
   if(o instanceof AddFriendBean){
       Toast.makeText(App.context,((AddFriendBean) o).getMessage(),Toast.LENGTH_LONG).show();
   }
    }

    @Override
    public void failur(Throwable throwable) {

    }
}
