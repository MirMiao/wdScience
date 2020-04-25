package com.wd.tech.model;

import com.wd.tech.api.ApiService;
import com.wd.tech.bean.beancommunity.CommentaryData;
import com.wd.tech.bean.beancommunity.CommunityData;
import com.wd.tech.bean.beancommunity.MyPostData;
import com.wd.tech.bean.beancommunity.ReleasepostData;
import com.wd.tech.bean.informationentity.BannerEntity;
import com.wd.tech.bean.informationentity.FindAllInfoPlate;
import com.wd.tech.bean.informationentity.InfoRecommendListEntity;
import com.wd.tech.bean.informationentity.LoginEntity;
import com.wd.tech.bean.informationentity.RegEntity;
import com.wd.tech.bean.informationentity.SerchInfoByKeyWordEntity;
import com.wd.tech.bean.messagebean.AddFriendBean;
import com.wd.tech.bean.messagebean.AlterCrowdGroupIntroBean;
import com.wd.tech.bean.messagebean.AlterCrowdGroupNameBean;
import com.wd.tech.bean.messagebean.AlterFriendGroupingNameBean;
import com.wd.tech.bean.messagebean.AlterFriendRemarkBean;
import com.wd.tech.bean.messagebean.CheckCrowdApplyBean;
import com.wd.tech.bean.messagebean.CheckFriendApplyBean;
import com.wd.tech.bean.messagebean.CrowGroupDetailMessageBean;
import com.wd.tech.bean.messagebean.CrowdChatContentBean;
import com.wd.tech.bean.messagebean.CrowdGroupAllUserMessageBean;
import com.wd.tech.bean.messagebean.CrowdInfromBean;
import com.wd.tech.bean.messagebean.DeleteCrowdGroupBean;
import com.wd.tech.bean.messagebean.DeleteFriendBean;
import com.wd.tech.bean.messagebean.DeleteFriendChatRrecordBean;
import com.wd.tech.bean.messagebean.DeleteFriendGroupingBean;
import com.wd.tech.bean.messagebean.ExisisMyFriendBean;
import com.wd.tech.bean.messagebean.FriendChatDialogueRecordBean;
import com.wd.tech.bean.messagebean.FriendChatRrecordBean;
import com.wd.tech.bean.messagebean.FriendListBean;
import com.wd.tech.bean.messagebean.FriendMessageBean;
import com.wd.tech.bean.messagebean.MyAllAddCrowdGroupBean;
import com.wd.tech.bean.messagebean.MySetCrowGroupBean;
import com.wd.tech.bean.messagebean.PhoneUserMessangeBean;
import com.wd.tech.bean.messagebean.QuitCrowdBean;
import com.wd.tech.bean.messagebean.SendCrowdMessageBean;
import com.wd.tech.bean.messagebean.SendMessageBean;
import com.wd.tech.bean.messagebean.SetCrowdBean;
import com.wd.tech.bean.messagebean.SetCustomFriendGroupingBean;
import com.wd.tech.bean.messagebean.ShiftFriendGroupingBean;
import com.wd.tech.bean.messagebean.UserAllGroupingBean;
import com.wd.tech.bean.messagebean.UserExisisCrowdBean;
import com.wd.tech.bean.messagebean.UserFriendInfromRecordBean;
import com.wd.tech.bean.messagebean.UserFriendListBean;
import com.wd.tech.bean.beancommunity.CommentaryData;
import com.wd.tech.bean.beancommunity.CommunityData;
import com.wd.tech.bean.informationentity.BannerEntity;
import com.wd.tech.contract.IContract;
import com.wd.tech.util.RetrofitUtil;

