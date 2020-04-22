package com.wd.tech.bean.messagebean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/18 8:46
 * @classname :
 */
public class UserFriendInfromRecordBean implements Serializable {

    /**
     * result : [{"fromHeadPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","fromNickName":"夹心","fromUid":1387,"noticeId":1060,"noticeTime":1587128747000,"receiveUid":1389,"remark":"我是","status":1}]
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
         * fromHeadPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * fromNickName : 夹心
         * fromUid : 1387
         * noticeId : 1060
         * noticeTime : 1587128747000
         * receiveUid : 1389
         * remark : 我是
         * status : 1
         */

        private String fromHeadPic;
        private String fromNickName;
        private int fromUid;
        private int noticeId;
        private long noticeTime;
        private int receiveUid;
        private String remark;
        private int status;

        public String getFromHeadPic() {
            return fromHeadPic;
        }

        public void setFromHeadPic(String fromHeadPic) {
            this.fromHeadPic = fromHeadPic;
        }

        public String getFromNickName() {
            return fromNickName;
        }

        public void setFromNickName(String fromNickName) {
            this.fromNickName = fromNickName;
        }

        public int getFromUid() {
            return fromUid;
        }

        public void setFromUid(int fromUid) {
            this.fromUid = fromUid;
        }

        public int getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(int noticeId) {
            this.noticeId = noticeId;
        }

        public long getNoticeTime() {
            return noticeTime;
        }

        public void setNoticeTime(long noticeTime) {
            this.noticeTime = noticeTime;
        }

        public int getReceiveUid() {
            return receiveUid;
        }

        public void setReceiveUid(int receiveUid) {
            this.receiveUid = receiveUid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
