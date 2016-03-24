package net.devwiki.rxjavademo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import net.devwiki.rxjavademo.bean.AppInfo;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by zyz on 2016/3/23.
 */
public class AppHelper {

    public static AppHelper getHelper(){
        return HelperHolder.helper;
    }

    static class HelperHolder{
        static AppHelper helper = new AppHelper();
    }

    private Context context;

    private AppHelper(){}

    public List<AppInfo> getListByNormal(Context context){
        return AppUtil.getAppList(context);
    }

    public Observable<List<AppInfo>> getListByRxJava(final Context context){
        Observable<List<AppInfo>> observer = Observable.create(new Observable.OnSubscribe<List<AppInfo>>() {
            @Override
            public void call(Subscriber<? super List<AppInfo>> subscriber) {
                List<AppInfo> infoList = AppUtil.getAppList(context);
                subscriber.onNext(infoList);
                subscriber.onCompleted();
            }
        });
        return observer;
    }

    public Observable<AppInfo> getAppInfoByRxJava(final Context context){
        Observable<AppInfo> observer = Observable.create(new Observable.OnSubscribe<AppInfo>() {
            @Override
            public void call(Subscriber<? super AppInfo> subscriber) {
                List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);
                for (PackageInfo packageInfo : packages) {
                    AppInfo appInfo = new AppInfo();
                    appInfo.setName(packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
                    appInfo.setIcon(packageInfo.applicationInfo.loadIcon(context.getPackageManager()));
                    appInfo.setInstallTime(AppUtil.getFormatTime(packageInfo.firstInstallTime));
                    appInfo.setVersionCode(packageInfo.versionCode);
                    appInfo.setVersionName(packageInfo.versionName);
                    subscriber.onNext(appInfo);
                }
                subscriber.onCompleted();
            }
        });
        return observer;
    }
}
