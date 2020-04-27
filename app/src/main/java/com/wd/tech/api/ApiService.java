package com.wd.tech.api;
import com.wd.tech.bean.beanMyHomePage.MyAllData;
import com.wd.tech.bean.beanMyHomePage.MyFollowUserData;
import com.wd.tech.bean.beanMyHomePage.MySetUpData;
import com.wd.tech.bean.beanMyHomePage.MySysNoticeData;
import com.wd.tech.bean.beanMyHomePage.MyUserIntegralData;
import com.wd.tech.bean.beanMyHomePage.MyUserIntegralRecordData;
import com.wd.tech.bean.beanMyHomePage.MyUserTaskData;
import com.wd.tech.bean.beancommunity.CommentData;
import com.wd.tech.bean.informationentity.FindAllInfoPlate;
import com.wd.tech.bean.informationentity.LoginEntity;
import com.wd.tech.bean.informationentity.RegEntity;
import com.wd.tech.bean.informationentity.SerchInfoByKeyWordEntity;
import com.wd.tech.bean.messagebean.AddFriendBean;
import com.wd.tech.bean.messagebean.AdjustCrowdMemberBean;
import com.wd.tech.bean.messagebean.AlterCrowdGroupIntroBean;
import com.wd.tech.bean.messagebean.AlterCrowdGroupNameBean;
import com.wd.tech.bean.messagebean.AlterFriendGroupingNameBean;
import com.wd.tech.bean.messagebean.AlterFriendRemarkBean;
import com.wd.tech.bean.messagebean.AnviteAddCrowdBean;
import com.wd.tech.bean.messagebean.ApplyAddCrowdBean;
import com.wd.tech.bean.messagebean.BatchAnviteAddCrowdBean;
import com.wd.tech.bean.messagebean.CheckCrowdApplyBean;
import com.wd.tech.bean.messagebean.CheckFriendApplyBean;
import com.wd.tech.bean.messagebean.CrowGroupDetailMessageBean;
import com.wd.tech.bean.messagebean.CrowdChatContentBean;
import com.wd.tech.bean.messagebean.CrowdGroupAllUserMessageBean;
import com.wd.tech.bean.messagebean.CrowdInfromBean;
import com.wd.tech.bean.messagebean.DeleteCrowdGroupBean;
import com.wd.tech.bean.messagebean.DeleteCrowdMemberBean;
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
import com.wd.tech.bean.messagebean.UploadCrowdHeadpicBean;
import com.wd.tech.bean.messagebean.UserAllGroupingBean;
import com.wd.tech.bean.messagebean.UserExisisCrowdBean;
import com.wd.tech.bean.messagebean.UserFriendInfromRecordBean;
import com.wd.tech.bean.messagebean.UserFriendListBean;
import com.wd.tech.bean.beancommunity.CommentaryData;
import com.wd.tech.bean.beancommunity.CommunityData;
import com.wd.tech.bean.beancommunity.MyPostData;
import com.wd.tech.bean.beancommunity.ReleasepostData;

