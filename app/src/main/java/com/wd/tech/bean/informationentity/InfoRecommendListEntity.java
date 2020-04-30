package com.wd.tech.bean.informationentity;

import java.util.List;

/**
 * 时间 :2020/4/22  16:27
 * 作者 :苗恒
 * 功能 :
 */
public class InfoRecommendListEntity {

    /**
     * result : [{"collection":0,"id":25,"releaseTime":1539313463000,"share":0,"source":"单向街书店©","summary":"刚刚结束的十一小长假，谷歌还没重返大陆，Google AI 体验展却先它一步去到了\u201c魔都\u201d上海。","thumbnail":"https://img.huxiucdn.com/article/cover/201805/03/170410209918.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"AI 真正可怕的地方在于，它证明了人类不是特别的","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":2,"id":24,"releaseTime":1539313236000,"share":0,"source":"量子位","summary":"亚马逊栽了个大跟头。","thumbnail":"https://img.huxiucdn.com/article/cover/201806/14/190902520265.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"AI学会了人类的\u201c傲慢与偏见\u201d，比如性别歧视","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":-3,"id":23,"releaseTime":1539312919000,"share":0,"source":"大数据文摘出品","summary":"从天猫精灵、监控探头，到自动驾驶汽车，情绪检测技术正变得无处不在。语音助手检测着我们的音调和音色，以便更好地理解命令。公共空间遍布了跟踪识别人脸的摄像头，据称可以在犯罪分子犯罪之前锁定他们。在未来，自动驾驶汽车将能够发现驾驶员路怒行为，并强制控制车辆。","thumbnail":"https://img.huxiucdn.com/article/cover/201810/11/214823642984.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"AI情绪识别技术：你的情绪将不再由你自己定义","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":1,"id":22,"releaseTime":1539248227000,"share":0,"source":"雷锋网","summary":"在目前的AR应用中，2D AR跟踪，如海报、卡牌等平面物体的跟踪已经成为核心技术之一，在营销、教育、游戏、展示展览等方面都很常见。然而，尽管近年来2D AR跟踪算法已经取得了很大的进步，但在一些外部条件、环境因素影响下的效果仍然有很大提升空间，如何处理光照变化、运动模糊等因素带来的挑战，也是目前进行底层算法研发的AR公司以及学者的研发热点。","thumbnail":"https://inews.gtimg.com/newsapp_ls/0/5694585743_294195/0","title":"亮风台2D AR算法新突破，夺冠世界权威评测","whetherAdvertising":2,"whetherCollection":2,"whetherPay":1},{"collection":2,"id":21,"releaseTime":1539247754000,"share":0,"source":"砍柴网 ","summary":"滑铁卢大学开设了新的虚拟现实(VR)培训实验室，这将有利于加拿大的下一代的验光师学习如何更快速，更准确地诊断视力问题和眼部疾病。","thumbnail":"https://inews.gtimg.com/newsapp_bt/0/5705462812/1000","title":"加拿大开设了首个VR验光实验室","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2}]
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
         * collection : 0
         * id : 25
         * releaseTime : 1539313463000
         * share : 0
         * source : 单向街书店©
         * summary : 刚刚结束的十一小长假，谷歌还没重返大陆，Google AI 体验展却先它一步去到了“魔都”上海。
         * thumbnail : https://img.huxiucdn.com/article/cover/201805/03/170410209918.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg
         * title : AI 真正可怕的地方在于，它证明了人类不是特别的
         * whetherAdvertising : 2
         * whetherCollection : 2
         * whetherPay : 2
         */

        private int collection;
        private int id;
        private long releaseTime;
        private int share;
        private String source;
        private String summary;
        private String thumbnail;
        private String title;
        private int whetherAdvertising;
        private int whetherCollection;
        private int whetherPay;
        public boolean isAnXinChecked;
        public boolean isSharChecked;
        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

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

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWhetherAdvertising() {
            return whetherAdvertising;
        }

        public void setWhetherAdvertising(int whetherAdvertising) {
            this.whetherAdvertising = whetherAdvertising;
        }

        public int getWhetherCollection() {
            return whetherCollection;
        }

        public void setWhetherCollection(int whetherCollection) {
            this.whetherCollection = whetherCollection;
        }

        public int getWhetherPay() {
            return whetherPay;
        }

        public void setWhetherPay(int whetherPay) {
            this.whetherPay = whetherPay;
        }
    }
}
