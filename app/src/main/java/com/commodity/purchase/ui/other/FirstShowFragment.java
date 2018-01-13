package com.commodity.purchase.ui.other;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.commodity.purchase.R;
import com.commodity.purchase.base.SFragment;
import com.commodity.purchase.ui.MainActivity;
import com.commodity.purchase.until.AppUntils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@SuppressLint("ValidFragment")
@ContentView(R.layout.fragment_first_show)
public class FirstShowFragment extends SFragment {
    @ViewInject(R.id.load_bg_img)
    ImageView load_bg_img;//显示图片的控件
    @ViewInject(R.id.enter_btn)
    TextView enter_btn;//透明的按钮，是为了让用户以为点击的是图片上的按钮
    int resId = -1;
    int position = 0;
    FirstShowActivity mActivity;


    @Override
    protected void loadData() {
    }


    @Override
    public void initView() {
        if (resId != -1) {
            //  load_bg_img.setImageDrawable(AppUntils.getBitmap(mActivity, resId));
            load_bg_img.setImageBitmap(decodeSampledBitmapFromResource(getResources(), resId, AppUntils.getSH(), AppUntils.getSH()));
        }
        //最后一页的显示单击按钮，否则隐藏按钮
        switch (position) {
            case 0:
                enter_btn.setVisibility(View.GONE);
                break;
            case 1:
                enter_btn.setVisibility(View.VISIBLE);
                break;
        }
    }

    public FirstShowFragment(FirstShowActivity activity, int resId, int position) {
        this.mActivity = activity;
        this.resId = resId;
        this.position = position;
    }


    @Event(value = {R.id.enter_btn})
    private void clicks(View v) {
        Intent intent = new Intent(mActivity, MainActivity.class);
        startActivity(intent);
        mActivity.finish();
    }

    @Override
    public void onDestroyView() {
        releaseImageViewResouce(load_bg_img);//清除图片
        super.onDestroyView();
    }

    /**
     * 处理资源图片，防止oom
     *
     * @param res
     * @param resId     资源的id
     * @param reqWidth  返回图片的宽度
     * @param reqHeight 返回图片的高度
     * @return 处理后的图片
     */
    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    /**
     * 在做跳转的时候主动回收背景图片
     *
     * @param imageView
     */
    public static void releaseImageViewResouce(ImageView imageView) {
        if (imageView == null) return;
        Drawable drawable = imageView.getBackground();
        if (drawable != null && drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
    }
}
