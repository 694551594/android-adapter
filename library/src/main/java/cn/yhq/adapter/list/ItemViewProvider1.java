package cn.yhq.adapter.list;

import cn.yhq.adapter.core.ItemView;

/**
 * 必须使用key注册的provider
 *
 * @param <T>
 */
public abstract class ItemViewProvider1<T> extends ItemView<android.widget.BaseAdapter>
        implements
        IItemViewProvider<T> {

}
