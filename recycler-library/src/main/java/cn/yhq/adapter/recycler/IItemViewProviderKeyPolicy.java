package cn.yhq.adapter.recycler;


public interface IItemViewProviderKeyPolicy<T> {
  int getItemViewProviderKey(int position, T entity);
}
