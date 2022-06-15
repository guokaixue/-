package com.abc.code.utils;

import android.widget.ImageView;


import com.abc.code.MainApplication;
import com.bumptech.glide.Glide;
import com.abc.code.R;


/**
 * @author: hedianbo.
 * @desc:
 */

public class GlideHelper {

    private static MainApplication instance = MainApplication.getInstance();

    public static void loadImageMother(String url, ImageView view) {
        Glide.with(instance.getApplicationContext())
                .load(url)
                .placeholder(R.mipmap.mother)
                .error(R.mipmap.mother)
                .centerCrop()
                .into(view);
    }


    public static void loadImageExpet(String url, ImageView view) {
        Glide.with(instance.getApplicationContext())
                .load(url)
                .placeholder(R.mipmap.expet)
                .error(R.mipmap.expet)
                .centerCrop()
                .into(view);
    }


    /**
     * Glide加载z资源图片
     */
    public static void loadLocalResource(int resourceId, ImageView view) {
        Glide.with(instance.getApplicationContext())
                .load(resourceId)
                .placeholder(R.mipmap.image)
                .error(R.mipmap.image)
                .centerCrop()
                .into(view);
    }


    /**
     * 选择图库图片时候用
     * @param path
     * @param view
     */
    public static void loadLocalResource(String path, ImageView view) {
        Glide.with(instance.getApplicationContext())
                .load(path)
                .placeholder(R.mipmap.mother)
                .error(R.mipmap.mother)
                .centerCrop()
                .into(view);
    }

}
