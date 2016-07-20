package com.feealan.bottomdialog.utils;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

/**
 * ================================================
 *
 * 图像选择
 * @author fee https://github.com/FeeAlan/BootomDialog
 *
 * created 2016/07/20
 *
 * ================================================
 */
public class ImageUtils {
    /**
     * 跳转到相册的状态码
     */
    public static final int REQUEST_CODE_FROM_ALBUM = 1;
    /**
     * 跳转到相机的状态码
     */
    public static final int REQUEST_CODE_FROM_CARMERA = 2;

    /**
     * 跳转到相册
     */
    public static void getBitmapFromAlbum(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(intent, REQUEST_CODE_FROM_ALBUM);
    }

    /**
     * 跳转到相机
     * @param activity
     */
    public static void getBitmapFromCarmera(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, REQUEST_CODE_FROM_CARMERA);
    }
}
