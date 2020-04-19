package com.wd.tech.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/19 9:12
 * @classname :
 */
public class FriendMessageBean implements Serializable {

    /**
     * result : {"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","integral":0,"myGroupList":[],"nickName":"心心","phone":"13555278077","sex":1,"userId":1389,"userName":"G3Yd0q13555278077","whetherFaceId":2,"whetherVip":2}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * integral : 0
         * myGroupList : []
         * nickName : 心心
         * phone : 13555278077
         * sex : 1
         * userId : 1389
         * userName : G3Yd0q13555278077
         * whetherFaceId : 2
         * whetherVip : 2
         */

        private String headPic;
        private int integral;
        private String nickName;
        private String phone;
        private int sex;
        private int userId;
        private String userName;
        private int whetherFaceId;
        private int whetherVip;
        private List<?> myGroupList;

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getWhetherFaceId() {
            return whetherFaceId;
        }

        public void setWhetherFaceId(int whetherFaceId) {
            this.whetherFaceId = whetherFaceId;
        }

        public int getWhetherVip() {
            return whetherVip;
        }

        public void setWhetherVip(int whetherVip) {
            this.whetherVip = whetherVip;
        }

        public List<?> getMyGroupList() {
            return myGroupList;
        }

        public void setMyGroupList(List<?> myGroupList) {
            this.myGroupList = myGroupList;
        }
    }
}
