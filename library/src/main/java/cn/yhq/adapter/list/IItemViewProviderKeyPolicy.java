package cn.yhq.adapter.list;


public interface IItemViewProviderKeyPolicy<T> {
    int getItemViewProviderKey(int position, T entity);
}
