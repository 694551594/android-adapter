package cn.yhq.adapter.app;

import android.content.Context;

import java.util.List;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.adapter.expand.BaseExpandableListAdapter;
import cn.yhq.adapter.expand.ChildItemViewProvider1;
import cn.yhq.adapter.expand.GroupItemViewProvider1;
import cn.yhq.adapter.expand.IChildItemViewProviderKeyPolicy;
import cn.yhq.adapter.expand.IGroupItemViewProviderKeyPolicy;

/**
 * Created by Administrator on 2016/10/8.
 */

public class MutipleTypeExpandableListAdapter1
    extends BaseExpandableListAdapter<MutipleTypeGroupObject, MutipleTypeChildObject> {
  public final static int TYPE_1 = 1;
  public final static int TYPE_2 = 2;

  public MutipleTypeExpandableListAdapter1(Context context, List<MutipleTypeGroupObject> list) {
    super(context, list);
    // 注册类型1的视图提供器
    this.register(TYPE_1, new GroupItemViewProvider1<MutipleTypeGroupObject>() {
      @Override
      public int getItemViewLayoutId() {
        return R.layout.layout_item;
      }

      @Override
      public void setupView(ViewHolder viewHolder, int position, MutipleTypeGroupObject entity,
          boolean expand) {
        viewHolder.setText(R.id.item_text, entity.text);
      }
    });
    // 注册类型2的视图提供器
    this.register(TYPE_2, new GroupItemViewProvider1<MutipleTypeGroupObject>() {
      @Override
      public int getItemViewLayoutId() {
        return R.layout.layout_item;
      }

      @Override
      public void setupView(ViewHolder viewHolder, int position, MutipleTypeGroupObject entity,
          boolean expand) {
        viewHolder.setText(R.id.item_text, entity.text);
      }
    });
    // 注册类型1的视图提供器
    this.register(TYPE_1,
        new ChildItemViewProvider1<MutipleTypeGroupObject, MutipleTypeChildObject>() {
          @Override
          public int getItemViewLayoutId() {
            return R.layout.layout_item;
          }

          @Override
          public void setupView(ViewHolder viewHolder, int groupPosition,
              MutipleTypeGroupObject groupEntity, int childPosition,
              MutipleTypeChildObject childEntity) {
            viewHolder.setText(R.id.item_text, childEntity.text);
          }
        });
    // 注册类型2的视图提供器
    this.register(TYPE_2,
        new ChildItemViewProvider1<MutipleTypeGroupObject, MutipleTypeChildObject>() {
          @Override
          public int getItemViewLayoutId() {
            return R.layout.layout_item;
          }

          @Override
          public void setupView(ViewHolder viewHolder, int groupPosition,
              MutipleTypeGroupObject groupEntity, int childPosition,
              MutipleTypeChildObject childEntity) {
            viewHolder.setText(R.id.item_text, childEntity.text);
          }
        });
    // 设置视图提供器的选择策略
    this.setGroupItemViewProviderKeyPolicy(
        new IGroupItemViewProviderKeyPolicy<MutipleTypeGroupObject>() {
          @Override
          public int getItemViewProviderKey(int position, MutipleTypeGroupObject entity) {
            if (entity.type == MutipleTypeGroupObject.TYPE_1) {
              return TYPE_1;
            } else if (entity.type == MutipleTypeGroupObject.TYPE_2) {
              return TYPE_2;
            }
            return -1;
          }
        });
    this.setChildItemViewProviderKeyPolicy(
        new IChildItemViewProviderKeyPolicy<MutipleTypeGroupObject, MutipleTypeChildObject>() {
          @Override
          public int getItemViewProviderKey(int groupPosition, MutipleTypeGroupObject groupEntity,
              int childPosition, MutipleTypeChildObject childEntity) {
            if (childEntity.type == MutipleTypeChildObject.TYPE_1) {
              return TYPE_1;
            } else if (childEntity.type == MutipleTypeChildObject.TYPE_2) {
              return TYPE_2;
            }
            return -1;
          }
        });
  }

  @Override
  public MutipleTypeChildObject getChild(MutipleTypeGroupObject group, int childPosition) {
    return group.childs.get(childPosition);
  }

  @Override
  public int getChildrenCount(MutipleTypeGroupObject group) {
    return group.childs.size();
  }

}
