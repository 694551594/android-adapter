package cn.yhq.adapter.recycler;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import java.io.File;

import cn.yhq.view.binding.binder.BindType;
import cn.yhq.view.binding.binder.DataBindProvider;
import cn.yhq.view.binding.binder.DataBindProviderFactory;

import static android.R.attr.id;


public class ViewHolder extends RecyclerView.ViewHolder {
  // 子控件
  private int currentResId;
  private DataBindProvider binderProvider;

  public ViewHolder(View itemView) {
    super(itemView);
    this.binderProvider = DataBindProviderFactory.create(itemView);
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
