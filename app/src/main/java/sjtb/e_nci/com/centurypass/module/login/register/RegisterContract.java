package sjtb.e_nci.com.centurypass.module.login.register;

import com.zhy.http.okhttp.callback.GenericsCallback;

import sjtb.e_nci.com.centurypass.base.BasePresenter;
import sjtb.e_nci.com.centurypass.base.BaseView;
import sjtb.e_nci.com.centurypass.module.login.bean.GetVerificationCodeResponseBean;
import sjtb.e_nci.com.centurypass.module.login.bean.RegisterResponseBean;

/**
 * Created by hui on 2017/3/23 0023.
 *
 * @author hui
 */

public interface RegisterContract {

    interface View extends BaseView {

        String getMobileNo();

        String getVerificationCode();

        String getPassword();

        String getConfirmPassword();

        /**
         *  获取验证码成功
         * @param verificationCodeResponseBean
         */
        void getCodeSuccess(GetVerificationCodeResponseBean verificationCodeResponseBean
        );

        /**
         *  获取验证码失败
         * @param errorMsg
         */
        void getCodeFailure(String errorMsg);

        void registerSuccess(RegisterResponseBean registerResponseBean);

        void registerFailure(String errorMsg);

    }

    interface Presenter extends BasePresenter {

        void getVerificationCode();

        void register();

    }



}
