package cn.yhq.adapter.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListViewMutipleActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_expandable_list_view);
    ExpandableListView expandableListView =
        (ExpandableListView) this.findViewById(R.id.simple_expandable_listview);
    MutipleTypeExpandableListAdapter1 adapter =
        new MutipleTypeExpandableListAdapter1(this, getDatas());
    expandableListView.setAdapter(adapter);
  }

  private List<MutipleTypeGroupObject> getDatas() {
    List<MutipleTypeGroupObject> groups = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      MutipleTypeGroupObject group = new MutipleTypeGroupObject();
      group.childs = new ArrayList<>();
      group.type = (int) (1 + Math.random() * 2) == 1
          ? MutipleTypeGroupObject.TYPE_1
          : MutipleTypeGroupObject.TYPE_2;
      group.text =
          "group" + i + ":" + (group.type == MutipleTypeGroupObject.TYPE_1 ? "类型1" : "类型2");
      for (int j = 0; j < 3; j++) {
        MutipleTypeChildObject child = new MutipleTypeChildObject();
        child.type = (int) (1 + Math.random() * 2) == 1
            ? MutipleTypeChildObject.TYPE_1
            : MutipleTypeChildObject.TYPE_2;
        child.text =
            "child" + j + ":" + (child.type == MutipleTypeChildObject.TYPE_1 ? "类型1" : "类型2");
        group.childs.add(child);
      }
      groups.add(group);
    }
    return groups;
  }
}
