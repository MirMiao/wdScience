package com.wd.tech.view.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.stx.xhb.androidx.XBanner;
import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.contract.IContract;
import com.wd.tech.entity.BannerEntity;
import com.wd.tech.presenter.Presenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InformationFragment extends BaseFragment<Presenter> implements IContract.IView {
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.iv_serch)
    ImageView ivSerch;
    @BindView(R.id.xbaner)
    XBanner xbaner;
    @BindView(R.id.tv_xbannerHint)
    TextView tvXbannerHint;

    @Override
    protected int bindLayoutid() {
        return R.layout.fragment_information;
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
        presenter.getBannerData(); //请求banner数据
    }

    @Override
    public void success(Object o) {
        if (o instanceof BannerEntity) {
            List<BannerEntity.ResultBean> result = ((BannerEntity) o).getResult();
            xbaner.setIsClipChildrenMode(true);  //开启一瓶多图片模式
            xbaner.setPointsIsVisible(false);  //设置指示器
            xbaner.setBannerData(result);
            xbaner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(getContext()).load(result.get(position).getImageUrl())
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(8))) //圆角图
                            .error(R.mipmap.ic_launcher)
                            .placeholder(R.mipmap.ic_launcher_round)
                            .into((ImageView) view);
                        tvXbannerHint.setText(result.get(position).getTitle());

                }
            });

        }
    }

    @Override
    public void failur(Throwable throwable) {

    }

    @OnClick({R.id.iv_menu, R.id.iv_serch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_menu:
                break;
            case R.id.iv_serch:
                break;
        }
    }
}
