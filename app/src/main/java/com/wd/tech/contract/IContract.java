package com.wd.tech.contract;

import com.wd.tech.base.mvp.IBaseModel;
import com.wd.tech.base.mvp.IBaseView;
import com.wd.tech.model.Model;

import java.io.File;

/*
 * 时间 :2020/3/27  12:01
 * 作者 :苗恒
 * 功能 :
 */
public interface IContract {
    interface IModel extends IBaseModel {
        //消息模块
        //好友相关
     void  getUserFriendInfromRecorddata(int page,int count,ModelCallBack modelCallBack);////查询用户的好友通知记录
     void  getUserFriendListdata(ModelCallBack modelCallBack);//初始化我的好友列表全量信息
     void  getFriendMessagedata(int friend,ModelCallBack modelCallBack);//查询好友信息
     void  getExisisMyFrienddata(int friendUid,ModelCallBack modelCallBack);//检测是否为我的好友
     void  getCheckFriendApplydata(int noticeId,int flag,ModelCallBack modelCallBack);//审核好友申请
     void  getSendMessageBeandata(int receiveUid,String content,ModelCallBack modelCallBack);//发送消息
     void  getFriendChatDialogueRecordBeandata(int friendUid,int page,int count,ModelCallBack modelCallBack);//查询好友对话记录
     void  getAlterFriendRemarkBeandata(int friendUid,String remarkName,ModelCallBack modelCallBack);//修改好友备注
     void  getFriendChatRrecordBeandata(int friendUid, int page,int count,ModelCallBack modelCallBack);//查询好友聊天记录
     void  getDeleteFriendChatRrecordBeandata(int friendUid,ModelCallBack modelCallBack);//删除好友聊天记录
     void  getDeleteFriendBeandata(int friendUid,ModelCallBack modelCallBack);//删除好友
     void  getPhoneUserMessangeBeanata(String phone,ModelCallBack modelCallBack);//根据手机号查询用户信息
     void  getAddFriendBeanata(int friendUid,String remark,ModelCallBack modelCallBack);//添加好友
     void  getSetCustomFriendGroupingBeandata(String groupName,ModelCallBack modelCallBack);//创建自定义好友分组
     void  getUserAllGroupingBeandata(ModelCallBack modelCallBack);//查询用户所有分组
     void  getAlterFriendGroupingNameBeandata(int groupId,String groupName,ModelCallBack modelCallBack);//修改好友分组名称
     void  getShiftFriendGroupingBeandata(int newGroupId,int friendUid,ModelCallBack modelCallBack);//转移好友到其他分组
     void  getDeleteFriendGroupingBeandata(int groupId,ModelCallBack modelCallBack);//删除用户好友分组
     void  getFriendListBeandata(String searchName,ModelCallBack modelCallBack);//查询我的好友列表
      //群组相关
      void  getSetCrowdBeandata(String name,String description,ModelCallBack modelCallBack);//创建群
      void  getCrowdInfromBeandata(int page,int count,ModelCallBack modelCallBack);//查询群通知记录
      void  getMySetCrowGroupBeandata(ModelCallBack modelCallBack);//查询我创建的群组
      void  getMyAllAddCrowdGroupBeandata(ModelCallBack modelCallBack);//查询我所有加入的群组
      void  getUserExisisCrowdBeandata(int groupId,ModelCallBack modelCallBack);//判断用户是否已在群内
      void  getCheckCrowdApplyBeandata(int noticeId,int flag,ModelCallBack modelCallBack);//审核群申请
      void  getCrowGroupDetailMessageBeandata(int groupId,ModelCallBack modelCallBack);//查询群组详细信息
      void  getAlterCrowdGroupNameBeandata(int groupId,String groupName,ModelCallBack modelCallBack);//修改群组名
      void  getAlterCrowdGroupIntroBeandata(int groupId,String description,ModelCallBack modelCallBack);//修改群简介
      void  getDeleteCrowdGroupBeandata(int groupId,ModelCallBack modelCallBack);//解散群组
      void  getQuitCrowdBeandata(int groupId,ModelCallBack modelCallBack);//退群
      void  getCrowdGroupAllUserMessageBeandata(int groupId,ModelCallBack modelCallBack);//查询群组内所有用户信息
      void  getSendCrowdMessageBeandata(int groupId,String content,ModelCallBack modelCallBack);//发送群信息
      void  getCrowdChatContentBeandata(int groupId,int page,int count,ModelCallBack modelCallBack);//查询群聊天内容
      void  getDeleteCrowdMemberBeandata(int groupId,int groupUserId,ModelCallBack modelCallBack);//移出群成员(管理员与群主才有的权限)
      void  getAdjustCrowdMemberBeandata(int groupId,int groupUserId,int role,ModelCallBack modelCallBack);//调整群成员角色(群主才有的权限)
      void  getApplyAddCrowdBeandata(int groupId,String remark,ModelCallBack modelCallBack);//申请进群
      void  getAnviteAddCrowdBeandata(int groupId,int receiverUid,ModelCallBack modelCallBack);//邀请加群
      void  getBatchAnviteAddCrowdBeandata(int groupId,String receiverUids,ModelCallBack modelCallBack);//批量邀请加群


