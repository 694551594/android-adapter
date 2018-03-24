package cn.yhq.adapter.list;

import cn.yhq.adapter.core.ViewHolder;


public interface IItemViewProvider<T> {

    /**
     * 获取视图id
     *
     * @return
     */
    int getItemViewLayoutId();

    /**
     * 组装View
     *
     * @param viewHolder
     * @param position
     * @param entity
     */
    void setupView(ViewHolder viewHolder, int position, T entity);


}
