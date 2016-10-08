package cn.yhq.adapter.recycler;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


public abstract class RecyclerBaseAdapter<L, I> extends RecyclerAdapter<ViewHolder> {
  private ItemViewProviderFactory<I> mItemViewProviderFactory;
  private Context mContext;
  protected L mListData;

  public RecyclerBaseAdapter(Context context) {
    this(context, null);
  }

  public RecyclerBaseAdapter(Context context, L listData) {
    this.mContext = context;
    if (listData == null) {
      mListData = newInstance();
    } else {
      mListData = listData;
    }
    this.mItemViewProviderFactory = new ItemViewProviderFactory<I>(context, this);
  }

  @Override
  public final void onBindViewHolder(ViewHolder holder, int position) {
    this.mItemViewProviderFactory.onBindViewHolder(holder, position, this.getItem(position));
  }

  public final IItemViewProvider<I> register(ItemViewProvider2<I> itemViewProvider) {
    return mItemViewProviderFactory.register(itemViewProvider);
  }

  public final IItemViewProvider<I> register(
      Class<? extends ItemViewProvider2<I>> itemViewProviderClass) {
    return mItemViewProviderFactory.register(itemViewProviderClass);
  }

  public final IItemViewProvider<I> register(int key, ItemViewProvider1<I> itemViewProvider) {
    return mItemViewProviderFactory.register(key, itemViewProvider);
  }

  public final IItemViewProvider<I> register(int key,
      Class<? extends ItemViewProvider1<I>> itemViewProviderClass) {
    return mItemViewProviderFactory.register(key, itemViewProviderClass);
  }

  public final void setItemViewProviderKeyPolicy(
      IItemViewProviderKeyPolicy<I> itemViewProviderKeyPolicy) {
    this.mItemViewProviderFactory.setItemViewProviderKeyPolicy(itemViewProviderKeyPolicy);
  }

  public abstract I getItem(int position);

  protected abstract L newInstance();

  public final L getListData() {
    return mListData;
  }

  public final void setListData(L dataList) {
    this.mListData = dataList;
  }

  @Override
  public final View onCreateView(ViewGroup parent, int viewType) {
    View itemView = mItemViewProviderFactory.onCreateItemView(parent, viewType);
    return itemView;
  }

  @Override
  public final int getItemViewType(int position) {
    return mItemViewProviderFactory.getItemViewType(position, this.getItem(position));
  }

  public final Context getContext() {
    return this.mContext;
  }

  public abstract void clearAllItem();

  public abstract void addAllItem(L items);

  public abstract void addItem(I item);

  public abstract void removeItem(I item);

  public abstract void removeItem(int position);
}
