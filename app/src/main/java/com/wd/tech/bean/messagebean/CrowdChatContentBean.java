package com.wd.tech.bean.messagebean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/24 17:11
 * @classname :
 */
public class CrowdChatContentBean implements Serializable {

    /**
     * result : [{"chatContent":"JGL4RAFk80m6zlrYejaXyDmtk0+LZaMmhJGSrBunwKh49Mg2u21efKC1xItwyJ+7SymOgQAT8Amn2uax5lk8c+rpQyOsxWCsdI42NjRRyjv8t33c1A4kFbCRkc2tzo5e4wVrnFkglkcaODdAzHLSJ5UNKjwxSLxAXCZ3Ohh2GAQ=","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-19/20200419193529.png","nickName":"界王神","userId":1387}]
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
         * chatContent : JGL4RAFk80m6zlrYejaXyDmtk0+LZaMmhJGSrBunwKh49Mg2u21efKC1xItwyJ+7SymOgQAT8Amn2uax5lk8c+rpQyOsxWCsdI42NjRRyjv8t33c1A4kFbCRkc2tzo5e4wVrnFkglkcaODdAzHLSJ5UNKjwxSLxAXCZ3Ohh2GAQ=
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-04-19/20200419193529.png
         * nickName : 界王神
         * userId : 1387
         */

        private String chatContent;
        private String headPic;
        private String nickName;
        private int userId;

        public String getChatContent() {
            return chatContent;
        }

        public void setChatContent(String chatContent) {
            this.chatContent = chatContent;
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
