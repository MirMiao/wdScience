package com.wd.tech.bean.messagebean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/24 15:16
 * @classname :
 */
public class CrowdGroupAllUserMessageBean implements Serializable {

    /**
     * result : [{"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-19/20200419193529.png","nickName":"界王神","role":3,"userId":1387},{"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-19/20200419193453.png","nickName":"宝贝","role":1,"userId":1400}]
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
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-04-19/20200419193529.png
         * nickName : 界王神
         * role : 3
         * userId : 1387
         */

        private String headPic;
        private String nickName;
        private int role;
        private int userId;

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

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
