package com.taowei.opinions.data;


import java.util.List;

// 栏目
public class ColumnBean {


    /**
     * code : 1
     * data : [{"id":1,"item_name":"新闻","number":99999996,"url":"http://www.taobao.com","url_name":"淘宝"},{"id":2,"item_name":"视频","number":88888886,"url":"http://v.qq.com","url_name":"腾讯"},{"id":3,"item_name":"读书","number":11},{"id":14,"item_name":"测试","number":8,"url":"https://www.sohu.com/","url_name":"一点资讯"},{"id":5,"item_name":"漫画","number":5,"url":" http://www.baidu.com","url_name":"豆瓣读书"},{"id":4,"item_name":"购物","number":2,"url":"https://sports.sina.com.cn","url_name":"sohu"}]
     * data2 : [{"id":3,"item_name":"读书","number":11},{"id":14,"item_name":"测试","number":8,"url":"https://www.sohu.com/","url_name":"一点资讯"},{"id":5,"item_name":"漫画","number":5,"url":" http://www.baidu.com","url_name":"豆瓣读书"},{"id":4,"item_name":"购物","number":2,"url":"https://sports.sina.com.cn","url_name":"sohu"}]
     * message : 查询成功
     */

    private String code;
    private String message;
    private List<DataBean> data;
    private List<Data2Bean> data2;

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

    public List<Data2Bean> getData2() {
        return data2;
    }

    public void setData2(List<Data2Bean> data2) {
        this.data2 = data2;
    }

    public static class DataBean {
        /**
         * id : 1
         * item_name : 新闻
         * number : 99999996
         * url : http://www.taobao.com
         * url_name : 淘宝
         */

        private int id;
        private String item_name;
        private int number;
        private String url;
        private String url_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl_name() {
            return url_name;
        }

        public void setUrl_name(String url_name) {
            this.url_name = url_name;
        }
    }

    public static class Data2Bean {
        /**
         * id : 3
         * item_name : 读书
         * number : 11
         * url : https://www.sohu.com/
         * url_name : 一点资讯
         */

        private int id;
        private String item_name;
        private int number;
        private String url;
        private String url_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl_name() {
            return url_name;
        }

        public void setUrl_name(String url_name) {
            this.url_name = url_name;
        }
    }
}
