package sjtb.e_nci.com.centurypass.module.main.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import infrastructure.base.fragment.BaseLazyFragment;
import sjtb.e_nci.com.centurypass.R;

/**
 * Created by hui on 2017/3/13 0013.
 *
 * @author hui
 */

public class OrderFragment extends BaseLazyFragment {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.view_label_bg)
    View viewLabelBg;
    @BindView(R.id.tv_label_complete_no)
    TextView tvLabelCompleteNo;
    @BindView(R.id.tv_label_complete)
    TextView tvLabelComplete;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_query;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void lazyLoadData() {

    }


    @OnClick({R.id.tv_label_complete_no, R.id.tv_label_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_label_complete_no:
                break;
            case R.id.tv_label_complete:
                break;
        }
    }
}
