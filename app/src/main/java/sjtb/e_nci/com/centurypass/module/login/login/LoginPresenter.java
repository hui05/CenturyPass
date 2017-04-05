package sjtb.e_nci.com.centurypass.module.login.login;

import com.zhy.http.okhttp.callback.GenericsCallback;

import okhttp3.Call;
import sjtb.e_nci.com.centurypass.module.login.bean.LoginResponseBean;
import sjtb.e_nci.com.centurypass.util.CheckUtils;

/**
 * Created by hui on 2017/3/23 0023.
 *
 * @author hui
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private final LoginModel loginModel;

    public LoginPresenter(LoginContract.View view, LoginModel loginModel) {
        this.view = view;
        this.loginModel = loginModel;
    }

    @Override
    public void login() {
        String userName = view.getUserName();
        String password = view.getPassWord();
        if (!CheckUtils.checkOwnerMobile(userName)) {
            return;
        }

        if (!CheckUtils.checkPwd(password)) {
            return;
        }

        view.showLoading();
        loginModel.login(userName, password, new GenericsCallback<LoginResponseBean>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                view.hideLoading();
                view.loginFailure(e.getMessage());
            }

            @Override
            public void onResponse(LoginResponseBean response, int id) {
                view.hideLoading();
                view.loginSuccess(response);
            }
        });
    }

//    @Override
    public void onDestroy() {
        view = null;
        if (loginModel != null) {
            loginModel.cancel();
        }
    }
}
