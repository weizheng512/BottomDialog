package com.feealan.bottomdialog;

import android.content.Context;
import android.view.View;

/**
 *  图片选择弹出框
 *
 * @author fee
 * @version 1.0
 * @created 2016/04/20
 */
public class PhotoChioceDialog extends BaseDialog{
    private ClickCallback clickCallback;
    public PhotoChioceDialog(Context context){
        super(context);
        dialog.setContentView(R.layout.dialog_pic_chioce);
        dialog.findViewById(R.id.btn_album).setOnClickListener(this);
        dialog.findViewById(R.id.btn_camera).setOnClickListener(this);
        dialog.findViewById(R.id.btn_cancel).setOnClickListener(this);
        setDialogLocation(mContext, dialog);
    }

    public void setClickCallback(ClickCallback clickCallback) {
        this.clickCallback = clickCallback;
    }

    public interface ClickCallback {
        /**
         * 进入相册
         */
        void doAlbum();

        /**
         * 取消
         */
        void doCancel();

        /**
         * 进入相机
         */
        void doCamera();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_album:
                if (clickCallback!=null)
                    clickCallback.doAlbum();
                break;
            case R.id.btn_camera:
                if (clickCallback!=null)
                    clickCallback.doCamera();
                break;
            case R.id.btn_cancel:
                if (clickCallback!=null)
                    clickCallback.doCancel();
                break;
        }
    }


}
