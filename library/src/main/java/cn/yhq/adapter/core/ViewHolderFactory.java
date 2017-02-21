package cn.yhq.adapter.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yanghuijuan on 2017/2/1.
 */

public interface ViewHolderFactory<T extends ViewHolder> {

    T createViewHolder(Context context, View convertView, ViewGroup parent, int layoutId, int position);

}
