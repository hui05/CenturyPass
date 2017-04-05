package sjtb.e_nci.com.centurypass.ui;

/**
 * Created by hui on 2016/11/8 0008.
 *
 * @author hui
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import sjtb.e_nci.com.centurypass.R;


/**
 * 加载提醒对话框
 */
public class CustomProgressDialog extends ProgressDialog {

    public TextView getLoadingText() {
        return loadingText;
    }
    private TextView loadingText;

    public CustomProgressDialog(Context context) {
        super(context);
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(getContext());
    }

    private void init(Context context) {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        setContentView(R.layout.dialog_loading);
        loadingText = (TextView) findViewById(R.id.tv_load_dialog);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(params);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }


}