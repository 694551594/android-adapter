package cn.yhq.adapter.app;

import android.content.Context;

import java.util.List;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.adapter.list.IItemViewProviderKeyPolicy;
import cn.yhq.adapter.list.ItemViewProvider1;
import cn.yhq.adapter.list.ListAdapter;

/**
 * Created by Administrator on 2016/10/8.
 */

public class MutipleTypeListAdapter1 extends ListAdapter<MutipleTypeObject> {
  public final static int TYPE_1 = 1;
  public final static int TYPE_2 = 2;

  public MutipleTypeListAdapter1(Context context, List<MutipleTypeObject> list) {
    super(context, list);
    // 注册类型1的视图提供器
    this.register(TYPE_1, new ItemViewProvider1<MutipleTypeObject>() {
      @Override
      public int getItemViewLayoutId() {
        return R.layout.layout_item;
      }

      @Override
      public void setupView(ViewHolder viewHolder, int position, MutipleTypeObject entity) {
        viewHolder.bindTextData(R.id.item_text, entity.text);
      }
    });
    // 注册类型2的视图提供器
    this.register(TYPE_2, new ItemViewProvider1<MutipleTypeObject>() {
      @Override
      public int getItemViewLayoutId() {
        return R.layout.layout_item;
      }

      @Override
      public void setupView(ViewHolder viewHolder, int position, MutipleTypeObject entity) {
        viewHolder.bindTextData(R.id.item_text, entity.text);
      }
    });
    // 设置视图提供器的选择策略
    this.setItemViewProviderKeyPolicy(new IItemViewProviderKeyPolicy<MutipleTypeObject>() {
      @Override
      public int getItemViewProviderKey(int position, MutipleTypeObject entity) {
        if (entity.type == MutipleTypeObject.TYPE_1) {
          return TYPE_1;
        } else if (entity.type == MutipleTypeObject.TYPE_2) {
          return TYPE_2;
        }
        return -1;
      }
    });
  }

}
