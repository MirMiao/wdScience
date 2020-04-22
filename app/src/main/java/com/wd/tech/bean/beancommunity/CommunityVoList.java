package com.wd.tech.bean.beancommunity;

import java.io.Serializable;

public class CommunityVoList implements Serializable {

    private String content;
    private String nickName;
    private int userId;

    public String getContent() {
        return content;
    }

    public String getNickName() {
        return nickName;
    }

    public int getUserId() {
        return userId;
    }
}
