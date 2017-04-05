package sjtb.e_nci.com.centurypass.module.purchase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import sjtb.e_nci.com.centurypass.R;
import sjtb.e_nci.com.centurypass.base.AppBaseActivity;

public class SupplyInfoActivity extends AppBaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_read_driving)
    TextView tvReadDriving;
    @BindView(R.id.et_vin)
    EditText etVin;
    @BindView(R.id.et_engine_no)
    EditText etEngineNo;
    @BindView(R.id.et_register_date)
    EditText etRegisterDate;
    @BindView(R.id.et_brand)
    EditText etBrand;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.tv_transfer_yes)
    TextView tvTransferYes;
    @BindView(R.id.iv_transfer_yes)
    ImageView ivTransferYes;
    @BindView(R.id.rl_transfer_yes)
    RelativeLayout rlTransferYes;
    @BindView(R.id.tv_transfer_no)
    TextView tvTransferNo;
    @BindView(R.id.iv_transfer_no)
    ImageView ivTransferNo;
    @BindView(R.id.rl_transfer_no)
    RelativeLayout rlTransferNo;
    @BindView(R.id.tv_loan_yes)
    TextView tvLoanYes;
    @BindView(R.id.iv_loan_yes)
    ImageView ivLoanYes;
    @BindView(R.id.rl_loan_yes)
    RelativeLayout rlLoanYes;
    @BindView(R.id.tv_loan_no)
    TextView tvLoanNo;
    @BindView(R.id.iv_loan_no)
    ImageView ivLoanNo;
    @BindView(R.id.rl_loan_no)
    RelativeLayout rlLoanNo;
    @BindView(R.id.tv_read_driver)
    TextView tvReadDriver;
    @BindView(R.id.et_owner_name)
    EditText etOwnerName;
    @BindView(R.id.et_owner_id)
    EditText etOwnerId;
    @BindView(R.id.et_owner_phone)
    EditText etOwnerPhone;
    @BindView(R.id.btn_place_order)
    Button btnPlaceOrder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_supply_info;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        tvTitle.setText("填写补充信息");
    }

    @OnClick({R.id.ll_back, R.id.tv_read_driving, R.id.rl_transfer_yes, R.id.rl_transfer_no, R.id
            .rl_loan_yes, R.id.rl_loan_no, R.id
            .tv_read_driver, R.id.btn_place_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_read_driving:
                break;
            case R.id.rl_transfer_yes:
                isTransfer = true;
                chooseWhether(isTransfer, tvTransferYes, ivTransferYes, tvTransferNo, ivTransferNo);
                break;
            case R.id.rl_transfer_no:
                isTransfer = false;
                chooseWhether(isTransfer, tvTransferYes, ivTransferYes, tvTransferNo, ivTransferNo);
                break;
            case R.id.rl_loan_yes:
                isLoan = true;
                chooseWhether(isLoan, tvLoanYes, ivLoanYes, tvLoanNo, ivLoanNo);
                break;
            case R.id.rl_loan_no:
                isLoan = false;
                chooseWhether(isLoan, tvLoanYes, ivLoanYes, tvLoanNo, ivLoanNo);
                break;
            case R.id.tv_read_driver:
                break;
            case R.id.btn_place_order:
                break;
        }
    }


    private boolean isTransfer = false;
    private boolean isLoan = false;


    /**
     * 选择是否
     */
    private void chooseWhether(boolean isYes, TextView tvYes, ImageView ivYes, TextView tvNo,
                               ImageView ivNo) {
        if (isYes) {
            tvYes.setTextColor(getResources().getColor(R.color.text_blue));
            ivYes.setImageResource(R.mipmap.img_purchase_checked);

            tvNo.setTextColor(getResources().getColor(R.color.text_gray));
            ivNo.setImageResource(R.mipmap.img_purchase_unchecked);
        } else {
            tvYes.setTextColor(getResources().getColor(R.color.text_gray));
            ivYes.setImageResource(R.mipmap.img_purchase_unchecked);

            tvNo.setTextColor(getResources().getColor(R.color.text_blue));
            ivNo.setImageResource(R.mipmap.img_purchase_checked);
        }
    }


}
