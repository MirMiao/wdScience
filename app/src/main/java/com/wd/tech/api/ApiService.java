package com.wd.tech.api;

import com.wd.tech.bean.UserFriendInfromRecordBean;
import com.wd.tech.bean.UserFriendListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/18 8:51
 * @classname :
 */
public interface ApiService {

    @GET(Api.USERFRIENDINFROMRECORD_URL)//查询用户的好友通知记录
    Observable<UserFriendInfromRecordBean>getUserFriendInfromRecordBeandata(@Query("page")int page,@Query("count")int count);

    @GET(Api.USERFRIENDLIST_URL)//初始化我的好友列表全量信息
    Observable<UserFriendListBean>getUserFriendListBeanndata();

}
