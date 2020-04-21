package com.wd.tech.model;

import com.wd.tech.api.ApiService;
import com.wd.tech.bean.AddFriendBean;
import com.wd.tech.bean.AlterFriendGroupingNameBean;
import com.wd.tech.bean.AlterFriendRemarkBean;
import com.wd.tech.bean.CheckFriendApplyBean;
import com.wd.tech.bean.DeleteFriendBean;
import com.wd.tech.bean.DeleteFriendChatRrecordBean;
import com.wd.tech.bean.DeleteFriendGroupingBean;
import com.wd.tech.bean.ExisisMyFriendBean;
import com.wd.tech.bean.FriendChatDialogueRecordBean;
import com.wd.tech.bean.FriendChatRrecordBean;
import com.wd.tech.bean.FriendMessageBean;
import com.wd.tech.bean.PhoneUserMessangeBean;
import com.wd.tech.bean.SendMessageBean;
import com.wd.tech.bean.SetCustomFriendGroupingBean;
import com.wd.tech.bean.ShiftFriendGroupingBean;
import com.wd.tech.bean.UserAllGroupingBean;
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
   //修改好友备注
    @Override
    public void getAlterFriendRemarkBeandata(int friendUid, String remarkName, ModelCallBack modelCallBack) {
        Observable<AlterFriendRemarkBean>  alterFriendRemarkBeandata = apiService.getAlterFriendRemarkBeandata(friendUid, remarkName);
         alterFriendRemarkBeandata.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<AlterFriendRemarkBean>() {
                     @Override
                     public void accept(AlterFriendRemarkBean alterFriendRemarkBean) throws Exception {
                        modelCallBack.success(alterFriendRemarkBean);
                     }
                 }, new Consumer<Throwable>() {
                     @Override
                     public void accept(Throwable throwable) throws Exception {
                          modelCallBack.failur(throwable);
                     }
                 });
    }
    //查询好友聊天记录
    @Override
    public void getFriendChatRrecordBeandata(int friendUid, int page, int count, ModelCallBack modelCallBack) {
        Observable<FriendChatRrecordBean>    friendChatRrecordBeandata = apiService.getFriendChatRrecordBeandata(friendUid, page, count);
         friendChatRrecordBeandata.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<FriendChatRrecordBean>() {
                     @Override
                     public void accept(FriendChatRrecordBean friendChatRrecordBean) throws Exception {
                      modelCallBack.success(friendChatRrecordBean);
                     }
                 }, new Consumer<Throwable>() {
                     @Override
                     public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                     }
                 });
    }
    //删除好友聊天记录
    @Override
    public void getDeleteFriendChatRrecordBeandata(int friendUid, ModelCallBack modelCallBack) {
        Observable<DeleteFriendChatRrecordBean>  deleteFriendChatRrecordBeandata = apiService.getDeleteFriendChatRrecordBeandata(friendUid);
        deleteFriendChatRrecordBeandata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DeleteFriendChatRrecordBean>() {
                    @Override
                    public void accept(DeleteFriendChatRrecordBean deleteFriendChatRrecordBean) throws Exception {
                        modelCallBack.success(deleteFriendChatRrecordBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       modelCallBack.failur(throwable);
                    }
                });
    }
    //删除好友
    @Override
    public void getDeleteFriendBeandata(int friendUid, ModelCallBack modelCallBack) {
        Observable<DeleteFriendBean>  deleteFriendBeandata = apiService.getDeleteFriendBeandata(friendUid);
        deleteFriendBeandata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DeleteFriendBean>() {
                    @Override
                    public void accept(DeleteFriendBean deleteFriendBean) throws Exception {
                        modelCallBack.success(deleteFriendBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                    }
                });
    }
 //根据手机号查询用户信息
    @Override
    public void getPhoneUserMessangeBeanata(String phone, ModelCallBack modelCallBack) {
        Observable<PhoneUserMessangeBean>     phoneUserMessangeBeandata = apiService.getPhoneUserMessangeBeandata(phone);
        phoneUserMessangeBeandata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PhoneUserMessangeBean>() {
                    @Override
                    public void accept(PhoneUserMessangeBean phoneUserMessangeBean) throws Exception {
                      modelCallBack.success(phoneUserMessangeBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                    }
                });
    }
     //添加好友
    @Override
    public void getAddFriendBeanata(int friendUid, String remark, ModelCallBack modelCallBack) {
        Observable<AddFriendBean>     addFriendBeandata = apiService.getAddFriendBeandata(friendUid, remark);
        addFriendBeandata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddFriendBean>() {
                    @Override
                    public void accept(AddFriendBean addFriendBean) throws Exception {
                     modelCallBack.success(addFriendBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                    }
                });
    }
