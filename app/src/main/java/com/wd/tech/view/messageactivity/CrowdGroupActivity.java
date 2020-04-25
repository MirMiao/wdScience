package com.wd.tech.view.messageactivity;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyAllAddcrowdGroupadapter;
import com.wd.tech.adapter.infromation.MySetCrowGrouadapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.MyAllAddCrowdGroupBean;
import com.wd.tech.bean.messagebean.MySetCrowGroupBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import java.util.List;

import butterknife.BindView;
//查询群组
public class CrowdGroupActivity extends BaseActivity<Presenter> implements IContract.IView {
    @BindView(R.id.mycrowd_group_RecyclerView)
    RecyclerView mycrowdGroupRecyclerView;
    @BindView(R.id.myaddcrowd_group_RecyclerView)
    RecyclerView myaddcrowdGroupRecyclerView;
    private List<MySetCrowGroupBean.ResultBean> mysetcrowdresult;
    private List<MyAllAddCrowdGroupBean.ResultBean> myalladdcrowdresult;
    private MySetCrowGrouadapter myMySetCrowGrouadapter;
    private MyAllAddcrowdGroupadapter myAllAddcrowdGroupadapter;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_crowd_group;
    }

    @Override
    protected void initData() {
        //我创建的群
        presenter.getMySetCrowGroupBeandata();
        //我加入的群
        presenter.getMyAllAddCrowdGroupBeandata();
    }

    @Override
    protected void initView() {

    }

    public void back(View view) {
        finish();
    }

    @Override
    public void success(Object o) {
        if (o instanceof MySetCrowGroupBean) {
            mysetcrowdresult = ((MySetCrowGroupBean) o).getResult();
            myMySetCrowGrouadapter = new MySetCrowGrouadapter(mysetcrowdresult,this);
            mycrowdGroupRecyclerView.setAdapter(myMySetCrowGrouadapter);
            mycrowdGroupRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        if (o instanceof MyAllAddCrowdGroupBean) {
            myalladdcrowdresult = ((MyAllAddCrowdGroupBean) o).getResult();
            myAllAddcrowdGroupadapter = new MyAllAddcrowdGroupadapter(myalladdcrowdresult,this);
            myaddcrowdGroupRecyclerView.setAdapter(myAllAddcrowdGroupadapter);
            myaddcrowdGroupRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

    }

    @Override
    public void failur(Throwable throwable) {

    }
}
