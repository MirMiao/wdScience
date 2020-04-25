package com.wd.tech.view.communityactivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.adapter.adaptercommunity.MyCommunityMyPostRecyAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.beancommunity.MyPostData;
import com.wd.tech.bean.beancommunity.MyPostResult;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import java.util.List;

public class MyPostActivity extends BaseActivity<Presenter> implements IContract.IView {
    private androidx.recyclerview.widget.RecyclerView mypostRecy;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_my_post;
    }

    @Override
    protected void initData() {
        presenter.getMyPostdata(1,5);
    }

    @Override
    protected void initView() {

        mypostRecy = (RecyclerView) findViewById(R.id.mypost_recy);
        mypostRecy.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void success(Object o) {

        if(o instanceof MyPostData){
            List<MyPostResult> result = ((MyPostData) o).getResult();
            MyCommunityMyPostRecyAdapter myCommunityMyPostRecyAdapter=new MyCommunityMyPostRecyAdapter(result,this);
            mypostRecy.setAdapter(myCommunityMyPostRecyAdapter);
        }

    }

    @Override
    public void failur(Throwable throwable) {

    }
}
