package com.spy.easyframe.module.ThreeModule;

import android.os.Bundle;

import com.spy.easyframe.R;
import com.spy.easyframe.cache.preference.PrefrenceTools;
import com.spy.easyframe.model.BannerModel;
import com.spy.easyframe.model.LiveListModel;
import com.spy.easyframe.module.BaseImpl.IBannerView;
import com.spy.easyframe.module.BaseImpl.ILiveListView;
import com.spy.easyframe.presenter.BannerPresenter;
import com.spy.easyframe.presenter.LiveListPresenter;
import com.spy.easyframe.ui.BaseActivity;
import com.spy.easyframe.ui.WebViewActivity;
import com.spy.easyframe.ui.widget.TitleBar;
import com.spy.easyframe.util.LogUtils;
import com.spy.easyframe.util.RxBus;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class
ThreeActivity extends BaseActivity implements IBannerView, ILiveListView {
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.mTitle)
    TitleBar mTitle;
    private BannerPresenter bannerPresenter;
    private LiveListPresenter liveListPresenter;
    private List<BannerModel.ItemBean> itemBeens;
    private String images[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        ButterKnife.bind(this);
        initData();
        initListener();
        new Thread(new Runnable() {
            @Override
            public void run() {
                BannerModel bannerModel = new BannerModel();
                bannerModel.setMsg("啦啦，发送消息了");
                RxBus.getInstance().post(bannerModel);
            }
        }).start();
        String slogen = PrefrenceTools.getSlogen(this);
        LogUtils.e("TAG",slogen);
    }

    @Override
    protected void initData() {
        mTitle.setTitleInfo("发现");
        bannerPresenter = new BannerPresenter(this, subscription);
        liveListPresenter = new LiveListPresenter(this, subscription);
        bannerPresenter.getDate(1, 10, "");
        liveListPresenter.getDate();
    }

    @Override
    protected void initListener() {
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                BannerModel.ItemBean itemBean = itemBeens.get(position);
                WebViewActivity.startActivity(ThreeActivity.this, itemBean.getTitle(), itemBean.getUrl());
            }
        });
    }

    @Override
    protected void injectComponent() {

    }

    @Override
    public String getActivityTag() {
        return null;
    }

    @Override
    public void showBanner(List<BannerModel.ItemBean> itemBeens) {
        this.itemBeens = itemBeens;
        images = bannerPresenter.getImages();
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
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
        LogUtils.e("TAG", list.get(0).getLiveTeam().getName());

    }
}
