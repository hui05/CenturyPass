package sjtb.e_nci.com.centurypass.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

import infrastructure.base.fragment.BaseFragment;
import infrastructure.util.LogUtil;
import sjtb.e_nci.com.centurypass.module.main.fragment.FragmentFactory;


/**
 * Created by hui on 2016/8/9 0009.
 *
 * @author hui
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainPagerAdapter(FragmentManager supportFragmentManager, List<BaseFragment> fragments) {
        super(supportFragmentManager);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        LogUtil.e("position::"+position);
        return FragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
//        int size = FragmentFactory.map.size();
//        LogUtil.e("size::"+size);
        return 4;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        LogUtil.e("destroyItemposition::"+position);
    }
}
