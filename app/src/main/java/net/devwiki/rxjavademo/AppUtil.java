package net.devwiki.rxjavademo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import net.devwiki.rxjavademo.bean.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyz on 2016/3/22.
 */
public class AppUtil {

    public static List<AppInfo> getAppList(Context context){
        List<AppInfo> appInfoList = new ArrayList<>();
        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);
        for (PackageInfo packageInfo : packages) {
            AppInfo appInfo = new AppInfo();
            appInfo.setName(packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
            appInfo.setIcon(packageInfo.applicationInfo.loadIcon(context.getPackageManager()));
            appInfo.setInstallTime(packageInfo.firstInstallTime);
            appInfoList.add(appInfo);
        }
        return appInfoList;
    }
}
