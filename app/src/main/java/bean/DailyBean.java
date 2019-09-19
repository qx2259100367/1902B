package bean;

import java.util.List;

/**
 * Created by ASUS on 2019/9/8.
 */

public class DailyBean {

    /**
     * date : 20190908
     * stories : [{"images":["https://pic3.zhimg.com/v2-23326ab915cbbfc6d8b3a494f747a766.jpg"],"type":0,"id":9714967,"ga_prefix":"090809","title":"机械行业熬下去真的就是铁饭碗，就越老越吃香么？"},{"images":["https://pic3.zhimg.com/v2-5fde4d5c3e5a4cad4fb77945d94d512e.jpg"],"type":0,"id":9714982,"ga_prefix":"090807","title":"苹果研发投入创新高 ，但为何又给用户创新不足的感觉？"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-8a2aafdcc8815d9fcbe667a651705e3d.jpg","type":0,"id":9714945,"ga_prefix":"090609","title":"新中国体育史上，有哪些比赛的瞬间令你心潮澎湃？"},{"image":"https://pic1.zhimg.com/v2-939f9cb29d0f59b5cfde39f5a47cc158.jpg","type":0,"id":9714934,"ga_prefix":"090607","title":"沉重的底色与扭曲的方向\u2014\u2014香港修例风波背后的一些社会深层根源"},{"image":"https://pic1.zhimg.com/v2-0281a6f0f911d2254b1077ba64af05bc.jpg","type":0,"id":9714900,"ga_prefix":"090407","title":"家庭年入百万+，有必要告诉孩子我们家的收入吗？"},{"image":"https://pic4.zhimg.com/v2-8b0b44dd2df4ea75a2cf8c1a94006673.jpg","type":0,"id":9714837,"ga_prefix":"090309","title":"农夫山泉一年卖那么多，长白山不会枯竭吗？"},{"image":"https://pic4.zhimg.com/v2-9326d71b99346b8d1936b7b0e2923e63.jpg","type":0,"id":9714689,"ga_prefix":"082620","title":"如何用一天，看尽新中国的 70 年？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic3.zhimg.com/v2-23326ab915cbbfc6d8b3a494f747a766.jpg"]
         * type : 0
         * id : 9714967
         * ga_prefix : 090809
         * title : 机械行业熬下去真的就是铁饭碗，就越老越吃香么？
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic2.zhimg.com/v2-8a2aafdcc8815d9fcbe667a651705e3d.jpg
         * type : 0
         * id : 9714945
         * ga_prefix : 090609
         * title : 新中国体育史上，有哪些比赛的瞬间令你心潮澎湃？
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
