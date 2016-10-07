package cn.yhq.adapter.recycler;

public interface IItemViewProvider<T> {

  int getItemViewLayoutId();

  /**
   * 组装View
   * 
   * @param viewHolder
   * @param position
   * @param entity
   */
  void onBindViewHolder(ViewHolder viewHolder, int position, T entity);


}
