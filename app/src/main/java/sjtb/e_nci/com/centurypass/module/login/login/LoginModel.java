package sjtb.e_nci.com.centurypass.module.login.login;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.GenericsCallback;

import infrastructure.util.LogUtil;
import okhttp3.Call;
import sjtb.e_nci.com.centurypass.base.BaseAppModel;
import sjtb.e_nci.com.centurypass.finals.AppConstant;
import sjtb.e_nci.com.centurypass.module.login.bean.LoginRequestBean;
import sjtb.e_nci.com.centurypass.module.login.bean.LoginResponseBean;

/**
 * Created by hui on 2017/3/23 0023.
 *
 * @author hui
 */

public class LoginModel extends BaseAppModel implements ILoginModel {

    @Override
    public void login(String userName, String password, final Callback<LoginResponseBean>
            callback) {

        final LoginRequestBean loginRequestBean = new LoginRequestBean();
        loginRequestBean.transNo = "1111";
        loginRequestBean.messageBody = new LoginRequestBean.MessageBodyBean();
        loginRequestBean.messageBody.mobile = userName;
        loginRequestBean.messageBody.password = password;
        loginRequestBean.messageBody.type = "R";

        String json = new Gson().toJson(loginRequestBean);
        LogUtil.d("loginRequestBean::" + json);

        LogUtil.d("LoginModelTAG:"+TAG);

        OkHttpUtils.postJson().url(AppConstant.SIT_URL_LOGIN).tag(TAG).json(json).build()
                .execute(new GenericsCallback<LoginResponseBean>() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onError(call, e, id);
                    }

                    @Override
                    public void onResponse(LoginResponseBean loginResponseBean, int id) {
                        callback.onResponse(loginResponseBean, id);
                    }
                });
    }
}
