package com.wd.tech.view.communityactivity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.adapter.adaptercommunity.MyUserPostRecyAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.beancommunity.UserPostData;
import com.wd.tech.bean.beancommunity.UserPostResult;
import com.wd.tech.bean.beancommunity.UserPostVoList;
import com.wd.tech.bean.beancommunity.UserPostVoListVo;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.util.SpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class CommunityUserPostActivity extends BaseActivity<Presenter> implements IContract.IView {

    private android.widget.ImageView haoyouHeadim;
    private android.widget.TextView haoyouName;
    private android.widget.TextView haoyouQianming;
    private androidx.recyclerview.widget.RecyclerView haoyouRecy;
    private int mid;
    private android.widget.LinearLayout haoyouLle;
    private android.widget.EditText haoyouXiepinglun;
    private TextView haoyouFasongpinglun;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_community_user_post;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        haoyouHeadim = (ImageView) findViewById(R.id.haoyou_headim);
        haoyouName = (TextView) findViewById(R.id.haoyou_name);
        haoyouQianming = (TextView) findViewById(R.id.haoyou_qianming);

        EventBus.getDefault().register(this);

        haoyouRecy = (RecyclerView) findViewById(R.id.haoyou_recy);
        haoyouRecy.setLayoutManager(new LinearLayoutManager(this));

        haoyouLle = (LinearLayout) findViewById(R.id.haoyou_lle);
        haoyouXiepinglun = (EditText) findViewById(R.id.haoyou_xiepinglun);
        haoyouFasongpinglun = (TextView) findViewById(R.id.haoyou_fasongpinglun);


        haoyouFasongpinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getComment(mid,haoyouXiepinglun.getText().toString());
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void UserPostEvent(UserpostEvent userpostEvent){
        int fimid= userpostEvent.useid;
        presenter.getUserPost(fimid,1,10);
    }

    @Override
    public void success(Object o) {

        if(o instanceof UserPostData){
            List<UserPostResult> result = ((UserPostData) o).getResult();
            for(int i=0;i<20;i++){
                UserPostResult userPostResult = result.get(i);
                UserPostVoListVo communityUserVo = userPostResult.getCommunityUserVo();
                String headPic = communityUserVo.getHeadPic();
                String nickName = communityUserVo.getNickName();
                String signature = communityUserVo.getSignature();
                RetrofitUtil instance = RetrofitUtil.getInstance();
                instance.getRoundphoto(headPic,haoyouHeadim);
                haoyouName.setText(nickName);
                haoyouQianming.setText(signature);
                MyUserPostRecyAdapter myUserPostRecyAdapter=new MyUserPostRecyAdapter(userPostResult.getCommunityUserPostVoList(),this);
                haoyouRecy.setAdapter(myUserPostRecyAdapter);

                myUserPostRecyAdapter.setDianzan(new MyUserPostRecyAdapter.Dianzan() {
                    @Override
                    public void dianzan(int st, int num, int id, TextView textView) {
                        int userid = SpUtil.getInt("userid");
                        String sesseion = SpUtil.getString("sesseion");

                        if (st % 2 == 1) {
                            presenter.getAddCommunityGreat(userid, sesseion, id);
                        } else {
                            presenter.getCancelCommunityGreat(userid, sesseion, id);
                        }
                    }
                });

                myUserPostRecyAdapter.setPinglun(new MyUserPostRecyAdapter.Pinglun() {
                    @Override
                    public void click(View view, int id) {
                        mid=id;
                        haoyouLle.setVisibility(View.VISIBLE);
                    }
                });

            }
        }

    }

    @Override
    public void failur(Throwable throwable) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
