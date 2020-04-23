package com.wd.tech.bean.messagebean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/23 8:45
 * @classname :
 */
public class FriendListBean implements Serializable {

    /**
     * result : [{"buddySource":"我的好友","friendUid":1389,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"玉盏","pwd":"GQ5J51fqZFe7hMS/t6xShxzyKPSOgCOXWChlN51xbcxP9DnMB0/ZYIxaoqJOR3lqHom2lJsiL8IZPuinMCE9bOpOIDbJpXNj4o62OgNDYNDgzYlouveWek6eukGTIJAdw9tq06Wg8uBZiRHrMspZEiDacVgg/gcfiql0rWGzJoc=","remarkName":"心心","signature":"金戈峥嵘归 玉盏琉璃杯","userName":"G3Yd0q13555278077"}]
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
         * buddySource : 我的好友
         * friendUid : 1389
         * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * nickName : 玉盏
         * pwd : GQ5J51fqZFe7hMS/t6xShxzyKPSOgCOXWChlN51xbcxP9DnMB0/ZYIxaoqJOR3lqHom2lJsiL8IZPuinMCE9bOpOIDbJpXNj4o62OgNDYNDgzYlouveWek6eukGTIJAdw9tq06Wg8uBZiRHrMspZEiDacVgg/gcfiql0rWGzJoc=
         * remarkName : 心心
         * signature : 金戈峥嵘归 玉盏琉璃杯
         * userName : G3Yd0q13555278077
         */

        private String buddySource;
        private int friendUid;
        private String headPic;
        private String nickName;
        private String pwd;
        private String remarkName;
        private String signature;
        private String userName;

        public String getBuddySource() {
            return buddySource;
        }

        public void setBuddySource(String buddySource) {
            this.buddySource = buddySource;
        }

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

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(String remarkName) {
            this.remarkName = remarkName;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
