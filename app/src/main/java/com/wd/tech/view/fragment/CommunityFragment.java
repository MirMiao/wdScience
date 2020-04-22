package com.wd.tech.view.fragment;
import android.view.View;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wd.tech.R;
import com.wd.tech.adapter.adaptercommunity.MyCommunityHomeRecyAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.beancommunity.CommunityData;
import com.wd.tech.bean.beancommunity.CommunityResult;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import java.util.List;


public class CommunityFragment extends BaseFragment <Presenter> implements IContract.IView {
    private RecyclerView communitylist;


    @Override
    protected int bindLayoutid() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initView(View inflate) {
        communitylist = (RecyclerView) inflate.findViewById(R.id.communitylist);
        communitylist.setLayoutManager(new LinearLayoutManager(getActivity()));
        communitylist.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected void initData() {
   presenter.getCommunitydata(1,5);
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
