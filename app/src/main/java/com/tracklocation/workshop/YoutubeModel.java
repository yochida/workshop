package com.tracklocation.workshop;

import java.util.List;

/**
 * Created by taweesak on 13/9/2561.
 */

public class YoutubeModel {

    private List<ItemsBean> items;

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * icon : https://yt3.ggpht.com/a-/AN66SAz8CoVGQSF0PJft1Vzw5pSHWWPWP_x96V_ITQ=s88-mo-c-c0xffffffff-rj-k-no
         * title : วิธีทำช็อคบอล แบบไม่ใช้เตาอบ ใช้ไมโครเวฟ ทำกินเองที่บ้าน
         * subtitle : ช็อคบอลเป็นขนมที่ใครๆ ต่างก็ชอบกิน เพราะมันทั้งหอม ทั้งหวานจนยั่วน้ำลายขนาดนี้ แถมสีสันของช็อคบอลยังดูน่ากินอีกด้วย
         * youtubeImgage : https://i.ytimg.com/an_webp/EEJRVYiVN1o/mqdefault_6s.webp?du=3000&sqp=CPra59wF&rs=AOn4CLA5pzHMdlZTqmkRzFqFbH-AkZLfOA
         * youtubeID : https://www.youtube.com/watch?v=EEJRVYiVN1o
         */

        private String icon;
        private String title;
        private String subtitle;
        private String youtubeImgage;
        private String youtubeID;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getYoutubeImgage() {
            return youtubeImgage;
        }

        public void setYoutubeImgage(String youtubeImgage) {
            this.youtubeImgage = youtubeImgage;
        }

        public String getYoutubeID() {
            return youtubeID;
        }

        public void setYoutubeID(String youtubeID) {
            this.youtubeID = youtubeID;
        }
    }
}
