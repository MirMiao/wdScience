package com.wd.tech.presenter;


import com.wd.tech.base.mvp.BasePresenter;
import com.wd.tech.contract.IContract;
import com.wd.tech.model.Model;

/**
 * 时间 :2020/3/27  12:04
 * 作者 :苗恒
 * 功能 :
 */
public class Presenter extends BasePresenter<Model, IContract.IView> implements IContract.IPresenter {
    @Override
    protected Model initModel() {
        return new Model();
    }

    @Override
    public void getUserFriendInfromRecorddata(int page, int count) {
        model.getUserFriendInfromRecorddata(page, count, new IContract.IModel.ModelCallBack() {
            @Override
            public void success(Object o) {
                getView().success(o);
            }

            @Override
            public void failur(Throwable throwable) {
            getView().failur(throwable);
            }
        });
    }

    @Override
    public void getUserFriendListdata() {
     model.getUserFriendListdata(new IContract.IModel.ModelCallBack() {
         @Override
         public void success(Object o) {
             getView().success(o);
         }

         @Override
         public void failur(Throwable throwable) {
           getView().failur(throwable);
         }
     });
    }
    //社区列表
    @Override
    public void getCommunitydata(int page, int count) {
        model.getCommunitydata(page, count, new IContract.IModel.ModelCallBack() {
            @Override
            public void success(Object o) {
                getView().success(o);
            }

            @Override
            public void failur(Throwable throwable) {
                getView().failur(throwable);
            }
        });
    }
    //社区用户评论
    @Override
    public void getCommentary(int communityId, int page, int count) {
        model.getCommentary(communityId, page, count, new IContract.IModel.ModelCallBack() {
            @Override
            public void success(Object o) {
                getView().success(o);
            }

            @Override
            public void failur(Throwable throwable) {
                getView().failur(throwable);
            }
        });
    }
}
