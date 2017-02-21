package cn.yhq.adapter.core;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.io.File;

/**
 * Created by Administrator on 2016/11/12.
 */

public interface ImageLoader {
    void setImage(View imageView, File file);

    void setImage(View imageView, String url);

    void setImage(View imageView, int resId);

    void setImage(View imageView, Bitmap bitmap);

    void setImage(View imageView, Drawable drawable);
}
