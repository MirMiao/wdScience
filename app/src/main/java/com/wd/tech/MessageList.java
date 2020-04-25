package com.wd.tech;

import java.io.Serializable;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/25 14:02
 * @classname :
 */
public class MessageList  implements Serializable {
    public  String headpic;
    public  String content;
    public  String time;
    public  String name;

    public MessageList(String headpic, String content, String time, String name) {
        this.headpic = headpic;
        this.content = content;
        this.time = time;
        this.name = name;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
