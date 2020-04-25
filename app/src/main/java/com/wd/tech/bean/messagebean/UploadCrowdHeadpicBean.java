package com.wd.tech.bean.messagebean;

import java.io.Serializable;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/24 13:52
 * @classname :
 */
public class UploadCrowdHeadpicBean implements Serializable {

    /**
     * result : http://172.17.8.100/images/tech/group_pic/2018-09-29/20180929160146.jpg
     * message : 上传成功
     * status : 0000
     */

    private String result;
    private String message;
    private String status;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
