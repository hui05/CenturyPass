package sjtb.e_nci.com.centurypass.module.login.register;

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
import sjtb.e_nci.com.centurypass.module.login.bean.GetVerificationCodeResponseBean;
import sjtb.e_nci.com.centurypass.module.login.bean.RegisterResponseBean;
import sjtb.e_nci.com.centurypass.ui.CustomProgressDialog;

/**
 * Created by hui on 2017/3/16 0016.
 *
 * @author hui
 */

public class RegisterActivity extends AppBaseActivity implements RegisterContract.View {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_get_verification_code)
    TextView tvGetVerificationCode;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.imv_show_pwd)
    ImageView imvShowPwd;
    @BindView(R.id.et_pwd_confirm)
    EditText etPwdConfirm;
    @BindView(R.id.imv_show_pwd_confirm)
    ImageView imvShowPwdConfirm;
    @BindView(R.id.btn_ok)
    Button btnOK;
    private boolean isRegistered;
    private RegisterPresenter presenter;

    @Override
    protected void initVariables() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null)
            return;
        isRegistered = bundle.getBoolean(AppConstant.isRegistered, false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        presenter = new RegisterPresenter(this, new RegisterModel());


        progressDialog = new CustomProgressDialog(this, R.style.CustomProgressDialog);
        if (isRegistered) {
            tvTitle.setText("注册页面");
        } else {
            tvTitle.setText("找回密码");
        }
    }

    protected boolean isShowPwd = false;
    protected boolean isShowPwdConfirm = false;

    @OnClick({R.id.ll_back, R.id.tv_get_verification_code, R.id.imv_show_pwd, R.id
            .imv_show_pwd_confirm, R.id.btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_get_verification_code:
                //  请求后台,获取短信验证码
                presenter.getVerificationCode();
                break;
            case R.id.imv_show_pwd:
                showOrHide(etPwd, imvShowPwd, isShowPwd);
                isShowPwd = !isShowPwd;
                break;
            case R.id.imv_show_pwd_confirm:
                showOrHide(etPwdConfirm, imvShowPwdConfirm, isShowPwdConfirm);
                isShowPwdConfirm = !isShowPwdConfirm;
                break;
            case R.id.btn_ok:
                presenter.register();
                break;
        }
    }


    @Override
    public String getMobileNo() {
        return etPhone.getText().toString().trim();
    }

    @Override
    public String getVerificationCode() {
        return etVerificationCode.getText().toString().trim();
    }


    @Override
    public String getPassword() {
        return etPwd.getText().toString().trim();
    }

    @Override
    public String getConfirmPassword() {
        return etPwdConfirm.getText().toString().trim();
    }

    @Override
    public void getCodeSuccess(GetVerificationCodeResponseBean verificationCodeResponseBean) {
        if (verificationCodeResponseBean == null) {
            ToastUtils.showToast("短信获取失败");
            return;
        }
        switch (verificationCodeResponseBean.code) {
            case "1000":
                ToastUtils.showToast("短信验证码已经发送到你的手机,请注意查收");
                break;
            default:
                ToastUtils.showLongToast(verificationCodeResponseBean.errMsg);
                break;
        }
    }

    @Override
    public void getCodeFailure(String errorMsg) {
        ToastUtils.showToast(errorMsg);
    }

    @Override
    public void registerSuccess(RegisterResponseBean registerResponseBean) {
        if (registerResponseBean == null) {
            ToastUtils.showToast("注册失败");
            return;
        }
        switch (registerResponseBean.code) {
            case "1000":
                ToastUtils.showToast("注册成功");
                SpUtil.putString(AppConstant.tokenId, registerResponseBean.messageBody.appOpenId);
                finish();
                break;
            default:
                ToastUtils.showLongToast(registerResponseBean.errMsg);
                break;
        }
    }

    @Override
    public void registerFailure(String errorMsg) {
        ToastUtils.showToast(errorMsg);
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
}
