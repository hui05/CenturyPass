package infrastructure.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import infrastructure.base.BaseApplication;


/**
 * ToastUtils
 * 
 *
 */
public class ToastUtils {

    /**
     * 获取上下文(长生命周期)
     *
     * @return
     */
    public static Context getContext() {
        return BaseApplication.getContext();
    }

    private static Toast mToast;

    /**
     * 静态吐司
     *
     * @param context
     * @param text
     */
    public static void showToast(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        mToast.setGravity(Gravity.CENTER,0,0);
        mToast.setText(text);
        mToast.show();
    }

    /**
     * 静态吐司  (长时间)
     *
     * @param context
     * @param text
     */
    public static void showLongToast(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        }
        mToast.setGravity(Gravity.CENTER,0,0);
        mToast.setText(text);
        mToast.show();
    }

    /**
     * 不需要上下文对象的  静态toast
     */
    public static void showToast(String text) {
        showToast(getContext(), text);
    }


    /**
     * 不需要上下文对象的  静态toast (长时间)
     */
    public static void showLongToast(String text) {
        showLongToast(getContext(), text);
    }



    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}