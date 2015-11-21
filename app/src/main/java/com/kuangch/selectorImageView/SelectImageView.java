package com.kuangch.selectorImageView;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.kuangch.bitmapdemo.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by KuangCH on 2015/11/20.
 * @email kuangch_8017@qq.com
 */
public class SelectImageView extends ImageView{

    /**变色方式（COLOR2GRAY：彩色图和黑白图互转，COLOR2COLOR：自定义颜色互转（对图片有要求））*/
    public enum ChangeMethod {
        COLOR2GRAY,COLOR2COLOR
    }

    /**控件的用途（checkbox,table button）*/
    public enum ViewType {
        CHECKBOX,TABLE_BUTTON
    }

    private int changeMethod = ChangeMethod.COLOR2GRAY.ordinal();
    private int viewType = ViewType.CHECKBOX.ordinal();

    /**选择时颜色*/
    private int selectColor = Color.GREEN;
    /**原始颜色*/
    private int originColor = Color.GRAY;

    /**选中状态(作为checkbox)*/
    private boolean isSelected = false;

    /**原始图片drawable*/
    private Drawable originDrawable = null;

    /**自定义的图片资源id（性能更好）*/
    private int customImgSrc = -1;

    public SelectImageView(Context context) {
        super(context);
    }

    public SelectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SelectImageView);
        selectColor = ta.getColor(R.styleable.SelectImageView_select_color, selectColor);
        originColor = ta.getColor(R.styleable.SelectImageView_origin_color, originColor);
        changeMethod = ta.getInt(R.styleable.SelectImageView_change_method, changeMethod);
        viewType = ta.getInt(R.styleable.SelectImageView_view_type, viewType);

        // 自定义的图片资源id（彩色图转灰度图的性能更好）
        customImgSrc = ta.getResourceId(R.styleable.SelectImageView_custom_img_src,customImgSrc);

        // 如果有自定义的图片资源就不使用系统定义的图片资源提高性能
        if(customImgSrc != -1){
            Bitmap image = null;
            try
            {
                InputStream is = getResources().openRawResource(customImgSrc);
                image = BitmapFactory.decodeStream(is);
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            if(image != null)
                setCustomImageBitmap(image);
        }else{

            // 获得原始图片drawable
            getOriginDrawable();

            // 初始化显示
            initImage();
        }
    }

    @Override
    public boolean performClick() {
        if(viewType == ViewType.CHECKBOX.ordinal()) {
            changeState();
            isSelected = !isSelected;
            if(onStateChangeListener != null){
                onStateChangeListener.onStateChange(isSelected);
            }
            MyLogger.i("kuangch", "now selected:" + (isSelected ? "true" : "false"));
        }else{
            MyLogger.i("kuangch", "Previous selected:" + (isSelected ? "true" : "false"));
        }
        return super.performClick();
    }

    public void setCustomImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        getOriginDrawable();
        initImage();
    }

    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }

    private Drawable getOriginDrawable(){

        originDrawable = getDrawable();

        // 防止用户不设置图片
        if(originDrawable == null){
            Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
            bitmap.setPixels(new int[1],0,1,0,0,1,1);
            originDrawable = new BitmapDrawable(bitmap);
        }

        // 标记drawable需要编辑
        return originDrawable.mutate();
    }

    private void initImage(){
        if(changeMethod == ChangeMethod.COLOR2COLOR.ordinal()){
            setImageDrawable(tintDrawable(originDrawable, ColorStateList.valueOf(originColor))) ;
        }else{
            setImageBitmap(BitmapUtils.convertToBlackWhite(BitmapUtils.drawable2Bitmap(originDrawable)));
        }
    }

    private void changeState(){
        if(changeMethod == ChangeMethod.COLOR2COLOR.ordinal()) {
            setImageDrawable(tintDrawable(originDrawable, ColorStateList.valueOf(isSelected ? originColor : selectColor)));

        }else{
            if(viewType == ViewType.CHECKBOX.ordinal()) {
                   switchState(isSelected);
            }else{
                   switchState(!isSelected);
            }
        }
        invalidate();
    }

    private void switchState(boolean isToGrey){
        if (isToGrey) {
            setImageBitmap(BitmapUtils.convertToBlackWhite(BitmapUtils.drawable2Bitmap(originDrawable)));
        } else {
            setImageBitmap(BitmapUtils.drawable2Bitmap(originDrawable));
        }
    }

    public boolean getSelectState(){
        return isSelected;
    }

    public void setSelectState(boolean isSelected){

        this.isSelected = isSelected;
        if(viewType == ViewType.TABLE_BUTTON.ordinal()){
            // 如果作为table button使用
            changeState();
        }else{

            //如果作为checkbox使用
            switchState(!isSelected);
        }
    }

    public void setSelectColor(int selectColor) {
        this.selectColor = selectColor;
    }

    public void setOriginColor(int originColor) {
        this.originColor = originColor;
    }

    // 选中状态改变回调
    private IonStateChangeListener onStateChangeListener = null;
    public interface IonStateChangeListener{
        void onStateChange(boolean isSelector);
    }
    public void setOnStateChangeListener(IonStateChangeListener onStateChangeListener){
        this.onStateChangeListener = onStateChangeListener;
    }
}
