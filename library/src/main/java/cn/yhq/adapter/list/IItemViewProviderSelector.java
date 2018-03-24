package cn.yhq.adapter.list;

/**
 * itemviewprovider的选择器
 * <p>
 * 判断entity或者position的item provider是否是当前的provider
 * <p>
 * Created by Yanghuiqiang on 2016/5/20.
 */
public interface IItemViewProviderSelector<T> {
    boolean isForProvider(int position, T entity);
}
