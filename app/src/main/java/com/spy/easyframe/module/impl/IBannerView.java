package com.spy.easyframe.module.impl;

import com.spy.easyframe.model.BannerModel;

import java.util.List;

/**
 * Created by Administrator on 2016/12/24.
 */

public interface IBannerView {
    void showBanner(List<BannerModel.ItemBean> itemBeens);
}
