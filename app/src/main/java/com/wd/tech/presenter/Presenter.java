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
    //查询用户的好友通知记录
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
    //初始化我的好友列表全量信息
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
    //查询好友信息
    @Override
    public void getFriendMessagedata(int friend) {
        model.getFriendMessagedata(friend, new IContract.IModel.ModelCallBack() {
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
     //检测是否为我的好友
    @Override
    public void getExisisMyFrienddata(int friendUid) {
      model.getExisisMyFrienddata(friendUid, new IContract.IModel.ModelCallBack() {
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
//审核好友申请
    @Override
    public void getCheckFriendApplydata(int noticeId, int flag) {
     model.getCheckFriendApplydata(noticeId, flag, new IContract.IModel.ModelCallBack() {
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
//发送信息
    @Override
    public void getSendMessageBeandata(int receiveUid, String content) {
       model.getSendMessageBeandata(receiveUid, content, new IContract.IModel.ModelCallBack() {
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
//查询好友对话记录
    @Override
    public void getFriendChatDialogueRecordBeandata(int friendUid, int page, int count) {
    model.getFriendChatDialogueRecordBeandata(friendUid, page, count, new IContract.IModel.ModelCallBack() {
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
