package com.wd.tech.bean.messagebean;

import java.io.Serializable;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/23 14:07
 * @classname :
 */
public class UserExisisCrowdBean implements Serializable {

    /**
     * flag : 1
     * message : 已是该群成员
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
