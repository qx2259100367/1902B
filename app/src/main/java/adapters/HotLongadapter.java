package adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.geeknews.R;

import java.util.ArrayList;

import bean.LongBean;

/**
 * Created by ASUS on 2019/9/9.
 */

public class HotLongadapter extends RecyclerView.Adapter {

    private ArrayList<LongBean.CommentsBean> longlist;

    public HotLongadapter(ArrayList<LongBean.CommentsBean> longlist) {
        this.longlist = longlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_long, null);

        return new MyViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder1 holder1 = (MyViewHolder1) holder;
        Glide.with(holder.itemView).load(longlist.get(position).getAvatar()).apply(RequestOptions.circleCropTransform()).into(holder1.mHeadimg);

        holder1.mName.setText(longlist.get(position).getAuthor());
        holder1.mMsg.setText(longlist.get(position).getContent());
        holder1.mTimes.setText("时间"+longlist.get(position).getTime());
        holder1.mNums.setText(longlist.get(position).getLikes()+"");
    }

    @Override
    public int getItemCount() {
        return longlist.size();
    }


    class MyViewHolder1 extends RecyclerView.ViewHolder {
        ImageView mHeadimg;
        TextView mName;
        TextView mMsg;
        TextView mTimes;
        ImageView mIvs;
        TextView mNums;

        public MyViewHolder1(View itemView) {
            super(itemView);
            this.mHeadimg = (ImageView) itemView.findViewById(R.id.headimg);
            this.mName = (TextView) itemView.findViewById(R.id.name);
            this.mMsg = (TextView) itemView.findViewById(R.id.msg);
            this.mTimes = (TextView) itemView.findViewById(R.id.times);
            this.mIvs = (ImageView) itemView.findViewById(R.id.ivs);
            this.mNums = (TextView) itemView.findViewById(R.id.nums);
        }
    }

}
