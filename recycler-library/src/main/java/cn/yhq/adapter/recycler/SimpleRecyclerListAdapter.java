package cn.yhq.adapter.recycler;

/**
 * Created by Administrator on 2016/10/8.
 */

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SimpleRecyclerListAdapter<T> extends RecyclerListAdapter<T> {

    public interface IItemViewSetup<T> {
        void setupView(ViewHolder viewHolder, int position, T entity);
    }

    SimpleRecyclerListAdapter(Context context, List<T> items, final int itemViewLayoutId,
                              final IItemViewSetup itemViewSetup) {
        super(context, items);
        this.register(new ItemViewProvider2<T>() {
            @Override
            public boolean isForProvider(int position, T entity) {
                return true;
            }

            @Override
            public int getItemViewLayoutId() {
                return itemViewLayoutId;
            }

            @Override
            public void onBindViewHolder(ViewHolder viewHolder, int position, T entity) {
                itemViewSetup.setupView(viewHolder, position, entity);
            }
        });
    }

    public static <I> SimpleRecyclerListAdapter<I> create(Context context, List<I> items,
                                                          int itemViewLayoutId, IItemViewSetup itemViewSetup) {
        return new SimpleRecyclerListAdapter<>(context, items, itemViewLayoutId, itemViewSetup);
    }

    public static <I> SimpleRecyclerListAdapter<I> create(Context context, I[] items,
                                                          int itemViewLayoutId, IItemViewSetup itemViewSetup) {
        return new SimpleRecyclerListAdapter<>(context, Arrays.asList(items), itemViewLayoutId,
                itemViewSetup);
    }

    public static <I> SimpleRecyclerListAdapter<I> create(Context context, int itemViewLayoutId,
                                                          IItemViewSetup itemViewSetup) {
        return new SimpleRecyclerListAdapter<>(context, new ArrayList<I>(), itemViewLayoutId,
                itemViewSetup);
    }

}
