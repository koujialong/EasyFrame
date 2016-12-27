package com.spy.easyframe.module.TwoModule;

import android.os.Bundle;

import com.spy.easyframe.R;
import com.spy.easyframe.model.LiveListModel;
import com.spy.easyframe.module.BaseImpl.ILiveListView;
import com.spy.easyframe.presenter.LiveListPresenter;
import com.spy.easyframe.ui.BaseActivity;
import com.spy.easyframe.util.LogUtils;

import java.util.List;

public class TwoActivity extends BaseActivity implements ILiveListView{
    private LiveListPresenter liveListPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
    }

    @Override
    protected void initData() {
        liveListPresenter=new LiveListPresenter(this,subscription);
        liveListPresenter.getDate(1,10,"");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void injectComponent() {

    }

    @Override
    public String getActivityTag() {
        return null;
    }

    @Override
    public void showLiveList(List<LiveListModel.ItemBean.TeamListBean> list) {
        for (LiveListModel.ItemBean.TeamListBean teamListBean : list) {
            LogUtils.e("TAG",teamListBean.getLiveTeam().getName());
        }
    }
}
