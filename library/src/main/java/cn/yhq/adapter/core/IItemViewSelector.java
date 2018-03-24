package cn.yhq.adapter.core;

/**
 * itemview的选择器
 * <p>
 * 判断entity或者position的item是否是当前的itemview
 * <p>
 * Created by Yanghuiqiang on 2016/5/20.
 */
public interface IItemViewSelector<T> {
    boolean isForItemView(T itemView);
}
