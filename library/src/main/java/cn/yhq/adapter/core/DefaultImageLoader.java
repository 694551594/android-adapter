package cn.yhq.adapter.core;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Yanghuiqiang on 2017/2/21.
 */

public class DefaultImageLoader implements ImageLoader {
    @Override
    public void setImage(View imageView, File file) {

    }

    @Override
    public void setImage(View imageView, String url) {

    }

    @Override
    public void setImage(View imageView, int resId) {
        ImageView view = (ImageView) imageView;
        view.setImageResource(resId);
    }

    @Override
    public void setImage(View imageView, Bitmap bitmap) {
        ImageView view = (ImageView) imageView;
        view.setImageBitmap(bitmap);
    }

    @Override
    public void setImage(View imageView, Drawable drawable) {
        ImageView view = (ImageView) imageView;
        view.setImageDrawable(drawable);
    }
}
