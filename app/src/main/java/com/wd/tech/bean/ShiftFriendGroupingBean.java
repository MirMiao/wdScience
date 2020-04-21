package com.wd.tech.bean;

import java.io.Serializable;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/21 9:07
 * @classname :
 */
public class ShiftFriendGroupingBean implements Serializable {

    /**
     * message : 转移成功
     * status : 0000
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
