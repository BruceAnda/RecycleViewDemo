package com.bawei.channelmanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bawei.channelmanager.bean.ChannelBean;

import java.util.ArrayList;
import java.util.List;

public class ChannelDao {

    private Context context;
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public ChannelDao(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(ChannelBean bean) {
        ContentValues values = new ContentValues();
        values.put("channel", bean.getChannel());
        values.put("sort", bean.getIndex());
        values.put("isMore", bean.isMore());
        values.put("url", bean.getUrl());
        db.insert("channel", null, values);
    }

    public List<ChannelBean> load(boolean isMore) {
        Cursor cursor;
        List<ChannelBean> beanList = new ArrayList<>();
        if (isMore) {   // 0 false 1 true
            cursor = db.rawQuery("select * from channel where isMore = ?", new String[]{String.valueOf(1)});
        } else {
            cursor = db.rawQuery("select * from channel where isMore = ?", new String[]{String.valueOf(0)});
        }
        ChannelBean channelBean = null;
        while (cursor.moveToNext()) {
            String more = cursor.getString(cursor.getColumnIndex("isMore"));
            channelBean = new ChannelBean(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("channel")),
                    cursor.getInt(cursor.getColumnIndex("sort")),
                    cursor.getString(cursor.getColumnIndex("url")),
                    "0".equals(more) ? false : true);
            beanList.add(channelBean);

        }
        return beanList;
    }

    public void update(ChannelBean bean) {
        ContentValues values = new ContentValues();
        values.put("channel", bean.getChannel());
        values.put("sort", bean.getIndex());
        values.put("isMore", bean.isMore());
        values.put("url", bean.getUrl());
        db.update("channel", values, "where _id = ?", new String[]{String.valueOf(bean.get_id())});
    }

    public void updateAll(List<ChannelBean> channelBeans) {
        for (ChannelBean channelBean : channelBeans) {
            update(channelBean);
        }
    }
}
