package com.kuangch.bitmapdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kuangch.selectorImageView.SelectImageView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Thinkpad on 2015/11/21.
 */
@ContentView(R.layout.activity_img_handler)
public class ImageHandleAct extends AppCompatActivity {

    @ViewInject(R.id.iv_img)
    private SelectImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

        Bitmap image = null;
        try
        {
            InputStream is = getResources().getAssets().open("realman.jpg");
            image = BitmapFactory.decodeStream(is);
            is.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        if(image != null)
            img.setCustomImageBitmap(image);

    }
}
