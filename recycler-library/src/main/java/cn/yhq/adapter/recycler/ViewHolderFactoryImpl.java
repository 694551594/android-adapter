package cn.yhq.adapter.recycler;

import android.view.View;


/**
 * Created by yanghuijuan on 2017/2/1.
 */

public class ViewHolderFactoryImpl implements ViewHolderFactory<ViewHolder> {

    @Override
    public ViewHolder createViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

}
