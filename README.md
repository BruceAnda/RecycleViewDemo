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
