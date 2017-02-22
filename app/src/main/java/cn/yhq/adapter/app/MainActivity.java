package cn.yhq.adapter.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.yhq.adapter.core.ImageLoader;
import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.adapter.list.SimpleListAdapter;
import cn.yhq.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onConfig(Config config) {
        super.onConfig(config);
        config.setSwipeBackWrapper(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ListView listView = (ListView) this.findViewById(R.id.simple_listview);
        ViewHolder.setImageLoader(new ImageLoader() {
            @Override
            public void setImage(View imageView, File file) {

            }

            @Override
            public void setImage(View imageView, String url) {

            }

            @Override
            public void setImage(View imageView, int resId) {

            }

            @Override
            public void setImage(View imageView, Bitmap bitmap) {

            }

            @Override
            public void setImage(View imageView, Drawable drawable) {

            }
        });
        SimpleListAdapter<String> adapter = SimpleListAdapter.create(this, getDatas(), R.layout.layout_item,
                new SimpleListAdapter.IItemViewSetup<String>() {
                    @Override
                    public void setupView(ViewHolder viewHolder, int position, String entity) {
                        viewHolder.setText(R.id.item_text, entity);
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

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_main;
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
