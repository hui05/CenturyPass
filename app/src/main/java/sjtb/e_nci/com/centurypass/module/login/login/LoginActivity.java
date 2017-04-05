package sjtb.e_nci.com.centurypass.module.login.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import infrastructure.util.SpUtil;
import infrastructure.util.ToastUtils;
import sjtb.e_nci.com.centurypass.R;
import sjtb.e_nci.com.centurypass.base.AppBaseActivity;
import sjtb.e_nci.com.centurypass.finals.AppConstant;
import sjtb.e_nci.com.centurypass.module.login.bean.LoginResponseBean;
import sjtb.e_nci.com.centurypass.module.login.register.RegisterActivity;
import sjtb.e_nci.com.centurypass.ui.CustomProgressDialog;

/**
 * Created by hui on 2017/3/16 0016.
 *
 * @author hui
 */

public class LoginActivity extends AppBaseActivity implements LoginContract.View {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.imv_show_pwd)
    ImageView imvShowPwd;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_phone_login)
    Button btnPhoneLogin;

    private boolean needCallBack = false;
    private boolean isShowPwd = false;
    private LoginPresenter loginPresenter;

    @Override
    protected void initVariables() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null)
            return;
        needCallBack = bundle.getBoolean(AppConstant.needCallBack, false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        loginPresenter = new LoginPresenter(this, new LoginModel());

        progressDialog = new CustomProgressDialog(this, R.style.CustomProgressDialog);
        tvRegister.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tvForgetPassword.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线

    }


    @OnClick({R.id.ll_back, R.id.tv_register, R.id.imv_show_pwd, R.id.tv_forget_password, R.id
            .btn_login, R.id.btn_phone_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean(AppConstant.isRegistered, true);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.imv_show_pwd:
                showOrHide(etPassword, imvShowPwd, isShowPwd);
                isShowPwd = !isShowPwd;
                break;
            case R.id.tv_forget_password:
                loadActivity(RegisterActivity.class);
                break;
            case R.id.btn_login:
                loginPresenter.login();
                break;
            case R.id.btn_phone_login:

                break;
        }
    }

    /**
     * 密码显示或隐藏
     *
     * @param etPwd
     * @param imvShowPwd
     * @param isShowPwd
     */
    protected void showOrHide(EditText etPwd, ImageView imvShowPwd, boolean isShowPwd) {
        isShowPwd = !isShowPwd;
        if (isShowPwd) {
            etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            imvShowPwd.setImageResource(R.mipmap.ic_login_hide);
        } else {
            etPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            imvShowPwd.setImageResource(R.mipmap.ic_login_show);
        }
        Editable etable = etPwd.getText();
        Selection.setSelection(etable, etable.length());
    }

    @Override
    public String getUserName() {
        return etPhone.getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return etPassword.getText().toString().trim();
    }

    @Override
    public void loginSuccess(LoginResponseBean loginResponseBean) {
        if (loginResponseBean == null) {
            ToastUtils.showToast("请求失败");
            return;
        }
        if (loginResponseBean.code == null) {
            ToastUtils.showToast("请求失败");
            return;
        }
        switch (loginResponseBean.code) {
            case "1000":
                ToastUtils.showToast("登录成功");
                SpUtil.putString(AppConstant.tokenId, loginResponseBean.messageBody
                        .appOpenId);
//                        Global.APP_OPEN_ID = loginResponseBean.messageBody.appOpenId;
//                        saveLoginState();
                if (needCallBack) {
                    setResult(Activity.RESULT_OK);
//                    finish();
                } else {
//                            loadActivity();
                }
                finish();
                break;
            default:
                ToastUtils.showLongToast(loginResponseBean.errMsg);
                break;
        }
    }

    @Override
    public void loginFailure(String errorMessage) {
        ToastUtils.showToast("登录失败::" + errorMessage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginPresenter != null) {
            loginPresenter.onDestroy();
        }
    }

}
