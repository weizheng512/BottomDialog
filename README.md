# Bootom Dialog
仿照IOS从下方弹窗的效果，符合用户习惯

## 效果
 ![效果](/screenshot/GIF.gif)
## 代码实现
### BaseDialog

	public class BaseDialog implements View.OnClickListener{
	    protected Dialog  dialog;
	    protected Context mContext;
	
	    protected BaseDialog(Context context) {
	        this.mContext = context;
	        this.dialog = new Dialog(mContext, R.style.picker_dialog);
	    }
	
	    /**
	     * 设置dialog从下方弹出
	     * @param context
	     * @param dialog
	     */
	    protected void setDialogLocation(Context context, Dialog dialog) {
	        Window window = dialog.getWindow();
	        window.setWindowAnimations(R.style.main_menu_animstyle);
	        WindowManager.LayoutParams lp      = window.getAttributes();
	        WindowManager              manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	        lp.x = 0;
	        lp.y = manager.getDefaultDisplay().getHeight();
	        // 以下这两句是为了保证按钮可以水平满屏
	        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
	        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
	        // 设置显示位置
	        dialog.onWindowAttributesChanged(lp);
	        // 设置点击外围解散
	        dialog.setCanceledOnTouchOutside(true);
	    }
	
	    public void show() {
	        if (dialog != null) {
	            dialog.show();
	        }
	    }
	
	    public void dismiss() {
	        if (dialog != null) {
	            dialog.dismiss();
	        }
	    }
	
	    @Override
	    public void onClick(View v) {
	        dismiss();
	    }
	}

### PhotoChioceDialog


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
### MainActivity
	PhotoChioceDialog dialog = new PhotoChioceDialog(this);
        dialog.setClickCallback(new PhotoChioceDialog.ClickCallback() {
            @Override
            public void doAlbum() {
                Toast.makeText(MainActivity.this,"选择相册",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void doCancel() {
            }

            @Override
            public void doCamera() {
                Toast.makeText(MainActivity.this,"选择相机",Toast.LENGTH_SHORT).show();
            }
        });
	dialog.show();
