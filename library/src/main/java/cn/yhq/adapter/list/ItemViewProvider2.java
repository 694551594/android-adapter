package cn.yhq.adapter.list;

/**
 * 不需要使用key注册的provider，但是需要实现一个选择器的接口，用来判断当前的provider是否是需要的provider
 *
 * @param <T>
 */
public abstract class ItemViewProvider2<T> extends ItemViewProvider1<T>
        implements
        IItemViewProviderSelector<T> {


}
