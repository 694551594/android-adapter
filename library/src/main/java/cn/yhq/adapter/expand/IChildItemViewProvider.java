package cn.yhq.adapter.expand;

import cn.yhq.adapter.core.ViewHolder;


public interface IChildItemViewProvider<G, C> {

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
     * @param groupPosition
     * @param groupEntity
     * @param childPosition
     * @param childEntity
     */
    void setupView(ViewHolder viewHolder, int groupPosition, G groupEntity, int childPosition,
                   C childEntity);


}
