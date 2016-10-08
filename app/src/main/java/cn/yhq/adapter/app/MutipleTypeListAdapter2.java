package cn.yhq.adapter.app;

import android.content.Context;

import java.util.List;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.adapter.list.ItemViewProvider2;
import cn.yhq.adapter.list.ListAdapter;

/**
 * Created by Administrator on 2016/10/8.
 */

public class MutipleTypeListAdapter2 extends ListAdapter<MutipleTypeObject> {

  public MutipleTypeListAdapter2(Context context, List<MutipleTypeObject> list) {
    super(context, list);
    // 注册类型1的视图提供器
    this.register(new ItemViewProvider2<MutipleTypeObject>() {
      @Override
      public boolean isForProvider(int position, MutipleTypeObject entity) {
        return entity.type == MutipleTypeObject.TYPE_1;
      }

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
    this.register(new ItemViewProvider2<MutipleTypeObject>() {
      @Override
      public boolean isForProvider(int position, MutipleTypeObject entity) {
        return entity.type == MutipleTypeObject.TYPE_2;
      }

      @Override
      public int getItemViewLayoutId() {
        return R.layout.layout_item;
      }

      @Override
      public void setupView(ViewHolder viewHolder, int position, MutipleTypeObject entity) {
        viewHolder.bindTextData(R.id.item_text, entity.text);
      }
    });
  }
}
