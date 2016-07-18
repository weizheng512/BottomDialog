package com.feealan.bottomdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
                Toast.makeText(MainActivity.this,"选择相册",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void doCancel() {
                dialog.dismiss();
            }

            @Override
            public void doCamera() {
                Toast.makeText(MainActivity.this,"选择相机",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
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
}
