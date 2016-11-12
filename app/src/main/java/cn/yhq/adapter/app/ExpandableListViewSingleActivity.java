package cn.yhq.adapter.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.adapter.expand.SimpleExpandableListAdapter;

public class ExpandableListViewSingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);
        ExpandableListView expandableListView = (ExpandableListView) this.findViewById(R.id.simple_expandable_listview);
        SimpleExpandableListAdapter<Group, Child> adapter =
                SimpleExpandableListAdapter.create(this, getDatas(), R.layout.layout_item, R.layout.layout_item, new SimpleExpandableListAdapter.IItemViewSetup<Group, Child>() {
                    @Override
                    public void setupGroupView(ViewHolder viewHolder, int position, Group entity, boolean isExpanded) {
                        viewHolder.setText(R.id.item_text, entity.text);
                    }

                    @Override
                    public void setupChildView(ViewHolder viewHolder, int groupPosition, Group groupEntity, int childPosition, Child childEntity) {
                        viewHolder.setText(R.id.item_text, childEntity.text);
                    }

                    @Override
                    public Child getChild(Group group, int childPosition) {
                        return group.childs.get(childPosition);
                    }

                    @Override
                    public int getChildrenCount(Group group) {
                        return group.childs.size();
                    }
                });
        expandableListView.setAdapter(adapter);
    }

    private List<Group> getDatas() {
        List<Group> groups = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Group group = new Group();
            group.text = "group" + i;
            group.childs = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Child child = new Child();
                child.text = "child" + j;
                group.childs.add(child);
            }
            groups.add(group);
        }
        return groups;
    }
}
