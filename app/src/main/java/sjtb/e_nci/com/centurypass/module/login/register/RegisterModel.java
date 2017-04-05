package sjtb.e_nci.com.centurypass.module.login.register;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;

import infrastructure.util.LogUtil;
import okhttp3.Call;
import sjtb.e_nci.com.centurypass.finals.AppConstant;
import sjtb.e_nci.com.centurypass.module.login.bean.GetVerificationCodeRequestBean;
import sjtb.e_nci.com.centurypass.module.login.bean.GetVerificationCodeResponseBean;
import sjtb.e_nci.com.centurypass.module.login.bean.RegisterRequestBean;
import sjtb.e_nci.com.centurypass.module.login.bean.RegisterResponseBean;

/**
 * Created by hui on 2017/3/23 0023.
 *
 * @author hui
 */

public class RegisterModel implements IRegisterModel {

    protected String TAG = this.getClass().getSimpleName()+hashCode();

    @Override
    public void getVerificationCode(String mobileNo, final
    GenericsCallback<GetVerificationCodeResponseBean> callback) {
        final GetVerificationCodeRequestBean getVerificationCodeRequestBean = new
                GetVerificationCodeRequestBean();
        getVerificationCodeRequestBean.appOpenId = "app_test";
        getVerificationCodeRequestBean.transNo = "123";
        getVerificationCodeRequestBean.messageBody = new GetVerificationCodeRequestBean
                .MessageBodyBean();
        getVerificationCodeRequestBean.messageBody.mobile = mobileNo;

        String json = new Gson().toJson(getVerificationCodeRequestBean);

        LogUtil.d("getVerificationCodeRequestBean::" + json);

        OkHttpUtils.postJson().url(AppConstant.SIT_URL_GET_VERIFICATION_CODE).json
                (json).build().execute(new GenericsCallback<GetVerificationCodeResponseBean>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onError(call, e, id);
            }

            @Override
            public void onResponse(GetVerificationCodeResponseBean response, int id) {
                callback.onResponse(response, id);
            }
        });

    }

    @Override
    public void register(String mobileNo, String password, String verificationCode, final
    GenericsCallback<RegisterResponseBean>
            callback) {
        final RegisterRequestBean registerRequestBean = new RegisterRequestBean();
        registerRequestBean.transNo = "1111";
        registerRequestBean.messageBody = new RegisterRequestBean.MessageBodyBean();
        registerRequestBean.messageBody.mobile = mobileNo;
        registerRequestBean.messageBody.code = verificationCode;
        registerRequestBean.messageBody.password = password;

        String json = new Gson().toJson(registerRequestBean);
        LogUtil.d("registerRequestBean::" + json);

        OkHttpUtils.postJson().url(AppConstant.SIT_URL_REGISTER).json(json).build()
                .execute(new GenericsCallback<RegisterResponseBean>() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onError(call, e, id);
                    }

                    @Override
                    public void onResponse(RegisterResponseBean response, int id) {
                        callback.onResponse(response, id);
                    }
                });
    }
}
