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

import bean.HotBean;
import bean.TalkBean;

/**
 * Created by ASUS on 2019/9/7.
 */

public class RlvHot extends RecyclerView.Adapter {


    private ArrayList<HotBean.RecentBean> list;

    public RlvHot(ArrayList<HotBean.RecentBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.talkitem, null);
        return new MyViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
                MyViewHolder1 holder1= (MyViewHolder1) holder;
        Glide.with(holder.itemView).load(list.get(position).getThumbnail()).into(holder1.mTalkimg);
        holder1.mTalktv.setText(list.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                talkOnclic.MyTalkOnclic(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder {
        ImageView mTalkimg;
        TextView mTalktv;
        public MyViewHolder1(View itemView) {
            super(itemView);
            this.mTalkimg = (ImageView) itemView.findViewById(R.id.talkimg);
            this.mTalktv = (TextView) itemView.findViewById(R.id.talktv);

        }
    }
        public interface TalkOnclic{
                void MyTalkOnclic(int i);
        }
    TalkOnclic talkOnclic;
    public void setTalkOnclic(TalkOnclic talkOnclic) {
        this.talkOnclic = talkOnclic;
    }
}
