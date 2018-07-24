# RecycleViewDemo

# 使用步骤
1. 在proj的build.gradle 添加如下代码

    ```
    allprojects {
        repositories {
            maven { url 'https://dl.bintray.com/bruceanda/maven/' }
        }
    }
    ```
2. 在app的build.gradle 点击如下代码
    ```
    implementation 'channelmanager:channelmanager:0.4.5'
    ```
3. 在布局文件中使用

```
<com.bawei.channelmanager.ui.ChannelView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/channel_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```
4. 在Java代码中使用
```
private ChannelView channelView;    // 频道View
    private List<ChannelBean> channelBeans = new ArrayList<>();     // 我的频道数据集合
    private List<ChannelBean> moreChannelBeans = new ArrayList<>();     // 更多频道数据集合 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        channelView = findViewById(R.id.channel_view);      // 查找控件
        channelView.showDefaultHeadView(false);     // 设置隐藏默认的HeadView
        channelView.showDefaultMoreView(false);     // 设置隐藏默认的MoreView
        channelView.addHead(LayoutInflater.from(this).inflate(R.layout.love_channel, null, false));     // 添加自定义的head
        channelView.addMore(LayoutInflater.from(this).inflate(R.layout.more_channel, null, false));     // 添加自定义的more


        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                channelBeans.add(new ChannelBean("频道" + i, i, "url", true));
            } else {
                moreChannelBeans.add(new ChannelBean("频道" + i, i, "url", false));
            }

        }

        channelView.init(channelBeans, moreChannelBeans, new ChannelView.ChannelCallback() {
            @Override
            public void onMyChannelRemove(ChannelBean channelBean) {
                
            }

            @Override
            public void moMoreChannelRemove(ChannelBean channelBean) {

            }
        });
    }
```
