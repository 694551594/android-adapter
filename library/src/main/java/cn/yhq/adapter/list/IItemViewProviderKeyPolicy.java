package cn.yhq.adapter.list;


public interface IItemViewProviderKeyPolicy<T> {
   int getItemViewTypeKey(int position, T entity);
}
