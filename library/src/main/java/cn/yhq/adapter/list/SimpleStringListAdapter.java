package cn.yhq.adapter.list;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.yhq.adapter.core.ViewHolder;


/**
 * 只有一种类型的item的list-adapter
 *
 * Created by Yanghuiqiang on 2016/9/28.
 */

public class SimpleStringListAdapter extends SimpleListAdapter<String> {

  SimpleStringListAdapter(Context context, List<String> items) {
    super(context, items, android.R.layout.simple_list_item_1,
        new SimpleListAdapter.IItemViewSetup<String>() {

          @Override
          public void setupView(ViewHolder viewHolder, int position, String entity) {
            viewHolder.bindTextData(android.R.id.text1, entity);
          }
        });
  }

  public static SimpleStringListAdapter create(Context context, List<String> items) {
    return new SimpleStringListAdapter(context, items);
  }

  public static SimpleStringListAdapter create(Context context, String[] items) {
    return new SimpleStringListAdapter(context, Arrays.asList(items));
  }

  public static SimpleStringListAdapter create(Context context) {
    return new SimpleStringListAdapter(context, new ArrayList<String>());
  }

}
