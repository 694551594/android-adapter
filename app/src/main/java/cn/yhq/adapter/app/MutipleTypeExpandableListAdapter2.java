package cn.yhq.adapter.app;

import android.content.Context;

import java.util.List;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.adapter.expand.BaseExpandableListAdapter;
import cn.yhq.adapter.expand.ChildItemViewProvider2;
import cn.yhq.adapter.expand.GroupItemViewProvider2;

/**
 * Created by Administrator on 2016/10/8.
 */

public class MutipleTypeExpandableListAdapter2
    extends BaseExpandableListAdapter<MutipleTypeGroupObject, MutipleTypeChildObject> {
  public final static int TYPE_1 = 1;
  public final static int TYPE_2 = 2;

  public MutipleTypeExpandableListAdapter2(Context context, List<MutipleTypeGroupObject> list) {
    super(context, list);
    // 注册类型1的视图提供器
    this.register(new GroupItemViewProvider2<MutipleTypeGroupObject>() {
      @Override
      public boolean isForProvider(int position, MutipleTypeGroupObject entity) {
        return entity.type == MutipleTypeGroupObject.TYPE_1;
      }

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
    this.register(new GroupItemViewProvider2<MutipleTypeGroupObject>() {

      @Override
      public boolean isForProvider(int position, MutipleTypeGroupObject entity) {
        return entity.type == MutipleTypeGroupObject.TYPE_2;
      }

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
    this.register(new ChildItemViewProvider2<MutipleTypeGroupObject, MutipleTypeChildObject>() {
      @Override
      public boolean isForProvider(int groupPosition, MutipleTypeGroupObject groupEntity,
          int childPosition, MutipleTypeChildObject childEntity) {
        return childEntity.type == MutipleTypeGroupObject.TYPE_1;
      }

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
    this.register(new ChildItemViewProvider2<MutipleTypeGroupObject, MutipleTypeChildObject>() {
      @Override
      public boolean isForProvider(int groupPosition, MutipleTypeGroupObject groupEntity,
          int childPosition, MutipleTypeChildObject childEntity) {
        return childEntity.type == MutipleTypeGroupObject.TYPE_2;
      }

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
