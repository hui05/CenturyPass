package infrastructure.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import infrastructure.widgets.regionselection.ChangeAddressDialog;
import sjtb.e_nci.com.mylibrary.R;


/**
 * 对话框工具类, 提供常用对话框显示, 使用support.v7包内的AlertDialog样式
 */
public class DialogUtils {

    public static Dialog createProgressDialog(Context context) {
        return createProgressDialog(context, true);
    }

    public static Dialog createProgressDialog(Context context, boolean needCancle) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("Loading ...");
        dialog.setCancelable(needCancle);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    public static Dialog showCommonDialog(Context context, String message,
                                          DialogInterface.OnClickListener listener) {
        return new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("确定", listener)
                .setNegativeButton("取消", null)
                .show();
    }

    public static Dialog showConfirmDialog(Context context, String message,
                                           DialogInterface.OnClickListener listener) {
        return new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("确定", listener)
                .show();
    }


    /**
     *  弹出用户提示的对话框
     * @param title
     * @param content
     * @param activity
     */
    public static void showDescriptionInfoDialog(Activity activity, String title, String content) {
        // 初始化布局
        View contentView = View.inflate(activity, R.layout.dialog_description_info, null);
        final TextView tv_title = (TextView) contentView.findViewById(R.id.tv_title);
        final TextView tv_content = (TextView) contentView.findViewById(R.id.tv_content);

        Button btn_ok = (Button) contentView.findViewById(R.id.btn_ok);

        tv_title.setText(title);
        tv_content.setText(content);

        // 初始化对话框
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
        final android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.setView(contentView);
        dialog.show();

        // 设置对话框的位置
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL);
        dialogWindow.setWindowAnimations(R.style.dialogWindowAnim);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }



    /**
     * 选择车辆行驶城市的对话框
     *
     * @param editText
     */
    public static void showChooseCityDialog(Activity mActivity,final EditText editText) {
        ChangeAddressDialog mChangeAddressDialog = new ChangeAddressDialog(mActivity, ChangeAddressDialog.STYLE_CHOOSE_SENCONDARD);
        mChangeAddressDialog.show();
        mChangeAddressDialog.setAddresskListener(new ChangeAddressDialog.OnAddressCListener(){

            @Override
            public void onClick(String province, String city, String area) {
                editText.setText(province+" "+city);
            }

            @Override
            public void onClick(String provinceCode, String cityCode, String areaCode, boolean isCode) {

            }
        });

    }


}
