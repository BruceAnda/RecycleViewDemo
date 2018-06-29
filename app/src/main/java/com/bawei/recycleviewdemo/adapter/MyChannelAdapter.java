package com.bawei.recycleviewdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bawei.recycleviewdemo.R;
import com.bawei.recycleviewdemo.bean.ChannelBean;
import com.bawei.recycleviewdemo.callback.TouchInterface;

import java.util.Collections;
import java.util.List;


public class MyChannelAdapter extends RecyclerView.Adapter<MyChannelAdapter.MyCahnnelViewHolder> implements TouchInterface {

    private Context context;
    private List<ChannelBean> channelBeanList;
    private boolean isShow;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public MyChannelAdapter(Context context, List<ChannelBean> channelBeanList) {
        this.context = context;
        this.channelBeanList = channelBeanList;
    }

    @NonNull
    @Override
    public MyCahnnelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyCahnnelViewHolder(LayoutInflater.from(context).inflate(R.layout.my_channel_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCahnnelViewHolder holder, final int position) {
        holder.channel.setText(channelBeanList.get(position).getChannel());
        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChannelBean remove = channelBeanList.remove(position);
                notifyDataSetChanged();
                channelCallback.onItemRemove(remove);
            }
        });
        if (isShow) {
            holder.ivImage.setVisibility(View.VISIBLE);
        } else {
            holder.ivImage.setVisibility(View.GONE);
        }
       /* holder.rlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击" + channelBeanList.get(position).getChannel(), Toast.LENGTH_SHORT).show();
            }
        });*/
        /*holder.rlRoot.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return true;
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return channelBeanList.size();
    }

    @Override
    public void onMove(int currentPostion, int targetPostion) {
        Collections.swap(channelBeanList, currentPostion, targetPostion);
        notifyItemMoved(currentPostion, targetPostion);
    }

    class MyCahnnelViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rlRoot;
        private TextView channel;
        private ImageView ivImage;

        public MyCahnnelViewHolder(View itemView) {
            super(itemView);

            rlRoot = itemView.findViewById(R.id.rl_root);
            channel = itemView.findViewById(R.id.tv_channel);
            ivImage = itemView.findViewById(R.id.tv_image);
        }
    }

    private MyChannelCallback channelCallback;

    public void setChannelCallback(MyChannelCallback channelCallback) {
        this.channelCallback = channelCallback;
    }

    public interface MyChannelCallback {
        void onItemRemove(ChannelBean channelBean);
    }
}
