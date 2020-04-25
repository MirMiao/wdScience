package com.wd.tech.bean.informationentity;

/**
 * 时间 :2020/4/23  20:48
 * 作者 :苗恒
 * 功能 :
 */
public class LoginEntity {

    /**
     * result : {"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"泰山压顶","phone":"18580544823","pwd":"fP8ydP8MANeCTIYGGvfNLuBiUAwytIMNu/fFybJN7xmtYf1gcz+wh/A0x1NwZN+Ipvcij/Xuc2cJ54ukXfX4HE5uvPQV1m7EWnNqtPfc4GeaOvTchxDFTIBK5256v4KLyQCCJhDE2emm464Uji3/nPTNTUet72CO6DIHRGdsXH4=","sessionId":"15876461632251461","userId":1461,"userName":"2WSsrQ18580544823","whetherFaceId":0,"whetherVip":2}
     * message : 登录成功
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
         * nickName : 泰山压顶
         * phone : 18580544823
         * pwd : fP8ydP8MANeCTIYGGvfNLuBiUAwytIMNu/fFybJN7xmtYf1gcz+wh/A0x1NwZN+Ipvcij/Xuc2cJ54ukXfX4HE5uvPQV1m7EWnNqtPfc4GeaOvTchxDFTIBK5256v4KLyQCCJhDE2emm464Uji3/nPTNTUet72CO6DIHRGdsXH4=
         * sessionId : 15876461632251461
         * userId : 1461
         * userName : 2WSsrQ18580544823
         * whetherFaceId : 0
         * whetherVip : 2
         */

        private String headPic;
        private String nickName;
        private String phone;
        private String pwd;
        private String sessionId;
        private int userId;
        private String userName;
        private int whetherFaceId;
        private int whetherVip;
        private String signature;

        public String getSignature() {
            return signature;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
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
    }
}
