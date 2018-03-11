package com.boostcamp.mytwitter.mytwitter.orderprd.presenter;

import com.boostcamp.mytwitter.mytwitter.orderprd.adapter.contract.OrderDTO;
import com.boostcamp.mytwitter.mytwitter.orderprd.adapter.contract.OrderPrdAdapterContract;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract.TimelineAdapterContract;

/**
 * Created by DaHoon on 2017-07-27.
 */

public interface ChoicePrdPresenter {

    interface View {

    }

    interface Presenter {
        void setView(ChoicePrdPresenter.View view);

        void setTimelineListAdapterModel(OrderPrdAdapterContract.Model adapterModel);

        void setTimelineListAdapterView(OrderPrdAdapterContract.View adapterView);

        void loadProductList(String id);

        void orderProduct(OrderDTO orderDto);
    }
}
