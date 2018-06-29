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
private ChannelView channelView;
    private List<ChannelBean> channelBeans = new ArrayList<>();
    private List<ChannelBean> moreChannelBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        channelView = findViewById(R.id.channel_view);

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
