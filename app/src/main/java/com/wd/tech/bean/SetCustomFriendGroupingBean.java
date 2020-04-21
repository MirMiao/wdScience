package com.wd.tech.bean;

import java.io.Serializable;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/21 8:42
 * @classname :
 */
public class SetCustomFriendGroupingBean  implements Serializable {

    /**
     * message : 创建分组成功
     * status : 0000
     * groupId : 1111
     */

    private String message;
    private String status;
    private int groupId;

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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
