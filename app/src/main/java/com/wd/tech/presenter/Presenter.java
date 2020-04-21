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
    //修改好友备注
    @Override
    public void getAlterFriendRemarkBeandata(int friendUid, String remarkName) {
        model.getAlterFriendRemarkBeandata(friendUid, remarkName, new IContract.IModel.ModelCallBack() {
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
    //查询好友聊天记录
    @Override
    public void getFriendChatRrecordBeandata(int friendUid, int page, int count) {
       model.getFriendChatRrecordBeandata(friendUid, page, count, new IContract.IModel.ModelCallBack() {
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
    //删除好友聊天记录
    @Override
    public void getDeleteFriendChatRrecordBeandata(int friendUid) {
             model.getDeleteFriendChatRrecordBeandata(friendUid, new IContract.IModel.ModelCallBack() {
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
    //删除好友
    @Override
    public void getDeleteFriendBeandata(int friendUid) {
     model.getDeleteFriendBeandata(friendUid, new IContract.IModel.ModelCallBack() {
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
//根据手机号查询用户信息
    @Override
    public void getPhoneUserMessangeBeanata(String phone) {
        model.getPhoneUserMessangeBeanata(phone, new IContract.IModel.ModelCallBack() {
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
  //添加好友
    @Override
    public void getAddFriendBeanata(int friendUid, String remark) {
        model.getAddFriendBeanata(friendUid, remark, new IContract.IModel.ModelCallBack() {
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
//创建自定义好友分组
    @Override
    public void getSetCustomFriendGroupingBeandata(String groupName) {
       model.getSetCustomFriendGroupingBeandata(groupName, new IContract.IModel.ModelCallBack() {
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
//查询用户所有分组
    @Override
    public void getUserAllGroupingBeandata() {
   model.getUserAllGroupingBeandata(new IContract.IModel.ModelCallBack() {
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
//修改好友分组名称
    @Override
    public void getAlterFriendGroupingNameBeandata(int groupId, String groupName) {
        model.getAlterFriendGroupingNameBeandata(groupId, groupName, new IContract.IModel.ModelCallBack() {
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
//转移好友到其他分组
    @Override
    public void getShiftFriendGroupingBeandata(int newGroupId, int friendUid) {
      model.getShiftFriendGroupingBeandata(newGroupId, friendUid, new IContract.IModel.ModelCallBack() {
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
//删除用户好友分组
    @Override
    public void getDeleteFriendGroupingBeandata(int groupId) {
       model.getDeleteFriendGroupingBeandata(groupId, new IContract.IModel.ModelCallBack() {
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
//创建群
    @Override
    public void getSetCrowdBeandata(String name, String description) {
       model.getSetCrowdBeandata(name, description, new IContract.IModel.ModelCallBack() {
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
