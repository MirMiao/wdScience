package com.wd.tech.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/18 8:48
 * @classname :
 */
public class UserFriendListBean implements Serializable {

    /**
     * result : [{"black":1,"currentNumber":1,"customize":1,"friendInfoList":[{"friendUid":1389,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"心心","remarkName":"心心","userName":"G3Yd0q13555278077","vipFlag":2}],"groupId":2972,"groupName":"我的好友"},{"black":2,"currentNumber":0,"customize":1,"friendInfoList":[],"groupId":2973,"groupName":"黑名单"},{"black":1,"currentNumber":0,"customize":2,"friendInfoList":[],"groupId":2983,"groupName":"八维"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * black : 1
         * currentNumber : 1
         * customize : 1
         * friendInfoList : [{"friendUid":1389,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"心心","remarkName":"心心","userName":"G3Yd0q13555278077","vipFlag":2}]
         * groupId : 2972
         * groupName : 我的好友
         */

        private int black;
        private int currentNumber;
        private int customize;
        private int groupId;
        private String groupName;
        private List<FriendInfoListBean> friendInfoList;

        public int getBlack() {
            return black;
        }

        public void setBlack(int black) {
            this.black = black;
        }

        public int getCurrentNumber() {
            return currentNumber;
        }

        public void setCurrentNumber(int currentNumber) {
            this.currentNumber = currentNumber;
        }

        public int getCustomize() {
            return customize;
        }

        public void setCustomize(int customize) {
            this.customize = customize;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public List<FriendInfoListBean> getFriendInfoList() {
            return friendInfoList;
        }

        public void setFriendInfoList(List<FriendInfoListBean> friendInfoList) {
            this.friendInfoList = friendInfoList;
        }

        public static class FriendInfoListBean {
            /**
             * friendUid : 1389
             * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
             * nickName : 心心
             * remarkName : 心心
             * userName : G3Yd0q13555278077
             * vipFlag : 2
             */

            private int friendUid;
            private String headPic;
            private String nickName;
            private String remarkName;
            private String userName;
            private int vipFlag;

            public int getFriendUid() {
                return friendUid;
            }

            public void setFriendUid(int friendUid) {
                this.friendUid = friendUid;
            }

            public String getHeadPic() {
                return headPic;
            }

            public void setHeadPic(String headPic) {
                this.headPic = headPic;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getRemarkName() {
                return remarkName;
            }

            public void setRemarkName(String remarkName) {
                this.remarkName = remarkName;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public int getVipFlag() {
                return vipFlag;
            }

            public void setVipFlag(int vipFlag) {
                this.vipFlag = vipFlag;
            }
        }
    }
}
