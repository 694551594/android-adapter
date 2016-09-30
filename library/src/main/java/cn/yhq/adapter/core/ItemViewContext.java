package cn.yhq.adapter.core;

import android.content.Context;

/**
 * itemview的上下文
 *
 * Created by Yanghuiqiang on 2016/9/29.
 */

class ItemViewContext<Adapter> {
  private int type;
  private Context context;
  private Adapter adapter;

  public ItemViewContext() {}

  final void create(int type, Context context, Adapter adapter) {
    this.type = type;
    this.adapter = adapter;
    this.context = context;
    this.onCreate(this.context, this.adapter, this.type);
  }

  public final int getType() {
    return this.type;
  }

  public final Adapter getAdapter() {
    return adapter;
  }

  public final Context getContext() {
    return context;
  }

  /**
   * itemview创建的回调方法
   *
   * @param context
   * @param adapter
   * @param type
     */
  public void onCreate(Context context, Adapter adapter, int type) {

  }

}
