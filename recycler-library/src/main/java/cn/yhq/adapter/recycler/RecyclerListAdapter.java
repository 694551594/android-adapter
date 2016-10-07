package cn.yhq.adapter.recycler;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerListAdapter<T> extends RecyclerBaseAdapter<List<T>, T> {

  public RecyclerListAdapter(Context context) {
    super(context);
  }

  public RecyclerListAdapter(Context context, List<T> listData) {
    super(context, listData);
  }

  @Override
  public T getItem(int position) {
    return this.getListData().get(position);
  }

  @Override
  protected List<T> newInstance() {
    return new ArrayList<T>();
  }

  @Override
  public int getItemCount() {
    return this.getListData().size();
  }

}
