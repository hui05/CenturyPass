package sjtb.e_nci.com.centurypass.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by hui on 2017/1/10 0010.
 *
 * @author hui
 */

public class BasePagerAdapter<T extends View> extends PagerAdapter {

    protected List<T> mData;

    public BasePagerAdapter(List<T> viewList) {
        super();
        this.mData = viewList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mData.get(position));
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
