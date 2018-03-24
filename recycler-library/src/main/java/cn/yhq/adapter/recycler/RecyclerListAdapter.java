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

    @Override
    public void clearAllItem() {
        this.getListData().clear();
        this.notifyDataSetChanged();
    }

    @Override
    public void addAllItem(List<T> items) {
        this.getListData().addAll(items);
        this.notifyItemRangeChanged(this.getListData().size() - items.size() - 1, items.size());
    }

    @Override
    public void addItem(T item) {
        this.getListData().add(item);
        this.notifyItemChanged(this.getListData().size());
    }

    @Override
    public void removeItem(T item) {
        int position = this.getListData().indexOf(item);
        this.getListData().remove(item);
        this.notifyItemRemoved(position);
    }

    @Override
    public void removeItem(int position) {
        this.getListData().remove(position);
        this.notifyItemRemoved(position);
    }
}
