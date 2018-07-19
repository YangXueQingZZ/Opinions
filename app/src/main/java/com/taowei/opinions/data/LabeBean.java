package com.taowei.opinions.data;

import java.util.List;

public class LabeBean {


    /**
     * code : 1
     * data : [{"ico_name":"fa-ban","id":13,"order":0,"pid":0,"type_name":"视频"},{"ico_name":"fa-bookmark","id":19,"order":0,"pid":0,"type_name":"小说"},{"ico_name":"fa-beer","id":22,"order":0,"pid":0,"type_name":"导航"},{"ico_name":"fa-bolt","id":26,"order":0,"pid":0,"type_name":"新闻"}]
     * message : 成功
     */

    private String code;
    private String message;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ico_name : fa-ban
         * id : 13
         * order : 0
         * pid : 0
         * type_name : 视频
         */

        private String ico_name;
        private int id;
        private int order;
        private int pid;
        private String type_name;

        public String getIco_name() {
            return ico_name;
        }

        public void setIco_name(String ico_name) {
            this.ico_name = ico_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }
    }
}
