package cn.yhq.adapter.expand;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.yhq.adapter.core.ViewHolder;

public class SimpleExpandableListAdapter<G, C> extends BaseExpandableListAdapter<G, C> {
  private final IItemViewSetup<G, C> itemViewSetup;

  @Override
  public final int getChildrenCount(int groupPosition) {
    return itemViewSetup.getChildrenCount(groupPosition);
  }

  @Override
  public final C getChild(int groupPosition, int childPosition) {
    return itemViewSetup.getChild(groupPosition, childPosition);
  }

  public interface IItemViewSetup<G, C> {
    void setupGroupView(ViewHolder viewHolder, int position, G entity, boolean isExpanded);

    void setupChildView(ViewHolder viewHolder, int groupPosition, G groupEntity, int childPosition,
        C childEntity);

    C getChild(int groupPosition, int childPosition);

    int getChildrenCount(int groupPosition);

  }

  SimpleExpandableListAdapter(Context context, List<G> listData, final int groupItemViewLayoutId,
      final int childItemViewLayoutId, final IItemViewSetup<G, C> itemViewSetup) {
    super(context, listData);
    this.itemViewSetup = itemViewSetup;
    this.register(new GroupItemViewProvider2<G>() {
      @Override
      public void setupView(ViewHolder viewHolder, int position, G entity, boolean isExpanded) {
        itemViewSetup.setupGroupView(viewHolder, position, entity, isExpanded);
      }

      @Override
      public int getItemViewLayoutId() {
        return groupItemViewLayoutId;
      }

      @Override
      public boolean isForProvider(int position, G entity) {
        return true;
      }
    });
    this.register(new ChildItemViewProvider2<G, C>() {
      @Override
      public int getItemViewLayoutId() {
        return childItemViewLayoutId;
      }

      @Override
      public void setupView(ViewHolder viewHolder, int groupPosition, G groupEntity,
          int childPosition, C childEntity) {
        itemViewSetup.setupChildView(viewHolder, groupPosition, groupEntity, childPosition,
            childEntity);
      }

      @Override
      public boolean isForProvider(int groupPosition, G groupEntity, int childPosition,
          C childEntity) {
        return true;
      }
    });
  }

  public static <G, C> SimpleExpandableListAdapter<G, C> create(Context context, List<G> items,
      final int groupItemViewLayoutId, final int childItemViewLayoutId,
      IItemViewSetup itemViewSetup) {
    return new SimpleExpandableListAdapter<>(context, items, groupItemViewLayoutId,
        childItemViewLayoutId, itemViewSetup);
  }

  public static <G, C> SimpleExpandableListAdapter<G, C> create(Context context, G[] items,
      final int groupItemViewLayoutId, final int childItemViewLayoutId,
      IItemViewSetup itemViewSetup) {
    return new SimpleExpandableListAdapter<>(context, Arrays.asList(items), groupItemViewLayoutId,
        childItemViewLayoutId, itemViewSetup);
  }

  public static <G, C> SimpleExpandableListAdapter<G, C> create(Context context,
      final int groupItemViewLayoutId, final int childItemViewLayoutId,
      IItemViewSetup itemViewSetup) {
    return new SimpleExpandableListAdapter<>(context, new ArrayList<G>(), groupItemViewLayoutId,
        childItemViewLayoutId, itemViewSetup);
  }

}
