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
`change_method:`图片切换的变色方式。图片有两种方式变色(`color2color`,`color2grey`)，一种是通过drawableCompat着色，这种方式需要图片资源时标准的png图片不然显示异常。另一种通过算法将彩色图片转化成灰度图，原图和灰度图切换实现选中和非选中效果。  

建议使用`custom_img_src`设置图片资源，本控件也兼容imageview自身的`src`设置图片资源，但是前者性能更好  

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
`view_type:`控件的使用类型有两种方式（`tableButton`,`checkbox`）默认为`checkbox` 类似系统的checkbox控件。选择`tableButton`类型现在暂时没
有状态变化监听，后续会加上
#效果展示
![image](https://github.com/kuangch/SelectorImageView-master/blob/master/showpiece.GIF)
