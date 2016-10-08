package cn.yhq.adapter.list;

import android.content.Context;

import java.util.List;

import cn.yhq.adapter.core.ItemData;
import cn.yhq.adapter.core.ViewHolder;

public abstract class CommonListAdapter extends ListAdapter<ItemData> {

  private static class ItemViewProvider1Wrapper<T> extends ItemViewProvider1<ItemData> {

    private ItemViewProvider1<T> itemViewProvider;

    public ItemViewProvider1Wrapper(ItemViewProvider1<T> itemViewProvider) {
      this.itemViewProvider = itemViewProvider;
    }

    @Override
    public int getItemViewLayoutId() {
      return itemViewProvider.getItemViewLayoutId();
    }

    @Override
    public void setupView(ViewHolder viewHolder, int position, ItemData entity) {
      itemViewProvider.setupView(viewHolder, position, (T) entity.getData());
    }
  }

  private static class ItemViewProvider2Wrapper<T> extends ItemViewProvider2<ItemData> {

    private ItemViewProvider2<T> itemViewProvider;

    public ItemViewProvider2Wrapper(ItemViewProvider2<T> itemViewProvider) {
      this.itemViewProvider = itemViewProvider;
    }

    @Override
    public int getItemViewLayoutId() {
      return itemViewProvider.getItemViewLayoutId();
    }

    @Override
    public void setupView(ViewHolder viewHolder, int position, ItemData entity) {
      itemViewProvider.setupView(viewHolder, position, (T) entity.getData());
    }

    @Override
    public boolean isForProvider(int position, ItemData entity) {
      return itemViewProvider.isForProvider(position, (T) entity.getData());
    }
  }

  public CommonListAdapter(Context context) {
    super(context);
  }

  public CommonListAdapter(Context context, List<ItemData> listData) {
    super(context, listData);
  }

  public void appendItemData(List<Object> list) {
    for (Object o : list) {
      this.getListData().add(new ItemData(o));
    }
  }

  public <T> CommonListAdapter registerItemViewProvider(int key,
      ItemViewProvider1<T> itemViewProvider) {
    super.register(key, new ItemViewProvider1Wrapper<>(itemViewProvider));
    return this;
  }

  public <T> CommonListAdapter registerItemViewProvider(ItemViewProvider2<T> itemViewProvider) {
    super.register(new ItemViewProvider2Wrapper<>(itemViewProvider));
    return this;
  }

  public <T> CommonListAdapter setItemViewProviderKeyPolicy1(
      final IItemViewProviderKeyPolicy<T> itemViewProviderKeyPolicy) {
    this.setItemViewProviderKeyPolicy(new IItemViewProviderKeyPolicy<ItemData>() {
      @Override
      public int getItemViewProviderKey(int position, ItemData entity) {
        return itemViewProviderKeyPolicy.getItemViewProviderKey(position, (T) entity.getData());
      }
    });
    return this;
  }

}


