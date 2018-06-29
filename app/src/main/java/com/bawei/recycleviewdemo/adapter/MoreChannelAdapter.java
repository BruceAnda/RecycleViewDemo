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

import java.util.List;

public class MoreChannelAdapter extends RecyclerView.Adapter<MoreChannelAdapter.MoreChannelViewHolder> {

    private Context context;
    private List<ChannelBean> channelBeanList;

    public MoreChannelAdapter(Context context, List<ChannelBean> channelBeanList) {
        this.context = context;
        this.channelBeanList = channelBeanList;
    }

    @NonNull
    @Override
    public MoreChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoreChannelViewHolder(LayoutInflater.from(context).inflate(R.layout.my_channel_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MoreChannelViewHolder holder, final int position) {
        holder.channel.setText(channelBeanList.get(position).getChannel());
        holder.ivImage.setImageResource(R.drawable.ic_add_black_24dp);
        holder.rlRoot.setBackgroundResource(R.drawable.more_channel_item_bg);
        holder.rlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChannelBean remove = channelBeanList.remove(position);
                channelCallback.onItemClick(remove);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return channelBeanList.size();
    }

    class MoreChannelViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rlRoot;
        private TextView channel;
        private ImageView ivImage;

        public MoreChannelViewHolder(View itemView) {
            super(itemView);
            rlRoot = itemView.findViewById(R.id.rl_root);
            channel = itemView.findViewById(R.id.tv_channel);
            ivImage = itemView.findViewById(R.id.tv_image);
        }
    }

    private MoreChannelCallback channelCallback;

    public void setChannelCallback(MoreChannelCallback channelCallback) {
        this.channelCallback = channelCallback;
    }

    public interface MoreChannelCallback {
        void onItemClick(ChannelBean channelBean);
    }
}
