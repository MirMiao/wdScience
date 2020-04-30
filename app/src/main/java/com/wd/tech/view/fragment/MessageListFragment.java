package com.wd.tech.view.fragment;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wd.tech.MessageList;
import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyCrowdInfromadapter;
import com.wd.tech.adapter.infromation.MyMessageListAdapter;
import com.wd.tech.adapter.infromation.MyUserMessageListAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.messagebean.CrowdInfromBean;
import com.wd.tech.bean.messagebean.UserFriendInfromRecordBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.event.EventMeassage;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RsaCoder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    @BindView(R.id.message_RecyclerView)
    RecyclerView messageRecyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    private MyMessageListAdapter myMessageListAdapter;
    private List<CrowdInfromBean.ResultBean> crowdinfromresult;
    private List<UserFriendInfromRecordBean.ResultBean> userfriendinfromrecordresult;
    private MyCrowdInfromadapter myCrowdInfromadapter;
    private List<MessageList> list;
    private String format;
    private MyUserMessageListAdapter myUserMessageListAdapter;
    private  int count=5;
    @Override
    protected int bindLayoutid() {
        return R.layout.fragment_messagelist;
    }

    @Override
    protected void initView(View inflate) {
        EventBus.getDefault().register(this);
    }

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected void initData() {
        //好友通知
        presenter.getUserFriendInfromRecorddata(1, count);
        //群通知
        presenter.getCrowdInfromBeandata(1, count);

       refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
           @Override
           public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
           count++;
               //好友通知
               presenter.getUserFriendInfromRecorddata(1, count);
               myMessageListAdapter.loadmore(userfriendinfromrecordresult);
               //群通知
               presenter.getCrowdInfromBeandata(1, count);
               myCrowdInfromadapter.loadmore(crowdinfromresult);

               refresh.finishLoadMore();
           }
           @Override
           public void onRefresh(@NonNull RefreshLayout refreshLayout) {
               count=5;
               //好友通知
               presenter.getUserFriendInfromRecorddata(1, count);
               myMessageListAdapter.update(userfriendinfromrecordresult);
               //群通知
               presenter.getCrowdInfromBeandata(1, count);
               myCrowdInfromadapter.update(crowdinfromresult);

               refresh.finishRefresh();
           }
       });
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(EventMeassage eventMeassage) {
          list = new ArrayList<>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH-mm");
            Date date = new Date();
            format = simpleDateFormat.format(date);
                if (eventMeassage.headpic!=null&&eventMeassage.content!=null&&eventMeassage.name!=null) {
                MessageList messageList = new MessageList(eventMeassage.headpic,eventMeassage.content, format, eventMeassage.name);
                list.add(messageList);
               for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getName().equals(eventMeassage.name)) {
                        list.get(i).setContent(eventMeassage.content);
                    }
                }
                myUserMessageListAdapter = new MyUserMessageListAdapter(list, getActivity());
                messageRecyclerView.setAdapter(myUserMessageListAdapter);
                messageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
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
            myCrowdInfromadapter = new MyCrowdInfromadapter(crowdinfromresult, getActivity());
            crowdinfromRecyclerView.setAdapter(myCrowdInfromadapter);
            crowdinfromRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }


    }

    @Override
    public void failur(Throwable throwable) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
