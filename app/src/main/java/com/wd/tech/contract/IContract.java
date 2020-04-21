package com.wd.tech.contract;

import com.wd.tech.base.mvp.IBaseModel;
import com.wd.tech.base.mvp.IBaseView;

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
    }
    interface IView extends IBaseView {
        void success(Object o);
        void failur(Throwable throwable);
    }
}
