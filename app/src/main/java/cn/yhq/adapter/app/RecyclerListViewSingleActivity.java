package cn.yhq.adapter.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.adapter.recycler.SimpleRecyclerListAdapter;

public class RecyclerListViewSingleActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler_view);

    RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.simple_recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    SimpleRecyclerListAdapter<String> adapter = SimpleRecyclerListAdapter.create(this, getDatas(),
        R.layout.layout_item, new SimpleRecyclerListAdapter.IItemViewSetup<String>() {
          @Override
          public void setupView(cn.yhq.adapter.recycler.ViewHolder viewHolder, int position,
              String entity) {
            viewHolder.bindTextData(R.id.item_text, entity);
          }
        });

    recyclerView.setAdapter(adapter);
  }

  private List<String> getDatas() {
    List<String> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add("item" + i);
    }
    return list;
  }
}
