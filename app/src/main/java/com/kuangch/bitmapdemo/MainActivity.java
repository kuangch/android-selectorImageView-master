package com.kuangch.bitmapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);

	}

	@OnClick(R.id.btn_cb)
	public void btn_cbClick(View view){
		startActivity(new Intent(this, CheckBoxAct.class));
	}

	@OnClick(R.id.btn_img)
	public void btn_imgClick(View view){
		startActivity(new Intent(this, ImageHandleAct.class));
	}

	@OnClick(R.id.btn_tb)
	public void btn_tbClick(View view){
		startActivity(new Intent(this,TableButtonAct.class));
	}
}
