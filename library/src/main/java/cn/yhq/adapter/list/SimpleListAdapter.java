package cn.yhq.adapter.list;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.yhq.adapter.core.ViewHolder;


/**
 * 只有一种类型的item的list-adapter
 * <p>
 * Created by Yanghuiqiang on 2016/9/28.
 */

public class SimpleListAdapter<I> extends ListAdapter<I> {

    public interface IItemViewSetup<I> {
        void setupView(ViewHolder viewHolder, int position, I entity);
    }

    SimpleListAdapter(Context context, List<I> items, final int itemViewLayoutId,
                      final IItemViewSetup itemViewSetup) {
        super(context, items);
        this.register(new ItemViewProvider2<I>() {
            @Override
            public boolean isForProvider(int position, I entity) {
                return true;
            }

            @Override
            public int getItemViewLayoutId() {
                return itemViewLayoutId;
            }

            @Override
            public void setupView(ViewHolder viewHolder, int position, I entity) {
                itemViewSetup.setupView(viewHolder, position, entity);
            }
        });
    }

    public static <I> SimpleListAdapter<I> create(Context context, List<I> items, int itemViewLayoutId,
                                                  IItemViewSetup itemViewSetup) {
        return new SimpleListAdapter<>(context, items, itemViewLayoutId, itemViewSetup);
    }

    public static <I> SimpleListAdapter<I> create(Context context, I[] items, int itemViewLayoutId,
                                                  IItemViewSetup itemViewSetup) {
        return new SimpleListAdapter<>(context, Arrays.asList(items), itemViewLayoutId, itemViewSetup);
    }

    public static <I> SimpleListAdapter<I> create(Context context, int itemViewLayoutId,
                                                  IItemViewSetup itemViewSetup) {
        return new SimpleListAdapter<>(context, new ArrayList<I>(), itemViewLayoutId, itemViewSetup);
    }

}
