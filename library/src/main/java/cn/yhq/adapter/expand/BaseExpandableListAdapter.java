package cn.yhq.adapter.expand;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseExpandableListAdapter<G, C> extends BaseExpandableAdapter<List<G>, G, C> {
  public BaseExpandableListAdapter(Context context) {
    super(context);
  }

  public BaseExpandableListAdapter(Context context, List<G> listData) {
    super(context, listData);
  }

  @Override
  public int getGroupCount() {
    return this.getListData().size();
  }

  @Override
  protected List<G> newInstance() {
    return new ArrayList<G>();
  }

  @Override
  public G getGroup(int groupPosition) {
    if (groupPosition < 0 || groupPosition > this.getListData().size() - 1) {
      return null;
    }
    return this.getListData().get(groupPosition);
  }

  @Override
  public void clearAllItem() {
    this.getListData().clear();
    this.notifyDataSetChanged();
  }

  @Override
  public void addAllItem(List<G> items) {
    this.getListData().addAll(items);
    this.notifyDataSetChanged();
  }

  @Override
  public void addItem(G item) {
    this.getListData().add(item);
    this.notifyDataSetChanged();
  }

  @Override
  public void removeItem(G item) {
    this.getListData().remove(item);
    this.notifyDataSetChanged();
  }

  @Override
  public void removeItem(int position) {
    this.getListData().remove(position);
    this.notifyDataSetChanged();
  }

}
