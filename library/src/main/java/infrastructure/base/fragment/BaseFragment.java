package infrastructure.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import infrastructure.util.LogUtil;

/**
 * Created by hui on 2017/3/13 0013.
 *
 * @author hui
 */

public abstract class BaseFragment extends Fragment {

    protected static String TAG_LOG = null;

    protected Activity mActivity;
    protected Context mContext;

    /**
     * 传入Fragment的URL
     */
    public static String EXTRA_URL = "url";
    private String mUrl;

    private Unbinder mUnBinder;


    @Override
    /**
     *  获取上下文
     */
    public void onAttach(Context context) {
        mContext = context;
        mActivity = (Activity) context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG_LOG = this.getClass().getSimpleName();  // 获取类名作为LOG

        LogUtil.e("Name::"+TAG_LOG);

        // 获取传入Fragment的URL
        if (getArguments() != null) {
            mUrl = getArguments().getString(EXTRA_URL);
        }

        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        if (getLayoutView() != null) {
            return getLayoutView();
        }
        if (getLayoutId() != 0) {
            return inflater.inflate(getLayoutId(), null);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUnBinder = ButterKnife.bind(this, view);

        //
        initView(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnBinder != null) {
            mUnBinder.unbind();
            mUnBinder = null;
        }
    }

    /**
     * 获取传入Fragment的URL
     *
     * @return
     */
    public String getFragmentUrl() {
        return mUrl;
    }

    /**
     * 获取布局的布局ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    protected View getLayoutView() {
        return null;
    }

    /**
     * 初始化布局
     *
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);



    /**
     * 加载数据
     */
    protected abstract void loadData();
}
