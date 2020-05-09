package com.wd.tech.bean.informationentity;

/**
 * 时间 :2020/5/6  11:25
 * 作者 :苗恒
 * 功能 :
 */
public class BangDingFaceIdEntity {

    /**
     * faceId : gSGYIU6Z3alp3STuUCuxTJc+wLZp4JHZBdO9vueMZKs7v4tgm1lyRLbmSNoCkNDprx1bkJxYz4P9la4XkhNfS9mo8RFg3bjhTLioA5JEg4WkEd0b1pgJnsbOO36xJ89DgwesDnDoLtMMwdAU3v09NEo8Z2tZr+b0wbqqjkcrIj4=
     * message : 绑定成功
     * status : 0000
     */

    private String faceId;
    private String message;
    private String status;

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
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
}