import java.io.File;
import com.wd.tech.bean.informationentity.BannerEntity;
import com.wd.tech.bean.informationentity.InfoRecommendListEntity;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

    @GET(Api.RRIENDMYLIST_URL)//查询我的好友列表
    Observable<FriendListBean>getFriendListBeandata(@Query("searchName") String searchName);

    //群组相关
    @POST(Api.SETCROWD_URL)//创建群
    @FormUrlEncoded
    Observable<SetCrowdBean>getSetCrowdBeandata(@Field("name") String name,@Field("description") String description);

    @GET(Api.CROWDINFROM_URL)//查询群通知记录
    Observable<CrowdInfromBean>getCrowdInfromBeandata(@Query("page") int page, @Query("count") int count);


    @GET(Api.MYSETCROWDGROUP_URL)//查询我创建的群组
    Observable<MySetCrowGroupBean>getMySetCrowGroupBeandata();

    @GET(Api.MYALLADDCROWDGROUP_URL)//查询我所有加入的群组
    Observable<MyAllAddCrowdGroupBean>getMyAllAddCrowdGroupBeandata();

    @GET(Api.USEREXISISCROWD_URL)//判断用户是否已在群内
    Observable<UserExisisCrowdBean>getUserExisisCrowdBeandata(@Query("groupId") int groupId);

    @PUT(Api.CHECKCROWDAPPLY_URL)//审核群申请
    Observable<CheckCrowdApplyBean>getCheckCrowdApplyBeandata(@Query("noticeId") int noticeId, @Query("flag") int flag);

    @GET(Api.CROWDGROUPDETAILMESSAGE_URL)//查询群组详细信息
    Observable<CrowGroupDetailMessageBean>getCrowGroupDetailMessageBeandata(@Query("groupId") int groupId);

    @PUT(Api.ALTERCROWDGROUPNAME_URL)//修改群组名
    Observable<AlterCrowdGroupNameBean>getAlterCrowdGroupNameBeandata(@Query("groupId") int groupId, @Query("groupName") String groupName);

    @PUT(Api.ALTERCROWDGROUPINTRO_URL)//修改群简介
    Observable<AlterCrowdGroupIntroBean>getAlterCrowdGroupIntroBeandata(@Query("groupId") int groupId, @Query("description") String description);

    @DELETE(Api.DELETECROWDGROUP_URL)//解散群组
    Observable<DeleteCrowdGroupBean>getDeleteCrowdGroupBeandata(@Query("groupId") int groupId);

    @Multipart
    @POST(Api.UPLOADCROWDHEADPIC_URL)//上传群头像
    Observable<UploadCrowdHeadpicBean>getUploadCrowdHeadpicBeandata(@Part("groupId") RequestBody groupId, @Part MultipartBody.Part file);

    @DELETE(Api.AUITCROWD_URL)//退群
    Observable<QuitCrowdBean>getQuitCrowdBeandata(@Query("groupId") int groupId);

    @GET(Api.CROWDGROUPALLUSERMESSAGE_URL)//查询群组内所有用户信息
    Observable<CrowdGroupAllUserMessageBean>getCrowdGroupAllUserMessageBeandata(@Query("groupId") int groupId);

    @POST(Api.SENDCROWDMESSAGE_URL)//发送群信息
    @FormUrlEncoded
    Observable<SendCrowdMessageBean>getSendCrowdMessageBeandata(@Field("groupId") int groupId, @Field("content") String content);

    @GET(Api.CROWDCHATCONTENT_URL)//查询群聊天内容
    Observable<CrowdChatContentBean>getCrowdChatContentBeandata(@Query("groupId") int groupId, @Query("page")int page, @Query("count") int count);


    @DELETE(Api.DELETECROWDMEMBER_URL)//移出群成员(管理员与群主才有的权限)
    Observable<DeleteCrowdMemberBean>getDeleteCrowdMemberBeandata(@Query("groupId") int groupId, @Query("groupUserId") int groupUserId);

    @PUT(Api.ADJUSTCROWDMEMBER_URL)//调整群成员角色(群主才有的权限)
    Observable<AdjustCrowdMemberBean>getAdjustCrowdMemberBeandata(@Query("groupId") int groupId, @Query("groupUserId") int groupUserId, @Query("role") int role);

    @POST(Api.APPLYADDCROWD_URL)//申请进群
    @FormUrlEncoded
    Observable<ApplyAddCrowdBean> getApplyAddCrowdBeandata(@Field("groupId") int groupId, @Field("remark") String remark);

    @POST(Api.ANVITEADDCROWD_URL)//邀请加群
    @FormUrlEncoded
    Observable<AnviteAddCrowdBean> getAnviteAddCrowdBeandata(@Field("groupId") int groupId, @Field("receiverUid") int receiverUid);

    @POST(Api.BATCHANVITEADDCROWD_URL)//批量邀请加群
    @FormUrlEncoded
    Observable<BatchAnviteAddCrowdBean> getBatchAnviteAddCrowdBeandata(@Field("groupId") int groupId, @Field("receiverUids") String receiverUids);

    //社区
    //社区列表
    @GET(Api.COMMUNITY_LIST)
    Observable<CommunityData> getCommunitylist(@Query("page")int page, @Query("count")int count);
    //社区用户评论
    @GET(Api.COMMUNITY_Commentary)
    Observable<CommentaryData> getCommentary(@Query("communityId")int communityId, @Query("page")int page, @Query("count")int count);


    //展示banner数据
    @GET("information/v1/bannerShow")
    Observable<BannerEntity> getBannerData();

    //展示首页数据
    @GET("information/v1/infoRecommendList")
    Observable<InfoRecommendListEntity> getInfoRecommendListData(@Query("plateId") int plateId,@Query("page")int page,@Query("count")int count);

    //展示点击菜单按钮展示数据
    @GET("information/v1/findAllInfoPlate")
    Observable<FindAllInfoPlate> getAllPlate();

    //根据关键字模糊查询
    @GET("information/v1/findInformationByTitle")
    Observable<SerchInfoByKeyWordEntity> serchByKeyWord(@Query("title") String title,@Query("page") int page,@Query("count")int count);
    //社区评论
    @POST(Api.COMMUNITY_Comment)
    @FormUrlEncoded
    Observable<CommentData> getComment(@Field("communityId") int communityId, @Field("content") String content);
    //发布帖子
    @POST(Api.COMMUNITY_ReleasePost)
    @FormUrlEncoded
    Observable<ReleasepostData> getReleasepost(@Field("content") String content,@Field("file") File file);
    //我的帖子
    @GET(Api.COMMUNITY_MyPostById)
    Observable<MyPostData> getMyPostIdData(@Query("page")int page, @Query("count")int count);



    //登陆
    @FormUrlEncoded
    @POST("user/v1/login")
     Observable<LoginEntity> login(@Field("phone") String phone,@Field("pwd") String pwd);
    //注册
    @FormUrlEncoded
    @POST("user/v1/register")
    Observable<RegEntity> reg(@Field("nickName") String nickName,@Field("phone")String phone,@Field("pwd")String pwd );


    //我的收藏
    @GET(Api.HomePage_Allinfor)
    Observable<MyAllData> getAll(@Query("page")int page, @Query("count")int count);
    //我的关注
    @GET(Api.HomePage_FollowUser)
    Observable<MyFollowUserData> getFollowUser(@Query("page")int page, @Query("count")int count);
    //我的通知
    @GET(Api.HomePage_SysNotice)
    Observable<MySysNoticeData> getSysNotice(@Query("page")int page, @Query("count")int count);
    //查询用户积分
    @GET(Api.HomePage_UserIntegral)
    Observable<MyUserIntegralData> getUserIntegral();
    //查询用户积分明细
    @GET(Api.HomePage_UserIntegralRecord)
    Observable<MyUserIntegralRecordData> getUserIntegralRecord(@Query("page")int page, @Query("count")int count);
    //根据用户ID查询用户信息(设置页面)
    @GET(Api.HomePage_UserInfoByUserId)
    Observable<MySetUpData> getSetUp();
    //查询用户任务列表
    @GET(Api.HomePage_UserTask)
    Observable<MyUserTaskData> getUserTask();


}
