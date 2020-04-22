package com.wd.tech.beancommunity;

import java.io.Serializable;
import java.util.List;

public class CommunityData implements Serializable {

    private List<CommunityResult> result;

    public List<CommunityResult> getResult() {
        return result;
    }
}
