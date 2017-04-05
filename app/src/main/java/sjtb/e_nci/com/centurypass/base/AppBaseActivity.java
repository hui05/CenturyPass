package sjtb.e_nci.com.centurypass.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.GenericsCallback;
import com.zhy.http.okhttp.callback.IGenericsSerializator;

import infrastructure.base.activity.BaseActivity;
import infrastructure.util.LogUtil;
import okhttp3.Call;
import sjtb.e_nci.com.centurypass.module.login.login.LoginContract;
import sjtb.e_nci.com.centurypass.ui.CustomProgressDialog;

/**
 * Created by hui on 2017/3/9 0009.
 *
 * @author hui
 */

public abstract class AppBaseActivity extends BaseActivity implements BaseView{

    protected CustomProgressDialog progressDialog;

    /**
     *  统一处理联网异常回调
     * @param <T>
     */
    protected abstract class AbstractGenericsCallBack<T> extends GenericsCallback<T> {

        public AbstractGenericsCallBack() {
            super(new IGenericsSerializator() {
                @Override
                public <T> T transform(String response, Class<T> classOfT) {
                    T t = new Gson().fromJson(response, classOfT);
                    return t;
                }
            });
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            progressDialog.dismiss();
            LogUtil.e(e.getMessage());
            new AlertDialog.Builder(AppBaseActivity.this).setTitle("出错啦")
                    .setMessage("网络异常").setPositiveButton("确定", null)
                    .show();
        }

        @Override
        public abstract void onResponse(T response, int id);
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void setPresenter(Object presenter) {

    }
}