        //社区
        void  getCommunitydata(int page,int count,ModelCallBack modelCallBack);//社区列表
        void  getCommentary(int communityId,int page,int count,ModelCallBack modelCallBack);//社区用户评论
        void  getReleasepostdata(String content,File file,ModelCallBack modelCallBack);//发布帖子
        void  getMyPostdata(int page,int count,ModelCallBack modelCallBack);//我的帖子

        void getBannerData(ModelCallBack modelCallBack);//banner展示列表
        void getInfoRecommendListData(int plateId,int page,int count,ModelCallBack modelCallBack); //展示首页数据
        void getPlateData(ModelCallBack modelCallBack);  //展示菜单
         void serchByKeyWord(String title,int page,int count,ModelCallBack modelCallBack); //根据关键字模糊查询
        void login(String phone,String pwd,ModelCallBack modelCallBack); //登陆
         void reg(String nickName,String phone,String pwd,ModelCallBack modelCallBack); //注册

        //我的
        void getMyHomepageAll(int page,int count,ModelCallBack modelCallBack);//我的收藏
        void getMyFollowUser(int page, int count,ModelCallBack modelCallBack);//我的关注
        void getMySysNotice(int page, int count,ModelCallBack modelCallBack);//我的通知
        void getUserIntegral(ModelCallBack modelCallBack);//查询用户积分
        void getUserIntegralRecord(int page, int count,ModelCallBack modelCallBack);//查询用户积分明细
        void getSetUp(ModelCallBack modelCallBack);//设置页面（根据用户ID查询用户信息）
        void getUserTask(ModelCallBack modelCallBack);////查询用户任务列表
        void getUserSign(ModelCallBack modelCallBack);//签到

        void addGreatRecor(int userId,String sessionId,int infoId,ModelCallBack modelCallBack);//点赞
        void getInformationInfo(int id,ModelCallBack modelCallBack); //资讯详情
        void getAllPingLun(int infoId,int page,int count,ModelCallBack modelCallBack); //查询资讯所有评论
        void addInforComment(int userId,String sessionid,String content,int infoId,ModelCallBack modelCallBack);//添加资讯评论



