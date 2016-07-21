package com.feealan.bottomdialog.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ================================================
 * <p>
 * 图像选择
 *
 * @author fee https://github.com/FeeAlan/BootomDialog
 *         <p>
 *         created 2016/07/20
 *         <p>
 *         ================================================
 */
public class ImageUtils {
    /**
     * 跳转到相册的状态码
     */
    public static final int REQUEST_CODE_FROM_ALBUM   = 1;
    /**
     * 跳转到相机的状态码
     */
    public static final int REQUEST_CODE_FROM_CARMERA = 2;
    /**
     * 跳转到系统裁切页面的状态码
     */
    public static final int REQUEST_CODE_FROM_CROP    = 3;

    /**
     * 跳转到相册
     */
    public static void openAlbum(Activity activity) {
        Intent takePictureIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {// 相机被卸载时不会崩溃
            activity.startActivityForResult(takePictureIntent, REQUEST_CODE_FROM_ALBUM);
        }
    }

    /**
     * 跳转到相机
     *
     * @param activity
     */
    public static void openCarmera(Activity activity,Uri uri) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {//相机被卸载时不会崩溃
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
            activity.startActivityForResult(takePictureIntent, REQUEST_CODE_FROM_CARMERA);
        }
    }

    /**
     * 跳转到系统裁切界面
     * @param activity
     * @param uri
     */
    public static void cropPhoto(Activity activity, Uri uri) {
        //跳转到android系统自带的图片裁剪工具
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //设置裁剪
        intent.putExtra("crop", true);
        //比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //裁剪宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, REQUEST_CODE_FROM_CROP);
    }

    /**
     * 创建一个文件用于存放拍摄的照片
     *
     * @return
     * @throws IOException
     */
    public static Uri createImageFile() {
        String timeStamp     = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //存放在公共目录内
        //如果不想照片被外部应用查看可以放在 getExternalFilesDir()，该目录会随着app卸载而被清空
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image      = null;
        try {
            image = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Uri.fromFile(image);
    }
}
