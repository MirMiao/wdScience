package com.wd.tech.bean;

import java.io.Serializable;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/19 9:13
 * @classname :
 */
public class ExisisMyFriendBean implements Serializable {

    /**
     * flag : 1
     * message : 已是好友
     * status : 0000
     */

    private int flag;
    private String message;
    private String status;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
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
