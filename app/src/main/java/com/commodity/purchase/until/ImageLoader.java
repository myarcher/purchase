package com.commodity.purchase.until;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

import com.commodity.purchase.net.NetResponseListener;
import com.commodity.purchase.net.RequestHandler;
import org.xutils.image.ImageOptions;
import org.xutils.x;

public class ImageLoader {
    private static ImageLoader sImageLoader;

    private static final ImageLoader getImageLoader() {
        if (sImageLoader == null) {
            synchronized (ImageLoader.class) {
                if (sImageLoader == null) {
                    if (AppUntils.isClassExists("org.xutils.x")) {
                        sImageLoader = new ImageLoader();
                    } else {
                        throw new RuntimeException("必须在你的 build.gradle 文件中配置「XUtils3」图片加载库的依赖,或者检查是否添加了图库的混淆配置");
                    }
                }
            }
        }
        return sImageLoader;
    }

    public static void display(Activity activity, ImageView imageView, String path, @DrawableRes int loadingResId, @DrawableRes int failResId, int width, int height, final ImageLoader.SDisplayImageListener delegate) {
        getImageLoader().displayImage(activity, imageView, path, loadingResId, failResId, width, height, delegate);
    }

    public static void download(Context context, String path, final ImageLoader.SDownloadImageListener delegate) {
        getImageLoader().downloadImage(context, path, delegate);
    }


    private void displayImage(Activity activity, final ImageView imageView, final String finalPath, @DrawableRes int loadingResId, @DrawableRes int failResId, int width, int height, final SDisplayImageListener listener) {
        x.Ext.init(activity.getApplication());
        ImageOptions options = new ImageOptions.Builder().setLoadingDrawableId(loadingResId).setFailureDrawableId(failResId).setSize(width, height).build();
        RequestHandler<Drawable>  requestHandler=new RequestHandler<>();
        requestHandler.setOkListener(new NetResponseListener.NetOkListener<Drawable>() {
            @Override
            public void onSuccess(Drawable response,String mess, int requestCode) {
                if (listener != null) {
                    listener.onSuccess(imageView, finalPath, response);
                }
            }
        });
        x.image().bind(imageView, finalPath, options, requestHandler);
    }

    private void downloadImage(Context context, final String finalPath, final SDownloadImageListener listener) {
        x.Ext.init((Application) context.getApplicationContext());
        RequestHandler<Drawable>  requestHandler=new RequestHandler();
        requestHandler.setOkListener(new NetResponseListener.NetOkListener<Drawable>() {
            @Override
            public void onSuccess(Drawable response,String mess, int requestCode) {
                if (listener != null) {
                    listener.onSuccess(finalPath, ((BitmapDrawable)response).getBitmap());
                }
            }
        }).setFaildListener(new NetResponseListener.NetFaildListener() {
            @Override
            public void onFaild(Throwable t, int requestCode) {
                if (listener != null) {
                    listener.onFailed(finalPath);
                } 
            }
        });
        x.image().loadDrawable(finalPath, new ImageOptions.Builder().build(),requestHandler);
    }

    public interface SDisplayImageListener {
        void onSuccess(View view, String path, Drawable drawable);
    }

    public interface SDownloadImageListener {
        void onSuccess(String path, Bitmap bitmap);

        void onFailed(String path);
    }
}