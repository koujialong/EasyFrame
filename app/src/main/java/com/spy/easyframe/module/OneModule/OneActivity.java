package com.spy.easyframe.module.OneModule;

import android.os.Bundle;
import android.util.Log;

import com.spy.easyframe.R;
import com.spy.easyframe.model.BannerModel;
import com.spy.easyframe.module.OneModule.impl.IOneActivity;
import com.spy.easyframe.module.impl.IBannerView;
import com.spy.easyframe.presenter.BannerPresenter;
import com.spy.easyframe.ui.BaseActivity;
import com.spy.easyframe.ui.WebViewActivity;
import com.spy.easyframe.ui.widget.TitleBar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OneActivity extends BaseActivity implements IBannerView {

    private static final String TAG = OneActivity.class.getSimpleName();

    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.mTitle)
    TitleBar mTitle;
    private BannerPresenter oneActivityPresenter;
    private String images[];
    private List<BannerModel.ItemBean> itemBeens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    @Override
    protected void initData() {
        mTitle.setTitleInfo("首页");
        oneActivityPresenter = new BannerPresenter(this, subscription);
        oneActivityPresenter.getDate(1, 10, "");
    }

    @Override
    protected void initListener() {
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                if (position < itemBeens.size()) {
                    BannerModel.ItemBean itemBean = itemBeens.get(position);
                    WebViewActivity.startActivity(OneActivity.this, itemBean.getTitle(), itemBean.getUrl());
                }
            }
        });
    }

    @Override
    public String getActivityTag() {
        return TAG;
    }

    @Override
    public void showBanner(List<BannerModel.ItemBean> itemBeens) {
        this.itemBeens = itemBeens;
        images = new String[itemBeens.size()];
        for (int i = 0; i < itemBeens.size(); i++) {
            images[i] = itemBeens.get(i).getImg_url();
        }
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        //banner.setImages(images);
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器的位置
        banner.setIndicatorGravity(BannerConfig.CENTER);
    }
}
