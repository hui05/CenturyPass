package sjtb.e_nci.com.centurypass.module.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import infrastructure.base.fragment.BaseLazyFragment;
import infrastructure.ui.CircleImageView;
import sjtb.e_nci.com.centurypass.R;
import sjtb.e_nci.com.centurypass.module.login.login.LoginActivity;
import sjtb.e_nci.com.centurypass.module.main.MainActivity;

/**
 * Created by hui on 2017/3/13 0013.
 *
 * @author hui
 */

public class PersonalFragment extends BaseLazyFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_head_portrait)
    CircleImageView ivHeadPortrait;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.iv_identification_markings)
    ImageView ivIdentificationMarkings;
    @BindView(R.id.iv_badge)
    ImageView ivBadge;
    @BindView(R.id.iv_modify)
    ImageView ivModify;
    @BindView(R.id.tv_order_management)
    TextView tvOrderManagement;
    @BindView(R.id.tv_my_team)
    TextView tvMyTeam;
    @BindView(R.id.tv_my_card)
    TextView tvMyCard;
    @BindView(R.id.tv_query_promotion_rate)
    TextView tvQueryPromotionRate;
    @BindView(R.id.tv_query_promotion_fee)
    TextView tvQueryPromotionFee;
    @BindView(R.id.tv_exhibition_industry_guidance)
    TextView tvExhibitionIndustryGuidance;
    @BindView(R.id.tv_activity_zone)
    TextView tvActivityZone;
    @BindView(R.id.tv_exchange)
    TextView tvExchange;
    @BindView(R.id.tv_my_task)
    TextView tvMyTask;
    @BindView(R.id.tv_claims_guide)
    TextView tvClaimsGuide;
    @BindView(R.id.tv_common_problem)
    TextView tvCommonProblem;
    @BindView(R.id.tv_more)
    TextView tvMore;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal_center;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void lazyLoadData() {

    }


    @OnClick({R.id.iv_head_portrait, R.id.tv_order_management, R.id.tv_my_team, R.id.tv_my_card,
            R.id.tv_query_promotion_rate, R.id.tv_query_promotion_fee, R.id
            .tv_exhibition_industry_guidance, R.id.tv_activity_zone, R.id.tv_exchange, R.id
            .tv_my_task, R.id.tv_claims_guide, R.id.tv_common_problem, R.id.tv_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head_portrait:
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_order_management:
                break;
            case R.id.tv_my_team:
                Intent intent1 = new Intent(mActivity,LoginActivity.class);
                startActivityForResult(intent1, MainActivity.LOGIN_REDIRECT_INSIDE);
                break;
            case R.id.tv_my_card:
                break;
            case R.id.tv_query_promotion_rate:
                break;
            case R.id.tv_query_promotion_fee:
                break;
            case R.id.tv_exhibition_industry_guidance:
                break;
            case R.id.tv_activity_zone:
                break;
            case R.id.tv_exchange:
                break;
            case R.id.tv_my_task:
                break;
            case R.id.tv_claims_guide:
                break;
            case R.id.tv_common_problem:
                break;
            case R.id.tv_more:
                break;
        }
    }
}
