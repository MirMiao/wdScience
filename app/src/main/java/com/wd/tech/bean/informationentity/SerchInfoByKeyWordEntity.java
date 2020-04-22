package com.wd.tech.bean.informationentity;

import java.util.List;

/**
 * 时间 :2020/4/22  20:29
 * 作者 :苗恒
 * 功能 :
 */
public class SerchInfoByKeyWordEntity {

    /**
     * result : [{"id":66,"releaseTime":1572850283000,"source":"ITBEAR科技资讯","title":"国家互联网应急中心联手迅雷 推动契约生态区块链化"},{"id":64,"releaseTime":1572677483000,"source":"ITBEAR科技资讯","title":"区块链\u201c地位\u201d提升，百度智能云助力国家加快推动区块链产业自主创新"},{"id":61,"releaseTime":1553064018000,"source":"雨天","title":"区块链如何帮助人们更方便搞定法律服务？"},{"id":60,"releaseTime":1553063676000,"source":"侏罗纪","title":"威胁不止有51%攻击，区块链为何频遭黑客入侵？"},{"id":56,"releaseTime":1553040466000,"source":"科技行者","title":"谷歌踏入区块链搜索 触及八大最活跃网络完整数据集"},{"id":50,"releaseTime":1539582903000,"source":"全天候科技","title":"行业薪酬\u201c大跳水\u201d，区块链真的凉了？"},{"id":49,"releaseTime":1539582707000,"source":"蓝狐笔记","title":"为什么说区块链没那么简单？"},{"id":48,"releaseTime":1539582496000,"source":"网事风云","title":"区块链落地实体经济，这个领域可能是先锋"},{"id":47,"releaseTime":1539582250000,"source":"蓝狐笔记","title":"为什么说区块链\u201c无需信任\u201d？"},{"id":46,"releaseTime":1539582121000,"source":"懂懂笔记","title":"\u201c大会\u201d要开、\u201c大屏\u201d要占：区块链\u201c药\u201d不能停"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 66
         * releaseTime : 1572850283000
         * source : ITBEAR科技资讯
         * title : 国家互联网应急中心联手迅雷 推动契约生态区块链化
         */

        private int id;
        private long releaseTime;
        private String source;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
