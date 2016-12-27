package com.spy.easyframe.module.OneModule;

import android.os.Bundle;

import com.spy.easyframe.R;
import com.spy.easyframe.dagger.Component.DaggerOneActivityComponent;
import com.spy.easyframe.dagger.Component.OneActivityComponent;
import com.spy.easyframe.dagger.Module.OneActivityModule;
import com.spy.easyframe.model.BannerModel;
import com.spy.easyframe.model.LiveListModel;
import com.spy.easyframe.module.BaseImpl.IBannerView;
import com.spy.easyframe.module.BaseImpl.ILiveListView;
import com.spy.easyframe.presenter.BannerPresenter;
import com.spy.easyframe.presenter.LiveListPresenter;
import com.spy.easyframe.system.AppApplication;
import com.spy.easyframe.ui.BaseActivity;
import com.spy.easyframe.ui.WebViewActivity;
import com.spy.easyframe.ui.widget.TitleBar;
import com.spy.easyframe.util.LogUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OneActivity extends BaseActivity implements IBannerView ,ILiveListView{

    private static final String TAG = OneActivity.class.getSimpleName();

    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.mTitle)
    TitleBar mTitle;
    @Inject
    BannerPresenter oneActivityPresenter;
    @Inject
    LiveListPresenter liveListPresenter;
    private String images[];
    private List<BannerModel.ItemBean> itemBeens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        ButterKnife.bind(this);
        injectComponent();
        initData();
        initListener();
    }

    @Override
    protected void initData() {
        mTitle.setTitleInfo("首页");
        oneActivityPresenter.getDate(1, 10, "");
        liveListPresenter.getDate();
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
    protected void injectComponent() {
        OneActivityComponent build = DaggerOneActivityComponent.builder()
                .appComponent(AppApplication.getComponent())
                .oneActivityModule(new OneActivityModule(this,subscription))
                .build();
        build.inject(this);
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

    @Override
    public void showLiveList(List<LiveListModel.ItemBean.TeamListBean> list) {
        for (LiveListModel.ItemBean.TeamListBean teamListBean : list) {
            LogUtils.e("TAG",teamListBean.getLiveTeam().getName());
        }
    }
}
