package com.wd.tech.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/19 15:40
 * @classname :
 */
public class FriendChatDialogueRecordBean implements Serializable {

    /**
     * result : [{"chatTime":1587281447000,"content":"aaAlnBMkoj8OnUdpLmnsV159s189+GhdicD8fiDhZTmKmpLmaI4SGiZ8YmY4gnVZXylalYX5Jle719Q9jdygJiUZuNVU3qHFQXPNMhSJiOeLVJoHU5ANqztTMp5kud3hXYqcEpokgpOI+HEs6LTLkfx9uHbk4mgOyAtTOCB7Wwg=","direction":2,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","userId":1400},{"chatTime":1587281263000,"content":"aaAlnBMkoj8OnUdpLmnsV159s189+GhdicD8fiDhZTmKmpLmaI4SGiZ8YmY4gnVZXylalYX5Jle719Q9jdygJiUZuNVU3qHFQXPNMhSJiOeLVJoHU5ANqztTMp5kud3hXYqcEpokgpOI+HEs6LTLkfx9uHbk4mgOyAtTOCB7Wwg=","direction":1,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","userId":1387}]
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
         * chatTime : 1587281447000
         * content : aaAlnBMkoj8OnUdpLmnsV159s189+GhdicD8fiDhZTmKmpLmaI4SGiZ8YmY4gnVZXylalYX5Jle719Q9jdygJiUZuNVU3qHFQXPNMhSJiOeLVJoHU5ANqztTMp5kud3hXYqcEpokgpOI+HEs6LTLkfx9uHbk4mgOyAtTOCB7Wwg=
         * direction : 2
         * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * userId : 1400
         */

        private long chatTime;
        private String content;
        private int direction;
        private String headPic;
        private int userId;

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

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
