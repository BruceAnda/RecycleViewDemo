package com.bawei.channelmanager.bean;

public class ChannelBean {

    private String channel;
    private int index;
    private String url;
    private boolean isMore;
    private int _id;

    public ChannelBean() {
    }

    public ChannelBean(String channel, int index, String url) {
        this.channel = channel;
        this.index = index;
        this.url = url;
    }

    public ChannelBean(String channel, int index, String url, boolean isMore) {
        this.channel = channel;
        this.index = index;
        this.url = url;
        this.isMore = isMore;
    }

    public ChannelBean(int _id, String channel, int index, String url, boolean isMore) {
        this.channel = channel;
        this.index = index;
        this.url = url;
        this.isMore = isMore;
        this._id = _id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }
}
