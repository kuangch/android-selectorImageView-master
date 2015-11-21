package com.kuangch.bitmapdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kuangch.selectorImageView.SelectImageView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thinkpad on 2015/11/21.
 */
@ContentView(R.layout.activity_table)
public class TableButtonAct extends AppCompatActivity {

    @ViewInject(R.id.iv_home)
    private SelectImageView table_1;

    @ViewInject(R.id.iv_home2)
    private SelectImageView table_2;

    @ViewInject(R.id.iv_home3)
    private SelectImageView table_3;

    private List<SelectImageView> tables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

        tables = new ArrayList<SelectImageView>();
        tables.add(table_1);
        tables.add(table_2);
        tables.add(table_3);

        selectedTable(tables.get(0));
    }

    private void selectedTable(SelectImageView selected){
        for(SelectImageView table : tables){
            if(table == selected){
                table.setSelectState(true);
            }else{
                table.setSelectState(false);
            }
        }
    }

    @OnClick(R.id.iv_home)
    public void iv_homeClick(View view){
        selectedTable(tables.get(0));
    }

    @OnClick(R.id.iv_home2)
    public void iv_home2Click(View view){
        selectedTable(tables.get(1));
    }

    @OnClick(R.id.iv_home3)
    public void iv_home3Click(View view){
        selectedTable(tables.get(2));
    }
}
