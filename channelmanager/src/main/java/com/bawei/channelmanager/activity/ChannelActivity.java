package com.bawei.channelmanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bawei.channelmanager.adapter.MoreChannelAdapter;
import com.bawei.channelmanager.adapter.MyChannelAdapter;
import com.bawei.channelmanager.bean.ChannelBean;
import com.bawei.channelmanager.callback.MyItemTouchCallback;
import com.bawei.channelmanager.db.ChannelDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.bawei.channelmanager.R;


/**
 * 频道管理
 *
 * @author zhaoliang
 * @version 1.0
 * @create 2018/6/28
 */
public class ChannelActivity extends AppCompatActivity implements MoreChannelAdapter.MoreChannelCallback, MyChannelAdapter.MyChannelCallback {

    public static final String TAG = ChannelActivity.class.getSimpleName();
    private RecyclerView rvMyChannel;
    private RecyclerView rvAllCahnnel;
    private List<ChannelBean> myChannelBeans = new ArrayList<>();
    private List<ChannelBean> moreChannelBeans = new ArrayList<>();
    private MyChannelAdapter adapter;
    private MoreChannelAdapter moreChannelAdapter;
    private TextView tvEdit;

    private ChannelDao channelDao;
    private List<ChannelBean> loadMyChannel;
    private List<ChannelBean> loadMoreChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        channelDao = new ChannelDao(this);
        rvMyChannel = findViewById(R.id.rv_my_channel);
        rvAllCahnnel = findViewById(R.id.rv_more_channel);
        tvEdit = findViewById(R.id.tv_edit);

        loadLocal();

        if (loadMyChannel.size() < 1 || loadMoreChannel.size() < 1) {
            for (int i = 0; i < 20; i++) {
                if (i % 2 == 0) {
                    channelDao.insert(new ChannelBean("频道" + i, i, "url", true));
                } else {
                    channelDao.insert(new ChannelBean("频道" + i, i, "url", false));
                }
            }
            loadLocal();
        }

        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMyChannelState();
            }
        });

        rvMyChannel.setLayoutManager(new GridLayoutManager(this, 4));
        rvAllCahnnel.setLayoutManager(new GridLayoutManager(this, 4));

        for (int i = 0; i < 10; i++) {
            myChannelBeans.add(new ChannelBean("频道" + i, i, "url"));
            moreChannelBeans.add(new ChannelBean("频道1" + i, i + 10, "url"));
        }

        adapter = new MyChannelAdapter(this, loadMyChannel);
        rvMyChannel.setAdapter(adapter);
        moreChannelAdapter = new MoreChannelAdapter(this, loadMoreChannel);
        rvAllCahnnel.setAdapter(moreChannelAdapter);
        adapter.setChannelCallback(this);
        moreChannelAdapter.setChannelCallback(this);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new MyItemTouchCallback(adapter));
        itemTouchHelper.attachToRecyclerView(rvMyChannel);
    }

    private void loadLocal() {
        loadMyChannel = channelDao.load(true);
        loadMoreChannel = channelDao.load(false);
        Log.i(TAG, "loadmy:" + loadMyChannel.size());
        Log.i(TAG, "loadmore:" + loadMoreChannel.size());
        Collections.sort(loadMyChannel, new Comparator<ChannelBean>() {
            @Override
            public int compare(ChannelBean o1, ChannelBean o2) {
                if (o1.getIndex() < o2.getIndex()) {
                    return -1;
                }
                if (o1.getIndex() == o2.getIndex()) {
                    return 0;
                }
                return 1;
            }
        });
    }

    @Override
    public void onItemClick(ChannelBean channelBean) {
        myChannelBeans.add(channelBean);
        channelBean.setMore(false);
        loadMyChannel.add(channelBean);
       // channelDao.update(channelBean);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRemove(ChannelBean channelBean) {
        moreChannelBeans.add(channelBean);
        channelBean.setMore(true);
        loadMoreChannel.add(channelBean);
        //channelDao.update(channelBean);
        moreChannelAdapter.notifyDataSetChanged();
    }

    /**
     * 改变channel的状态
     */
    private void changeMyChannelState() {
        boolean show = adapter.isShow();
        if (show) {
            tvEdit.setText("编辑");
        } else {
            tvEdit.setText("完成");
        }
        adapter.setShow(!show);
        adapter.notifyDataSetChanged();
    }
}
