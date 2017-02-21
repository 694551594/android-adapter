package cn.yhq.adapter.list;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.adapter.core.ViewHolderFactory;

/**
 * base adapter
 * 
 * @author Yanghuiqiang 2015-3-13
 * 
 * @param <L> list的类型
 * @param <I> item的类型
 */
public abstract class BaseAdapter<L, I> extends android.widget.BaseAdapter {
  protected L mListData;
  private ItemViewProviderFactory<I> mItemViewProviderFactory;
  protected Context mContext;

  public BaseAdapter(Context context, L listData) {
    this.mContext = context;
    if (listData == null) {
      mListData = newInstance();
    } else {
      mListData = listData;
    }

    this.mItemViewProviderFactory = new ItemViewProviderFactory<>(context, this);
  }

  protected abstract L newInstance();

  public BaseAdapter(Context context) {
    this(context, null);
  }

  public static <T extends ViewHolder> void setViewHolderFactory(ViewHolderFactory<T> factory) {
    ItemViewProviderFactory.setViewHolderFactory(factory);
  }

  public final BaseAdapter<L, I> register(int key, ItemViewProvider1<I> itemViewProvider) {
    mItemViewProviderFactory.register(key, itemViewProvider);
    return this;
  }

  public BaseAdapter<L, I> register(int key,
      Class<? extends ItemViewProvider1<I>> itemViewProviderClass) {
    mItemViewProviderFactory.register(key, itemViewProviderClass);
    return this;
  }

  public final BaseAdapter<L, I> setItemViewProviderKeyPolicy(
      IItemViewProviderKeyPolicy<I> itemViewProviderKeyPolicy) {
    this.mItemViewProviderFactory.setItemViewProviderKeyPolicy(itemViewProviderKeyPolicy);
    return this;
  }

  public final BaseAdapter<L, I> register(ItemViewProvider2<I> itemViewProvider) {
    mItemViewProviderFactory.register(itemViewProvider);
    return this;
  }

  public final BaseAdapter<L, I> register(
      Class<? extends ItemViewProvider2<I>> itemViewProviderClass) {
    mItemViewProviderFactory.register(itemViewProviderClass);
    return this;
  }

  @SuppressWarnings("unchecked")
  public final <T extends IItemViewProvider<I>> List<T> getAllItemViewProvider() {
    List<? extends IItemViewProvider<I>> providers =
        mItemViewProviderFactory.getAllItemViewProvider();
    List<T> list = new ArrayList<>();
    for (IItemViewProvider<I> provider : providers) {
      list.add((T) provider);
    }
    return list;
  }

  public abstract void clearAllItem();

  public abstract void addAllItem(L items);

  public abstract void addItem(I item);

  public abstract void removeItem(I item);

  public abstract void removeItem(int position);

  public final L getListData() {
    return mListData;
  }

  public final void setListData(L dataList) {
    this.mListData = dataList;
  }

  public abstract I getItem(int position);

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public final int getItemViewType(int position) {
    return mItemViewProviderFactory.getItemViewType(position, this.getItem(position));
  }

  @Override
  public final int getViewTypeCount() {
    return mItemViewProviderFactory.getItemViewTypeCount();
  }

  @Override
  public final View getView(int position, View convertView, ViewGroup parent) {
    return this.mItemViewProviderFactory.setupView(position, this.getItem(position), convertView,
        parent);
  }

  public final Context getContext() {
    return mContext;
  }

  public final ItemViewProviderFactory<I> getItemViewProviderFactory() {
    return mItemViewProviderFactory;
  }

  public final ItemViewProvider1<I> getItemViewProvider(int key) {
    return mItemViewProviderFactory.getItemViewProviderByKey(key);
  }


}
