package com.wd.tech.api;
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
import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/18 8:51
 * @classname :
 */
public interface ApiService {
    //消息模块
    //好友聊天相关
    @GET(Api.USERFRIENDINFROMRECORD_URL)//查询用户的好友通知记录
    Observable<UserFriendInfromRecordBean>getUserFriendInfromRecordBeandata(@Query("page")int page,@Query("count")int count);

    @GET(Api.USERFRIENDLIST_URL)//初始化我的好友列表全量信息
    Observable<UserFriendListBean>getUserFriendListBeanndata();

    @GET(Api.FRIENDMESSAGE_URL)//查询好友信息
    Observable<FriendMessageBean>getFriendMessageBeandata(@Query("friend")int friend);

    @GET(Api.EXISISMYFRIEND_URL)//检测是否为我的好友
    Observable<ExisisMyFriendBean>getExisisMyFriendBeandata(@Query("friendUid")int friendUid);

    @PUT(Api.CHECKFRIENDAPPLY_URL)//审核好友申请
    Observable<CheckFriendApplyBean>getCheckFriendApplyBeandata(@Query("noticeId")int noticeId,@Query("flag")int flag);

    @POST(Api.SENDMESSAGE_URL)//发送消息
    @FormUrlEncoded
    Observable<SendMessageBean>getSendMessageBeandata(@Field("receiveUid") int receiveUid, @Field("content") String content);

    @GET(Api.FRIENDCHATDIALOGUERECORD_URL)//查询好友对话记录
    Observable<FriendChatDialogueRecordBean>getFriendChatDialogueRecordBeandata(@Query("friendUid")int friendUid, @Query("page")int page, @Query("count")int count);

    @PUT(Api.ALTERFRIENDREMARK_URL)//修改好友备注
    Observable<AlterFriendRemarkBean>getAlterFriendRemarkBeandata(@Query("friendUid")int friendUid, @Query("remarkName")String remarkName);

    @GET(Api.FRRIENDCHATRECORD_URL)//查询好友聊天记录
    Observable<FriendChatRrecordBean>getFriendChatRrecordBeandata(@Query("friendUid")int friendUid, @Query("page")int page, @Query("count") int count);

    @DELETE(Api.DELETEFRRIENDCHATRECORD_URL)//删除好友聊天记录
    Observable<DeleteFriendChatRrecordBean>getDeleteFriendChatRrecordBeandata(@Query("friendUid")int friendUid);

    @DELETE(Api.DELETEFRIEND_URL)//删除好友
    Observable<DeleteFriendBean>getDeleteFriendBeandata(@Query("friendUid")int friendUid);

    @GET(Api.PHONEUSERMESSSAGE_URL)//根据手机号查询用户信息
    Observable<PhoneUserMessangeBean>getPhoneUserMessangeBeandata(@Query("phone")String phone);

    @POST(Api.ADDFRIEND_URL)//添加好友
    @FormUrlEncoded
    Observable<AddFriendBean>getAddFriendBeandata(@Field("friendUid") int friendUid, @Field("remark") String remark);

    @POST(Api.SETCUSTOMFRIENDGROUPING_URL)//创建自定义好友分组
    @FormUrlEncoded
    Observable<SetCustomFriendGroupingBean>getSetCustomFriendGroupingBeandata(@Field("groupName") String groupName);

    @GET(Api.USERALLGROUPING_URL)//查询用户所有分组
    Observable<UserAllGroupingBean>getUserAllGroupingBeandata();

    @PUT(Api.ALTERFRIENDGROUPINGNAME_URL)//修改好友分组名称
    Observable<AlterFriendGroupingNameBean>getAlterFriendGroupingNameBeandata(@Query("groupId") int groupId, @Query("groupName") String groupName);

    @PUT(Api.SHIFTFRIENDGROUPING_URL)//转移好友到其他分组
    Observable<ShiftFriendGroupingBean>getShiftFriendGroupingBeandata(@Query("newGroupId") int newGroupId, @Query("friendUid") int friendUid);

    @DELETE(Api.DELETEFRIENDGROUPING_URL)//删除用户好友分组
    Observable<DeleteFriendGroupingBean>getDeleteFriendGroupingBeandata(@Query("groupId") int groupId);

}
