package cn.yhq.adapter.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.File;


/**
 * 视图持有者
 *
 * @author Yanghuiqiang 2015-6-29
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
  private SparseArray<View> views = new SparseArray<>();
  private static ImageLoader mImageLoader = new DefaultImageLoader();

  public static void setImageLoader(ImageLoader imageLoader) {
    mImageLoader = imageLoader;
  }

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
   * @return
   */
  public <T extends View> T getView(int id) {
    View view = views.get(id);
    if (view == null) {
      view = mConvertView.findViewById(id);
      if (view != null) {
        views.put(view.getId(), view);
      }
    }
    bindResId(id);
    return (T) view;
  }

  public ViewHolder bindResId(int resId) {
    this.currentResId = resId;
    return this;
  }

  public ViewHolder setImage(int resId, String url) {
    if (mImageLoader != null) {
      mImageLoader.setImage(this.getView(resId), url);
    }
    return this;
  }

  public ViewHolder setImage(int resId, File file) {
    if (mImageLoader != null) {
      mImageLoader.setImage(this.getView(resId), file);
    }
    return this;
  }

  public ViewHolder setVisibility(int resId, int visibility) {
    View view = this.getView(resId);
    view.setVisibility(visibility);
    return this;
  }

  public ViewHolder setText(CharSequence text) {
    this.setText(currentResId, text);
    return this;
  }

  public ViewHolder setText(int text) {
    this.setText(currentResId, text);
    return this;
  }

  public ViewHolder setChecked(boolean checked) {
    this.setChecked(currentResId, checked);
    return this;
  }

  public ViewHolder setImage(String url) {
    this.setImage(currentResId, url);
    return this;
  }

  public ViewHolder setImage(Bitmap bitmap) {
    this.setImage(currentResId, bitmap);
    return this;
  }

  public ViewHolder setImage(Drawable drawable) {
    this.setImage(currentResId, drawable);
    return this;
  }

  public ViewHolder setImage(File file) {
    this.setImage(currentResId, file);
    return this;
  }

  public ViewHolder setImage(int image) {
    this.setImage(currentResId, image);
    return this;
  }

  public ViewHolder setVisibility(int visibility) {
    this.setVisibility(currentResId, visibility);
    return this;
  }

  public ViewHolder setOnItemLongClickListener(int resId,
      AdapterView.OnItemLongClickListener onItemLongClickListener) {
    AdapterView<?> adapterView = this.getView(resId);
    adapterView.setOnItemLongClickListener(onItemLongClickListener);
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

  public ViewHolder setOnClickListener(View.OnClickListener onClickListener) {
    this.setOnClickListener(currentResId, onClickListener);
    return this;
  }

  public ViewHolder setText(int viewId, CharSequence text) {
    TextView tv = getView(viewId);
    tv.setText(text);
    return this;
  }

  public ViewHolder setText(int viewId, int resId) {
    TextView tv = getView(viewId);
    tv.setText(resId);
    return this;
  }

  public ViewHolder setImage(int viewId, int resId) {
    if (mImageLoader != null) {
      mImageLoader.setImage(this.getView(viewId), resId);
    }
    return this;
  }

  public ViewHolder setImage(int viewId, Bitmap bitmap) {
    if (mImageLoader != null) {
      mImageLoader.setImage(this.getView(viewId), bitmap);
    }
    return this;
  }

  public ViewHolder setImage(int viewId, Drawable drawable) {
    if (mImageLoader != null) {
      mImageLoader.setImage(this.getView(viewId), drawable);
    }
    return this;
  }

  public ViewHolder setBackgroundColor(int viewId, int color) {
    View view = getView(viewId);
    view.setBackgroundColor(color);
    return this;
  }

  public ViewHolder setBackgroundRes(int viewId, int backgroundRes) {
    View view = getView(viewId);
    view.setBackgroundResource(backgroundRes);
    return this;
  }

  public ViewHolder setTextColor(int viewId, int textColor) {
    TextView view = getView(viewId);
    view.setTextColor(textColor);
    return this;
  }

  public ViewHolder setTextColorRes(int viewId, int textColorRes) {
    TextView view = getView(viewId);
    view.setTextColor(mContext.getResources().getColor(textColorRes));
    return this;
  }

  public ViewHolder setVisible(int viewId, boolean visible) {
    View view = getView(viewId);
    view.setVisibility(visible ? View.VISIBLE : View.GONE);
    return this;
  }

  public ViewHolder linkify(int viewId) {
    TextView view = getView(viewId);
    Linkify.addLinks(view, Linkify.ALL);
    return this;
  }

  public ViewHolder setProgress(int viewId, int progress) {
    ProgressBar view = getView(viewId);
    view.setProgress(progress);
    return this;
  }

  public ViewHolder setBackgroundColor(int color) {
    this.setBackgroundColor(currentResId, color);
    return this;
  }

  public ViewHolder setBackgroundRes(int backgroundRes) {
    this.setBackgroundRes(currentResId, backgroundRes);
    return this;
  }

  public ViewHolder setTextColor(int textColor) {
    this.setTextColor(currentResId, textColor);
    return this;
  }

  public ViewHolder setTextColorRes(int textColorRes) {
    this.setTextColorRes(currentResId, textColorRes);
    return this;
  }

  public ViewHolder setVisible(boolean visible) {
    this.setVisible(currentResId, visible);
    return this;
  }

  public ViewHolder linkify() {
    this.linkify(currentResId);
    return this;
  }

  public ViewHolder setProgress(int progress) {
    this.setProgress(currentResId, progress);
    return this;
  }

  public ViewHolder setMax(int viewId, int max) {
    ProgressBar view = getView(viewId);
    view.setMax(max);
    return this;
  }

  public ViewHolder setRating(int viewId, float rating) {
    RatingBar view = getView(viewId);
    view.setRating(rating);
    return this;
  }

  public ViewHolder setMax(int max) {
    this.setMax(currentResId, max);
    return this;
  }

  public ViewHolder setRating(float rating) {
    this.setRating(currentResId, rating);
    return this;
  }

  public ViewHolder setRating(int viewId, float rating, int max) {
    RatingBar view = getView(viewId);
    view.setMax(max);
    view.setRating(rating);
    return this;
  }

  public ViewHolder setTag(int viewId, Object tag) {
    View view = getView(viewId);
    view.setTag(tag);
    return this;
  }

  public ViewHolder setKeyTag(int viewId, int key, Object tag) {
    View view = getView(viewId);
    view.setTag(key, tag);
    return this;
  }

  public ViewHolder setRating(float rating, int max) {
    this.setRating(currentResId, rating, max);
    return this;
  }

  public ViewHolder setTag(Object tag) {
    this.setTag(currentResId, tag);
    return this;
  }

  public ViewHolder setKeyTag(int key, Object tag) {
    this.setKeyTag(currentResId, key, tag);
    return this;
  }

  public ViewHolder setChecked(int viewId, boolean checked) {
    Checkable view = (Checkable) getView(viewId);
    view.setChecked(checked);
    return this;
  }

  public ViewHolder setEnabled(boolean enabled) {
    setEnabled(currentResId, enabled);
    return this;
  }

  public ViewHolder setEnabled(int viewId, boolean enabled) {
    View view = getView(viewId);
    view.setEnabled(enabled);
    return this;
  }

  /**
   * 关于事件的
   */
  public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
    View view = getView(viewId);
    view.setOnClickListener(listener);
    return this;
  }

  public ViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
    View view = getView(viewId);
    view.setOnTouchListener(listener);
    return this;
  }

  public ViewHolder setOnTouchListener(View.OnTouchListener listener) {
    this.setOnTouchListener(currentResId, listener);
    return this;
  }

  public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
    View view = getView(viewId);
    view.setOnLongClickListener(listener);
    return this;
  }

  public ViewHolder setOnCheckedChangeListener(int viewId,
      CompoundButton.OnCheckedChangeListener listener) {
    CompoundButton view = getView(viewId);
    view.setOnCheckedChangeListener(listener);
    return this;
  }

  public ViewHolder setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener) {
    this.setOnCheckedChangeListener(currentResId, listener);
    return this;
  }

  public ViewHolder setOnCheckedChangeListener(int viewId,
      RadioGroup.OnCheckedChangeListener listener) {
    RadioGroup view = getView(viewId);
    view.setOnCheckedChangeListener(listener);
    return this;
  }

  public ViewHolder setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener listener) {
    this.setOnCheckedChangeListener(currentResId, listener);
    return this;
  }
}
