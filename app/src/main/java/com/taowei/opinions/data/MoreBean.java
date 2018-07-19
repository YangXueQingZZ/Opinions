package com.taowei.opinions.data;

import java.util.List;

public class MoreBean {



    /**
     * data : [{},{},{},{},{"id":74,"pid":50,"type_name":"短视频","order":0,"ico_name":"","pic":null,"update_time":1528887756,"intro":"","num":0,"number":null,"app_name":"抖音短视频","app_packge_name":null,"app_url":"http://www.wandoujia.com/apps/com.ss.android.ugc.aweme/binding?source=web_inner_referral_binded","app_pic":"20180613/76331f8ee29bbd3bbf0d4c4080d05824.png","app_file":"20180613/48a4a205252d6e827dbf09ddda5307e4.apk","app_is_install":null,"app_Introduction":"","app_detail":""},{},{}]
     * message : 查询二级分类成功
     * code : 1
     */

    private String message;
    private String code;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 74
         * pid : 50
         * type_name : 短视频
         * order : 0
         * ico_name :
         * pic : null
         * update_time : 1528887756
         * intro :
         * num : 0
         * number : null
         * app_name : 抖音短视频
         * app_packge_name : null
         * app_url : http://www.wandoujia.com/apps/com.ss.android.ugc.aweme/binding?source=web_inner_referral_binded
         * app_pic : 20180613/76331f8ee29bbd3bbf0d4c4080d05824.png
         * app_file : 20180613/48a4a205252d6e827dbf09ddda5307e4.apk
         * app_is_install : null
         * app_Introduction :
         * app_detail :
         */

        private int id;
        private int pid;
        private String type_name;
        private int order;
        private String ico_name;
        private Object pic;
        private int update_time;
        private String intro;
        private int num;
        private Object number;
        private String app_name;
        private String app_packge_name;
        private String app_url;
        private String app_pic;
        private String app_file;
        private int app_is_install;
        private String app_Introduction;
        private String app_detail;

        boolean isInstaill;


        public boolean isSelected = false;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getIco_name() {
            return ico_name;
        }

        public void setIco_name(String ico_name) {
            this.ico_name = ico_name;
        }

        public Object getPic() {
            return pic;
        }

        public void setPic(Object pic) {
            this.pic = pic;
        }

        public int getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public Object getNumber() {
            return number;
        }

        public void setNumber(Object number) {
            this.number = number;
        }

        public String getApp_name() {
            return app_name;
        }

        public void setApp_name(String app_name) {
            this.app_name = app_name;
        }

        public String getApp_packge_name() {
            return app_packge_name;
        }

        public void setApp_packge_name(String app_packge_name) {
            this.app_packge_name = app_packge_name;
        }

        public String getApp_url() {
            return app_url;
        }

        public void setApp_url(String app_url) {
            this.app_url = app_url;
        }

        public String getApp_pic() {
            return app_pic;
        }

        public void setApp_pic(String app_pic) {
            this.app_pic = app_pic;
        }

        public String getApp_file() {
            return app_file;
        }

        public void setApp_file(String app_file) {
            this.app_file = app_file;
        }

        public int getApp_is_install() {
            return app_is_install;
        }

        public void setApp_is_install(int app_is_install) {
            this.app_is_install = app_is_install;
        }

        public String getApp_Introduction() {
            return app_Introduction;
        }

        public void setApp_Introduction(String app_Introduction) {
            this.app_Introduction = app_Introduction;
        }

        public String getApp_detail() {
            return app_detail;
        }

        public void setApp_detail(String app_detail) {
            this.app_detail = app_detail;
        }
    }
}
