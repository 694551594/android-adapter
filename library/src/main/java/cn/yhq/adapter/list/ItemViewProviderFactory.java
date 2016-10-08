package cn.yhq.adapter.list;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import cn.yhq.adapter.core.IItemViewKeyPolicy;
import cn.yhq.adapter.core.IItemViewSelector;
import cn.yhq.adapter.core.ItemViewFactory;
import cn.yhq.adapter.core.ViewHolder;

/**
 * listview-adapter item视图提供器工厂类
 *
 * @param <T>
 */
public final class ItemViewProviderFactory<T>
    extends ItemViewFactory<BaseAdapter, ItemViewProvider1<T>> {
  private IItemViewProviderKeyPolicy<T> mItemViewProviderKeyPolicy;

  public ItemViewProviderFactory(Context context, BaseAdapter adapter) {
    super(context, adapter);
  }

  public final void setItemViewProviderKeyPolicy(
      IItemViewProviderKeyPolicy<T> itemViewProviderKeyPolicy) {
    this.mItemViewProviderKeyPolicy = itemViewProviderKeyPolicy;
  }

  private final ItemViewProvider1<T> obtainItemViewProvider(final int position, final T entity) {
    return this.obtainItemView(new IItemViewKeyPolicy() {
      @Override
      public int getItemViewKey() {
        if (mItemViewProviderKeyPolicy != null) {
          return mItemViewProviderKeyPolicy.getItemViewProviderKey(position, entity);
        }
        return -1;
      }
    }, new IItemViewSelector<ItemViewProvider1<T>>() {
      @Override
      public boolean isForItemView(ItemViewProvider1<T> itemView) {
        if (itemView instanceof IItemViewProviderSelector) {
          return ((IItemViewProviderSelector) itemView).isForProvider(position, entity);
        }
        return false;
      }
    });
  }

  public final List<ItemViewProvider1<T>> getAllItemViewProvider() {
    return this.getAllItemView();
  }

  public final int getItemViewType(int position, T entity) {
    return this.obtainItemViewProvider(position, entity).getType();
  }

  public final int getItemViewTypeCount() {
    return this.getItemViewSize();
  }

  public final View setupView(int position, T entity, View convertView, ViewGroup parent) {
    try {
      // 获取该item类型的视图提供器
      IItemViewProvider<T> itemViewProvider = this.obtainItemViewProvider(position, entity);
      // 获取视图id
      int layoutId = itemViewProvider.getItemViewLayoutId();
      // 获取viewholder
      ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent, layoutId, position);
      // 组装视图
      itemViewProvider.setupView(viewHolder, position, entity);
      return viewHolder.getConvertView();
    } catch (Exception | Error e) {
      e.printStackTrace();
      return new View(mContext);
    }

  }

  public final ItemViewProvider1<T> getItemViewProviderByKey(int key) {
    return this.getItemViewByKey(key);
  }

}
