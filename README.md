# SelectorImageView-master
只需一张图片就可以实现自定义checkbox（通过给图片着色可以自定义原始颜色和选中颜色，很酷哦）和table button的功能（通过原图和他的灰度图区分选择和非选择状态）从此程序媛再也不用担心美工妹子给的两张图不对称而导致切换图片有微小的闪动，应为我们只是用了一张图片哦，而且还能缩小app的大小。
#做为checkbox使用方法
```java
<com.kuangch.selectorImageView.SelectImageView
                android:id="@+id/iv_checkbox3"
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:clickable="true"
                android:src="@drawable/icon_check"
                app:change_method="color2color"
                app:origin_color="#ffff00ff"
                app:select_color="#ff0000ff" />
```
建议使用custom_img_src设置图片资源，本空间也兼容src设置图片资源，但是前者性能跟好
选择状态改变监听方法
```java
checkbox1.setOnStateChangeListener(new SelectImageView.IonStateChangeListener() {
            @Override
            public void onStateChange(boolean isSelector) {
                tv_1.setText(isSelector ? "True" : "False");
            }
        });
```
#做为table button的使用方法
```java
<com.kuangch.selectorImageView.SelectImageView
                android:id="@+id/iv_home3"
                android:layout_width="150px"
                android:layout_height="150px"
                android:clickable="true"
                app:change_method="color2gray"
                app:custom_img_src="@drawable/icon_people"
                app:view_type="tableButton" />
```
#效果展示
![image](https://github.com/kuangch/SelectorImageView-master/showpiece.GIF)
