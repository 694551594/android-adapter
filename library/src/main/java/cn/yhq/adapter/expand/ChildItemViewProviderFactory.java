package cn.yhq.adapter.expand;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

import cn.yhq.adapter.core.IItemViewKeyPolicy;
import cn.yhq.adapter.core.IItemViewSelector;
import cn.yhq.adapter.core.ItemViewFactory;
import cn.yhq.adapter.core.ViewHolder;

public final class ChildItemViewProviderFactory<G, C>
    extends ItemViewFactory<BaseExpandableListAdapter, ChildItemViewProvider1<G, C>> {
  private IChildItemViewProviderKeyPolicy<G, C> mChildItemViewProviderKeyPolicy;

  public ChildItemViewProviderFactory(Context context, BaseExpandableListAdapter adapter) {
    super(context, adapter);
  }

  public final void setChildItemViewProviderKeyPolicy(
      IChildItemViewProviderKeyPolicy<G, C> childItemViewProviderKeyPolicy) {
    this.mChildItemViewProviderKeyPolicy = childItemViewProviderKeyPolicy;
  }

  private final ChildItemViewProvider1<G, C> obtainItemViewProvider(final int groupPosition,
                                                              final G groupEntity, final int childPosition, final C childEntity) {
    return this.obtainItemView(new IItemViewKeyPolicy() {
      @Override
      public int getItemViewKey() {
        if (mChildItemViewProviderKeyPolicy != null) {
          return mChildItemViewProviderKeyPolicy.getItemViewProviderKey(groupPosition, groupEntity,
              childPosition, childEntity);
        }
        return -1;
      }
    }, new IItemViewSelector<ChildItemViewProvider1<G, C>>() {
      @Override
      public boolean isForItemView(ChildItemViewProvider1<G, C> itemView) {
        if (itemView instanceof IChildItemViewProviderSelector) {
          return ((IChildItemViewProviderSelector) itemView).isForProvider(groupPosition,
              groupEntity, childPosition, childEntity);
        }
        return false;
      }
    });
  }

  public final int getItemViewType(int groupPosition, G groupEntity, int childPosition, C childEntity) {
    return obtainItemViewProvider(groupPosition, groupEntity, childPosition, childEntity).getType();
  }

  public final int getItemViewTypeCount() {
    return this.getItemViewSize();
  }

  public final List<ChildItemViewProvider1<G, C>> getAllItemViewProvider() {
    return this.getAllItemView();
  }

  public final View setupView(int groupPosition, G groupEntity, int childPosition, C childEntity,
      View convertView, ViewGroup parent) {
    try {
      // 获取该item类型的视图提供器
      IChildItemViewProvider<G, C> itemViewProvider =
          this.obtainItemViewProvider(groupPosition, groupEntity, childPosition, childEntity);
      // 获取视图id
      int layoutId = itemViewProvider.getItemViewLayoutId();
      // 获取viewholder
      ViewHolder viewHolder =
          ViewHolder.get(mContext, convertView, parent, layoutId, childPosition);
      // 组装视图
      itemViewProvider.setupView(viewHolder, groupPosition, groupEntity, childPosition,
          childEntity);
      return viewHolder.getConvertView();
    } catch (Exception | Error e) {
      e.printStackTrace();
      return new View(mContext);
    }
  }

  public final ChildItemViewProvider1<G, C> getItemViewProviderByKey(int key) {
    return this.getItemViewByKey(key);
  }


}
