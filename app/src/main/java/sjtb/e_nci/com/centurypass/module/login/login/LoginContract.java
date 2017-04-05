package sjtb.e_nci.com.centurypass.module.login.login;

import sjtb.e_nci.com.centurypass.base.BasePresenter;
import sjtb.e_nci.com.centurypass.base.BaseView;
import sjtb.e_nci.com.centurypass.module.login.bean.LoginResponseBean;

/**
 * Created by hui on 2017/3/23 0023.
 *
 * @author hui
 */

public interface LoginContract {

    interface View extends BaseView {

        String getUserName();

        String getPassWord();

        void loginSuccess(LoginResponseBean loginResponseBean);

        void loginFailure(String errorMessage);
    }

    interface Presenter extends BasePresenter{
        void login();
    }

}
