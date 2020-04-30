package com.wd.tech.bean.informationentity;

import java.util.List;

/**
 * 时间 :2020/4/27  21:51
 * 作者 :苗恒
 * 功能 :
 */
public class FindAllPingLunEntity {
    /**
     * result : [{"commentTime":1562405585000,"content":"中国北京市朝阳区","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2640,"infoId":5,"nickName":"12","userId":758},{"commentTime":1562042841000,"content":"中国北京市朝阳区","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2636,"infoId":5,"nickName":"12","userId":758},{"commentTime":1560477444000,"content":"中国北京市东城区锡拉胡同6号","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2608,"infoId":5,"nickName":"12","userId":758},{"commentTime":1560477439000,"content":"中国北京市东城区锡拉胡同6号","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2607,"infoId":5,"nickName":"12","userId":758},{"commentTime":1560345494000,"content":"中国北京市东城区锡拉胡同6号","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2606,"infoId":5,"nickName":"12","userId":758}]
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
         * commentTime : 1562405585000
         * content : 中国北京市朝阳区
         * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * id : 2640
         * infoId : 5
         * nickName : 12
         * userId : 758
         */

        private long commentTime;
        private String content;
        private String headPic;
        private int id;
        private int infoId;
        private String nickName;
        private int userId;

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInfoId() {
            return infoId;
        }

        public void setInfoId(int infoId) {
            this.infoId = infoId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
