package cn.yhq.adapter.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.adapter.list.SimpleAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) this.findViewById(R.id.simple_listview);
        SimpleAdapter<String> adapter = SimpleAdapter.create(this, getDatas(), R.layout.layout_item1,
                new SimpleAdapter.IItemViewSetup<String>() {
                    @Override
                    public void setupView(ViewHolder viewHolder, int position, String entity) {
                        viewHolder.bindTextData(R.id.item_text, entity);
                    }
                });
        listView.setAdapter(adapter);
    }

    List<String> getDatas() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("item" + i);
        }
        return list;
    }
}
