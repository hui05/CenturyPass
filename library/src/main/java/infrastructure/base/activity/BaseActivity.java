package infrastructure.base.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import infrastructure.base.BaseAppManager;
import infrastructure.widgets.statusbar.StatusBarUtil;
import sjtb.e_nci.com.mylibrary.R;

/**
 * Activity的基类: 公共逻辑
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected static String tag = null;

    /**
     * 上下文
     */
    protected Context mContext = null;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }


    private void init(Bundle savedInstanceState) {
        mContext = this;
        tag = "TAG_" + getClass().getSimpleName();  // 设置TAG值
        // 保存当前的Activity到任务栈中
        BaseAppManager.getInstance().addActivity(this);

        initVariables();

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        } else if (getLayoutView() != null) {
            setContentView(getLayoutView());
        }

        // 设置状态栏颜色
//        setStatusBarColor(getResources().getColor(R.color.app_primary));

        mUnbinder = ButterKnife.bind(this);  // bind在setContentView之后

        initViewsAndEvents(savedInstanceState);
    }



    /**
     * 加载布局前,初始化变量,包括Intent带来的和Activity中的变量
     */
    protected void initVariables() {

    }

    /**
     * 返回布局文件
     *
     */
    protected abstract int getLayoutId();

    /**
     *  返回自定义的布局,需要时从写
     * @return
     */
    protected View getLayoutView() {
        return null;
    }

    /**
     *  设置状态栏的颜色
     * @param color
     */
    protected void setStatusBarColor(int color) {
        StatusBarUtil.setColor(this,color);
    }

    /**
     * 联网获取数据
     * @param savedInstanceState
     */
    protected abstract void initViewsAndEvents(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        /**在Activity销毁的时候停止请求*/
        OkHttpUtils.getInstance().cancelTag(tag);
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    @Override
    public void finish() {
        super.finish();
        BaseAppManager.getInstance().removeActivity(this);  // 退出时从任务栈中移除
    }

    /**
     * 打开Activity
     *
     * @param cls
     */
    protected void loadActivity(Class<? extends Activity> cls) {
        Intent intent = new Intent(mContext, cls);
        startActivity(intent);
    }

}
