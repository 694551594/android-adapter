package cn.yhq.adapter.core;

import android.content.Context;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

/**
 * itemview容器，存放了一个adapter所有类型的的itemview
 *
 * Created by Yanghuiqiang on 2016/9/28.
 */

public final class ItemViewContainer<Adapter> {
  private Adapter mAdapter;
  private SparseArray<ItemView<Adapter>> mItemViews = new SparseArray<>();
  private SparseArray<Integer> mItemViewKeys = new SparseArray<>();
  private Context mContext;

  public ItemViewContainer(Context context, Adapter adapter) {
    this.mAdapter = adapter;
    this.mContext = context;
  }

  /**
   * 获取itemview的种类数
   *
   * @return
   */
  public int getSize() {
    return this.mItemViews.size();
  }

  /**
   * 根据key值查询itemview
   *
   * @param key
   * @return
   */
  public ItemView<Adapter> getByKey(int key) {
    return this.getByType(this.mItemViewKeys.get(key));
  }

  /**
   * 根据type值查询itemview
   *
   * @param type
   * @return
   */
  public ItemView<Adapter> getByType(int type) {
    return mItemViews.get(type);
  }

  /**
   * 获取所有类型的itemview
   *
   * @return
   */
  public List<ItemView<Adapter>> getAll() {
    List<ItemView<Adapter>> list = new ArrayList<>();
    for (int i = 0; i < this.mItemViews.size(); i++) {
      list.add(this.mItemViews.get(this.mItemViews.keyAt(i)));
    }
    return list;
  }

  /**
   * 初始化
   *
   * @param itemView
   */
  private void initItemView(ItemView<Adapter> itemView) {
    int type = getSize();
    itemView.create(type, mContext, mAdapter);
  }

  /**
   * 实例化
   *
   * @param itemViewClass
   */
  private ItemView<Adapter> newInstance(Class<? extends ItemView<Adapter>> itemViewClass) {
    try {
      ItemView<Adapter> itemViewType = itemViewClass.newInstance();
      return itemViewType;
    } catch (Exception e) {
      try {
        ItemView<Adapter> itemViewType =
            (ItemView<Adapter>) itemViewClass.getConstructors()[0].newInstance(mAdapter);
        return itemViewType;
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    }
    return null;
  }

  /**
   * 添加一个itemview
   *
   * @param key
   * @param type
   * @return
   */
  public ItemView<Adapter> put(int key, Class<? extends ItemView<Adapter>> type) {
    ItemView<Adapter> itemView = newInstance(type);
    if (itemView == null) {
      return null;
    }
    return put(key, itemView);
  }

  public ItemView<Adapter> put(Class<? extends ItemView<Adapter>> type) {
    ItemView<Adapter> itemView = newInstance(type);
    if (itemView == null) {
      return null;
    }
    return put(itemView);
  }

  /**
   * 添加一个itemview
   *
   * @param key
   * @param itemView
   * @return
   */
  public ItemView<Adapter> put(int key, ItemView<Adapter> itemView) {
    itemView = put(itemView);
    mItemViewKeys.put(key, itemView.getType());
    return itemView;
  }

  public ItemView<Adapter> put(ItemView<Adapter> itemView) {
    initItemView(itemView);
    mItemViews.put(itemView.getType(), itemView);
    return itemView;
  }

}
