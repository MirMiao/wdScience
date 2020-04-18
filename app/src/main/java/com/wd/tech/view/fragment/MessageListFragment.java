package com.wd.tech.view.fragment;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wd.tech.R;
import com.wd.tech.adapter.MyMessageListAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.UserFriendInfromRecordBean;
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
    private MyMessageListAdapter myMessageListAdapter;
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
     presenter.getUserFriendInfromRecorddata(1,5);
    }

    @Override
    public void success(Object o) {
        if (o instanceof UserFriendInfromRecordBean) {
            List<UserFriendInfromRecordBean.ResultBean>  userfriendinfromrecordresult =((UserFriendInfromRecordBean) o).getResult();
            myMessageListAdapter = new MyMessageListAdapter(userfriendinfromrecordresult,getActivity());
            MessageListRecyclerView.setAdapter(myMessageListAdapter);
            MessageListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }
}