        interface ModelCallBack{
            void success(Object o);
            void failur(Throwable throwable);
        }
    }
    interface IPresenter{
        void  getUserFriendInfromRecorddata(int page, int count);//查询用户的好友通知记录
        void  getUserFriendListdata();//初始化我的好友列表全量信息
        void  getFriendMessagedata(int friend);//查询好友信息
        void  getExisisMyFrienddata(int friendUid);//检测是否为我的好友
        void  getCheckFriendApplydata(int noticeId,int flag);//审核好友申请
        void  getSendMessageBeandata(int receiveUid,String content);//发送消息
        void  getFriendChatDialogueRecordBeandata(int friendUid,int page,int count);//查询好友对话记录
        void  getAlterFriendRemarkBeandata(int friendUid,String remarkName);//修改好友备注
        void  getFriendChatRrecordBeandata(int friendUid, int page,int count);//查询好友聊天记录
        void  getDeleteFriendChatRrecordBeandata(int friendUid);//删除好友聊天记录
        void  getDeleteFriendBeandata(int friendUid);//删除好友
        void  getPhoneUserMessangeBeanata(String phone);//根据手机号查询用户信息
        void  getAddFriendBeanata(int friendUid,String remark);//添加好友
        void  getSetCustomFriendGroupingBeandata(String groupName);//创建自定义好友分组
        void  getUserAllGroupingBeandata();//查询用户所有分组
        void  getAlterFriendGroupingNameBeandata(int groupId,String groupName);//修改好友分组名称
        void  getShiftFriendGroupingBeandata(int newGroupId,int friendUid);//转移好友到其他分组
        void  getDeleteFriendGroupingBeandata(int groupId);//删除用户好友分组
        void  getFriendListBeandata(String searchName);//查询我的好友列表
        //群组相关
        void  getSetCrowdBeandata(String name,String description);//创建群
        void  getCrowdInfromBeandata(int page,int count);//查询群通知记录
        void  getMySetCrowGroupBeandata();//查询我创建的群组
        void  getMyAllAddCrowdGroupBeandata();//查询我所有加入的群组
        void  getUserExisisCrowdBeandata(int groupId);//判断用户是否已在群内
        void  getCheckCrowdApplyBeandata(int noticeId,int flag);//审核群申请
        void  getCrowGroupDetailMessageBeandata(int groupId);//查询群组详细信息
        void  getAlterCrowdGroupNameBeandata(int groupId,String groupName);//修改群组名
        void  getAlterCrowdGroupIntroBeandata(int groupId,String description);//修改群简介
        void  getDeleteCrowdGroupBeandata(int groupId);//解散群组
        void  getQuitCrowdBeandata(int groupId);//退群
        void  getCrowdGroupAllUserMessageBeandata(int groupId);//查询群组内所有用户信息
        void  getSendCrowdMessageBeandata(int groupId,String content);//发送群信息
        void  getCrowdChatContentBeandata(int groupId,int page,int count);//查询群聊天内
        void  getDeleteCrowdMemberBeandata(int groupId,int groupUserId);//移出群成员(管理员与群主才有的权限)
        void  getAdjustCrowdMemberBeandata(int groupId,int groupUserId,int role);//调整群成员角色(群主才有的权限)
        void  getApplyAddCrowdBeandata(int groupId,String remark);//申请进群
        void  getAnviteAddCrowdBeandata(int groupId,int receiverUid);//邀请加群
        void  getBatchAnviteAddCrowdBeandata(int groupId,String receiverUids);//批量邀请加群
        //社区
        void  getCommunitydata(int page, int count);//社区列表
        void  getCommentary(int communityId, int page, int count);//社区用户评论
        void  getReleasepostdata(String content, File file);//发布帖子
        void  getMyPostdata(int page, int count);//我的帖子


        void getBannerData();  //展示banner列表
        void getInfoRecommendListData(int plateId,int page,int count);//展示首页数据
        void getPlateData(); //展示菜单
        void serchByKeyWord(String title,int page,int count); //根据关键字模糊查询
        void login(String phone,String pwd); //登陆
        void reg(String nickName,String phone,String pwd); //注册

        //我的
        void getMyHomepageAll(int page, int count);//我的收藏
        void getMyFollowUser(int page, int count);//我的关注
        void getMySysNotice(int page, int count);//我的通知
        void getUserIntegral();//查询用户积分
        void getUserIntegralRecord(int page, int count);//查询用户积分明细
        void getSetUp();//设置页面（根据用户ID查询用户信息）
        void getUserTask();////查询用户任务列表
        void getUserSign();//签到

        void addGreatRecor(int userId,String sessionId,int infoId);//点赞
        void getInformationInfo(int id); //资讯详情
        void getAllPingLun(int infoId,int page,int count); //查询资讯所有评论
        void addInforComment(int userId,String sessionid,String content,int infoId);//添加资讯评论
    }
    interface IView extends IBaseView {
        void success(Object o);
        void failur(Throwable throwable);
    }
}
