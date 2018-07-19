package com.taowei.opinions.data;

import java.util.List;

public class AppBean {


    /**
     * data : [{"id":11,"goods_name":"淘宝","goods_type":51,"goods_type2":78,"goods_type3":null,"detail":null,"pic":"20180613/c9fe6ecdb8930843f2f8844bf06da19c.png","file":null,"goods_status":2,"update_time":1530620178,"is_logistics":0,"log_company":0,"best_status":0,"pub_admin_uid":1,"pub_admin_username":"admin","ceng_goods_name":"","version":"5.1","Introduction":"","link":"http://download.taobaocdn.com/wireless/taobao4android/latest/701483.apk","bundle":null,"ico_base64":null,"number":null,"packge_name":"com.taobao.taobao","score":"020.00","app_is_install":0},{"id":17,"goods_name":"拼多多","goods_type":51,"goods_type2":78,"goods_type3":null,"detail":null,"pic":"20180621/531d729fc9fee039daae9e40f42578b6.jpg","file":null,"goods_status":2,"update_time":1530621620,"is_logistics":0,"log_company":0,"best_status":1,"pub_admin_uid":1,"pub_admin_username":"admin","ceng_goods_name":"","version":"","Introduction":"","link":"http://pinduoduoimg.yangkeduo.com/android/adv/pinduoduo_gw_pc_latest.apk","bundle":null,"ico_base64":null,"number":null,"packge_name":"com.xunmeng.pinduoduo","score":"011.00","app_is_install":0},{"id":18,"goods_name":"苏宁易购","goods_type":51,"goods_type2":78,"goods_type3":null,"detail":null,"pic":"20180627/d789767723ba0184eb4bdb0c0ceea67d.png","file":null,"goods_status":2,"update_time":1530062832,"is_logistics":0,"log_company":0,"best_status":0,"pub_admin_uid":1,"pub_admin_username":"admin","ceng_goods_name":"","version":"","Introduction":"","link":"http://f2.market.xiaomi.com/download/AppStore/0791c40328ea58d139d0d67c62c24731b0e43afc8/com.suning.mobile.ebuy.apk","bundle":null,"ico_base64":null,"number":null,"packge_name":"com.suning.mobile.ebuy","score":"000.00","app_is_install":0},{"id":19,"goods_name":"国美电器","goods_type":51,"goods_type2":78,"goods_type3":null,"detail":null,"pic":"20180621/ff4eac554606a8747e431b3383b4dc3c.png","file":null,"goods_status":2,"update_time":1529563857,"is_logistics":0,"log_company":0,"best_status":0,"pub_admin_uid":1,"pub_admin_username":"admin","ceng_goods_name":"","version":"","Introduction":"","link":"http://f5.market.xiaomi.com/download/AppStore/090244b02dbe22ba530addfbad7f9a3462542ec4b/com.gome.eshopnew.apk","bundle":null,"ico_base64":null,"number":null,"packge_name":"com.gome.eshopnew","score":"000.00","app_is_install":0},{"id":23,"goods_name":"唯品会","goods_type":51,"goods_type2":78,"goods_type3":null,"detail":null,"pic":"20180627/e6341bb77181d20478ce1bba6f66ef9d.png","file":null,"goods_status":2,"update_time":1530845428,"is_logistics":0,"log_company":0,"best_status":1,"pub_admin_uid":1,"pub_admin_username":"admin","ceng_goods_name":"","version":"","Introduction":"唯品会，全球精选，正品特卖！  数千名专业买手精选品牌商品，提供1折起的深度折扣，有了唯品会，从此再也不用担心买不起正品大牌啦！","link":"http://dda.5lpx.com/download/shop_android_003f6e68da3a48eb6e2bdd37254b45cf_6.20.4.apk","bundle":null,"ico_base64":null,"number":null,"packge_name":"com.achievo.vipshop","score":"000.00","app_is_install":0}]
     * message : 二级栏目app获取成功
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
         * id : 11
         * goods_name : 淘宝
         * goods_type : 51
         * goods_type2 : 78
         * goods_type3 : null
         * detail : null
         * pic : 20180613/c9fe6ecdb8930843f2f8844bf06da19c.png
         * file : null
         * goods_status : 2
         * update_time : 1530620178
         * is_logistics : 0
         * log_company : 0
         * best_status : 0
         * pub_admin_uid : 1
         * pub_admin_username : admin
         * ceng_goods_name :
         * version : 5.1
         * Introduction :
         * link : http://download.taobaocdn.com/wireless/taobao4android/latest/701483.apk
         * bundle : null
         * ico_base64 : null
         * number : null
         * packge_name : com.taobao.taobao
         * score : 020.00
         * app_is_install : 0
         */

