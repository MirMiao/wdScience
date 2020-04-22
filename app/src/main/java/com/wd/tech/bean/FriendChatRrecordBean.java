package com.wd.tech.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/20 8:47
 * @classname :
 */
public class FriendChatRrecordBean implements Serializable {

    /**
     * result : [{"chatTime":1587302142000,"content":"PGyla/asCHiNvyUyfKjXDakY8lXaK49yCsR094z/SLt1X5ZbhyeBkzl1dtqHqM3GcTcNSMhpAcQSzpUFB9umum74u3jbc9fO3Ke9ibFEAZeLcgtQtlE2EiZSDMI+xPNv04bdGduyprw+H1T4bFnSRQazmRG8yEmkJIhpRgx7A8I=","nickName":"夹心"},{"chatTime":1587301233000,"content":"PhFJTrxrTj4MttLLUE+/TFrsb3N6XjHsAZH6rC1HST/21OV+cDOSL3/7MB6VFcWoYH8QyCd1nDz25yyGYIZn4orxeVEy/G8xFUKZYGUTyaipyHxkFCQUhlH9dB0iIb1pVPDWmwBF3RqWFTb43GoEkrCuvN5IMf4ovQ++662Lz7s=","nickName":"夹心"},{"chatTime":1587300544000,"content":"OoVEDhqzJ4h0qHtVTkYnNmKxkR84Bc986V6ZB6HeCo9OB+yy1dWtxaXCa0qMj2JFvdHwQHlqKl9n50wQBw0l/sUMPpdUtpEiSO9D+bPti3+ndON3OijK5MayjV7Hc+ysAmtgUBSZzU3GOzfDk7qCQxJkJy7rtpgEYiGHzcM/yrY=","nickName":"夹心"}]
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
         * chatTime : 1587302142000
         * content : PGyla/asCHiNvyUyfKjXDakY8lXaK49yCsR094z/SLt1X5ZbhyeBkzl1dtqHqM3GcTcNSMhpAcQSzpUFB9umum74u3jbc9fO3Ke9ibFEAZeLcgtQtlE2EiZSDMI+xPNv04bdGduyprw+H1T4bFnSRQazmRG8yEmkJIhpRgx7A8I=
         * nickName : 夹心
         */

        private long chatTime;
        private String content;
        private String nickName;

        public long getChatTime() {
            return chatTime;
        }

        public void setChatTime(long chatTime) {
            this.chatTime = chatTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }
}
