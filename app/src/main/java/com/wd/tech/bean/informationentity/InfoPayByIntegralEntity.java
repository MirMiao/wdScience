package com.wd.tech.bean.informationentity;

/**
 * 时间 :2020/5/3  10:38
 * 作者 :苗恒
 * 功能 :
 */
public class InfoPayByIntegralEntity {

    /**
     * message : 积分不够,无法兑换
     * status : 1001
     */

    private String message;
    private String status;

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
