package com.wd.tech.view.communityactivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.adaptercommunity.MyCommentaryRecyAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.beancommunity.CommentaryData;
import com.wd.tech.beancommunity.Commentaryresult;
import com.wd.tech.contract.IContract;
import com.wd.tech.event.CommunityEvent;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RetrofitUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class QuanbupinglunActivity extends BaseActivity<Presenter> implements IContract.IView {
    private androidx.recyclerview.widget.RecyclerView recycommentary;
    private android.widget.ImageView fanhui;
    private android.widget.ImageView touxiang;
    private android.widget.TextView faburen;
    private String headpic;
    private String name;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_quanbupinglun;
    }

    @Override
    protected void initData() {
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VISIBLE);
        recycommentary.setLayoutManager(linearLayoutManager);

        presenter.getCommentary(1892,1,5);

    }

    @Override
    protected void initView() {

       EventBus.getDefault().register(this);

        recycommentary = (RecyclerView) findViewById(R.id.recycommentary);

        fanhui = (ImageView) findViewById(R.id.fanhui);
        touxiang = (ImageView) findViewById(R.id.touxiang);
        faburen = (TextView) findViewById(R.id.faburen);

        RetrofitUtil instance = RetrofitUtil.getInstance();
        instance.getRoundphoto(headpic,touxiang);
        faburen.setText(name);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void Event(CommunityEvent communityEvent){
        int id=communityEvent.id;
        headpic = communityEvent.headPic;
        name = communityEvent.nickName;
    }

    @Override
    public void success(Object o) {
        if(o instanceof CommentaryData){
            List<Commentaryresult> result = ((CommentaryData) o).getResult();
            if(result!=null){
                MyCommentaryRecyAdapter myCommentaryRecyAdapter=new MyCommentaryRecyAdapter(result,this);
                recycommentary.setAdapter(myCommentaryRecyAdapter);
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
