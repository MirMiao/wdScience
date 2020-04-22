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
     void  getUserFriendInfromRecorddata(int page,int count,ModelCallBack modelCallBack);////查询用户的好友通知记录
     void  getUserFriendListdata(ModelCallBack modelCallBack);//初始化我的好友列表全量信息
     void  getCommunitydata(int page,int count,ModelCallBack modelCallBack);//社区列表
     void  getCommentary(int communityId,int page,int count,ModelCallBack modelCallBack);//社区用户评论
        interface ModelCallBack{
            void success(Object o);
            void failur(Throwable throwable);
        }
    }
    interface IPresenter{
        void  getUserFriendInfromRecorddata(int page, int count);////查询用户的好友通知记录
        void  getUserFriendListdata();//初始化我的好友列表全量信息
        void  getCommunitydata(int page, int count);//社区列表
        void  getCommentary(int communityId, int page, int count);//社区用户评论
    }
    interface IView extends IBaseView {
        void success(Object o);
        void failur(Throwable throwable);
    }
}
