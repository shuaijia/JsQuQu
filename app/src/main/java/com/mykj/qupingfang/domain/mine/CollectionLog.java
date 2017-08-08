package com.mykj.qupingfang.domain.mine;

import java.util.List;

/**
 * Describtion:收藏记录
 * Created by jia on 2017/8/8.
 * 人之所以能，是相信能
 */
public class CollectionLog {
    private String status;
    //	"data": [{
    private List<Data> data;
    public class Data {

        private Resource resource;
        private View1 view;

        public class Resource{
            //			"id": 300,
            private String resource_id;
            //			"letv_id": "aa3cf1e09ef3ed2b08d293029d5adbb7_a",
            private String bl_id;
            //			"duration": "00:05:50",
            private String duration;
            //			"pid": 0,
            private String pid;
            //			"price": "1.00",
            private String price;
            //			"img_url": "/Uploads/Picture/2015-09-29/560a340fc8b97.jpg",
            private String img_url;
            //			"title": "草虫的村落",
            private String title;
            //			"type": 1,
            private String type;
            //			"url": "0",
            private String url;
            //			"grade": 6,
            private String grade;
            //			"term": 1,
            private String term;
            //			"course": 1,
            private String course;
            //			"descp": " 作者采用丰富的想象、拟人和比喻的修辞手法表达了对草虫和大自然的喜爱之情，以奇异的想象，追随着一只爬行的小虫，对草虫的村落进行了一次奇异的游历，从中反映了作者对大自然、对小生物的喜爱之情， 生动形象地写出了昆虫们生活的情景。\r\n 本篇文章采用淡彩画的表现方式，所谓淡彩画法，就是先用铅笔等工具画出对象的轮廓或画出明暗，然后再画上薄而透明的水彩。淡彩画法分为：铅笔淡彩、钢笔淡彩、炭笔淡彩，本篇文章属于钢笔淡彩，对绘画基础要求较高。画面中对各种草虫的刻画入微，每一只小虫都有自己独特的表情和特征，特别是对草虫颜色的刻画非常丰富，斑斓多彩。\r\n 郭枫 ，原名郭少鸣，1933年生于江苏徐州。台湾著名诗人，散文家，文学评论家和出版家，也是一位民族意识和传统意识强烈的作家。",
            private String descp;
            //			"avg_score": "0.90910",
            private String avg_score;
            //			"avg_score_count": 3,
            private String avg_score_count;
            //			"weight": 0,
            private String weight;
            //			"is_recommend": 1,
            private String is_recommend;
            //			"shares": 22,
            private String shares;
            //			"views": 0,
            private String views;
            //			"status": 1,
            private String status;
            //			"ctime": 1454491214,
            private String ctime;
            //			"update_time": 1459341345
            private String update_time;

            public String getResource_id() {
                return resource_id;
            }
            public void setResource_id(String resource_id) {
                this.resource_id = resource_id;
            }
            public String getBl_id() {
                return bl_id;
            }
            public void setBl_id(String bl_id) {
                this.bl_id = bl_id;
            }
            public String getDuration() {
                return duration;
            }
            public void setDuration(String duration) {
                this.duration = duration;
            }
            public String getPid() {
                return pid;
            }
            public void setPid(String pid) {
                this.pid = pid;
            }
            public String getPrice() {
                return price;
            }
            public void setPrice(String price) {
                this.price = price;
            }
            public String getImg_url() {
                return img_url;
            }
            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
            public String getTitle() {
                return title;
            }
            public void setTitle(String title) {
                this.title = title;
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
            public String getGrade() {
                return grade;
            }
            public void setGrade(String grade) {
                this.grade = grade;
            }
            public String getTerm() {
                return term;
            }
            public void setTerm(String term) {
                this.term = term;
            }
            public String getCourse() {
                return course;
            }
            public void setCourse(String course) {
                this.course = course;
            }
            public String getDescp() {
                return descp;
            }
            public void setDescp(String descp) {
                this.descp = descp;
            }
            public String getAvg_score() {
                return avg_score;
            }
            public void setAvg_score(String avg_score) {
                this.avg_score = avg_score;
            }
            public String getAvg_score_count() {
                return avg_score_count;
            }
            public void setAvg_score_count(String avg_score_count) {
                this.avg_score_count = avg_score_count;
            }
            public String getWeight() {
                return weight;
            }
            public void setWeight(String weight) {
                this.weight = weight;
            }
            public String getIs_recommend() {
                return is_recommend;
            }
            public void setIs_recommend(String is_recommend) {
                this.is_recommend = is_recommend;
            }
            public String getShares() {
                return shares;
            }
            public void setShares(String shares) {
                this.shares = shares;
            }
            public String getViews() {
                return views;
            }
            public void setViews(String views) {
                this.views = views;
            }
            public String getStatus() {
                return status;
            }
            public void setStatus(String status) {
                this.status = status;
            }
            public String getCtime() {
                return ctime;
            }
            public void setCtime(String ctime) {
                this.ctime = ctime;
            }
            public String getUpdate_time() {
                return update_time;
            }
            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }


        }
        public class View1{
            //			"id": 647,
            private String id;
            //			"user_id": 195,
            private String user_id;
            //			"resource_id": 300,
            private String resource_id;
            //			"status": 1,
            private String status;
            //			"ctime": 1459823525,
            private String ctime;
            //			"update_time": 1459823525
            private String update_time;
            public String getId() {
                return id;
            }
            public void setId(String id) {
                this.id = id;
            }
            public String getUser_id() {
                return user_id;
            }
            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }
            public String getResource_id() {
                return resource_id;
            }
            public void setResource_id(String resource_id) {
                this.resource_id = resource_id;
            }
            public String getStatus() {
                return status;
            }
            public void setStatus(String status) {
                this.status = status;
            }
            public String getCtime() {
                return ctime;
            }
            public void setCtime(String ctime) {
                this.ctime = ctime;
            }
            public String getUpdate_time() {
                return update_time;
            }
            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }


        }
        public Resource getResource() {
            return resource;
        }
        public void setResource(Resource resource) {
            this.resource = resource;
        }
        public View1 getView() {
            return view;
        }
        public void setView(View1 view) {
            this.view = view;
        }


    }
//		"resource": {

//		},
//		"view": {

    //		}
//	}],
//	"note": "成功"
    private String note;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
