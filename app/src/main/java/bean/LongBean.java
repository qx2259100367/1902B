package bean;

import java.util.List;

/**
 * Created by ASUS on 2019/9/9.
 */

public class LongBean {

    private List<CommentsBean> comments;

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        /**
         * author : 橙蓝蓝
         * content : 在知乎搜“知乎日报”就行，知乎晚报是“知乎日报”公众号的专栏
         * avatar : http://pic3.zhimg.com/v2-0645933ec0498464793d4677f53304be_im.jpg
         * time : 1567733425
         * reply_to : {"content":"港真 ，你们去app看了吗，为什么我的app输入\u201c知乎晚报\u201d是无结果啊！你们真见过晚报？","status":0,"id":33374059,"author":"西米露20120816"}
         * id : 33374188
         * likes : 1
         */

        private String author;
        private String content;
        private String avatar;
        private int time;
        private ReplyToBean reply_to;
        private int id;
        private int likes;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public ReplyToBean getReply_to() {
            return reply_to;
        }

        public void setReply_to(ReplyToBean reply_to) {
            this.reply_to = reply_to;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public static class ReplyToBean {
            /**
             * content : 港真 ，你们去app看了吗，为什么我的app输入“知乎晚报”是无结果啊！你们真见过晚报？
             * status : 0
             * id : 33374059
             * author : 西米露20120816
             */

            private String content;
            private int status;
            private int id;
            private String author;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }
        }
    }
}
