package cn.yhq.adapter.app;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.adapter.list.SimpleListAdapter;
import cn.yhq.base.BaseActivity;

public class ListViewSingleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = (ListView) this.findViewById(R.id.simple_listview);
        SimpleListAdapter<String> adapter = SimpleListAdapter.create(this, getDatas(), R.layout.layout_item,
                new SimpleListAdapter.IItemViewSetup<String>() {
                    @Override
                    public void setupView(ViewHolder viewHolder, int position, String entity) {
                        viewHolder.setText(R.id.item_text, entity);
                    }
                });
        listView.setAdapter(adapter);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_simple;
    }

    private List<String> getDatas() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("item" + i);
        }
        return list;
    }
}
