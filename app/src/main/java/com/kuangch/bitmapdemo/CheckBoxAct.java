package com.kuangch.bitmapdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kuangch.selectorImageView.SelectImageView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by Thinkpad on 2015/11/21.
 */
@ContentView(R.layout.activity_checkbox)
public class CheckBoxAct extends AppCompatActivity {

    @ViewInject(R.id.tv_1)
    private TextView tv_1;

    @ViewInject(R.id.tv_2)
    private TextView tv_2;

    @ViewInject(R.id.tv_3)
    private TextView tv_3;

    @ViewInject(R.id.iv_checkbox1)
    private SelectImageView checkbox1;

    @ViewInject(R.id.iv_checkbox2)
    private SelectImageView checkbox2;

    @ViewInject(R.id.iv_checkbox3)
    private SelectImageView checkbox3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

        checkbox1.setOnStateChangeListener(new SelectImageView.IonStateChangeListener() {
            @Override
            public void onStateChange(boolean isSelector) {
                tv_1.setText(isSelector ? "True" : "False");
            }
        });

        checkbox2.setOnStateChangeListener(new SelectImageView.IonStateChangeListener() {
            @Override
            public void onStateChange(boolean isSelector) {
                tv_2.setText(isSelector ? "True" : "False");
            }
        });

        checkbox3.setOnStateChangeListener(new SelectImageView.IonStateChangeListener() {
            @Override
            public void onStateChange(boolean isSelector) {
                tv_3.setText(isSelector ? "True" : "False");
            }
        });
    }
}
