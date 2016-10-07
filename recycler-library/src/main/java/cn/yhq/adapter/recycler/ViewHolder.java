package cn.yhq.adapter.recycler;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.io.File;

import cn.yhq.view.binding.ViewBinder;


public class ViewHolder extends RecyclerView.ViewHolder {
  // 子控件
  private SparseArray<View> mViews = new SparseArray<View>();
  private int currentResId;

  public ViewHolder(View itemView) {
    super(itemView);
  }

  public void setOnRecyclerViewItemLongClickListener(
      final OnRecyclerViewItemLongClickListener onRecyclerViewItemLongClickListener) {
    this.itemView.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
        if (onRecyclerViewItemLongClickListener != null) {
          return onRecyclerViewItemLongClickListener.onRecyclerViewItemLongClick(v,
              getAdapterPosition());
        }
        return false;
      }
    });
  }

  public void setOnRecyclerViewItemClickListener(
      final OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
    this.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (onRecyclerViewItemClickListener != null) {
          onRecyclerViewItemClickListener.onRecyclerViewItemClick(v, getAdapterPosition());
        }
      }
    });
  }

  @SuppressWarnings("unchecked")
  public <T> T getView(int resId) {
    View v = mViews.get(resId);
    if (v == null) {
      v = findViewById(resId);
      mViews.put(resId, v);
    }
    return (T) v;
  }

  /**
   * 根据id查找控件视图，不使用缓存
   * 
   * @param id
   * @return
   */
  @SuppressWarnings("unchecked")
  public <T extends View> T findViewById(int id) {
    return (T) this.itemView.findViewById(id);
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
