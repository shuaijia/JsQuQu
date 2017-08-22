package com.mykj.qupingfang.domain.login;

/**
 * Describtion：
 * Created by kangbai on 2017/8/20.
 * 滴水穿石，非一日之功
 */

public class SanFangLogin {
    // 判断是否成功: 0为不成功 1为成功
    private String status;
    private Data data;
    private String note;

    public class Data {
        // 用户id
        private String user_id;
        // 绑定状态 1不绑定2绑定
        private String state;
        //主题颜色
        private String color;
        public Data(String user_id, String state,String color) {
            super();
            this.user_id = user_id;
            this.state = state;
            this.color=color;
        }
        public String getUserid() {
            return user_id;
        }
        public void setUserid(String user_id) {
            this.user_id = user_id;
        }
        public String getState() {
            return state;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setState(String state) {
            this.state = state;
        }


    }

    public SanFangLogin(String status, Data data, String note) {
        super();
        this.status = status;
        this.data = data;
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
