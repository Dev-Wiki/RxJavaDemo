package net.devwiki.rxjavademo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.devwiki.rxjavademo.bean.AppInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.app_list)
    RecyclerView appList;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private List<AppInfo> infoList;
    private AppAdapter appAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        infoList = AppUtil.getAppList(this);
        appAdapter = new AppAdapter(this, infoList);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        appList.setAdapter(appAdapter);
        appList.setLayoutManager(llm);

        refreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }
}
