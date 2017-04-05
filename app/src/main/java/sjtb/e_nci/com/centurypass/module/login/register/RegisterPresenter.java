package sjtb.e_nci.com.centurypass.module.login.register;

import com.zhy.http.okhttp.callback.GenericsCallback;

import infrastructure.util.ToastUtils;
import okhttp3.Call;
import sjtb.e_nci.com.centurypass.module.login.bean.GetVerificationCodeResponseBean;
import sjtb.e_nci.com.centurypass.module.login.bean.RegisterResponseBean;
import sjtb.e_nci.com.centurypass.util.CheckUtils;

/**
 * Created by hui on 2017/3/23 0023.
 *
 * @author hui
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private final RegisterContract.View view;
    private final RegisterModel registerModel;

    public RegisterPresenter(RegisterContract.View view, RegisterModel registerModel) {
        this.view = view;
        this.registerModel = registerModel;
    }

    @Override
    public void getVerificationCode() {
        String mobileNo = view.getMobileNo();
        if (!CheckUtils.checkOwnerMobile(mobileNo)) {
            return;
        }
        view.showLoading();
        registerModel.getVerificationCode(mobileNo, new GenericsCallback<GetVerificationCodeResponseBean>() {

            @Override
            public void onError(Call call, Exception e, int id) {
                view.hideLoading();
                view.getCodeFailure(e.getMessage());
            }

            @Override
            public void onResponse(GetVerificationCodeResponseBean response, int id) {
                view.hideLoading();
                view.getCodeSuccess(response);
            }
        });
    }

    @Override
    public void register() {
        String mobileNo = view.getMobileNo();
        String verificationCode = view.getVerificationCode();
        String password = view.getPassword();
        String confirmPassword = view.getConfirmPassword();

        if (!CheckUtils.checkOwnerMobile(mobileNo)) {
            return;
        }
        if (!CheckUtils.checkVerificationCode(verificationCode)) {
            return;
        }
        if (!CheckUtils.checkPwd(password)) {
            return;
        }
        if (!CheckUtils.checkPwdConfirm(confirmPassword)) {
            return;
        }
        if (!password.equals(confirmPassword)) {
            ToastUtils.showToast("两次输入的密码不一致");
            return;
        }

        registerModel.register(mobileNo, password, verificationCode, new GenericsCallback<RegisterResponseBean>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                view.hideLoading();
                view.registerFailure(e.getMessage());
            }

            @Override
            public void onResponse(RegisterResponseBean response, int id) {
                view.hideLoading();
                view.registerSuccess(response);
            }
        });

    }
}
