package com.boostcamp.mytwitter.mytwitter.timeline.presenter;

import android.util.Log;

import com.boostcamp.mytwitter.mytwitter.listener.OnItemClickListener;
import com.boostcamp.mytwitter.mytwitter.listener.OnProfileItemClickListener;
import com.boostcamp.mytwitter.mytwitter.listener.OnReplyClickListener;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract.RecipeStatusDTO;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract.TimelineAdapterContract;
import com.boostcamp.mytwitter.mytwitter.timeline.model.TimelineModel;

import java.util.List;

import twitter4j.User;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

/**
 * Created by DaHoon on 2017-02-10.
 */

public class TimelinePresenterImpl implements TimelinePresenter.Presenter, TimelineModel.ModelDataChange, OnItemClickListener,
                                              OnProfileItemClickListener, OnReplyClickListener {

    private TimelinePresenter.View view;
    private TimelineModel model;

    private TimelineAdapterContract.Model adapterModel;
    private TimelineAdapterContract.View adapterView;

    // Pager Variables
    private boolean isLastPage = false;
    private boolean isLoading = false;
    private int currentPage = 1;


    @Override
    public void setView(TimelinePresenter.View view) {
        this.view = view;
        model = new TimelineModel();
        model.setOnChangeListener(this);
    }

    @Override
    public void initSidebarNavigation() {
    }

    @Override
    public void loadTimelineList() {
        model.callTimelineList();
    }

    @Override
    public void setTimelineListAdapterModel(TimelineAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setTimelineListAdapterView(TimelineAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnItemClickListener(this);
        this.adapterView.setOnProfileItemClickListener(this);
        this.adapterView.setOnReplyClickListener(this);
    }

    @Override
    public void checkListViewPositionBottom(int visibleItemCount, int totalItemCount, int firstVisibleItemPosition) {
        if (!isLoading && !isLastPage) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= PAGE_SIZE) {
                currentPage = currentPage + 1;
                loadTimelineList();
            }
        }
    }

    @Override
    public void updateRecipeData() {
        model.callTimelineList();
    }

    @Override
    public void updateRecipeData(String ename) {
        model.callTimelineList(ename);
    }

    @Override
    public void update(List<RecipeStatusDTO> list) {
        adapterModel.setListData(list);
        adapterView.notifyAdapter();
    }

    @Override
    public void onItemClick(int position) {
        view.moveToDetail(position);
    }

    @Override
    public void onProfileItemClick(long id) {
        view.moveToProfile(id);
    }

    @Override
    public void onReplyItemClick(long statusId) {
        view.moveToReply(statusId);
    }
}
