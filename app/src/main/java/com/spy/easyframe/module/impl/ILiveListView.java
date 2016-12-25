package com.spy.easyframe.module.impl;

import com.spy.easyframe.model.LiveListModel;

import java.util.List;

/**
 * Created by Administrator on 2016/12/24.
 */

public interface ILiveListView {
    void showLiveList(List<LiveListModel.ItemBean.TeamListBean> list);
}
