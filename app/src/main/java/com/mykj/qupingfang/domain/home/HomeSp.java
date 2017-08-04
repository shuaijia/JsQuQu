package com.mykj.qupingfang.domain.home;

import java.util.List;

/**
 * Describtion：
 * Created by kangbai on 2017/7/29.
 * 滴水穿石，非一日之功
 */

public class HomeSp {
    /**
     * status : 1
     * data : {"feature":[{"id":"15","img_name":"讲故事","img_curl":"http://test.lovek12.com/Uploads/Picture/2016-04-20/57172e34bbf43.jpg","jump_url":"http://api.lovek12.com/index.php?r=discovery/story&v=190"},{"id":"16","img_name":"绘画的秘密","img_curl":"http://test.lovek12.com/Uploads/Picture/2016-09-07/huihuademimi_zt.png","jump_url":"http://api.lovek12.com/index.php?r=discovery/picture-book-secret&v=190"},{"id":"17","img_name":"天天练","img_curl":"http://test.lovek12.com/Uploads/Picture/2016-04-20/5717286f2bf4css.jpg","jump_url":"http://api.lovek12.com./index.php?r=discovery%2Fclass"}]}
     * note : 成功
     */

    private int status;
    private DataBean data;
    private String note;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public static class DataBean {
        private List<FeatureBean> feature;

        public List<FeatureBean> getFeature() {
            return feature;
        }

        public void setFeature(List<FeatureBean> feature) {
            this.feature = feature;
        }

        public static class FeatureBean {
            /**
             * id : 15
             * img_name : 讲故事
             * img_curl : http://test.lovek12.com/Uploads/Picture/2016-04-20/57172e34bbf43.jpg
             * jump_url : http://api.lovek12.com/index.php?r=discovery/story&v=190
             */

            private String id;
            private String img_name;
            private String img_curl;
            private String jump_url;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImg_name() {
                return img_name;
            }

            public void setImg_name(String img_name) {
                this.img_name = img_name;
            }

            public String getImg_curl() {
                return img_curl;
            }

            public void setImg_curl(String img_curl) {
                this.img_curl = img_curl;
            }

            public String getJump_url() {
                return jump_url;
            }

            public void setJump_url(String jump_url) {
                this.jump_url = jump_url;
            }
        }
    }
}
