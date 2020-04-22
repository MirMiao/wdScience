package com.wd.tech.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.adaptercommunity.MyCommunityHomeRecyAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.beancommunity.CommunityData;
import com.wd.tech.beancommunity.CommunityResult;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.view.communityactivity.PingLunActivity;

import java.util.List;

public class CommunityFragment extends BaseFragment<Presenter> implements IContract.IView {
    private RecyclerView communitylist;

    @Override
    protected int bindLayoutid() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initView(View inflate) {

        communitylist = (RecyclerView) inflate.findViewById(R.id.communitylist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        communitylist.setLayoutManager(linearLayoutManager);
        communitylist.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));


    }

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected void initData() {
        presenter.getCommunitydata(1, 10);
    }

    @Override
    public void success(Object o) {

        if (o instanceof CommunityData) {
            List<CommunityResult> result = ((CommunityData) o).getResult();
            MyCommunityHomeRecyAdapter myCommunityHomeRecyAdapter = new MyCommunityHomeRecyAdapter(result, getContext());
            communitylist.setAdapter(myCommunityHomeRecyAdapter);
        }

    }

    @Override
    public void failur(Throwable throwable) {

    }
}
