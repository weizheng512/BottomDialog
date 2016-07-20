package com.feealan.bottomdialog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.feealan.bottomdialog.dialog.PhotoChioceDialog;
import com.feealan.bottomdialog.utils.ImageUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;

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
        if (requestCode == ImageUtils.REQUEST_CODE_FROM_ALBUM) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    img.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
