package cn.yhq.adapter.expand;

/**
 * Created by Administrator on 2016/5/20.
 */
public interface IGroupItemViewProviderSelector<T> {
    boolean isForProvider(int position, T entity);
}
