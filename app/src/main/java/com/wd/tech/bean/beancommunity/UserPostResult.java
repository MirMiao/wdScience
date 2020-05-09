package com.wd.tech.bean.beancommunity;

import java.util.List;

public class UserPostResult {

    private List<UserPostVoList> communityUserPostVoList;
    private UserPostVoListVo communityUserVo;

    public List<UserPostVoList> getCommunityUserPostVoList() {
        return communityUserPostVoList;
    }

    public UserPostVoListVo getCommunityUserVo() {
        return communityUserVo;
    }
}
