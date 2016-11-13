package cn.yhq.adapter.app;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.base.BaseActivity;

public class RecyclerListViewMutipleActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.simple_recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    MutipleTypeRecyclerAdapter1 adapter1 = new MutipleTypeRecyclerAdapter1(this, getDatas());
    MutipleTypeRecyclerAdapter2 adapter2 = new MutipleTypeRecyclerAdapter2(this, getDatas());

    recyclerView.setAdapter(adapter1);
  }

  @Override
  protected int getContentViewLayoutId() {
    return R.layout.activity_recycler_view;
  }

  private List<MutipleTypeObject> getDatas() {
    List<MutipleTypeObject> list = new ArrayList<>();
    MutipleTypeObject mutipleTypeObject1 = new MutipleTypeObject();
    mutipleTypeObject1.type = MutipleTypeObject.TYPE_1;
    mutipleTypeObject1.text = "类型1";
    list.add(mutipleTypeObject1);
    MutipleTypeObject mutipleTypeObject2 = new MutipleTypeObject();
    mutipleTypeObject2.type = MutipleTypeObject.TYPE_2;
    mutipleTypeObject2.text = "类型2";
    list.add(mutipleTypeObject2);
    return list;
  }
}
