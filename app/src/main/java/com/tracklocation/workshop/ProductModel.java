package com.tracklocation.workshop;

import java.util.List;

/**
 * Created by taweesak on 18/9/2561.
 */

public class ProductModel {


    /**
     * items : [{"f_id":1,"f_img":"https://nlovecooking.com/wp-content/uploads/2012/03/fried-stir-blasi-chicken-FB.png","f_name":"ผัดกระเพรา","f_desc":"ผัดกระเพราอร่อยมาก","f_price":50,"f_likeCount":3,"f_like":0},{"f_id":2,"f_img":"https://media.foody.co.th/res/g21/207805/prof/s/foody-upload-api-foody-mobile--11-jpg-171108145548.jpg","f_name":"ข้าวผัด","f_desc":"ข้าวผัดอร่อยมาก","f_price":60,"f_likeCount":1,"f_like":0},{"f_id":3,"f_img":"https://food.mthai.com/app/uploads/2017/12/Stir-Fried-Kale-with-Crispy-Pork.jpg","f_name":"คะน้าหมูกรอบ","f_desc":"คะน้าหมูกรอบอร่อยยิ่งนักๆ","f_price":55,"f_likeCount":2,"f_like":0},{"f_id":4,"f_img":"https://scm-assets.constant.co/scm/unilever/e9dc924f238fa6cc29465942875fe8f0/d64fdd31-632f-485e-bda3-429c9efc7ec5.jpg","f_name":"สปาเก็ตตี้","f_desc":"ทำเองง่ายๆแต่ขายได้","f_price":70,"f_likeCount":1,"f_like":0},{"f_id":5,"f_img":"https://scm-assets.constant.co/scm/unilever/e9dc924f238fa6cc29465942875fe8f0/7bc4930a-6865-4e4d-a505-a9fd0b3b3674.jpg","f_name":"ต้มยำกุ้ง","f_desc":"ต้มยำกุ้งน้ำข้นอร่อยจริงๆ","f_price":120,"f_likeCount":0,"f_like":0},{"f_id":6,"f_img":"https://food.mthai.com/app/uploads/2017/03/Pork-with-Lemon-spicy-sweet-sauce.jpg","f_name":"หมูมะนาว","f_desc":"กินง่ายในปารตี้","f_price":80,"f_likeCount":0,"f_like":0},{"f_id":7,"f_img":"https://i.ytimg.com/vi/GtO8GChLWK4/hqdefault.jpg","f_name":"สเต็ก","f_desc":"เนื้อเน้น","f_price":49,"f_likeCount":0,"f_like":0},{"f_id":8,"f_img":"https://www.kandafoodshop.com/wp-content/uploads/2016/11/%E0%B8%AA%E0%B8%A5%E0%B8%B1%E0%B8%94%E0%B8%8B%E0%B8%B5%E0%B8%9F%E0%B8%B9%E0%B8%94%E0%B9%8C.jpg","f_name":"สลัด","f_desc":"ผักอร่อยๆพร้อมน้ำสลัด","f_price":60,"f_likeCount":0,"f_like":0},{"f_id":9,"f_img":"https://www.cpbrandsite.com/contents/recipe/8dpmw5ur6bylki6upcejad7csomnygnifnxwusnv.jpg","f_name":"เคบับ","f_desc":"แป้งเน้นพร้อมกิน","f_price":59,"f_likeCount":0,"f_like":0},{"f_id":10,"f_img":"https://ed.files-media.com/static-cache/resize-cache/790x528/ud/images/1/135/404266/KTRAYTY-3_Cover.jpg","f_name":"ก๋วยเตี๋ยวน้ำตก","f_desc":"สุดยอดความอร่อยจริงๆ","f_price":80,"f_likeCount":0,"f_like":0}]
     * username : No Input Username
     */

    private String username;
    private List<ItemsBean> items;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * f_id : 1
         * f_img : https://nlovecooking.com/wp-content/uploads/2012/03/fried-stir-blasi-chicken-FB.png
         * f_name : ผัดกระเพรา
         * f_desc : ผัดกระเพราอร่อยมาก
         * f_price : 50
         * f_likeCount : 3
         * f_like : 0
         */

        private int f_id;
        private String f_img;
        private String f_name;
        private String f_desc;
        private int f_price;
        private int f_likeCount;
        private int f_like;

        public int getF_id() {
            return f_id;
        }

        public void setF_id(int f_id) {
            this.f_id = f_id;
        }

        public String getF_img() {
            return f_img;
        }

        public void setF_img(String f_img) {
            this.f_img = f_img;
        }

        public String getF_name() {
            return f_name;
        }

        public void setF_name(String f_name) {
            this.f_name = f_name;
        }

        public String getF_desc() {
            return f_desc;
        }

        public void setF_desc(String f_desc) {
            this.f_desc = f_desc;
        }

        public int getF_price() {
            return f_price;
        }

        public void setF_price(int f_price) {
            this.f_price = f_price;
        }

        public int getF_likeCount() {
            return f_likeCount;
        }

        public void setF_likeCount(int f_likeCount) {
            this.f_likeCount = f_likeCount;
        }

        public int getF_like() {
            return f_like;
        }

        public void setF_like(int f_like) {
            this.f_like = f_like;
        }
    }
}