//创建自定义好友分组
    @Override
    public void getSetCustomFriendGroupingBeandata(String groupName, ModelCallBack modelCallBack) {
    Observable<SetCustomFriendGroupingBean>   setCustomFriendGroupingBeandata = apiService.getSetCustomFriendGroupingBeandata(groupName);
     setCustomFriendGroupingBeandata.subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(new Consumer<SetCustomFriendGroupingBean>() {
                 @Override
                 public void accept(SetCustomFriendGroupingBean setCustomFriendGroupingBean) throws Exception {
                 modelCallBack.success(setCustomFriendGroupingBean);
                 }
             }, new Consumer<Throwable>() {
                 @Override
                 public void accept(Throwable throwable) throws Exception {
                     modelCallBack.failur(throwable);
                 }
             });
    }
//查询用户所有分组
    @Override
    public void getUserAllGroupingBeandata(ModelCallBack modelCallBack) {
        Observable<UserAllGroupingBean>  userAllGroupingBeandata = apiService.getUserAllGroupingBeandata();
        userAllGroupingBeandata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserAllGroupingBean>() {
                    @Override
                    public void accept(UserAllGroupingBean userAllGroupingBean) throws Exception {
                     modelCallBack.success(userAllGroupingBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                    }
                });
    }
//修改好友分组名称
    @Override
    public void getAlterFriendGroupingNameBeandata(int groupId, String groupName, ModelCallBack modelCallBack) {
        Observable<AlterFriendGroupingNameBean>     alterFriendGroupingNameBeandata = apiService.getAlterFriendGroupingNameBeandata(groupId, groupName);
        alterFriendGroupingNameBeandata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AlterFriendGroupingNameBean>() {
                    @Override
                    public void accept(AlterFriendGroupingNameBean alterFriendGroupingNameBean) throws Exception {
                     modelCallBack.success(alterFriendGroupingNameBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                      modelCallBack.failur(throwable);
                    }
                });
    }
//转移好友到其他分组
    @Override
    public void getShiftFriendGroupingBeandata(int newGroupId, int friendUid, ModelCallBack modelCallBack) {
        Observable<ShiftFriendGroupingBean>    shiftFriendGroupingBeandata = apiService.getShiftFriendGroupingBeandata(newGroupId, friendUid);
         shiftFriendGroupingBeandata.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<ShiftFriendGroupingBean>() {
                     @Override
                     public void accept(ShiftFriendGroupingBean shiftFriendGroupingBean) throws Exception {
                       modelCallBack.success(shiftFriendGroupingBean);
                     }
                 }, new Consumer<Throwable>() {
                     @Override
                     public void accept(Throwable throwable) throws Exception {
                      modelCallBack.failur(throwable);
                     }
                 });
    }
//删除用户好友分组
    @Override
    public void getDeleteFriendGroupingBeandata(int groupId, ModelCallBack modelCallBack) {
        Observable<DeleteFriendGroupingBean>  deleteFriendGroupingBeandata = apiService.getDeleteFriendGroupingBeandata(groupId);
         deleteFriendGroupingBeandata.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<DeleteFriendGroupingBean>() {
                     @Override
                     public void accept(DeleteFriendGroupingBean deleteFriendGroupingBean) throws Exception {
                      modelCallBack.success(deleteFriendGroupingBean);
                     }
                 }, new Consumer<Throwable>() {
                     @Override
                     public void accept(Throwable throwable) throws Exception {
                      modelCallBack.failur(throwable);
                     }
                 });
    }
}
