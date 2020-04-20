package com.wd.tech.api;

/**
 * 时间 :2020/4/17  14:55
 * 作者 :苗恒
 * 功能 :
 */
public interface Api {
    String BASE_URL="https://mobile.bwstudent.com/techApi/";//根路径
    //消息模块
    //好友聊天相关
    String USERFRIENDINFROMRECORD_URL="chat/verify/v1/findFriendNoticePageList";//查询用户的好友通知记录
    String USERFRIENDLIST_URL="chat/verify/v1/initFriendList";//初始化我的好友列表全量信息
    String FRIENDMESSAGE_URL="user/verify/v1/queryFriendInformation";//查询好友信息
    String EXISISMYFRIEND_URL="chat/verify/v1/checkMyFriend";//检测是否为我的好友
    String ADDFRIEND_URL="chat/verify/v1/addFriend";//添加好友
    String DELETEFRIEND_URL="chat/verify/v1/deleteFriendRelation";//删除好友
    String ALTERFRIENDREMARK_URL="chat/verify/v1/modifyFriendRemark";//修改好友备注
    String SETCUSTOMFRIENDGROUPING_URL="chat/verify/v1/addFriendGroup";//创建自定义好友分组
    String USERALLGROUPING_URL="chat/verify/v1/findFriendGroupList";//查询用户所有分组
    String ALTERFRIENDGROUPINGNAME_URL="chat/verify/v1/modifyGroupName";//修改好友分组名称
    String SHIFTFRIENDGROUPING_URL="chat/verify/v1/transferFriendGroup";//转移好友到其他分组
    String DELETEFRIENDGROUPING_URL="chat/verify/v1/deleteFriendGroup";//删除用户好友分组
    String GROUPINGFRIENDLISTMESSAGE_URL="chat/verify/v1/findFriendListByGroupId";//查询分组下的好友列表信息
    String CHECKFRIENDAPPLY_URL="chat/verify/v1/reviewFriendApply";//审核好友申请
    String SENDMESSAGE_URL="chat/verify/v1/sendMessage";//发送消息
    String FRRIENDCHATRECORD_URL="chat/verify/v1/findChatRecordPageList";//查询好友聊天记录
    String FRIENDCHATDIALOGUERECORD_URL="chat/verify/v1/findDialogueRecordPageList";//查询好友对话记录
    String DELETEFRRIENDCHATRECORD_URL="chat/verify/v1/deleteChatRecord";//删除好友聊天记录
    String RRIENDMYLIST_URL="chat/verify/v1/searchFriend";//查询我的好友列表
    String PHONEUSERMESSSAGE_URL="user/verify/v1/findUserByPhone";//根据手机号查询用户信息

    //群组相关
    String SETCROWD_URL="group/verify/v1/createGroup";//创建群
    String ALTERCROWDGROUPNAME_URL="group/verify/v1/modifyGroupName";//修改群组名
    String ALTERCROWDGROUPINTRO_URL="group/verify/v1/modifyGroupDescription";//修改群简介
    String DELETECROWDGROUP_URL="group/verify/v1/disbandGroup";//解散群组
    String MYSETCROWDGROUP_URL="group/verify/v1/findGroupsByUserId";//查询我创建的群组
    String MYALLADDCROWDGROUP_URL="group/verify/v1/findUserJoinedGroup";//查询我所有加入的群组
    String CROWDGROUPALLUSERMESSAGE_URL="group/verify/v1/findGroupMemberList";//查询群组内所有用户信息
    String CROWDGROUPDETAILMESSAGE_URL="group/verify/v1/findGroupInfo";//查询群组详细信息
    String SENDCROWDMESSAGE_URL="group/verify/v1/sendGroupMessage";//发送群信息
    String CROWDCHATCONTENT_URL="group/verify/v1/findGroupChatRecordPage";//查询群聊天内容
    String DELETECROWDMEMBER_URL="group/verify/v1/removeGroupMember";//移出群成员(管理员与群主才有的权限)
    String ADJUSTCROWDMEMBER_URL="group/verify/v1/modifyPermissionr";//调整群成员角色(群主才有的权限)
    String USEREXISISCROWD_URL="group/verify/v1/whetherInGroup";//判断用户是否已在群内
    String APPLYADDCROWD_URL="group/verify/v1/applyAddGroup";//申请进群
    String ANVITEADDCROWD_URL="group/verify/v1/inviteAddGroup";//邀请加群
    String BATCHANVITEADDCROWD_URL="group/verify/v1/batchInviteAddGroup";//邀请加群
    String CROWDINFROM_URL="group/verify/v1/findGroupNoticePageList";//查询群通知记录
    String CHECKCROWDAPPLY_URL="group/verify/v1/reviewGroupApply";//审核群申请
    String UPLOADCROWDHEADPIC_URL="group/verify/v1/uploadGroupHeadPic";//上传群头像
    String AUITCROWD_URL="group/verify/v1/retreat";//退群
}
