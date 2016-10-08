package cn.yhq.adapter.app;

import android.content.Context;

import java.util.List;

import cn.yhq.adapter.recycler.ItemViewProvider2;
import cn.yhq.adapter.recycler.RecyclerListAdapter;
import cn.yhq.adapter.recycler.ViewHolder;

/**
 * Created by Administrator on 2016/10/8.
 */

public class MutipleTypeRecyclerAdapter2 extends RecyclerListAdapter<MutipleTypeObject> {
  public final static int TYPE_1 = 1;
  public final static int TYPE_2 = 2;

  public MutipleTypeRecyclerAdapter2(Context context, List<MutipleTypeObject> list) {
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
      public void onBindViewHolder(ViewHolder viewHolder, int position, MutipleTypeObject entity) {
        super.onBindViewHolder(viewHolder, position, entity);
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
      public void onBindViewHolder(ViewHolder viewHolder, int position, MutipleTypeObject entity) {
        super.onBindViewHolder(viewHolder, position, entity);
        viewHolder.bindTextData(R.id.item_text, entity.text);
      }
    });

  }

}
