package com.wd.tech.bean.beancommunity;

import java.io.Serializable;
import java.util.List;

public class CommunityResult implements Serializable {

    private String content;
    private String file;
    private String headPic;
    private String nickName;
    private int userId;
    private long publishTime;
    private List<CommunityVoList> communityCommentVoList;
    private int comment;
    private int praise;
    private String signature;
    private int id;

    public int getId() {
        return id;
    }

    public String getSignature() {
        return signature;
    }

    public int getComment() {
        return comment;
    }

    public int getPraise() {
        return praise;
    }

    public String getContent() {
        return content;
    }

    public String getFile() {
        return file;
    }

    public String getHeadPic() {
        return headPic;
    }

    public String getNickName() {
        return nickName;
    }

    public int getUserId() {
        return userId;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public List<CommunityVoList> getCommunityCommentVoList() {
        return communityCommentVoList;
    }
}
