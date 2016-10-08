package cn.yhq.adapter.app;

import android.content.Context;

import cn.yhq.adapter.list.BaseAdapter;

/**
 * Created by Administrator on 2016/10/8.
 */

public class ListObjectAdapter extends BaseAdapter<ListObject, String> {

    public ListObjectAdapter(Context context, ListObject listData) {
        super(context, listData);
    }

    public ListObjectAdapter(Context context) {
        super(context);
    }

    @Override
    protected ListObject newInstance() {
        return new ListObject();
    }

    @Override
    public void clearAllItem() {

    }

    @Override
    public void addAllItem(ListObject items) {

    }

    @Override
    public void addItem(String item) {

    }

    @Override
    public void removeItem(String item) {

    }

    @Override
    public void removeItem(int position) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public String getItem(int position) {
        return null;
    }
}