import java.io.File;

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
//查询我的好友列表
    @Override
    public void getFriendListBeandata(String searchName, ModelCallBack modelCallBack) {
        Observable<FriendListBean>   friendListBeandata = apiService.getFriendListBeandata(searchName);
         friendListBeandata.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<FriendListBean>() {
                     @Override
                     public void accept(FriendListBean friendListBean) throws Exception {
                      modelCallBack.success(friendListBean);
                     }
                 }, new Consumer<Throwable>() {
                     @Override
                     public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                     }
                 });
    }

    //创建群
    @Override
    public void getSetCrowdBeandata(String name, String description, ModelCallBack modelCallBack) {
        Observable<SetCrowdBean>    setCrowdBeandata = apiService.getSetCrowdBeandata(name, description);
        setCrowdBeandata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SetCrowdBean>() {
                    @Override
                    public void accept(SetCrowdBean setCrowdBean) throws Exception {
                     modelCallBack.success(setCrowdBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                    }
                });
    }
//查询群通知记录
    @Override
    public void getCrowdInfromBeandata(int page, int count, ModelCallBack modelCallBack) {
        Observable<CrowdInfromBean>   crowdInfromBeandata = apiService.getCrowdInfromBeandata(page, count);
        crowdInfromBeandata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CrowdInfromBean>() {
                    @Override
                    public void accept(CrowdInfromBean crowdInfromBean) throws Exception {
                      modelCallBack.success(crowdInfromBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    modelCallBack.failur(throwable);
                    }
                });
    }
    //查询我创建的群组
    @Override
    public void getMySetCrowGroupBeandata(ModelCallBack modelCallBack) {
        Observable<MySetCrowGroupBean>    mySetCrowGroupBeandata = apiService.getMySetCrowGroupBeandata();
        mySetCrowGroupBeandata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MySetCrowGroupBean>() {
                    @Override
                    public void accept(MySetCrowGroupBean mySetCrowGroupBean) throws Exception {
                      modelCallBack.success(mySetCrowGroupBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                    }
                });
    }
    //查询我所有加入的群组
    @Override
    public void getMyAllAddCrowdGroupBeandata(ModelCallBack modelCallBack) {
        Observable<MyAllAddCrowdGroupBean>    myAllAddCrowdGroupBeandata = apiService.getMyAllAddCrowdGroupBeandata();
        myAllAddCrowdGroupBeandata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyAllAddCrowdGroupBean>() {
                    @Override
                    public void accept(MyAllAddCrowdGroupBean myAllAddCrowdGroupBean) throws Exception {
                      modelCallBack.success(myAllAddCrowdGroupBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                         modelCallBack.failur(throwable);
                    }
                });
    }
    //判断用户是否已在群内
    @Override
    public void getUserExisisCrowdBeandata(int groupId, ModelCallBack modelCallBack) {
        Observable<UserExisisCrowdBean>  userExisisCrowdBeandata = apiService.getUserExisisCrowdBeandata(groupId);
        userExisisCrowdBeandata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserExisisCrowdBean>() {
                    @Override
                    public void accept(UserExisisCrowdBean userExisisCrowdBean) throws Exception {
                    modelCallBack.success(userExisisCrowdBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                     modelCallBack.failur(throwable);
                    }
                });
    }
    //审核群申请
    @Override
    public void getCheckCrowdApplyBeandata(int noticeId, int flag, ModelCallBack modelCallBack) {
    Observable<CheckCrowdApplyBean>     checkCrowdApplyBeandata = apiService.getCheckCrowdApplyBeandata(noticeId, flag);
    checkCrowdApplyBeandata.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<CheckCrowdApplyBean>() {
                @Override
                public void accept(CheckCrowdApplyBean checkCrowdApplyBean) throws Exception {
                 modelCallBack.success(checkCrowdApplyBean);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                   modelCallBack.failur(throwable);
                }
            });
    }
    //查询群组详细信息
    @Override
    public void getCrowGroupDetailMessageBeandata(int groupId, ModelCallBack modelCallBack) {
    Observable<CrowGroupDetailMessageBean>    crowGroupDetailMessageBeandata = apiService.getCrowGroupDetailMessageBeandata(groupId);
     crowGroupDetailMessageBeandata.subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(new Consumer<CrowGroupDetailMessageBean>() {
                 @Override
                 public void accept(CrowGroupDetailMessageBean crowGroupDetailMessageBean) throws Exception {
                     modelCallBack.success(crowGroupDetailMessageBean);
                 }
             }, new Consumer<Throwable>() {
                 @Override
                 public void accept(Throwable throwable) throws Exception {
                     modelCallBack.failur(throwable);
                 }
             });
    }
   //修改群组名
    @Override
    public void getAlterCrowdGroupNameBeandata(int groupId, String groupName, ModelCallBack modelCallBack) {
    Observable<AlterCrowdGroupNameBean>     alterCrowdGroupNameBeandata = apiService.getAlterCrowdGroupNameBeandata(groupId, groupName);
     alterCrowdGroupNameBeandata.subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(new Consumer<AlterCrowdGroupNameBean>() {
                 @Override
                 public void accept(AlterCrowdGroupNameBean alterCrowdGroupNameBean) throws Exception {
                   modelCallBack.success(alterCrowdGroupNameBean);
                 }
             }, new Consumer<Throwable>() {
                 @Override
                 public void accept(Throwable throwable) throws Exception {
                      modelCallBack.failur(throwable);
                 }
             });
    }
    //修改群简介
    @Override
    public void getAlterCrowdGroupIntroBeandata(int groupId, String description, ModelCallBack modelCallBack) {
    Observable<AlterCrowdGroupIntroBean>   alterCrowdGroupIntroBeandata = apiService.getAlterCrowdGroupIntroBeandata(groupId, description);
    alterCrowdGroupIntroBeandata.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<AlterCrowdGroupIntroBean>() {
                @Override
                public void accept(AlterCrowdGroupIntroBean alterCrowdGroupIntroBean) throws Exception {
                    modelCallBack.success(alterCrowdGroupIntroBean);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                     modelCallBack.failur(throwable);
                }
            });
    }
    //解散群组
    @Override
    public void getDeleteCrowdGroupBeandata(int groupId, ModelCallBack modelCallBack) {
     Observable<DeleteCrowdGroupBean>   deleteCrowdGroupBeandata = apiService.getDeleteCrowdGroupBeandata(groupId);
      deleteCrowdGroupBeandata.subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Consumer<DeleteCrowdGroupBean>() {
                  @Override
                  public void accept(DeleteCrowdGroupBean deleteCrowdGroupBean) throws Exception {
                   modelCallBack.success(deleteCrowdGroupBean);
                  }
              }, new Consumer<Throwable>() {
                  @Override
                  public void accept(Throwable throwable) throws Exception {
                      modelCallBack.failur(throwable);
                  }
              });
    }
    //退群
    @Override
    public void getQuitCrowdBeandata(int groupId, ModelCallBack modelCallBack) {
    Observable<QuitCrowdBean>    quitCrowdBeandata = apiService.getQuitCrowdBeandata(groupId);
    quitCrowdBeandata.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<QuitCrowdBean>() {
                @Override
                public void accept(QuitCrowdBean quitCrowdBean) throws Exception {
                modelCallBack.success(quitCrowdBean);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    modelCallBack.failur(throwable);
                }
            });
    }
    //查询群组内所有用户信息
    @Override
    public void getCrowdGroupAllUserMessageBeandata(int groupId, ModelCallBack modelCallBack) {
    Observable<CrowdGroupAllUserMessageBean>    crowdGroupAllUserMessageBeandata = apiService.getCrowdGroupAllUserMessageBeandata(groupId);
    crowdGroupAllUserMessageBeandata.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<CrowdGroupAllUserMessageBean>() {
                @Override
                public void accept(CrowdGroupAllUserMessageBean crowdGroupAllUserMessageBean) throws Exception {
                   modelCallBack.success(crowdGroupAllUserMessageBean);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    modelCallBack.failur(throwable);
                }
            });
    }
    //发送群信息
    @Override
    public void getSendCrowdMessageBeandata(int groupId, String content, ModelCallBack modelCallBack) {
        Observable<SendCrowdMessageBean>   sendCrowdMessageBeandata = apiService.getSendCrowdMessageBeandata(groupId, content);
        sendCrowdMessageBeandata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SendCrowdMessageBean>() {
                    @Override
                    public void accept(SendCrowdMessageBean sendCrowdMessageBean) throws Exception {
                     modelCallBack.success(sendCrowdMessageBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                    }
                });
    }
    //查询群聊天内
    @Override
    public void getCrowdChatContentBeandata(int groupId, int page, int count, ModelCallBack modelCallBack) {
    Observable<CrowdChatContentBean>   crowdChatContentBeandata = apiService.getCrowdChatContentBeandata(groupId, page, count);
    crowdChatContentBeandata.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<CrowdChatContentBean>() {
                @Override
                public void accept(CrowdChatContentBean crowdChatContentBean) throws Exception {
                 modelCallBack.success(crowdChatContentBean);
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

    @Override
    public void getBannerData(ModelCallBack modelCallBack) {
                apiService.getBannerData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<BannerEntity>() {
                            @Override
                            public void accept(BannerEntity bannerEntity) throws Exception {
                                modelCallBack.success(bannerEntity);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                modelCallBack.failur(throwable);
                            }
                        });
    }
    //展示首页数据列表
    @Override
    public void getInfoRecommendListData(int plateId, int page, int count, ModelCallBack modelCallBack) {
           apiService.getInfoRecommendListData(plateId,page,count)
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Consumer<InfoRecommendListEntity>() {
                       @Override
                       public void accept(InfoRecommendListEntity infoRecommendListEntity) throws Exception {
                            modelCallBack.success(infoRecommendListEntity);
                       }
                   }, new Consumer<Throwable>() {
                       @Override
                       public void accept(Throwable throwable) throws Exception {
                           modelCallBack.failur(throwable);
                       }
                   });
    }

    @Override
    public void getPlateData(ModelCallBack modelCallBack) {
          apiService.getAllPlate()
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Consumer<FindAllInfoPlate>() {
                      @Override
                      public void accept(FindAllInfoPlate findAllInfoPlate) throws Exception {
                        modelCallBack.success(findAllInfoPlate);
                      }
                  }, new Consumer<Throwable>() {
                      @Override
                      public void accept(Throwable throwable) throws Exception {
                            modelCallBack.failur(throwable);
                      }
                  });
    }

    @Override
    public void serchByKeyWord(String title, int page, int count, ModelCallBack modelCallBack) {
        apiService.serchByKeyWord(title,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SerchInfoByKeyWordEntity>() {
                    @Override
                    public void accept(SerchInfoByKeyWordEntity serchInfoByKeyWordEntity) throws Exception {
                        modelCallBack.success(serchInfoByKeyWordEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                    }
                });
    }

    //发布帖子
    @Override
    public void getReleasepostdata(String content, File file, ModelCallBack modelCallBack) {
        Observable<ReleasepostData> releasepost = apiService.getReleasepost(content, file);
        releasepost.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReleasepostData>() {
                    @Override
                    public void accept(ReleasepostData releasepostData) throws Exception {
                        modelCallBack.success(releasepost);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                    }
                });
    }

    //我的帖子
    @Override
    public void getMyPostdata(int page, int count, ModelCallBack modelCallBack) {
        Observable<MyPostData> myPostIdData = apiService.getMyPostIdData(page, count);
        myPostIdData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyPostData>() {
                    @Override
                    public void accept(MyPostData myPostData) throws Exception {
                        modelCallBack.success(myPostData);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                    }
                });
    }



    @Override
    public void login(String phone, String pwd, ModelCallBack modelCallBack) {
        apiService.login(phone,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginEntity>() {
                    @Override
                    public void accept(LoginEntity loginEntity) throws Exception {
                    modelCallBack.success(loginEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                    }
                });
    }

    @Override
    public void reg(String nickName, String phone, String pwd, ModelCallBack modelCallBack) {
        apiService.reg(nickName,phone,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegEntity>() {
                    @Override
                    public void accept(RegEntity regEntity) throws Exception {
                        modelCallBack.success(regEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                    }
                });
    }
}
