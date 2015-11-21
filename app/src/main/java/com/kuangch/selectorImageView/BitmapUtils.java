package com.kuangch.selectorImageView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.Log;

/**
 * Created by KuangCH on 2015/11/20.
 * @email kuangch_8017@qq.com
 */
public class BitmapUtils {

    /**
     * 将彩色图转换为纯黑白二色
     *
     * @param bmp 目标位图
     * @return 返回转换好的位图
     */
    public static Bitmap convertToBlackWhite(Bitmap bmp) {

        long startT = System.currentTimeMillis();

        int width = bmp.getWidth();     // 获取位图的宽
        int height = bmp.getHeight();   // 获取位图的高

        // 通过位图的大小创建像素点数组
        int[] pixels = new int[width * height];

        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];

                //分离三原色和透明值
                int alpha = ((grey & 0xFF000000) >> 24);
                int red = ((grey & 0x00FF0000) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);

                //转化成灰度像素
                grey = (int) (red * 0.3 + green * 0.59 + blue * 0.11);
                grey = (alpha << 24) | (grey << 16) | (grey << 8) | grey;
                pixels[width * i + j] = grey;
            }
        }

        //新建bitmap图片
        Bitmap newBmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //设置图片数据
        newBmp.setPixels(pixels, 0, width, 0, 0, width, height);

        MyLogger.i("kuangch","color2greyTime: "+ (System.currentTimeMillis() - startT));
        return newBmp;
    }


    public static Bitmap drawable2Bitmap(Drawable drawable) {

        long startT = System.currentTimeMillis();

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof NinePatchDrawable) {
            Bitmap bitmap = Bitmap
                    .createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                    : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            drawable.draw(canvas);

            MyLogger.i("kuangch", "drawable2BitmapTime: " + (System.currentTimeMillis() - startT));
            return bitmap;
        } else {
            return null;
        }
    }
}
