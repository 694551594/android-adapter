package cn.yhq.adapter.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewMutipleActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_simple);
    ListView listView = (ListView) this.findViewById(R.id.simple_listview);
    MutipleTypeListAdapter1 adapter1 = new MutipleTypeListAdapter1(this, getDatas());
    MutipleTypeListAdapter2 adapter2 = new MutipleTypeListAdapter2(this, getDatas());
    listView.setAdapter(adapter1);
  }

  private List<MutipleTypeObject> getDatas() {
    List<MutipleTypeObject> list = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      MutipleTypeObject mutipleTypeObject1 = new MutipleTypeObject();
      mutipleTypeObject1.type = MutipleTypeObject.TYPE_1;
      mutipleTypeObject1.type =
          1 + Math.random() * 2 == 1 ? MutipleTypeObject.TYPE_1 : MutipleTypeObject.TYPE_2;
      mutipleTypeObject1.text = mutipleTypeObject1.type == MutipleTypeObject.TYPE_1 ? "类型1" : "类型2";
      list.add(mutipleTypeObject1);
    }

    return list;
  }
}
