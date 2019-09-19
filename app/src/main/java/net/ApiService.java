package net;

import bean.DailyBean;
import bean.FuliBean;
import bean.HotBean;
import bean.HotSonBean;
import bean.HotnumBean;
import bean.LongBean;
import bean.SectionBean;
import bean.TalkBean;
import bean.WxtBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ASUS on 2019/9/6.
 */

public interface ApiService {
    String FULI="http://gank.io/api/";
    @GET("data/%E7%A6%8F%E5%88%A9/20/1")
    Observable<FuliBean> fulis();

    //http://news-at.zhihu.com/api/4/sections
    String SCETION="http://news-at.zhihu.com/api/4/";
    @GET("sections")
    Observable<SectionBean> section();

   // http://news-at.zhihu.com/api/4/section/1

    String TALK="http://news-at.zhihu.com/api/4/";
      @GET("section/{num}")
    Observable<TalkBean> talk(@Path("num") int num);

      String HOT="http://news-at.zhihu.com/api/4/";
      @GET("news/hot")
    Observable<HotBean> hot();


      String HOTSON="http://news-at.zhihu.com/api/4/";
              @GET("news/{id}")
    Observable<HotSonBean> hotson(@Path("id") int id);


              String DAILY="http://news-at.zhihu.com/api/4/";
              @GET("news/latest")
    Observable<DailyBean> daily();

              //http://news-at.zhihu.com/api/4/news/before/{date}
    String OLDDATE="http://news-at.zhihu.com/api/4/";
        @GET("news/before/{date}")
    Observable<DailyBean> olddate(@Path("date") String date);


        @GET("story-extra/{news_id}")
        Observable<HotnumBean> hotnum(@Path("news_id") int news_id);

    @GET("story/{news_id}/long-comments")
    Observable<LongBean> longbean(@Path("news_id") String news_id);

    @GET("story/{news_id}/short-comments")
    Observable<LongBean> shortbean(@Path("news_id") String news_id);


    //http://api.tianapi.com/wxnew/?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=2
    String WXT="http://api.tianapi.com/";
        //wxnew/?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=2
    @GET("wxnew/")
    Observable<WxtBean> wxt(@Query("key") String key,@Query("num") int num,@Query("page") int page);


    @GET("wxnew/")
    Observable<WxtBean> quey(@Query("key") String key,@Query("num") int num,@Query("page") int page,@Query("word") String str);
}
