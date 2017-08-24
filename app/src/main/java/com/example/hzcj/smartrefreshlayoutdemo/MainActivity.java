package com.example.hzcj.smartrefreshlayoutdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> list;
    private NewsAdater newsAdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        init();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        newsAdater = new NewsAdater(list,MainActivity.this);
        recyclerView.setAdapter(newsAdater);
        newsAdater.setOnItemClickListener(new NewsAdater.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                Toast.makeText(MainActivity.this,"点击了item"+position,Toast.LENGTH_SHORT);
            }

            @Override
            public void OnItemLongClick(View v, int position) {
                Toast.makeText(MainActivity.this,"长按了item"+position,Toast.LENGTH_SHORT);
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                list.clear();
                for (int i = 0; i < 10; i++) {
                    list.add("刷新添加的item图文标题"+i);
                }
                newsAdater.notifyDataSetChanged();

            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
                for (int i = 0; i < 10; i++) {
                    list.add("加载更多的图文标题"+i);
                }
                newsAdater.notifyDataSetChanged();
            }
        });
    }

    private void init() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("图文标题"+i);
        }


    }
}
