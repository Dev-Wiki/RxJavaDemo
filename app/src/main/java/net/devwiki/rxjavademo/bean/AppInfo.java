package net.devwiki.rxjavademo.bean;

import android.graphics.drawable.Drawable;

/**
 * app的信息类
 * Created by zyz on 2016/3/22.
 */
public class AppInfo {

    private String name;

    private String installTime;

    private int versionCode;

    private String versionName;

    private Drawable icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstallTime() {
        return installTime;
    }

    public void setInstallTime(String installTime) {
        this.installTime = installTime;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "name='" + name + '\'' +
                ", installTime='" + installTime + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", versionName='" + versionName + '\'' +
                ", icon=" + icon +
                '}';
    }
}
