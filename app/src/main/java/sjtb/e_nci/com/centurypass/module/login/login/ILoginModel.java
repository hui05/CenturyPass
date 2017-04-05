package sjtb.e_nci.com.centurypass.module.login.login;

import com.zhy.http.okhttp.callback.Callback;

import sjtb.e_nci.com.centurypass.module.login.bean.LoginResponseBean;

/**
 * Created by hui on 2017/3/23 0023.
 *
 * @author hui
 */

public interface ILoginModel {
    void login(String userName, String password, Callback<LoginResponseBean> callback);
}
