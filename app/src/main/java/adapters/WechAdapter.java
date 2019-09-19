package adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.geeknews.R;

import java.util.ArrayList;

import bean.WxtBean;

/**
 * Created by ASUS on 2019/9/9.
 */

public class WechAdapter extends RecyclerView.Adapter {
    private ArrayList<WxtBean.NewslistBean> list;

    public WechAdapter(ArrayList<WxtBean.NewslistBean> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wech, null);
        return new MyViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            MyViewHolder1 holder1= (MyViewHolder1) holder;
        Glide.with(holder.itemView).load(list.get(position).getPicUrl()).into(holder1.mWximg);
        holder1.mWxtle.setText(list.get(position).getTitle());
        holder1.mWxt1.setText(list.get(position).getDescription());
        holder1.mWxt2.setText(list.get(position).getCtime());
    }


    class MyViewHolder1 extends RecyclerView.ViewHolder {

        ImageView mWximg;
        TextView mWxtle;
        TextView mWxt1;
        TextView mWxt2;
        public MyViewHolder1(View itemView) {
            super(itemView);
            this.mWximg = (ImageView) itemView.findViewById(R.id.wximg);
            this.mWxtle = (TextView) itemView.findViewById(R.id.wxtle);
            this.mWxt1 = (TextView) itemView.findViewById(R.id.wxt1);
            this.mWxt2 = (TextView) itemView.findViewById(R.id.wxt2);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
