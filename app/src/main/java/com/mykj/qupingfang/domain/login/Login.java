package com.mykj.qupingfang.domain.login;

import java.io.Serializable;

/**
 * 关于登录操作的接口JavaBean
 *
 * Created by cx on 2016.11.14
 *
 */
public class Login {
        public String note  ;
        public String status  ;
        public  Data data;

    public Login(String note, String status, Data data) {
        this.note = note;
        this.status = status;
        this.data = data;
    }

    public String getNote() {
        return note;
    }

    public String getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setStatus(String  status) {
        this.status = status;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data implements Serializable  {

            public String color  ;
            public String user_id  ;
            public String textbook_id  ;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getTextbook_id() {
            return textbook_id;
        }

        public void setTextbook_id(String textbook_id) {
            this.textbook_id = textbook_id;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "color='" + color + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", textbook_id='" + textbook_id + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Login{" +
                "note='" + note + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
