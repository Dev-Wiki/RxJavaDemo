package net.devwiki.rxjavademo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.devwiki.rxjavademo.bean.AppInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zyz on 2016/3/22.
 */
public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {

    private Context context;
    private List<AppInfo> infoList;

    public AppAdapter(Context context, List<AppInfo> infoList) {
        this.context = context;
        this.infoList = infoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_app_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppInfo appInfo = infoList.get(position);
        holder.appName.setText(appInfo.getName());
        holder.appIcon.setImageDrawable(appInfo.getIcon());
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.app_icon)
        ImageView appIcon;
        @Bind(R.id.app_name)
        TextView appName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
