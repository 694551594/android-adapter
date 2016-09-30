package cn.yhq.adapter.core;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * itemview类图提供器的工厂基类
 *
 * 注册的key值可以为任意的整数
 *
 * @param <Adapter>
 * @param <T>
 */
public abstract class ItemViewFactory<Adapter, T extends ItemView<Adapter>> {
  protected ItemViewContainer<Adapter> mItemViewContainer;
  protected Context mContext;

  public ItemViewFactory(Context context, Adapter adapter) {
    this.mContext = context;
    this.mItemViewContainer = new ItemViewContainer<>(context, adapter);
  }

  protected final T obtainItemView(IItemViewKeyPolicy keyPolicy, IItemViewSelector<T> selector) {
    // 首先通过效率比较高的IItemViewKeyPolicy查找
    T itemView = this.getItemViewByKeyPolicy(keyPolicy);

    // 如果没查找到，则通过IItemViewSelector遍历查找
    if (itemView == null) {
      itemView = this.getItemViewBySelector(selector);
    }

    // 否则程序无法判断你要返回的是哪一个
    return itemView;
  }

  /**
   * 注册itemview，这种方式注册的必须使用type才能获取到
   *
   * @param itemView
   * @return
   */
  public final T register(T itemView) {
    return (T) mItemViewContainer.put(itemView);
  }

  /**
   * 注册itemview，这种方式注册的必须使用type才能获取到
   *
   * @param itemViewClass
   * @return
   */
  public final T register(Class<? extends T> itemViewClass) {
    return (T) mItemViewContainer.put(itemViewClass);
  }

  /**
   * 根据key注册itemview，这种方式注册的可以使用key或者type获取到
   *
   * @param key
   * @param itemView
   * @return
   */
  public final T register(int key, T itemView) {
    return (T) mItemViewContainer.put(key, itemView);
  }

  /**
   * 根据key注册itemview，这种方式注册的可以使用key或者type获取到
   *
   * @param key
   * @param itemViewClass
   * @return
   */
  public final T register(int key, Class<? extends T> itemViewClass) {
    return (T) mItemViewContainer.put(key, itemViewClass);
  }

  /**
   * 根据key获取itemview
   *
   * @param key
   * @return
   */
  protected final T getItemViewByKey(int key) {
    return (T) mItemViewContainer.getByKey(key);
  }

  /**
   * 根据type的值获取itemview
   *
   * @param type
   * @return
   */
  protected final T getItemViewByType(int type) {
    return (T) mItemViewContainer.getByType(type);
  }

  /**
   * 根据选择器获取itemview
   *
   * @param selector
   * @return
   */
  protected final T getItemViewBySelector(IItemViewSelector<T> selector) {
    if (selector == null) {
      return null;
    }
    for (T itemView : this.getAllItemView()) {
      if (selector.isForItemView(itemView)) {
        return itemView;
      }
    }
    return null;
  }

  protected final T getItemViewByKeyPolicy(IItemViewKeyPolicy keyPolicy) {
    if (keyPolicy == null) {
      return null;
    }
    int key = keyPolicy.getItemViewKey();
    return this.getItemViewByKey(key);
  }

  /**
   * 获取注册的itemview的总个数
   *
   * @return
   */
  protected final int getItemViewSize() {
    return mItemViewContainer.getSize();
  }

  /**
   * 获取所有注册的itemview
   *
   * @return
   */
  protected final List<T> getAllItemView() {
    List<ItemView<Adapter>> itemViewTypes = this.mItemViewContainer.getAll();
    List<T> list = new ArrayList<>();
    for (int i = 0; i < itemViewTypes.size(); i++) {
      list.add((T) itemViewTypes.get(i));
    }
    return list;
  }
}
