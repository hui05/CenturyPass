package sjtb.e_nci.com.centurypass.adapter;

import android.content.Context;

import java.util.List;

import infrastructure.widgets.recycleview.adapter.CommonAdapter;
import infrastructure.widgets.recycleview.base.ViewHolder;
import sjtb.e_nci.com.centurypass.R;

/**
 * Created by hui on 2017/3/21 0021.
 *
 * @author hui
 */

public class HomeFragmentAdapter extends CommonAdapter<Integer> {

    public HomeFragmentAdapter(Context context, int layoutId, List<Integer> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Integer integer, int position) {
        holder.setImageResource(R.id.iv_image, integer);
    }
}
