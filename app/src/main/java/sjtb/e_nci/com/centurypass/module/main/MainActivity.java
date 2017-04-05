package sjtb.e_nci.com.centurypass.module.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import infrastructure.base.fragment.BaseFragment;
import infrastructure.ui.MainRadioButton;
import infrastructure.ui.NoSlideViewPager;
import infrastructure.util.LogUtil;
import sjtb.e_nci.com.centurypass.R;
import sjtb.e_nci.com.centurypass.adapter.MainPagerAdapter;
import sjtb.e_nci.com.centurypass.base.AppBaseActivity;
import sjtb.e_nci.com.centurypass.module.main.fragment.FragmentFactory;

public class MainActivity extends AppBaseActivity {

    private static final int HOME_PAGE = 0;
    private static final int PRODUCT_MARKET = 1;
    private static final int PERSONAL = 2;
    private static final int MORE = 3;

    public static final int LOGIN_REDIRECT_OUTSIDE = 3000;    //登录后跳转到其它页面
    public static final int LOGIN_REDIRECT_INSIDE = 3001;    //登录后仍然在本页面

    @BindView(R.id.toolbar)
    Toolbar toolBar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.layout_content)
    FrameLayout layoutContent;
    @BindView(R.id.my_viewPager)
    NoSlideViewPager myViewPager;
    @BindView(R.id.rb_homepage)
    MainRadioButton rbHomepage;
    @BindView(R.id.rb_product_market)
    MainRadioButton rbProductMarket;
    @BindView(R.id.rb_personal)
    MainRadioButton rbPersonal;
    @BindView(R.id.point)
    TextView point;
    @BindView(R.id.shopping_cart_page)
    RelativeLayout shoppingCartPage;
    @BindView(R.id.rb_more)
    MainRadioButton rbMore;
    @BindView(R.id.main_radio)
    LinearLayout mainRadio;

//    private List<BaseFragment> fragments = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        tvTitle.setText("太汇保车险");

//        fragments.clear();
//        fragments.add(FragmentFactory.createFragment(HOME_PAGE));
//        fragments.add(FragmentFactory.createFragment(PRODUCT_MARKET));
//        fragments.add(FragmentFactory.createFragment(PERSONAL));
//        fragments.add(FragmentFactory.createFragment(MORE));

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
//        myViewPager.setOffscreenPageLimit(3);
        myViewPager.setAdapter(adapter);
        myViewPager.setCurrentItem(0, false);
        changeState(true, false, false, false);
    }

    private int item = HOME_PAGE;

    @OnClick({R.id.rb_homepage, R.id.rb_product_market, R.id.rb_personal, R.id.rb_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_homepage:
                item = HOME_PAGE;
                changeState(true, false, false, false);
                break;
            case R.id.rb_product_market:
                item = PRODUCT_MARKET;
                changeState(false, true, false, false);
                break;
            case R.id.rb_personal:
                item = PERSONAL;
                changeState(false, false, true, false);
                break;
            case R.id.rb_more:
                item = MORE;
                changeState(false, false, false, true);
                break;
        }
        // 切换对应的Fragment
        myViewPager.setCurrentItem(item, false);
    }

    private void changeState(boolean homeB, boolean goodsB, boolean shopB, boolean userB) {
        rbHomepage.setChecked(homeB);
        rbProductMarket.setChecked(goodsB);
        rbPersonal.setChecked(shopB);
        rbMore.setChecked(userB);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case LOGIN_REDIRECT_INSIDE:
                doSomething();
                break;
            case LOGIN_REDIRECT_OUTSIDE:
//                loadActivity();
                break;
        }
    }

    private void doSomething() {
        LogUtil.e("doSomething");
    }
}
