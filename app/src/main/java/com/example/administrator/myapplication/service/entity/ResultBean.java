package com.example.administrator.myapplication.service.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/11/1.
 */

public class ResultBean {


    /**
     * error : false
     * results : [{"_id":"5a4362db421aa90fe50c02a9","createdAt":"2017-12-27T17:07:39.802Z","desc":"图解RxJava2(三)","images":["http://img.gank.io/40c7c720-b439-4e77-9b3b-12ce12b6eb6a"],"publishedAt":"2018-01-04T13:45:57.211Z","source":"web","type":"Android","url":"http://rkhcy.github.io/2017/12/22/%E5%9B%BE%E8%A7%A3RxJava2(%E4%B8%89)/","used":true,"who":"HuYounger"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5a4362db421aa90fe50c02a9
         * createdAt : 2017-12-27T17:07:39.802Z
         * desc : 图解RxJava2(三)
         * images : ["http://img.gank.io/40c7c720-b439-4e77-9b3b-12ce12b6eb6a"]
         * publishedAt : 2018-01-04T13:45:57.211Z
         * source : web
         * type : Android
         * url : http://rkhcy.github.io/2017/12/22/%E5%9B%BE%E8%A7%A3RxJava2(%E4%B8%89)/
         * used : true
         * who : HuYounger
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
