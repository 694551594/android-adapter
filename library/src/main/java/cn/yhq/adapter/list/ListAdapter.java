package cn.yhq.adapter.list;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public abstract class ListAdapter<I> extends BaseAdapter<List<I>, I> {

    public ListAdapter(Context context) {
        this(context, null);
    }

    public ListAdapter(Context context, List<I> listData) {
        super(context, listData);
    }

    @Override
    public I getItem(int position) {
        if (position < 0 || position > this.getListData().size() - 1) {
            return null;
        }
        return this.getListData().get(position);
    }

    @Override
    public int getCount() {
        return this.getListData().size();
    }

    @Override
    protected List<I> newInstance() {
        return new ArrayList<I>();
    }

    @Override
    public void clearAllItem() {
        this.getListData().clear();
        this.notifyDataSetChanged();
    }

    @Override
    public void addAllItem(List<I> items) {
        this.getListData().addAll(items);
        this.notifyDataSetChanged();
    }

    @Override
    public void addItem(I item) {
        this.getListData().add(item);
        this.notifyDataSetChanged();
    }

    @Override
    public void removeItem(I item) {
        this.getListData().remove(item);
        this.notifyDataSetChanged();
    }

    @Override
    public void removeItem(int position) {
        this.getListData().remove(position);
        this.notifyDataSetChanged();
    }

}
