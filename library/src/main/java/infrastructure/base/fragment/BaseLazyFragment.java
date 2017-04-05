package infrastructure.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import infrastructure.util.LogUtil;

/**
 * Created by hui on 2017/3/13 0013.
 *  懒加载的Fragment的基类
 * @author hui
 */

public abstract class BaseLazyFragment extends BaseFragment {

    private boolean isFirstLoad = true;
    private boolean isPrepared;
    private boolean isVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        isFirstLoad = true;
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        isPrepared = true;
        super.onViewCreated(view, savedInstanceState);
        lazyLoad(); //load data before getLayoutId and ButterKnife bind
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     *  界面可见的时候的操作
     */
    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {

    }

    /**
     *  懒加载
     */
    protected void lazyLoad() {
        if (!isFirstLoad || !isPrepared || !isVisible) {
            return;
        }
        isFirstLoad = false;
        lazyLoadData();
        LogUtil.d(TAG_LOG+"我执行了lazyLoadData");
    }

    protected abstract void lazyLoadData();

    @Override
    protected void loadData() {
        // 使用lazyLoadData代替
    }
}
