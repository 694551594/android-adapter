package cn.yhq.adapter.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.yhq.adapter.core.IItemViewKeyPolicy;
import cn.yhq.adapter.core.IItemViewSelector;
import cn.yhq.adapter.core.ItemViewFactory;


public final class ItemViewProviderFactory<T>
    extends ItemViewFactory<Adapter<ViewHolder>, ItemViewProvider<T>> {
  private IItemViewProviderKeyPolicy<T> mItemViewProviderKeyPolicy;

  public ItemViewProviderFactory(Context context, Adapter<ViewHolder> adapter) {
    super(context, adapter);
  }

  private ItemViewProvider<T> obtainItemViewProvider(final int position, final T entity) {
    return this.obtainItemView(new IItemViewKeyPolicy() {
      @Override
      public int getItemViewKey() {
        if (mItemViewProviderKeyPolicy != null) {
          return mItemViewProviderKeyPolicy.getItemViewProviderKey(position, entity);
        }
        return -1;
      }
    }, new IItemViewSelector<ItemViewProvider<T>>() {
      @Override
      public boolean isForItemView(ItemViewProvider<T> itemView) {
        if (itemView instanceof IItemViewProviderSelector) {
          return ((IItemViewProviderSelector) itemView).isForProvider(position, entity);
        }
        return false;
      }
    });
  }

  public void setItemViewProviderKeyPolicy(
      IItemViewProviderKeyPolicy<T> itemViewProviderKeyPolicy) {
    this.mItemViewProviderKeyPolicy = itemViewProviderKeyPolicy;
  }

  public int getItemViewType(int position, T entity) {
    return obtainItemViewProvider(position, entity).getType();
  }

  public void onBindViewHolder(ViewHolder holder, int position, T entity) {
    IItemViewProvider<T> itemViewProvider = this.obtainItemViewProvider(position, entity);
    itemViewProvider.onBindViewHolder(holder, position, entity);
  }

  public View onCreateItemView(ViewGroup parent, int viewType) {
    IItemViewProvider<T> itemViewProvider = this.getItemViewByType(viewType);
    int layoutId = itemViewProvider.getItemViewLayoutId();
    return LayoutInflater.from(mContext).inflate(layoutId, parent, false);
  }
}
