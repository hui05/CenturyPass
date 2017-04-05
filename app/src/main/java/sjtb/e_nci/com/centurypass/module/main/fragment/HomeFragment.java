package sjtb.e_nci.com.centurypass.module.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import infrastructure.base.fragment.BaseLazyFragment;
import infrastructure.util.DialogUtils;
import infrastructure.util.VerifyUtils;
import infrastructure.widgets.banner.RecommendController;
import infrastructure.widgets.bean.NewsItem;
import sjtb.e_nci.com.centurypass.R;
import sjtb.e_nci.com.centurypass.adapter.HomeFragmentAdapter;
import sjtb.e_nci.com.centurypass.module.purchase.SupplyInfoActivity;

/**
 * Created by hui on 2017/3/13 0013.
 *
 * @author hui
 */

public class HomeFragment extends BaseLazyFragment {

    @BindView(R.id.rl_banner)
    RelativeLayout rlBanner;
    @BindView(R.id.et_travel_city)
    EditText etTravelCity;
    @BindView(R.id.iv_travel_city)
    ImageView ivTravelCity;
    @BindView(R.id.et_plate_no)
    EditText etPlateNo;
    @BindView(R.id.tv_plate_no_whether)
    TextView tvPlateNoWhether;
    @BindView(R.id.iv_plate_no_whether)
    ImageView ivPlateNoWhether;
    @BindView(R.id.tv_history_inquiry)
    TextView tvHistoryInquiry;
    @BindView(R.id.btn_place_order)
    Button btnPlaceOrder;
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;

    String[] str = new String[]{
            "http://g.hiphotos.baidu.com/image/h%3D360/sign=5381d7c63b01213fd03348da64e636f8" +
                    "/fc1f4134970a304efb8e43e5d3c8a786c9175c05.jpg",
            "http://a.hiphotos.baidu.com/image/h%3D360/sign=cb8bf0660db30f242a9aea05f895d192" +
                    "/a8014c086e061d95ba796c3f79f40ad162d9cafe.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/b151f8198618367ac7d2a1e92b738bd4b31ce5af" +
                    ".jpg",
    };
    private RecommendController mController;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mController = new RecommendController(mContext);
        mController.setOnClickListener(new RecommendController.OnItemClickListener() {
            @Override
            public void onItemClick(NewsItem topic) {

            }
        });

        rlBanner.addView(mController.getView());

        loadBanner();
    }

    private ArrayList<Integer> mImageDatas = new ArrayList<>();

    @Override
    protected void lazyLoadData() {
        mImageDatas.clear();
        mImageDatas.add(R.mipmap.icon_logo_xinda);
        mImageDatas.add(R.mipmap.icon_logo_chinese);
        mImageDatas.add(R.mipmap.icon_logo_sunshine);
        mImageDatas.add(R.mipmap.icon_logo_picc);
        mImageDatas.add(R.mipmap.icon_logo_xinda);

        HomeFragmentAdapter adapter = new HomeFragmentAdapter(mContext, R.layout
                .item_home_fragment, mImageDatas);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.et_travel_city, R.id.iv_travel_city, R.id.et_plate_no, R.id
            .tv_plate_no_whether, R.id.iv_plate_no_whether, R.id.tv_history_inquiry, R.id
            .btn_place_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_travel_city:
                DialogUtils.showChooseCityDialog(mActivity, etTravelCity);
                break;
            case R.id.iv_travel_city:
                DialogUtils.showDescriptionInfoDialog(mActivity,"请完善您要填写的信息","");
                break;
            case R.id.et_plate_no:
                break;
            case R.id.tv_plate_no_whether:  // 是否上牌
                chooseWhetherPlateNo();
                break;
            case R.id.iv_plate_no_whether:
                break;
            case R.id.tv_history_inquiry:
                break;
            case R.id.btn_place_order:
                placeOrder();
                break;
        }
    }

    private boolean isPlateNo = false;

    /**
     *  选择是否上牌
     */
    private void chooseWhetherPlateNo() {
        isPlateNo = !isPlateNo;
        if (isPlateNo) {
            tvPlateNoWhether.setText("已上牌");
            tvPlateNoWhether.setTextColor(getResources().getColor(R.color.text_blue));
            ivPlateNoWhether.setImageResource(R.mipmap.img_purchase_checked);
        } else {
            tvPlateNoWhether.setText("未上牌");
            tvPlateNoWhether.setTextColor(getResources().getColor(R.color.text_gray));
            ivPlateNoWhether.setImageResource(R.mipmap.img_purchase_unchecked);
        }
    }

    /**
     * 立即出单
     */
    private void placeOrder() {

        Intent intent = new Intent(mContext, SupplyInfoActivity.class);
        mActivity.startActivity(intent);


        if (!VerifyUtils.checkVehicleRunCity(etTravelCity.getText().toString().trim())) {
            return;
        }
        if (!VerifyUtils.checkVehicleWholeBrand(etPlateNo.getText().toString().trim())) {
            return;
        }

    }

    /**
     * 加载Banner
     */
    private void loadBanner() {
        ArrayList<NewsItem> newsItems = new ArrayList<>();
        NewsItem newsItem = null;
        for (int i = 0; i < str.length; i++) {
            newsItem = new NewsItem();
            newsItem.setTitle("图片" + i);
            ArrayList<String> images = new ArrayList<>();
            images.add(str[i]);
            newsItem.setImages(images);
            newsItems.add(newsItem);
        }
        addTopic(newsItems);
    }


    private void addTopic(ArrayList<NewsItem> newsItems) {
        if (mController != null) {
            mController.setTopicList(newsItems);
        }
    }

}
