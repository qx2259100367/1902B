package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.geeknews.R;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import bean.DailyBean;
import util.DateUtil;

/**
 * Created by ASUS on 2019/9/8.
 */

public class RlvDailyadapter extends RecyclerView.Adapter {
    private final Context context;
    private final ArrayList<DailyBean.TopStoriesBean> bannerlist;
    private final ArrayList<DailyBean.StoriesBean> newslist;
    private String date;
    private static final int TYPE_BANNER = 0;
    private static final int TYPE_TIME = 1;
    private static final int TYPE_NEWS = 2;

    public RlvDailyadapter(Context context, ArrayList<DailyBean.TopStoriesBean> bannerlist,
                           ArrayList<DailyBean.StoriesBean> newslist, String date) {
        this.context = context;
        this.bannerlist = bannerlist;
        this.newslist = newslist;
        this.date = date;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
            return new Vhbanner(view);
        } else if (viewType == TYPE_TIME) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_time, null);
            return new Vhtime(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.itemnews, null);
            return new Vhnews(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        int type = holder.getItemViewType();
        if (type==TYPE_BANNER){
            Vhbanner vhbanner= (Vhbanner) holder;
            vhbanner.mBanner.setImages(bannerlist)
                    .setImageLoader(
                            new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            //path 的类型是mBannerList 的泛型
                            DailyBean.TopStoriesBean imgs= (DailyBean.TopStoriesBean) path;
                            Glide.with(context).load(imgs.getImage()).into(imageView);
                        }
                    }).start();
        }else if (type==TYPE_TIME){
                Vhtime vhtime= (Vhtime) holder;
            //判断是否需是当天,如果是,显示今日新闻,不是显示日期
            String currentTime = DateUtil.getCurrentTime();
            //"A".equals("a") = false(区分大小写),"A".equalsIgnoreCase("a") = true,(比较不去分大小写)
            if (currentTime.equalsIgnoreCase(date)){   //显示的和当前为同一天
                    vhtime.mTv.setText("今日新闻");
            }else {
                vhtime.mTv.setText(date);
            }
        }else {      //新闻
            ///假设mNewsList 有三条数据:0,1,2
            //position : 如果有banner,从2,3,4,如果这样取数据,直接索引越界了,IndexOutOfBoundsException
          int  newsposition=position-1;   //先将时间的减去  因为时间是必定存在的
          if (bannerlist.size()>0){    //如果banner也存在
              newsposition-=1;    //减去banner那一条
          }

            DailyBean.StoriesBean storiesBean = newslist.get(newsposition);
          Vhnews vhnews= (Vhnews) holder;
          Glide.with(context).load(storiesBean.getImages().get(0)).into(vhnews.mNewsimg);
          vhnews.mNewstv.setText(storiesBean.getTitle());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailyOnclic.MyDailyOnclic(position);
            }
        });


        if (bannerlist!=null && bannerlist.size()>0&&position==0){
            Vhbanner vhbanner= (Vhbanner) holder;
             vhbanner.mBanner.setOnBannerListener(new OnBannerListener() {
                 @Override
                 public void OnBannerClick(int position) {
                        brOnclic.MyBrOnclic(position);
                 }
             });
        }
    }

    @Override
    public int getItemCount() {
        //条目的种类 3种:banner,日期,新闻条目
        //如果有banner : 1(banner) + 1(日期) + mNewslist.size()(新闻条数)
        //如果没有banner :  1(日期) + mNewslist.size()(新闻条数)
        if (bannerlist.size() > 0) {   //显示轮播图
            return newslist.size() + 1 + 1;    //返回新闻的条目加上时间和轮播图的
        } else {
            return newslist.size() + 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        /**此recycler有三种布局
         * 1.当解析数据内有轮播图时要显示banner
         * 2.每次必显示时间
         * 3.显示新闻
         * */
        if (bannerlist.size() > 0) {   //如果轮播图集合不为空  要显示banner
            if (position == 0) {
                return TYPE_BANNER;  //banner
            } else if (position == 1) {    //时间
                return TYPE_TIME;
            } else {
                return TYPE_NEWS;   //新闻
            }
        } else {    //当轮播图集合没有数据时  要取消轮播图他
            if (position == 0) {
                return TYPE_TIME;  //显示时间
            } else {
                return TYPE_NEWS;  //显示新闻
            }

        }

    }
    //banner
    class Vhbanner extends RecyclerView.ViewHolder {
        Banner mBanner;

        public Vhbanner(View itemView) {
            super(itemView);
            this.mBanner = (Banner) itemView.findViewById(R.id.banner);
        }
    }
    //time时间
    class Vhtime extends RecyclerView.ViewHolder {
        TextView mTv;

        public Vhtime(View itemView) {
            super(itemView);
            this.mTv = (TextView) itemView.findViewById(R.id.tv);

        }
    }
    //news
    class Vhnews extends RecyclerView.ViewHolder {
        ImageView mNewsimg;
        TextView mNewstv;
        public Vhnews(View itemView) {
            super(itemView);
            this.mNewsimg = (ImageView) itemView.findViewById(R.id.newsimg);
            this.mNewstv = (TextView) itemView.findViewById(R.id.newstv);
        }
    }

    public interface DailyOnclic{
        void MyDailyOnclic(int i);
    }
    DailyOnclic dailyOnclic;

    public void setDailyOnclic(DailyOnclic dailyOnclic) {
        this.dailyOnclic = dailyOnclic;
    }
    public interface BrOnclic{
        void MyBrOnclic(int i);
    }
    BrOnclic brOnclic;

    public void setBrOnclic(BrOnclic brOnclic) {
        this.brOnclic = brOnclic;
    }
}
