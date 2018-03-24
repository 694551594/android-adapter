package cn.yhq.adapter.expand;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.adapter.core.ViewHolderFactory;

public abstract class BaseExpandableAdapter<L, G, C>
        extends android.widget.BaseExpandableListAdapter {
    protected L mListData;
    private GroupItemViewProviderFactory<G> mGroupItemViewProviderFactory;
    private ChildItemViewProviderFactory<G, C> mChildItemViewProviderFactory;
    protected Context mContext;
    // 展开与不展开的状态保存
    protected SparseBooleanArray mExpandState = new SparseBooleanArray();

    public BaseExpandableAdapter(Context context, L listData) {
        this.mContext = context;
        if (listData == null) {
            mListData = newInstance();
        } else {
            mListData = listData;
        }
        mGroupItemViewProviderFactory = new GroupItemViewProviderFactory<>(this.getContext(), this);
        mChildItemViewProviderFactory = new ChildItemViewProviderFactory<>(this.getContext(), this);
    }

    protected abstract L newInstance();

    public BaseExpandableAdapter(Context context) {
        this(context, null);
    }

    public final BaseExpandableAdapter<L, G, C> setGroupItemViewProviderKeyPolicy(
            IGroupItemViewProviderKeyPolicy<G> itemViewProviderKeyPolicy) {
        this.mGroupItemViewProviderFactory.setGroupItemViewProviderKeyPolicy(itemViewProviderKeyPolicy);
        return this;
    }

    public static <T extends ViewHolder> void setGroupViewHolderFactory(ViewHolderFactory<T> factory) {
        GroupItemViewProviderFactory.setViewHolderFactory(factory);
    }

    public static <T extends ViewHolder> void setChildViewHolderFactory(ViewHolderFactory<T> factory) {
        ChildItemViewProviderFactory.setViewHolderFactory(factory);
    }

    public final BaseExpandableAdapter<L, G, C> register(int key,
                                                         GroupItemViewProvider1<G> itemViewProvider) {
        mGroupItemViewProviderFactory.register(key, itemViewProvider);
        return this;
    }

    public final BaseExpandableAdapter<L, G, C> registerGroupViewProvider(int key,
                                                                          Class<? extends GroupItemViewProvider1<G>> itemViewProviderClass) {
        mGroupItemViewProviderFactory.register(key, itemViewProviderClass);
        return this;
    }

    public final BaseExpandableAdapter<L, G, C> register(GroupItemViewProvider2<G> itemViewProvider) {
        mGroupItemViewProviderFactory.register(itemViewProvider);
        return this;
    }

    public final BaseExpandableAdapter<L, G, C> registerGroupViewProvider(
            Class<? extends GroupItemViewProvider2<G>> itemViewProviderClass) {
        mGroupItemViewProviderFactory.register(itemViewProviderClass);
        return this;
    }

    public final BaseExpandableAdapter<L, G, C> setChildItemViewProviderKeyPolicy(
            IChildItemViewProviderKeyPolicy<G, C> itemViewProviderKeyPolicy) {
        this.mChildItemViewProviderFactory.setChildItemViewProviderKeyPolicy(itemViewProviderKeyPolicy);
        return this;
    }

    public final BaseExpandableAdapter<L, G, C> register(int key,
                                                         ChildItemViewProvider1<G, C> itemViewProvider) {
        mChildItemViewProviderFactory.register(key, itemViewProvider);
        return this;
    }

    public final BaseExpandableAdapter<L, G, C> registerChildViewProvider(int key,
                                                                          Class<? extends ChildItemViewProvider1<G, C>> itemViewProviderClass) {
        mChildItemViewProviderFactory.register(key, itemViewProviderClass);
        return this;
    }

    public final BaseExpandableAdapter<L, G, C> register(
            ChildItemViewProvider2<G, C> itemViewProvider) {
        mChildItemViewProviderFactory.register(itemViewProvider);
        return this;
    }

    public final BaseExpandableAdapter<L, G, C> registerChildViewProvider(
            Class<? extends ChildItemViewProvider2<G, C>> itemViewProviderClass) {
        mChildItemViewProviderFactory.register(itemViewProviderClass);
        return this;
    }

    public abstract void clearAllItem();

    public abstract void addAllItem(L items);

    public abstract void addItem(G item);

    public abstract void removeItem(G item);

    public abstract void removeItem(int position);

    public final L getListData() {
        return mListData;
    }

    public final void setListData(L dataList) {
        this.mListData = dataList;
    }

    public abstract G getGroup(int groupPosition);

    public abstract C getChild(int groupPosition, int childPosition);

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public final View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                                   ViewGroup parent) {
        mExpandState.put(groupPosition, isExpanded);
        return mGroupItemViewProviderFactory.setupView(groupPosition, this.getGroup(groupPosition),
                convertView, parent, isExpanded);
    }

    @Override
    public final View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                                   View convertView, ViewGroup parent) {
        return mChildItemViewProviderFactory.setupView(groupPosition, this.getGroup(groupPosition),
                childPosition, this.getChild(groupPosition, childPosition), convertView, parent);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public final int getChildType(int groupPosition, int childPosition) {
        return mChildItemViewProviderFactory.getItemViewType(groupPosition,
                this.getGroup(groupPosition), childPosition, this.getChild(groupPosition, childPosition));
    }

    @Override
    public final int getChildTypeCount() {
        return mChildItemViewProviderFactory.getItemViewTypeCount();
    }

    @Override
    public final int getGroupType(int groupPosition) {
        return mGroupItemViewProviderFactory.getItemViewType(groupPosition, getGroup(groupPosition));
    }

    @Override
    public final int getGroupTypeCount() {
        return mGroupItemViewProviderFactory.getItemViewTypeCount();
    }

    public final Context getContext() {
        return mContext;
    }

    /**
     * groupPosition位置的组是否被展开
     *
     * @param groupPosition
     * @return
     */
    public final boolean isExpand(int groupPosition) {
        return mExpandState.get(groupPosition, false);
    }

    @SuppressWarnings("unchecked")
    public final <T extends IGroupItemViewProvider<G>> List<T> getAllGroupItemViewProvider() {
        List<GroupItemViewProvider1<G>> providers =
                mGroupItemViewProviderFactory.getAllItemViewProvider();
        List<T> list = new ArrayList<T>();
        for (IGroupItemViewProvider<G> provider : providers) {
            list.add((T) provider);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public final <T extends IChildItemViewProvider<G, C>> List<T> getAllChildItemViewProvider() {
        List<ChildItemViewProvider1<G, C>> providers =
                mChildItemViewProviderFactory.getAllItemViewProvider();
        List<T> list = new ArrayList<T>();
        for (IChildItemViewProvider<G, C> provider : providers) {
            list.add((T) provider);
        }
        return list;
    }

    public final GroupItemViewProviderFactory<G> getGroupItemViewProviderFactory() {
        return mGroupItemViewProviderFactory;
    }

    public final ChildItemViewProviderFactory<G, C> getChildItemViewProviderFactory() {
        return mChildItemViewProviderFactory;
    }

    public final IGroupItemViewProvider<G> getGroupItemViewProvider(int key) {
        return mGroupItemViewProviderFactory.getItemViewProviderByKey(key);
    }

    public final IChildItemViewProvider<G, C> getChildItemViewProvider(int key) {
        return mChildItemViewProviderFactory.getItemViewProviderByKey(key);
    }
}
