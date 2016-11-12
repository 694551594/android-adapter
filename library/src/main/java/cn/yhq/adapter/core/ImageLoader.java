package cn.yhq.adapter.core;

import android.view.View;

import java.io.File;

/**
 * Created by Administrator on 2016/11/12.
 */

public interface ImageLoader {
    void setImage(View imageView, File file);

    void setImage(View imageView, String url);
}
