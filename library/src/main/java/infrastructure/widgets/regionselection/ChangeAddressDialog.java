package infrastructure.widgets.regionselection;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sjtb.e_nci.com.mylibrary.R;


public class ChangeAddressDialog extends Dialog implements
        View.OnClickListener, OnWheelChangedListener {

    // 省市区控件
    private WheelView wvProvince;
    private WheelView wvCitys;
    private WheelView wvArea;

    private TextView btnSure;// 确定按钮
    private TextView btnCancel;// 取消按钮

    private Context context;// 上下文对象

    private JSONObject mJsonObj;// 存放地址信息的json对象

    private String[] mProvinceDatas;
    private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();

    private ArrayList<String> arrProvinces = new ArrayList<String>();
    private ArrayList<String> arrCitys = new ArrayList<String>();
    private HashMap<String,String> province2Code = new HashMap<String,String>();
    private HashMap<String,String> city2Code = new HashMap<String,String>();
    private HashMap<String,String> area2Code = new HashMap<String,String>();
    private ArrayList<String> arrAreas = new ArrayList<String>();

    private AddressTextAdapter provinceAdapter;
    private AddressTextAdapter cityAdapter;
    private AddressTextAdapter areaAdapter;

    // 选中的省市区信息
    private String strProvince;
    private String strCity;
    private String strCityCode;
    private String strArea;


    // 回调方法
    private OnAddressCListener onAddressCListener;

    // 显示文字的字体大小
    private int maxsize = 24;
    private int minsize = 14;


    /** 省份 一级选择*/
    public static final int STYLE_CHOOSE_SINGLE = 1;

    /** 省市 二级选择*/
    public static final int STYLE_CHOOSE_SENCONDARD = 2;

    /** 省市区 三级选择*/
    public static final int STYLE_CHOOSE_MULTI = 3;

    private int style = STYLE_CHOOSE_MULTI; // 默认为三级选择

    public ChangeAddressDialog(Context context) {
        super(context, R.style.ShareDialog);
        this.context = context;
    }
    /**
     *
     * @param context
     * @param style  显示的样式
     *                      1: 表示省份    一级选择
     *                      2: 表示省市    二级选择
     *                      3: 表示省市区  三级选择
     */
    public ChangeAddressDialog(Context context,int style) {
        super(context, R.style.ShareDialog);
        this.context = context;
        this.style = style;
    }

    /**
     *  设置地区选项隐藏
     */
    public void setCountryGone() {
        wvArea.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_address);

        wvProvince = (WheelView) findViewById(R.id.wv_address_province);
        wvCitys = (WheelView) findViewById(R.id.wv_address_city);
        wvArea = (WheelView) findViewById(R.id.wv_address_area);
        btnSure = (TextView) findViewById(R.id.btn_myinfo_sure);
        btnCancel = (TextView) findViewById(R.id.btn_myinfo_cancel);

        switch (style) {
            case STYLE_CHOOSE_SINGLE:
                wvCitys.setVisibility(View.GONE);
            case STYLE_CHOOSE_SENCONDARD:
                wvArea.setVisibility(View.GONE);
                break;
        }

        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        wvProvince.addChangingListener(this);
        wvCitys.addChangingListener(this);
        wvArea.addChangingListener(this);

        initJsonData();
        initDatas();
        initProvinces();
        provinceAdapter = new AddressTextAdapter(context, arrProvinces,
                getProvinceItem(strProvince), maxsize, minsize);
        wvProvince.setVisibleItems(5);
        wvProvince.setViewAdapter(provinceAdapter);
        // wvProvince.setCyclic(true);//设置内容循环
        wvProvince.setCurrentItem(getProvinceItem(strProvince));

        initCitys(mCitisDatasMap.get(strProvince));
        cityAdapter = new AddressTextAdapter(context, arrCitys,
                getCityItem(strCity), maxsize, minsize);
        wvCitys.setVisibleItems(5);
        wvCitys.setViewAdapter(cityAdapter);
        wvCitys.setCurrentItem(getCityItem(strCity));

        initAreas(mAreaDatasMap.get(strCity));
        areaAdapter = new AddressTextAdapter(context, arrAreas,
                getCityItem(strArea), maxsize, minsize);
        wvArea.setVisibleItems(5);
        wvArea.setViewAdapter(areaAdapter);
        wvArea.setCurrentItem(getAreaItem(strArea));
        wvProvince.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) provinceAdapter.getItemText(wheel
                        .getCurrentItem());
                setTextviewSize(currentText, provinceAdapter);
            }
        });
        wvCitys.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) cityAdapter.getItemText(wheel
                        .getCurrentItem());
                setTextviewSize(currentText, cityAdapter);
            }
        });
        wvArea.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) areaAdapter.getItemText(wheel
                        .getCurrentItem());
                setTextviewSize(currentText, areaAdapter);
            }
        });
    }

    /**
     * 初始化省会
     */
    public void initProvinces() {
        int length = mProvinceDatas.length;
        for (int i = 0; i < length; i++) {
            arrProvinces.add(mProvinceDatas[i]);
        }
    }

    /**
     * 根据省会，生成该省会的所有城市
     *
     * @param citys
     */
    public void initCitys(String[] citys) {
        if (citys != null) {
            arrCitys.clear();
            int length = citys.length;
            for (int i = 0; i < length; i++) {
                arrCitys.add(citys[i]);
            }
        } else {
            String[] city = mCitisDatasMap.get("北京");
            arrCitys.clear();
            int length = city.length;
            for (int i = 0; i < length; i++) {
                arrCitys.add(city[i]);
            }
        }
        if (arrCitys != null && arrCitys.size() > 0
                && !arrCitys.contains(strCity)) {
            strCity = arrCitys.get(0);

        }
    }

    /**
     * 根据城市，生成该城市的所有地区
     *
     */
    public void initAreas(String[] areas) {
        if (areas != null) {
            arrAreas.clear();
            int length = areas.length;
            for (int i = 0; i < length; i++) {
                arrAreas.add(areas[i]);
            }
        } else {
            String[] city = mCitisDatasMap.get("北京");
            arrCitys.clear();
            int length = city.length;
            for (int i = 0; i < length; i++) {
                arrCitys.add(city[i]);
            }
        }
        if (arrAreas != null && arrAreas.size() > 0
                && !arrAreas.contains(strArea)) {
            strArea = arrAreas.get(0);
        }
    }

    /**
     * 初始化地点
     *
     * @param province
     * @param city
     */
    public void setAddress(String province, String city, String area) {
        if (province != null && province.length() > 0) {
            this.strProvince = province;
        }
        if (city != null && city.length() > 0) {
            this.strCity = city;
        }
        if (area != null && area.length() > 0) {
            this.strArea = area;
        }
    }

    /**
     * 返回省会索引
     */
    public int getProvinceItem(String province) {
        int size = arrProvinces.size();
        int provinceIndex = 0;
        boolean noprovince = true;
        if (province != null) {
            for (int i = 0; i < size; i++) {
                if (province.equals(arrProvinces.get(i))) {
                    noprovince = false;
                    return provinceIndex;
                } else {
                    provinceIndex++;
                }
            }
        }
        if (noprovince) {
            strProvince = "北京";
            return 0;
        }
        return provinceIndex;
    }

    /**
     * 得到城市索引
     */
    public int getCityItem(String city) {
        int size = arrCitys.size();
        int cityIndex = 0;
        boolean nocity = true;
        if (city != null) {
            for (int i = 0; i < size; i++) {
                if (city.equals(arrCitys.get(i))) {
                    nocity = false;
                    return cityIndex;
                } else {
                    cityIndex++;
                }
            }
        }
        if (nocity) {
            strCity = "北京市";
            return 0;
        }
        return cityIndex;
    }

    // 得到地区
    public int getAreaItem(String area) {
        int size = arrAreas.size();
        int cityIndex = 0;
        boolean nocity1 = true;
        if (area != null) {
            for (int i = 0; i < size; i++) {
                if (area.equals(arrAreas.get(i))) {
                    nocity1 = false;
                    return cityIndex;
                } else {
                    cityIndex++;
                }
            }
        }
        if (nocity1) {
            strArea = "东城区";
            return 0;
        }
        return cityIndex;
    }

    // 根据省来更新wheel的状态
    private void updateCities() {
        String currentText = (String) provinceAdapter.getItemText(wvProvince
                .getCurrentItem());
        strProvince = currentText;
        setTextviewSize(currentText, provinceAdapter);
        String[] citys = mCitisDatasMap.get(currentText);
        if (citys == null) {
            citys = new String[]{""};
        }
        initCitys(citys);
        cityAdapter = new AddressTextAdapter(context, arrCitys, 0, maxsize,
                minsize);
        wvCitys.setViewAdapter(cityAdapter);
        wvCitys.setCurrentItem(0);
        updateAreas();
    }

    // 根据城市来更新wheel的状态
    private void updateAreas() {
        String currentText = (String) cityAdapter.getItemText(wvCitys
                .getCurrentItem());
        strCity = currentText;
        setTextviewSize(currentText, cityAdapter);
        String[] areas = mAreaDatasMap.get(currentText);
        if (areas == null) {
            areas = new String[]{""};
        }
        strArea = areas[0];
        initAreas(areas);
        areaAdapter = new AddressTextAdapter(context, arrAreas, 0, maxsize,
                minsize);
        wvArea.setViewAdapter(areaAdapter);
        wvArea.setCurrentItem(0);
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == wvProvince) {
            // 切换省份的操作
            updateCities();
        } else if (wheel == wvCitys) {
            updateAreas();
        } else if (wheel == wvArea) {
            String currentText = (String) areaAdapter.getItemText(wheel
                    .getCurrentItem());
            strArea = currentText;
            strArea = mAreaDatasMap.get(strCity)[newValue];
            setTextviewSize(currentText, areaAdapter);
        }
    }

    // //////////////////////////////////////////////////华丽的分界线
    private void initJsonData1() {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = context.getAssets().open("city.json");
            int len = -1;
            byte[] buf = new byte[is.available()];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "utf-8"));
            }
            is.close();
            mJsonObj = new JSONObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initDatas1() {
        try {
            JSONArray jsonArray = mJsonObj.getJSONArray("citylist");
            mProvinceDatas = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonP = jsonArray.getJSONObject(i);// 每个省的json对象
                String province = jsonP.getString("p");// 省名字

                mProvinceDatas[i] = province;

                JSONArray jsonCs = null;
                try {
                    /**
                     * Throws JSONException if the mapping doesn't exist or is
                     * not a JSONArray.
                     */
                    jsonCs = jsonP.getJSONArray("c");
                } catch (Exception e1) {
                    continue;
                }
                String[] mCitiesDatas = new String[jsonCs.length()];
                for (int j = 0; j < jsonCs.length(); j++) {
                    JSONObject jsonCity = jsonCs.getJSONObject(j);
                    String city = jsonCity.getString("n");// 市名字
                    mCitiesDatas[j] = city;
                    JSONArray jsonAreas = null;
                    try {
                        /**
                         * Throws JSONException if the mapping doesn't exist or
                         * is not a JSONArray.
                         */
                        jsonAreas = jsonCity.getJSONArray("a");
                    } catch (Exception e) {
                        continue;
                    }

                    String[] mAreasDatas = new String[jsonAreas.length()];// 当前市的所有区
                    for (int k = 0; k < jsonAreas.length(); k++) {
                        String area = jsonAreas.getJSONObject(k).getString("s");// 区域的名称
                        mAreasDatas[k] = area;
                    }
                    mAreaDatasMap.put(city, mAreasDatas);
                }

                mCitisDatasMap.put(province, mCitiesDatas);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mJsonObj = null;
    }

    ////////////////////////////////////////////////////华丽的分界线
    private void initJsonData() {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = context.getAssets().open("city.json");
            int len = -1;
            byte[] buf = new byte[is.available()];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "utf-8"));
            }
            is.close();
            mJsonObj = new JSONObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String[] mProvinceCodes;
    private String[] mCitiesCode;
    private String[] mAreasCodes;

    private void initDatas() {
        try {
            JSONArray jsonArray = mJsonObj.getJSONArray("86");

            mProvinceDatas = new String[jsonArray.length()];
            mProvinceCodes = new String[jsonArray.length()];
            province2Code.clear();
            city2Code.clear();
            area2Code.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonP = jsonArray.getJSONObject(i);// 每个省的json对象
                String province = jsonP.getString("cityName");// 省名字
                String provinceCode = jsonP.getString("cityCode");// 省名字
                province2Code.put(province, provinceCode);
                mProvinceDatas[i] = province;
                mProvinceCodes[i] = provinceCode;

                JSONArray jsonCs = null;
                try {
                    jsonCs = mJsonObj.getJSONArray(provinceCode);
                } catch (Exception e1) {
                    continue;
                }
                String[] mCitiesDatas = new String[jsonCs.length()];
                String[] mCitiesCodes = new String[jsonCs.length()];

                for (int j = 0; j < jsonCs.length(); j++) {
                    JSONObject jsonCity = jsonCs.getJSONObject(j);
                    String city = jsonCity.getString("cityName");// 市名字
                    String cityCode = jsonCity.getString("cityCode");// 市名字
                    city2Code.put(city, cityCode);
                    mCitiesDatas[j] = city;
                    mCitiesCodes[j] = cityCode;
                    JSONArray jsonAreas = null;
                    try {
                        jsonAreas = mJsonObj.getJSONArray(cityCode);
                    } catch (Exception e) {
                        continue;
                    }
                    String[] mAreasDatas = new String[jsonAreas.length()];// 当前市的所有区
                    String[] mAreasCodes = new String[jsonAreas.length()];// 当前市的所有区
                    for (int k = 0; k < jsonAreas.length(); k++) {
                        String area = jsonAreas.getJSONObject(k).getString("cityName");// 区域的名称
                        String areaCode = jsonAreas.getJSONObject(k).getString("cityCode");// 区域的名称
                        area2Code.put(area, areaCode);
                        mAreasDatas[k] = area;
                        mAreasCodes[k] = areaCode;
                    }
                    mAreaDatasMap.put(city, mAreasDatas);
                }
                mCitisDatasMap.put(province, mCitiesDatas);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mJsonObj = null;
    }

    private class AddressTextAdapter extends AbstractWheelTextAdapter {
        ArrayList<String> list;

        protected AddressTextAdapter(Context context, ArrayList<String> list,
                                     int currentItem, int maxsize, int minsize) {
            super(context, R.layout.dialog_change_address_item, NO_RESOURCE, currentItem,
                    maxsize, minsize);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.size() == 0 ? "" : list.get(index) + "";
        }
    }

    public interface OnAddressCListener {
        public void onClick(String province, String city, String area);
        public void onClick(String provinceCode, String cityCode, String areaCode, boolean isCode);
    }

    public void setTextviewSize(String curriteItemText,
                                AddressTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(24);
            } else {
                textvew.setTextSize(14);
            }
        }
    }

    public void setAddresskListener(OnAddressCListener onAddressCListener) {
        this.onAddressCListener = onAddressCListener;
    }

    @Override
    public void onClick(View v) {
        if (v == btnSure) {
            if (onAddressCListener != null) {
                onAddressCListener.onClick(strProvince, strCity, strArea);
                onAddressCListener.onClick(province2Code.get(strProvince),city2Code.get(strCity),area2Code.get(strArea),true);
            }
        }
        dismiss();
    }
}