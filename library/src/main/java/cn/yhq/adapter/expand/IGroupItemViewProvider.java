package cn.yhq.adapter.expand;

import cn.yhq.adapter.core.ViewHolder;


public interface IGroupItemViewProvider<T> {

    /**
     * 组装View
     *
     * @param viewHolder
     * @param position
     * @param entity
     */
    void setupView(ViewHolder viewHolder, int position, T entity, boolean isExpanded);


    int getItemViewLayoutId();
}
