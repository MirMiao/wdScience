package com.wd.tech.api;

/**
 * 时间 :2020/4/17  14:55
 * 作者 :苗恒
 * 功能 :
 */
public interface Api {
    String BASE_URL="https://mobile.bwstudent.com/techApi/";//根路径
    String USERFRIENDINFROMRECORD_URL="chat/verify/v1/findFriendNoticePageList";//查询用户的好友通知记录
    String USERFRIENDLIST_URL="chat/verify/v1/initFriendList";//初始化我的好友列表全量信息

}
