package sjtb.e_nci.com.centurypass.base;

import com.zhy.http.okhttp.OkHttpUtils;

import infrastructure.util.LogUtil;

/**
 * Created by hui on 2017/3/27 0027.
 *
 * @author hui
 */

public abstract class BaseAppModel implements BaseModel {

    protected String TAG = getClass().getSimpleName();

    @Override
    public void cancel() {
        LogUtil.d("TAG:" + TAG);
        OkHttpUtils.getInstance().cancelTag(TAG);
    }
}
