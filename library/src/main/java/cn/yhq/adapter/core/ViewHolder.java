package cn.yhq.adapter.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.io.File;

import cn.yhq.view.binding.ViewBinder;


/**
 * 视图持有者
 * 
 * @author Yanghuiqiang 2015-6-29
 * 
 */
public final class ViewHolder {
  private Context mContext;
  // 根布局
  private View mConvertView;
  // 子控件
  private SparseArray<View> mViews = new SparseArray<View>();
  // 当前需要绑定的资源id
  private int currentResId;
  // 当前的position
  private int mPosition;
  // 当前的item是否是复用的item
  private boolean isRecycler;

  ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
    this(context, LayoutInflater.from(context).inflate(layoutId, parent, false), position);
  }

  ViewHolder(Context context, View convertView, int position) {
    this.mContext = context;
    this.mPosition = position;
    this.mConvertView = convertView;
    this.mConvertView.setTag(this);
  }

  public static ViewHolder get(Context context, View convertView, int position) {
    return new ViewHolder(context, convertView, position);
  }

  public static ViewHolder get(View convertView, int position) {
    ViewHolder viewHolder = (ViewHolder) convertView.getTag();
    viewHolder.setPosition(position);
    return viewHolder;
  }

  public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId,
      int position) {
    if (convertView == null) {
      if (layoutId == 0) {
        return new ViewHolder(context, new View(context), position);
      } else {
        return new ViewHolder(context, parent, layoutId, position);
      }
    }

    ViewHolder viewHolder = (ViewHolder) convertView.getTag();
    viewHolder.setPosition(position);
    viewHolder.setRecycler(true);
    return viewHolder;
  }

  void setRecycler(boolean isRecycler) {
    this.isRecycler = isRecycler;
  }

  public boolean isRecycler() {
    return isRecycler;
  }

  public View getConvertView() {
    return mConvertView;
  }

  public int getPosition() {
    return mPosition;
  }

  void setPosition(int position) {
    this.mPosition = position;
  }

  /**
   * 根据id查找控件视图，不使用缓存
   * 
   * @param id
   * @return
   */
  public <T extends View> T findViewById(int id) {
    bindResId(id);
    return ViewBinder.findViewById(mConvertView, id);
  }

  /**
   * 获取控件视图，使用缓存
   * 
   * @param key
   * @return
   */
  @SuppressWarnings("unchecked")
  public <T extends View> T getView(int key) {
    View v = mViews.get(key);
    if (v == null) {
      v = findViewById(key);
      mViews.put(key, v);
    }
    bindResId(key);
    return (T) v;
  }

  public ViewHolder bindResId(int resId) {
    this.currentResId = resId;
    return this;
  }

  public ViewHolder bindTextData(int resId, CharSequence text) {
    View v = this.getView(resId);
    ViewBinder.bindTextData(v, text);
    return this;
  }

  public ViewHolder bindTextData(int resId, int text) {
    View v = this.getView(resId);
    ViewBinder.bindTextData(v, text);
    return this;
  }

  public ViewHolder bindCheckData(int resId, boolean checked) {
    View v = this.getView(resId);
    ViewBinder.bindCheckData(v, checked);
    return this;
  }

  public ViewHolder bindImageData(int resId, String url) {
    View v = this.getView(resId);
    ViewBinder.bindImageData(v, url);
    return this;
  }

  public ViewHolder bindImageData(int resId, Bitmap bitmap) {
    View v = this.getView(resId);
    ViewBinder.bindImageData(v, bitmap);
    return this;
  }

  public ViewHolder bindImageData(int resId, Drawable drawable) {
    View v = this.getView(resId);
    ViewBinder.bindImageData(v, drawable);
    return this;
  }

  public ViewHolder bindImageData(int resId, File file) {
    View v = this.getView(resId);
    ViewBinder.bindImageData(v, file);
    return this;
  }

  public ViewHolder bindImageData(int resId, int image) {
    View v = this.getView(resId);
    ViewBinder.bindImageData(v, image);
    return this;
  }

  public ViewHolder setVisibility(int resId, int visibility) {
    View v = this.getView(resId);
    ViewBinder.setVisibility(v, visibility);
    return this;
  }


  public ViewHolder bindTextData(CharSequence text) {
    View v = this.getView(currentResId);
    ViewBinder.bindTextData(v, text);
    return this;
  }

  public ViewHolder bindTextData(int text) {
    View v = this.getView(currentResId);
    ViewBinder.bindTextData(v, text);
    return this;
  }

  public ViewHolder bindCheckData(boolean checked) {
    View v = this.getView(currentResId);
    ViewBinder.bindCheckData(v, checked);
    return this;
  }

  public ViewHolder bindImageData(String url) {
    View v = this.getView(currentResId);
    ViewBinder.bindImageData(v, url);
    return this;
  }

  public ViewHolder bindImageData(Bitmap bitmap) {
    View v = this.getView(currentResId);
    ViewBinder.bindImageData(v, bitmap);
    return this;
  }

  public ViewHolder bindImageData(Drawable drawable) {
    View v = this.getView(currentResId);
    ViewBinder.bindImageData(v, drawable);
    return this;
  }

  public ViewHolder bindImageData(File file) {
    View v = this.getView(currentResId);
    ViewBinder.bindImageData(v, file);
    return this;
  }

  public ViewHolder bindImageData(int image) {
    View v = this.getView(currentResId);
    ViewBinder.bindImageData(v, image);
    return this;
  }

  public ViewHolder setVisibility(int visibility) {
    View v = this.getView(currentResId);
    ViewBinder.setVisibility(v, visibility);
    return this;
  }

  public ViewHolder setOnItemLongClickListener(int resId,
      AdapterView.OnItemLongClickListener onItemLongClickListener) {
    AdapterView<BaseAdapter> v = this.getView(resId);
    v.setOnItemLongClickListener(onItemLongClickListener);
    return this;
  }

  public ViewHolder setOnItemLongClickListener(
      AdapterView.OnItemLongClickListener onItemLongClickListener) {
    AdapterView<BaseAdapter> v = this.getView(currentResId);
    v.setOnItemLongClickListener(onItemLongClickListener);
    return this;
  }

  public ViewHolder setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
    View v = this.getView(currentResId);
    v.setOnLongClickListener(onLongClickListener);
    return this;
  }

  public ViewHolder setOnLongClickListener(int resId,
      View.OnLongClickListener onLongClickListener) {
    View v = this.getView(resId);
    v.setOnLongClickListener(onLongClickListener);
    return this;
  }

  public ViewHolder setOnClickListener(View.OnClickListener onClickListener) {
    View v = this.getView(currentResId);
    v.setOnClickListener(onClickListener);
    return this;
  }

  public ViewHolder setOnClickListener(int resId, View.OnClickListener onClickListener) {
    View v = this.getView(resId);
    v.setOnClickListener(onClickListener);
    return this;
  }

  public ViewHolder setTag(int resId, Object tag) {
    View v = this.getView(resId);
    v.setTag(tag);
    return this;
  }

  public ViewHolder setTag(Object tag) {
    View v = this.getView(currentResId);
    v.setTag(tag);
    return this;
  }

  public ViewHolder setKeyTag(int resId, int key, Object tag) {
    View v = this.getView(resId);
    v.setTag(key, tag);
    return this;
  }

  public ViewHolder setKeyTag(int key, Object tag) {
    View v = this.getView(currentResId);
    v.setTag(key, tag);
    return this;
  }
}
