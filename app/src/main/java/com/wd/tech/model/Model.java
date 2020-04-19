package com.wd.tech.model;

import com.wd.tech.api.ApiService;
import com.wd.tech.bean.CheckFriendApplyBean;
import com.wd.tech.bean.ExisisMyFriendBean;
import com.wd.tech.bean.FriendChatDialogueRecordBean;
import com.wd.tech.bean.FriendMessageBean;
import com.wd.tech.bean.SendMessageBean;
import com.wd.tech.bean.UserFriendInfromRecordBean;
import com.wd.tech.bean.UserFriendListBean;
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
    //查询用户的好友通知记录
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
    //初始化我的好友列表全量信息
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
    //查询好友信息
    @Override
    public void getFriendMessagedata(int friend, ModelCallBack modelCallBack) {
        Observable<FriendMessageBean>   friendMessageBeandata = apiService.getFriendMessageBeandata(friend);
        friendMessageBeandata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FriendMessageBean>() {
                    @Override
                    public void accept(FriendMessageBean friendMessageBean) throws Exception {
                     modelCallBack.success(friendMessageBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                    }
                });
    }
    //检测是否为我的好友
    @Override
    public void getExisisMyFrienddata(int friendUid, ModelCallBack modelCallBack) {
        Observable<ExisisMyFriendBean>    exisisMyFriendBeandata = apiService.getExisisMyFriendBeandata(friendUid);
        exisisMyFriendBeandata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ExisisMyFriendBean>() {
                    @Override
                    public void accept(ExisisMyFriendBean exisisMyFriendBean) throws Exception {
                     modelCallBack.success(exisisMyFriendBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                         modelCallBack.failur(throwable);
                    }
                });
    }
//审核好友申请
    @Override
    public void getCheckFriendApplydata(int noticeId, int flag, ModelCallBack modelCallBack) {
        Observable<CheckFriendApplyBean>   checkFriendApplyBeandata = apiService.getCheckFriendApplyBeandata(noticeId, flag);
        checkFriendApplyBeandata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CheckFriendApplyBean>() {
                    @Override
                    public void accept(CheckFriendApplyBean checkFriendApplyBean) throws Exception {
                    modelCallBack.success(checkFriendApplyBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       modelCallBack.failur(throwable);
                    }
                });
    }
    //发送消息
    @Override
    public void getSendMessageBeandata(int receiveUid, String content, ModelCallBack modelCallBack) {
        Observable<SendMessageBean>  sendMessageBeandata = apiService.getSendMessageBeandata(receiveUid, content);
        sendMessageBeandata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SendMessageBean>() {
                    @Override
                    public void accept(SendMessageBean sendMessageBean) throws Exception {
                      modelCallBack.success(sendMessageBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       modelCallBack.failur(throwable);
                    }
                });
    }
    //查询好友对话记录
    @Override
    public void getFriendChatDialogueRecordBeandata(int friendUid, int page, int count, ModelCallBack modelCallBack) {
        Observable<FriendChatDialogueRecordBean>   friendChatDialogueRecordBeandata = apiService.getFriendChatDialogueRecordBeandata(friendUid, page, count);
        friendChatDialogueRecordBeandata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FriendChatDialogueRecordBean>() {
                    @Override
                    public void accept(FriendChatDialogueRecordBean friendChatDialogueRecordBean) throws Exception {
                      modelCallBack.success(friendChatDialogueRecordBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                    }
                });
    }
}
