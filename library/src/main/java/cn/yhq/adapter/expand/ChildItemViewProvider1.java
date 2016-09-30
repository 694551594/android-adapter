package cn.yhq.adapter.expand;

import android.widget.BaseExpandableListAdapter;

import cn.yhq.adapter.core.ItemView;

public abstract class ChildItemViewProvider1<G, C> extends ItemView<BaseExpandableListAdapter>
    implements
      IChildItemViewProvider<G, C> {}
