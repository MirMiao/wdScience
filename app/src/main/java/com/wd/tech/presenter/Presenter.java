package com.wd.tech.presenter;


import com.wd.tech.base.mvp.BasePresenter;
import com.wd.tech.contract.IContract;
import com.wd.tech.model.Model;

import java.io.File;

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
//查询我的好友列表
    @Override
    public void getFriendListBeandata(String searchName) {
        model.getFriendListBeandata(searchName, new IContract.IModel.ModelCallBack() {
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
    //查询群通知记录
    @Override
    public void getCrowdInfromBeandata(int page, int count) {
       model.getCrowdInfromBeandata(page, count, new IContract.IModel.ModelCallBack() {
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
    //查询我创建的群组
    @Override
    public void getMySetCrowGroupBeandata() {
     model.getMySetCrowGroupBeandata(new IContract.IModel.ModelCallBack() {
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
    //查询我所有加入的群组
    @Override
    public void getMyAllAddCrowdGroupBeandata() {
     model.getMyAllAddCrowdGroupBeandata(new IContract.IModel.ModelCallBack() {
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
    //判断用户是否已在群内
    @Override
    public void getUserExisisCrowdBeandata(int groupId) {
     model.getUserExisisCrowdBeandata(groupId, new IContract.IModel.ModelCallBack() {
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
    //审核群申请
    @Override
    public void getCheckCrowdApplyBeandata(int noticeId, int flag) {
    model.getCheckCrowdApplyBeandata(noticeId, flag, new IContract.IModel.ModelCallBack() {
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
    //查询群组详细信息
    @Override
    public void getCrowGroupDetailMessageBeandata(int groupId) {
       model.getCrowGroupDetailMessageBeandata(groupId, new IContract.IModel.ModelCallBack() {
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
    //修改群组名
    @Override
    public void getAlterCrowdGroupNameBeandata(int groupId, String groupName) {
  model.getAlterCrowdGroupNameBeandata(groupId, groupName, new IContract.IModel.ModelCallBack() {
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
    //修改群简介
    @Override
    public void getAlterCrowdGroupIntroBeandata(int groupId, String description) {
   model.getAlterCrowdGroupIntroBeandata(groupId, description, new IContract.IModel.ModelCallBack() {
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
    //解散群组
    @Override
    public void getDeleteCrowdGroupBeandata(int groupId) {
      model.getDeleteCrowdGroupBeandata(groupId, new IContract.IModel.ModelCallBack() {
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
    //退群
    @Override
    public void getQuitCrowdBeandata(int groupId) {
     model.getQuitCrowdBeandata(groupId, new IContract.IModel.ModelCallBack() {
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
//查询群组内所有用户信息
    @Override
    public void getCrowdGroupAllUserMessageBeandata(int groupId) {
        model.getCrowdGroupAllUserMessageBeandata(groupId, new IContract.IModel.ModelCallBack() {
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
    //发送群信息
    @Override
    public void getSendCrowdMessageBeandata(int groupId, String content) {
     model.getSendCrowdMessageBeandata(groupId, content, new IContract.IModel.ModelCallBack() {
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
    //查询群聊天内
    @Override
    public void getCrowdChatContentBeandata(int groupId, int page, int count) {
       model.getCrowdChatContentBeandata(groupId, page, count, new IContract.IModel.ModelCallBack() {
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
    //移出群成员(管理员与群主才有的权限)
    @Override
    public void getDeleteCrowdMemberBeandata(int groupId, int groupUserId) {
        model.getDeleteCrowdMemberBeandata(groupId, groupUserId, new IContract.IModel.ModelCallBack() {
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
    //调整群成员角色(群主才有的权限)
    @Override
    public void getAdjustCrowdMemberBeandata(int groupId, int groupUserId, int role) {
    model.getAdjustCrowdMemberBeandata(groupId, groupUserId, role, new IContract.IModel.ModelCallBack() {
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
    //申请进群
    @Override
    public void getApplyAddCrowdBeandata(int groupId, String remark) {
     model.getApplyAddCrowdBeandata(groupId, remark, new IContract.IModel.ModelCallBack() {
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
    //邀请加群
    @Override
    public void getAnviteAddCrowdBeandata(int groupId, int receiverUid) {
     model.getAnviteAddCrowdBeandata(groupId, receiverUid, new IContract.IModel.ModelCallBack() {
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
    //批量邀请加群
    @Override
    public void getBatchAnviteAddCrowdBeandata(int groupId, String receiverUids) {
      model.getBatchAnviteAddCrowdBeandata(groupId, receiverUids, new IContract.IModel.ModelCallBack() {
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
     //展示banner列表
    @Override
    public void getBannerData() {
        model.getBannerData( new IContract.IModel.ModelCallBack() {
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
    public void getInfoRecommendListData(int plateId, int page, int count) {
        model.getInfoRecommendListData(plateId,page,count, new IContract.IModel.ModelCallBack() {
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
    public void getPlateData() {
        model.getPlateData( new IContract.IModel.ModelCallBack() {
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
    public void serchByKeyWord(String title, int page, int count) {
        model.serchByKeyWord(title,page,count, new IContract.IModel.ModelCallBack() {
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

    //发布帖子
    @Override
    public void getReleasepostdata(String content, File file) {
        model.getReleasepostdata(content, file, new IContract.IModel.ModelCallBack() {
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

    //我的帖子
    @Override
    public void getMyPostdata(int page, int count) {
        model.getMyPostdata(page, count, new IContract.IModel.ModelCallBack() {
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
    public void login(String phone, String pwd) {
        model.login(phone,pwd, new IContract.IModel.ModelCallBack() {
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
    public void reg(String nickName, String phone, String pwd) {
        model.reg(nickName,phone,pwd, new IContract.IModel.ModelCallBack() {
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

    //我的收藏
    @Override
    public void getMyHomepageAll(int page, int count) {
        model.getMyHomepageAll(page, count, new IContract.IModel.ModelCallBack() {
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
    public void getMyFollowUser(int page, int count) {
        model.getMyFollowUser(page, count, new IContract.IModel.ModelCallBack() {
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
    //我的通知
    @Override
    public void getMySysNotice(int page, int count) {
        model.getMySysNotice(page, count, new IContract.IModel.ModelCallBack() {
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
    public void getUserIntegral() {
        model.getUserIntegral(new IContract.IModel.ModelCallBack() {
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
    public void getUserIntegralRecord(int page, int count) {
        model.getUserIntegralRecord(page, count, new IContract.IModel.ModelCallBack() {
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
    public void getSetUp() {
        model.getSetUp(new IContract.IModel.ModelCallBack() {
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
