package net.devwiki.rxjavademo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import net.devwiki.rxjavademo.bean.AppInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.app_list)
    RecyclerView appList;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.normal)
    Button normal;
    @Bind(R.id.rxJava)
    Button rxjava;
    @Bind(R.id.rxSingle)
    Button rxSingle;

    private List<AppInfo> infoList;
    private AppAdapter appAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        infoList = new ArrayList<>();
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
                getByRxJava();
            }
        });
    }

    @OnClick({R.id.normal, R.id.rxJava, R.id.rxSingle, R.id.rxFilter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.normal:
                getByNormal();
                break;
            case R.id.rxJava:
                getByRxJava();
                break;
            case R.id.rxSingle:
                getByRxJavaSingle();
                break;
            case R.id.rxFilter:
                filterByVersionCode(100);
                break;
        }
    }

    private void getByNormal() {
        refreshLayout.setRefreshing(true);
        infoList.clear();
        appAdapter.notifyDataSetChanged();
        infoList.addAll(AppHelper.getHelper().getListByNormal(this));
        appAdapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);
    }

    private void getByRxJava() {
        refreshLayout.setRefreshing(true);
        infoList.clear();
        appAdapter.notifyDataSetChanged();
        AppHelper.getHelper().getListByRxJava(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<AppInfo>>() {
                    @Override
                    public void onCompleted() {
                        appAdapter.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<AppInfo> list) {
                        infoList.addAll(list);
                    }
                });
    }

    private void getByRxJavaSingle() {
        refreshLayout.setRefreshing(true);
        infoList.clear();
        appAdapter.notifyDataSetChanged();
        AppHelper.getHelper().getAppInfoByRxJava(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AppInfo>() {
                    @Override
                    public void onCompleted() {
                        appAdapter.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AppInfo appInfo) {
                        infoList.add(appInfo);
                    }
                });
    }

    private void filterByVersionCode(final int code){
        refreshLayout.setRefreshing(true);
        infoList.clear();
        appAdapter.notifyDataSetChanged();
        AppHelper.getHelper().getAppInfoByRxJava(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .filter(new Func1<AppInfo, Boolean>() {
                    @Override
                    public Boolean call(AppInfo appInfo) {
                        return appInfo.getVersionCode() > code;
                    }
                })
                .subscribe(new Subscriber<AppInfo>() {
                    @Override
                    public void onCompleted() {
                        appAdapter.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AppInfo appInfo) {
                        if (appInfo != null){
                            infoList.add(appInfo);
                        }
                    }
                });
    }
}
