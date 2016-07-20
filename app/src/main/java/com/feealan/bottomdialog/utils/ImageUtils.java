package com.feealan.bottomdialog.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Administrator on 2016/7/20.
 */
public class ImageUtils {
    /**
     * 跳转到相册的状态码
     */
    public static final int REQUEST_CODE_FROM_ALBUM = 1;
    /**
     * 跳转到相机的状态码
     */
    public static final int REQUEST_CODE_FROM_CARMERA = 101;

    /**
     * 从相册获取Bitmap
     */
    public static void getBitmapFromAlbum(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(intent, REQUEST_CODE_FROM_ALBUM);
    }

    public static void getBitmapFromCarmera(Activity activity) {

    }
}
