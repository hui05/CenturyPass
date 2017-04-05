package sjtb.e_nci.com.centurypass.module.splash;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import infrastructure.ui.CircleProgressBar;
import infrastructure.util.LogUtil;
import infrastructure.util.SpUtil;
import sjtb.e_nci.com.centurypass.R;
import sjtb.e_nci.com.centurypass.adapter.BasePagerAdapter;
import sjtb.e_nci.com.centurypass.base.AppBaseActivity;
import sjtb.e_nci.com.centurypass.module.main.MainActivity;

/**
 * 加载页面
 */
public class LoadingActivity extends AppBaseActivity {

    @BindView(R.id.pb_splash)
    CircleProgressBar pbSplash;
    @BindView(R.id.layout_splash)
    RelativeLayout layoutSplash;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.pager_indicator)
    CirclePageIndicator pagerIndicator;
    @BindView(R.id.btn_enter_home)
    Button btnEnterHome;
    @BindView(R.id.layout_intro)
    RelativeLayout layoutIntro;

    private long mShowMainTime;
    private AsyncTask<String, String, String> mShowMainTask;

    @Override
    protected void initVariables() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_loading;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        if (whetherFirstTimeEnterApp()) {
            initIntroView();        // 进入说明页
        } else {
            initSplashView();       //进入闪屏页
        }
    }


    private void initSplashView() {
        layoutSplash.setVisibility(View.VISIBLE);
        layoutIntro.setVisibility(View.INVISIBLE);

        mShowMainTime = System.currentTimeMillis() + 1000;
        mShowMainTask = new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... strings) {
                if (mShowMainTime > System.currentTimeMillis()) {
                    long sleepTime = mShowMainTime - System.currentTimeMillis();
                    if (sleepTime > 0) {
                        SystemClock.sleep(sleepTime);
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loadActivity(MainActivity.class);
                finish();
            }
        };

        mShowMainTask.execute();
    }

    int[] loadingPics = new int[]{R.mipmap.loading_bg1, R.mipmap.loading_bg2, R.mipmap.loading_bg3};
    private List<ImageView> mPageView;

    private void initIntroView() {
        layoutSplash.setVisibility(View.INVISIBLE);
        layoutIntro.setVisibility(View.VISIBLE);
        // 添加数据
        mPageView = new ArrayList<>();
        for (int loadingPic : loadingPics) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(loadingPic);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mPageView.add(imageView);
        }

        viewPager.setAdapter(new BasePagerAdapter(mPageView));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.d("position:" + position);
                LogUtil.d(" mPageView.size():" + mPageView.size());
                if (position == mPageView.size() - 1) {
                    btnEnterHome.setVisibility(View.VISIBLE);
                } else {
                    btnEnterHome.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        pagerIndicator.setViewPager(viewPager);  // 设置指示器
        btnEnterHome.setVisibility(View.INVISIBLE);
    }

    /**
     * @return true ,first time entering . false, not first time
     */
    public boolean whetherFirstTimeEnterApp() {
        return SpUtil.getBoolean(this, getString(R.string.isFirst), true);
    }

    @OnClick({R.id.btn_enter_home, R.id.pb_splash})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_enter_home:
                SpUtil.putBoolean(mContext, getString(R.string.isFirst), false);
                loadActivity(MainActivity.class);
                finish();
                break;
            case R.id.pb_splash:
                pbSplash.stop();
                if (!mShowMainTask.isCancelled()) {
                    mShowMainTask.cancel(true);
                }
                loadActivity(MainActivity.class);
                finish();
                break;
        }
    }

}
