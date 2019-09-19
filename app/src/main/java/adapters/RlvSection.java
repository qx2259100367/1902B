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

import bean.SectionBean;

/**
 * Created by ASUS on 2019/9/7.
 */

public class RlvSection extends RecyclerView.Adapter {


    private ArrayList<SectionBean.DataBean> list;

    public RlvSection(ArrayList<SectionBean.DataBean> beans) {
        this.list = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sectionitem, null);
        return new MyViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            MyViewHolder1 holder1 = (MyViewHolder1) holder;
        Glide.with(holder.itemView).load(list.get(position).getThumbnail()).into(holder1.mRlvimg);
        holder1.mRlvtv.setText(list.get(position).getDescription());
        holder1.mRlvtv2.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Onclic.MyOnclic(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder1 extends RecyclerView.ViewHolder {
        ImageView mRlvimg;
        TextView mRlvtv;
        TextView mRlvtv2;
        public MyViewHolder1(View itemView) {
            super(itemView);
            this.mRlvimg = (ImageView) itemView.findViewById(R.id.rlvimg);
            this.mRlvtv = (TextView) itemView.findViewById(R.id.rlvtv);
            this.mRlvtv2 = (TextView) itemView.findViewById(R.id.rlvtv2);
        }
    }
    public interface  SectionOnclic{
        void MyOnclic(int i);
    }
    SectionOnclic Onclic;

    public void setOnclic(SectionOnclic onclic) {
        Onclic = onclic;
    }
}