        private int id;
        private String goods_name;
        private int goods_type;
        private int goods_type2;
        private Object goods_type3;
        private Object detail;
        private String pic;
        private String file;
        private int goods_status;
        private int update_time;
        private int is_logistics;
        private int log_company;
        private int best_status;
        private int pub_admin_uid;
        private String pub_admin_username;
        private String ceng_goods_name;
        private String version;
        private String Introduction;
        private String link;
        private Object bundle;
        private Object ico_base64;
        private Object number;
        private String packge_name;
        private String score;
        private int app_is_install;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public int getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(int goods_type) {
            this.goods_type = goods_type;
        }

        public int getGoods_type2() {
            return goods_type2;
        }

        public void setGoods_type2(int goods_type2) {
            this.goods_type2 = goods_type2;
        }

        public Object getGoods_type3() {
            return goods_type3;
        }

        public void setGoods_type3(Object goods_type3) {
            this.goods_type3 = goods_type3;
        }

        public Object getDetail() {
            return detail;
        }

        public void setDetail(Object detail) {
            this.detail = detail;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public int getGoods_status() {
            return goods_status;
        }

        public void setGoods_status(int goods_status) {
            this.goods_status = goods_status;
        }

        public int getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public int getIs_logistics() {
            return is_logistics;
        }

        public void setIs_logistics(int is_logistics) {
            this.is_logistics = is_logistics;
        }

        public int getLog_company() {
            return log_company;
        }

        public void setLog_company(int log_company) {
            this.log_company = log_company;
        }

        public int getBest_status() {
            return best_status;
        }

        public void setBest_status(int best_status) {
            this.best_status = best_status;
        }

        public int getPub_admin_uid() {
            return pub_admin_uid;
        }

        public void setPub_admin_uid(int pub_admin_uid) {
            this.pub_admin_uid = pub_admin_uid;
        }

        public String getPub_admin_username() {
            return pub_admin_username;
        }

        public void setPub_admin_username(String pub_admin_username) {
            this.pub_admin_username = pub_admin_username;
        }

        public String getCeng_goods_name() {
            return ceng_goods_name;
        }

        public void setCeng_goods_name(String ceng_goods_name) {
            this.ceng_goods_name = ceng_goods_name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getIntroduction() {
            return Introduction;
        }

        public void setIntroduction(String Introduction) {
            this.Introduction = Introduction;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Object getBundle() {
            return bundle;
        }

        public void setBundle(Object bundle) {
            this.bundle = bundle;
        }

        public Object getIco_base64() {
            return ico_base64;
        }

        public void setIco_base64(Object ico_base64) {
            this.ico_base64 = ico_base64;
        }

        public Object getNumber() {
            return number;
        }

        public void setNumber(Object number) {
            this.number = number;
        }

        public String getPackge_name() {
            return packge_name;
        }

        public void setPackge_name(String packge_name) {
            this.packge_name = packge_name;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public int getApp_is_install() {
            return app_is_install;
        }

        public void setApp_is_install(int app_is_install) {
            this.app_is_install = app_is_install;
        }
    }
}
