package com.wd.tech.model;

import com.wd.tech.api.ApiService;
import com.wd.tech.bean.UserFriendInfromRecordBean;
import com.wd.tech.bean.UserFriendListBean;
import com.wd.tech.beancommunity.CommentaryData;
import com.wd.tech.beancommunity.CommunityData;
import com.wd.tech.contract.IContract;
import com.wd.tech.util.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 时间 :2020/3/27  12:03
 * 作者 :苗恒
 * 功能 :
 */
public class Model implements IContract.IModel {
    RetrofitUtil   instance = RetrofitUtil.getInstance();
    ApiService apiService = instance.creatService(ApiService.class);
    @Override
    public void getUserFriendInfromRecorddata(int page, int count, ModelCallBack modelCallBack) {
    Observable<UserFriendInfromRecordBean>userFriendInfromRecordBeandata = apiService.getUserFriendInfromRecordBeandata(page, count);
    userFriendInfromRecordBeandata.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<UserFriendInfromRecordBean>() {
                @Override
                public void accept(UserFriendInfromRecordBean userFriendInfromRecordBean) throws Exception {
                modelCallBack.success(userFriendInfromRecordBean);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                 modelCallBack.failur(throwable);
                }
            });
    }

    @Override
    public void getUserFriendListdata(ModelCallBack modelCallBack) {
    Observable<UserFriendListBean>   userFriendListBeanndata = apiService.getUserFriendListBeanndata();
    userFriendListBeanndata.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<UserFriendListBean>() {
                @Override
                public void accept(UserFriendListBean userFriendListBean) throws Exception {
                    modelCallBack.success(userFriendListBean);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    modelCallBack.failur(throwable);
                }
            });
    }
    //社区列表
    @Override
    public void getCommunitydata(int page, int count, ModelCallBack modelCallBack) {
        Observable<CommunityData> communitylist = apiService.getCommunitylist(page, count);
        communitylist.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CommunityData>() {
                    @Override
                    public void accept(CommunityData communityData) throws Exception {
                        modelCallBack.success(communityData);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                    }
                });
    }


    //社区用户评论
    @Override
    public void getCommentary(int communityId, int page, int count, ModelCallBack modelCallBack) {
        Observable<CommentaryData> commentary = apiService.getCommentary(communityId, page, count);
        commentary.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CommentaryData>() {
                    @Override
                    public void accept(CommentaryData commentaryData) throws Exception {
                        modelCallBack.success(commentaryData);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                    }
                });
    }




}
