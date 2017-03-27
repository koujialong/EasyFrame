package com.spy.easyframe.module.OneModule;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.spy.easyframe.R;
import com.spy.easyframe.cache.preference.PrefrenceTools;
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
import com.spy.easyframe.ui.widget.DialogBuilder;
import com.spy.easyframe.ui.widget.TitleBar;
import com.spy.easyframe.util.LogUtils;
import com.spy.easyframe.util.RxBus;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

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

    Subscription rxSubscription=RxBus.getInstance().toObservable(BannerModel.class)
            .subscribe(new Action1<BannerModel>() {
                           @Override
                           public void call(BannerModel bannerModel) {
                                LogUtils.e("TAG",bannerModel.getMsg());
                           }
                       },
                    new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {

                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        ButterKnife.bind(this);
        injectComponent();
        initData();
        initListener();
        PrefrenceTools.updateSlogen(this,"啦啦啦啦啦，我存储成功了");
        PrefrenceTools.updateSlogen(this,"这个是一条来自mac的存储信息");

        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("spy://easyframe.spy.com?action=101"));
        startActivity(intent);

        DialogBuilder builder=new DialogBuilder();
        builder.setOnDialogOneListener(new DialogBuilder.DialogOneListener() {
            @Override
            public void onCenterBtnClick() {

            }
        });

        //builder.buildOneBtnDialogProperty(OneActivity.this,"单按钮对话框","确定");
        builder.buildDoubleBtnDialogProperty(OneActivity.this,"双按钮对话框");
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
        String[] images = oneActivityPresenter.getImages();
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
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
