package com.wd.tech.view.messagefragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.messagebean.CrowGroupDetailMessageBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.view.messageactivity.AddCrowdActivity;
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
 * @date :2020/4/18 19:29
 * @classname :加群
 */
public class AddCrowdFragment extends BaseFragment<Presenter> implements IContract.IView {
    @BindView(R.id.crowd_head)
    ImageView crowdHead;
    @BindView(R.id.crowd_name)
    TextView crowdName;
    @BindView(R.id.wudata)
    TextView wudata;
    @BindView(R.id.btn_addcrowd)
    LinearLayout btnAddcrowd;
    private CrowGroupDetailMessageBean.ResultBean crowgroupdetailmessageresult;
    private Integer integer;

    @Override
    protected int bindLayoutid() {
        return R.layout.fragment_addcrowd;
    }

    @Override
    protected void initView(View inflate) {
        wudata.setVisibility(View.GONE);
        btnAddcrowd.setVisibility(View.GONE);
        EventBus.getDefault().register(this);
    }

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected void initData() {

    }
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public  void  onEvnet(String text){
        integer = Integer.valueOf(text);
        presenter.getCrowGroupDetailMessageBeandata(integer);
    }
    @Override
    public void success(Object o) {
   if (o instanceof CrowGroupDetailMessageBean){
       crowgroupdetailmessageresult = ((CrowGroupDetailMessageBean) o).getResult();
       if (crowgroupdetailmessageresult==null){
           wudata.setVisibility(View.VISIBLE);
           btnAddcrowd.setVisibility(View.GONE);
       }else {
           wudata.setVisibility(View.GONE);
           btnAddcrowd.setVisibility(View.VISIBLE);
           RetrofitUtil.getInstance().getRoundphoto(crowgroupdetailmessageresult.getGroupImage(),crowdHead);
           crowdName.setText(crowgroupdetailmessageresult.getGroupName());
       }
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

    @OnClick(R.id.btn_addcrowd)
    public void onViewClicked() {
        Intent intent=new Intent(getActivity(), AddCrowdActivity.class);
        int groupId= crowgroupdetailmessageresult.getGroupId();
         intent.putExtra("groupId",groupId);
        startActivity(intent);
    }
}
