package com.boostcamp.mytwitter.mytwitter.timeline.presenter;

import com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract.TimelineAdapterContract;

import twitter4j.User;

/**
 * Created by DaHoon on 2017-02-10.
 */

public interface TimelinePresenter {

    interface View {
        void initSidebarNavigation(User user);

        void moveToDetail(int position);

        void moveToProfile(long id);

        void moveToReply(long statusId);
    }

    interface Presenter {
        void setView(TimelinePresenter.View view);

        void initSidebarNavigation();

        void loadTimelineList();

        void setTimelineListAdapterModel(TimelineAdapterContract.Model adapterModel);

        void setTimelineListAdapterView(TimelineAdapterContract.View adapterView);

        void checkListViewPositionBottom(int visibleItemCount, int totalItemCount, int firstVisibleItemPosition);

        void updateRecipeData();

        void updateRecipeData(String ename);
    }

}
