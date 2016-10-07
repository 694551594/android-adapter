package cn.yhq.adapter.recycler;

/**
 * Created by Administrator on 2016/5/20.
 */
public interface IItemViewProviderSelector<T> {
    boolean isForProvider(int position, T entity);
}
