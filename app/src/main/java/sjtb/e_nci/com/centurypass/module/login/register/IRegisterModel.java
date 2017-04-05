package sjtb.e_nci.com.centurypass.module.login.register;

import com.zhy.http.okhttp.callback.GenericsCallback;

import sjtb.e_nci.com.centurypass.module.login.bean.GetVerificationCodeResponseBean;
import sjtb.e_nci.com.centurypass.module.login.bean.RegisterResponseBean;

/**
 * Created by hui on 2017/3/23 0023.
 *
 * @author hui
 */

public interface IRegisterModel {

    void getVerificationCode(String mobileNo, GenericsCallback<GetVerificationCodeResponseBean>
            callback);

    void register(String mobileNo, String password, String verificationCode, GenericsCallback<RegisterResponseBean>
            callback);

}
