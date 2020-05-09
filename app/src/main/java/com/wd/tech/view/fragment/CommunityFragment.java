package com.wd.tech.view.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.wd.tech.util.SpUtil;

import java.util.List;


public class CommunityFragment extends BaseFragment<Presenter> implements IContract.IView {
    private RecyclerView communitylist;
    private MyCommunityHomeRecyAdapter myCommunityHomeRecyAdapter;
    private LinearLayout lle;
    private EditText xiepinglun;
    private TextView fasongpinglun;
    private int mid;


    @Override
    protected int bindLayoutid() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initView(View inflate) {
        communitylist = (RecyclerView) inflate.findViewById(R.id.communitylist);
        communitylist.setLayoutManager(new LinearLayoutManager(getContext()));
        communitylist.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        lle = (LinearLayout) inflate.findViewById(R.id.lle);
        xiepinglun = (EditText) inflate.findViewById(R.id.xiepinglun);
        fasongpinglun = (TextView) inflate.findViewById(R.id.fasongpinglun);

        fasongpinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getComment(mid,xiepinglun.getText().toString());
            }
        });

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
            myCommunityHomeRecyAdapter = new MyCommunityHomeRecyAdapter(result, getContext());
            communitylist.setAdapter(myCommunityHomeRecyAdapter);
            myCommunityHomeRecyAdapter.setDianzan(new MyCommunityHomeRecyAdapter.Dianzan() {
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


            myCommunityHomeRecyAdapter.setPinglun(new MyCommunityHomeRecyAdapter.Pinglun() {
                @Override
                public void click(View view, int id) {
                    mid=id;
                    lle.setVisibility(View.VISIBLE);
                }
            });

        }
    }

    @Override
    public void failur(Throwable throwable) {

    }
}
