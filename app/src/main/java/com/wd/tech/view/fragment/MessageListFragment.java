package com.wd.tech.view.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyCrowdInfromadapter;
import com.wd.tech.adapter.infromation.MyMessageListAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.messagebean.CrowdInfromBean;
import com.wd.tech.bean.messagebean.UserFriendInfromRecordBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import java.util.List;

import butterknife.BindView;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/17 16:23
 * @classname :消息
 */
public class MessageListFragment extends BaseFragment<Presenter> implements IContract.IView {
    @BindView(R.id.MessageList_RecyclerView)
    RecyclerView MessageListRecyclerView;
    @BindView(R.id.crowdinfrom_RecyclerView)
    RecyclerView crowdinfromRecyclerView;
    private MyMessageListAdapter myMessageListAdapter;
    private List<CrowdInfromBean.ResultBean> crowdinfromresult;
    private List<UserFriendInfromRecordBean.ResultBean> userfriendinfromrecordresult;
    private MyCrowdInfromadapter myCrowdInfromadapter;

    @Override
    protected int bindLayoutid() {
        return R.layout.fragment_messagelist;
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
        //好友通知
        presenter.getUserFriendInfromRecorddata(1, 5);
        //群通知
        presenter.getCrowdInfromBeandata(1, 5);
    }

    @Override
    public void success(Object o) {
        if (o instanceof UserFriendInfromRecordBean) {
            userfriendinfromrecordresult = ((UserFriendInfromRecordBean) o).getResult();
            myMessageListAdapter = new MyMessageListAdapter(userfriendinfromrecordresult, getActivity());
            MessageListRecyclerView.setAdapter(myMessageListAdapter);
            MessageListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        if (o instanceof CrowdInfromBean) {
            crowdinfromresult = ((CrowdInfromBean) o).getResult();
            myCrowdInfromadapter = new MyCrowdInfromadapter(crowdinfromresult,getActivity());
            crowdinfromRecyclerView.setAdapter(myCrowdInfromadapter);
            crowdinfromRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        }


    }

    @Override
    public void failur(Throwable throwable) {

    }
}
