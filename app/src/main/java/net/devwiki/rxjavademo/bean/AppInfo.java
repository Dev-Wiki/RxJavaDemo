package net.devwiki.rxjavademo.bean;

import android.graphics.drawable.Drawable;

/**
 * app的信息类
 * Created by zyz on 2016/3/22.
 */
public class AppInfo {

    private String name;

    private long installTime;

    private Drawable icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getInstallTime() {
        return installTime;
    }

    public void setInstallTime(long installTime) {
        this.installTime = installTime;
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
                ", installTime=" + installTime +
                ", icon=" + icon +
                '}';
    }
}
