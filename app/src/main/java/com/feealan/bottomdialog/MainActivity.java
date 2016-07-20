package com.feealan.bottomdialog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.feealan.bottomdialog.dialog.PhotoChioceDialog;
import com.feealan.bottomdialog.utils.ImageUtils;
import com.feealan.bottomdialog.utils.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author fee https://github.com/FeeAlan/BootomDialog
 * @created 2016/07/18
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button            btn;
    private ImageView         img;
    private PhotoChioceDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDialog();


    }

    private void initDialog() {
        dialog = new PhotoChioceDialog(this);
        dialog.setClickCallback(new PhotoChioceDialog.ClickCallback() {
            @Override
            public void doAlbum() {
                chioceAlbum();
            }

            @Override
            public void doCancel() {
            }

            @Override
            public void doCamera() {
                chioceCamera();
            }
        });
    }

    /**
     * 选择相机
     */
    private void chioceCamera() {
        ImageUtils.getBitmapFromCarmera(this);
    }

    /**
     * 选择相册
     */
    private void chioceAlbum() {
        ImageUtils.getBitmapFromAlbum(this);
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);
        img = (ImageView) findViewById(R.id.img);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                dialog.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ImageUtils.REQUEST_CODE_FROM_ALBUM:
                    onSelectFromGalleryResult(data);
                    break;
                case ImageUtils.REQUEST_CODE_FROM_CARMERA:
                    onSelectFromCameraResult(data);
                    break;
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    if(userChoosenTask.equals("Take Photo"))
//                        cameraIntent();
//                    else if(userChoosenTask.equals("Choose from Library"))
//                        galleryIntent();
//                } else {
////code for deny
//                }
                break;
        }
    }

    /**
     * 选择相机获取结果
     * @param data
     */
    private void onSelectFromCameraResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        //压缩
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        img.setImageBitmap(thumbnail);
    }

    /**
     * 从相册返回的结果处理
     * @param data
     */
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bitmap = null;
        if (data != null) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        img.setImageBitmap(bitmap);
    }
}
