package cn.yhq.adapter.recycler;

/**
 * Created by Administrator on 2016/10/8.
 */

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SimpleRecyclerStringListAdapter extends SimpleRecyclerListAdapter<String> {

    SimpleRecyclerStringListAdapter(Context context, List<String> items) {
        super(context, items, android.R.layout.simple_list_item_1,
                new SimpleRecyclerListAdapter.IItemViewSetup<String>() {

                    @Override
                    public void setupView(ViewHolder viewHolder, int position, String entity) {
                        viewHolder.setText(android.R.id.text1, entity);
                    }

                });
    }

    public static SimpleRecyclerStringListAdapter create(Context context, List<String> items) {
        return new SimpleRecyclerStringListAdapter(context, items);
    }

    public static SimpleRecyclerStringListAdapter create(Context context, String[] items) {
        return new SimpleRecyclerStringListAdapter(context, Arrays.asList(items));
    }

    public static SimpleRecyclerStringListAdapter create(Context context) {
        return new SimpleRecyclerStringListAdapter(context, new ArrayList<String>());
    }
}
