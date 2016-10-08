# android-adapter
android极简adapter，支持list-adapter、expandable-list-adapter以及recycler-adapter，可以非常方便的创建单type以及多type的adapter。
### 1、创建最简单的adapter（单type）
#####（1）类似`List<T>`的数据(单List，即ListView或者GridView适配的adapter)，使用SimpleListAdapter即可，代码如下：
```java
    SimpleListAdapter<String> adapter = SimpleListAdapter.create(this, getDatas(), R.layout.layout_item1,
            new SimpleListAdapter.IItemViewSetup<String>() {
                @Override
                public void setupView(ViewHolder viewHolder, int position, String entity) {
                    viewHolder.bindTextData(R.id.item_text, entity);
                }
            });
```
#####（2）类似`List<List<T>>`的数据（List里面嵌套List，即ExpandableListView适配的adapter），使用SimpleExpandableListAdapter即可，代码如下：
```java
 SimpleExpandableListAdapter<Group, Child> adapter =
            SimpleExpandableListAdapter.create(this, getDatas(), R.layout.layout_item1, R.layout.layout_item1, new SimpleExpandableListAdapter.IItemViewSetup<Group, Child>() {
                @Override
                public void setupGroupView(ViewHolder viewHolder, int position, Group entity, boolean isExpanded) {
                    viewHolder.bindTextData(R.id.item_text, entity.text);
                }
    
                @Override
                public void setupChildView(ViewHolder viewHolder, int groupPosition, Group groupEntity, int childPosition, Child childEntity) {
                    viewHolder.bindTextData(R.id.item_text, childEntity.text);
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
```
#####（3）RecyclerView适配的adapter，使用SimpleRecyclerListAdapter即可，代码如下：
```java
 SimpleRecyclerListAdapter<String> adapter = SimpleRecyclerListAdapter.create(this, getDatas(),
        R.layout.layout_item1, new SimpleRecyclerListAdapter.IItemViewSetup<String>() {
          @Override
          public void setupView(cn.yhq.adapter.recycler.ViewHolder viewHolder, int position,
              String entity) {
            viewHolder.bindTextData(R.id.item_text, entity);
          }
        });
```
#####（4）当然，如果你不想使用自带的Simple**Adapter，你可以使用上述三种对应的ListAdapter、BaseExpandableListAdapter、RecyclerListAdapter去实现自己的adapter。
#####（5）如果你的数据类型不是`List<T>`这种的，比如是`JsonArray`或者`Object {List<T>}`这种的，就需要继承`BaseAdapter<L, I>`、`BaseExpandableAdapter<L, G, C>`、`RecyclerBaseAdapter<L, I>`去实现自己的adapter，这里举一个自定义ListAdapter的例子：
```java
 public class ListObjectAdapter extends BaseAdapter<ListObject, String> {
    
        public ListObjectAdapter(Context context, ListObject listData) {
            super(context, listData);
        }
    
        public ListObjectAdapter(Context context) {
            super(context);
        }
    
        @Override
        protected ListObject newInstance() {
            return new ListObject();
        }
    
        @Override
        public void clearAllItem() {
    
        }
    
        @Override
        public void addAllItem(ListObject items) {
    
        }
    
        @Override
        public void addItem(String item) {
    
        }
    
        @Override
        public void removeItem(String item) {
    
        }
    
        @Override
        public void removeItem(int position) {
    
        }
    
        @Override
        public int getCount() {
            return 0;
        }
    
        @Override
        public String getItem(int position) {
            return null;
        }
    }

    public class ListObject {
        public List<String> list;
    }

```
### 2、创建多type的adapter
- 这里使用ListAdapter举例说明，ExpandableListAdapter与RecyclerAdapter的类似。
- 多type的adapter的创建方式是通过注册多种type的视图提供器，然后根据position与对应的entity去选择对应的视图提供器，然后进行视图的渲染与数据绑定。
- 多type的adapter创建方式有两种，一种是根据key值注册视图提供器，一种是直接注册带选择器的视图提供器。这里分别举例说明：
#####（1）根据key去注册视图提供器
此种方式通过调用adapter的register(key，ItemViewProvider1)方法去注册对应类型的视图提供器，此外还需要通过setItemViewProviderKeyPolicy设置视图提供器key的选择策略，用来确定某个item该用哪个视图提供器进行渲染，现在我们写一个有两种类型的item的adapter：
######示例代码
```java
 public class MutipleTypeListAdapter1 extends ListAdapter<MutipleTypeObject> {
      public final static int TYPE_1 = 1;
      public final static int TYPE_2 = 2;
    
      public MutipleTypeListAdapter1(Context context) {
        super(context);
        // 注册类型1的视图提供器
        this.register(TYPE_1, new ItemViewProvider1<MutipleTypeObject>() {
          @Override
          public int getItemViewLayoutId() {
            return R.layout.layout_item1;
          }
    
          @Override
          public void setupView(ViewHolder viewHolder, int position, MutipleTypeObject entity) {
            viewHolder.bindTextData(R.id.item_text, entity.text);
          }
        });
        // 注册类型2的视图提供器
        this.register(TYPE_2, new ItemViewProvider1<MutipleTypeObject>() {
          @Override
          public int getItemViewLayoutId() {
            return R.layout.layout_item2;
          }
    
          @Override
          public void setupView(ViewHolder viewHolder, int position, MutipleTypeObject entity) {
            viewHolder.bindTextData(R.id.item_text, entity.text);
          }
        });
        // 设置视图提供器的选择策略
        this.setItemViewProviderKeyPolicy(new IItemViewProviderKeyPolicy<MutipleTypeObject>() {
          @Override
          public int getItemViewProviderKey(int position, MutipleTypeObject entity) {
            if (entity.type == MutipleTypeObject.TYPE_1) {
              return TYPE_1;
            } else if (entity.type == MutipleTypeObject.TYPE_2) {
              return TYPE_2;
            }
            return -1;
          }
        });
      }
    }
```
#####（2）根据选择器去选择视图提供器
此种方式通过调用adapter的register(ItemViewProvider2)方法去注册对应类型的视图提供器，与第一种方式不同的是，这种方式需要实现isForProvider方法，用来确定某个item是否使用当前视图提供器进行渲染，现在我们写一个有两种类型的item的adapter：
######示例代码
```java
  public class MutipleTypeListAdapter2 extends ListAdapter<MutipleTypeObject> {
    
      public MutipleTypeListAdapter2(Context context) {
        super(context);
        // 注册类型1的视图提供器
        this.register(new ItemViewProvider2<MutipleTypeObject>() {
          @Override
          public boolean isForProvider(int position, MutipleTypeObject entity) {
            return entity.type == MutipleTypeObject.TYPE_1;
          }
    
          @Override
          public int getItemViewLayoutId() {
            return R.layout.layout_item1;
          }
    
          @Override
          public void setupView(ViewHolder viewHolder, int position, MutipleTypeObject entity) {
            viewHolder.bindTextData(R.id.item_text, entity.text);
          }
        });
        // 注册类型2的视图提供器
        this.register(new ItemViewProvider2<MutipleTypeObject>() {
          @Override
          public boolean isForProvider(int position, MutipleTypeObject entity) {
            return entity.type == MutipleTypeObject.TYPE_2;
          }
    
          @Override
          public int getItemViewLayoutId() {
            return R.layout.layout_item2;
          }
    
          @Override
          public void setupView(ViewHolder viewHolder, int position, MutipleTypeObject entity) {
            viewHolder.bindTextData(R.id.item_text, entity.text);
          }
        });
      }
    }
```
#####（3）以上两种方式，第一种方式去获取视图提供器的效率较高，第二种方式因为要轮询，所以效率较低，如果item type的数量较多，建议采用第一种方式去注册视图提供器。

