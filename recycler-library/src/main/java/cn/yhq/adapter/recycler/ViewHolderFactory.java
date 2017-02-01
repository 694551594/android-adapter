package cn.yhq.adapter.recycler;

import android.view.View;

/**
 * Created by yanghuijuan on 2017/2/1.
 */

public interface ViewHolderFactory<T extends ViewHolder> {

    T createViewHolder(View itemView);

}
