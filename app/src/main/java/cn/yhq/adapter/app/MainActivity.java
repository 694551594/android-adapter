package cn.yhq.adapter.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.adapter.list.SimpleListAdapter;
import cn.yhq.view.binding.ViewBinder;
import cn.yhq.view.binding.provider.impl.CheckBoxBinding;
import cn.yhq.view.binding.provider.impl.ImageViewBinding;
import cn.yhq.view.binding.provider.impl.TextViewBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) this.findViewById(R.id.simple_listview);
        SimpleListAdapter<String> adapter = SimpleListAdapter.create(this, getDatas(), R.layout.layout_item,
                new SimpleListAdapter.IItemViewSetup<String>() {
                    @Override
                    public void setupView(ViewHolder viewHolder, int position, String entity) {
                        viewHolder.bindTextData(R.id.item_text, entity);
                    }
                });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch(position) {
                    case 0:
                        intent = new Intent(getBaseContext(), ListViewSingleActivity.class);
                        break;
                    case 1:
                        intent = new Intent(getBaseContext(), ListViewMutipleActivity.class);
                        break;
                    case 2:
                        intent = new Intent(getBaseContext(), ExpandableListViewSingleActivity.class);
                        break;
                    case 3:
                        intent = new Intent(getBaseContext(), ExpandableListViewMutipleActivity.class);
                        break;
                    case 4:
                        intent = new Intent(getBaseContext(), RecyclerListViewSingleActivity.class);
                        break;
                    case 5:
                        intent = new Intent(getBaseContext(), RecyclerListViewMutipleActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }

    List<String> getDatas() {
        List<String> list = new ArrayList<>();
        list.add("list-adapter单type");
        list.add("list-adapter多type");
        list.add("expandable-adapter单type");
        list.add("expandable-adapter多type");
        list.add("recycler-adapter单type");
        list.add("recycler-adapter多type");
        return list;
    }
}
