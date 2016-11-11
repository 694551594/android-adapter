package cn.yhq.adapter.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.io.File;

import cn.yhq.view.binding.binder.BindType;
import cn.yhq.view.binding.binder.DataBindProvider;
import cn.yhq.view.binding.binder.DataBindProviderFactory;

import static android.R.attr.id;


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
  // 当前需要绑定的资源id
  private int currentResId;
  // 当前的position
  private int mPosition;
  // 当前的item是否是复用的item
  private boolean isRecycler;

  private DataBindProvider binderProvider;

  ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
    this(context, LayoutInflater.from(context).inflate(layoutId, parent, false), position);
  }

  ViewHolder(Context context, View convertView, int position) {
    this.mContext = context;
    this.mPosition = position;
    this.mConvertView = convertView;
    this.mConvertView.setTag(this);
    this.binderProvider = DataBindProviderFactory.create(convertView);
  }

  public static ViewHolder get(Context context, View convertView, int position) {
    return new ViewHolder(context, convertView, position);
  }

  public static ViewHolder get(View convertView, int position) {
    ViewHolder viewHolder = (ViewHolder) convertView.getTag();
    viewHolder.setPosition(position);
    viewHolder.setRecycler(true);
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
   * 获取控件视图，使用缓存
   * 
   * @param key
   * @return
   */
  public <T extends View> T getView(int key) {
    T view = this.binderProvider.getViewRender().getView(id);
    bindResId(key);
    return view;
  }

  public ViewHolder bindResId(int resId) {
    this.currentResId = resId;
    return this;
  }

  public ViewHolder bindTextData(int resId, CharSequence text) {
    this.binderProvider.bind(resId, BindType.TEXT, text);
    return this;
  }

  public ViewHolder bindTextData(int resId, int text) {
    this.binderProvider.bind(resId, BindType.TEXT, text);
    return this;
  }

  public ViewHolder bindCheckData(int resId, boolean checked) {
    this.binderProvider.bind(resId, BindType.CHECKED, checked);
    return this;
  }

  public ViewHolder bindImageData(int resId, String url) {
    this.binderProvider.bind(resId, BindType.IMAGE_URL, url);
    return this;
  }

  public ViewHolder bindImageData(int resId, Bitmap bitmap) {
    this.binderProvider.bind(resId, BindType.IMAGE_BITMAP, bitmap);
    return this;
  }

  public ViewHolder bindImageData(int resId, Drawable drawable) {
    this.binderProvider.bind(resId, BindType.IMAGE_DRAWABLE, drawable);
    return this;
  }

  public ViewHolder bindImageData(int resId, File file) {
    this.binderProvider.bind(resId, BindType.IMAGE_FILE, file);
    return this;
  }

  public ViewHolder bindImageData(int resId, int image) {
    this.binderProvider.bind(resId, BindType.IMAGE_RESID, image);
    return this;
  }

  public ViewHolder setVisibility(int resId, int visibility) {
    this.binderProvider.bind(resId, BindType.VISIBILITY, visibility);
    return this;
  }

  public ViewHolder bindTextData(CharSequence text) {
    this.bindTextData(currentResId, text);
    return this;
  }

  public ViewHolder bindTextData(int text) {
    this.bindTextData(currentResId, text);
    return this;
  }

  public ViewHolder bindCheckData(boolean checked) {
    this.bindCheckData(currentResId, checked);
    return this;
  }

  public ViewHolder bindImageData(String url) {
    this.bindImageData(currentResId, url);
    return this;
  }

  public ViewHolder bindImageData(Bitmap bitmap) {
    this.bindImageData(currentResId, bitmap);
    return this;
  }

  public ViewHolder bindImageData(Drawable drawable) {
    this.bindImageData(currentResId, drawable);
    return this;
  }

  public ViewHolder bindImageData(File file) {
    this.bindImageData(currentResId, file);
    return this;
  }

  public ViewHolder bindImageData(int image) {
    this.bindImageData(currentResId, image);
    return this;
  }

  public ViewHolder setVisibility(int visibility) {
    this.setVisibility(currentResId, visibility);
    return this;
  }

  public ViewHolder setOnItemLongClickListener(int resId,
      AdapterView.OnItemLongClickListener onItemLongClickListener) {
    this.binderProvider.bind(resId, BindType.LISTENER_ITEM_LONG_CLICK, onItemLongClickListener);
    return this;
  }

  public ViewHolder setOnItemLongClickListener(
      AdapterView.OnItemLongClickListener onItemLongClickListener) {
    this.setOnItemLongClickListener(currentResId, onItemLongClickListener);
    return this;
  }

  public ViewHolder setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
    this.setOnLongClickListener(currentResId, onLongClickListener);
    return this;
  }

  public ViewHolder setOnLongClickListener(int resId,
      View.OnLongClickListener onLongClickListener) {
    this.binderProvider.bind(resId, BindType.LISTENER_LONG_CLICK, onLongClickListener);
    return this;
  }

  public ViewHolder setOnClickListener(View.OnClickListener onClickListener) {
    this.setOnClickListener(currentResId, onClickListener);
    return this;
  }

  public ViewHolder setOnClickListener(int resId, View.OnClickListener onClickListener) {
    this.binderProvider.bind(resId, BindType.LISTENER_CLICK, onClickListener);
    return this;
  }

  public ViewHolder setTag(int resId, Object tag) {
    this.binderProvider.bind(resId, BindType.TAG, tag);
    return this;
  }

  public ViewHolder setTag(Object tag) {
    this.setTag(currentResId, tag);
    return this;
  }
}
