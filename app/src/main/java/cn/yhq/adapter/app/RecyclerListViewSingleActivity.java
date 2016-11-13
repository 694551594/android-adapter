package cn.yhq.adapter.app;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.adapter.recycler.SimpleRecyclerListAdapter;
import cn.yhq.base.BaseActivity;

public class RecyclerListViewSingleActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.simple_recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    SimpleRecyclerListAdapter<String> adapter = SimpleRecyclerListAdapter.create(this, getDatas(),
        R.layout.layout_item, new SimpleRecyclerListAdapter.IItemViewSetup<String>() {
          @Override
          public void setupView(cn.yhq.adapter.recycler.ViewHolder viewHolder, int position,
              String entity) {
            viewHolder.setText(R.id.item_text, entity);
          }
        });

    recyclerView.setAdapter(adapter);
  }

  @Override
  protected int getContentViewLayoutId() {
    return R.layout.activity_recycler_view;
  }

  private List<String> getDatas() {
    List<String> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add("item" + i);
    }
    return list;
  }
}
