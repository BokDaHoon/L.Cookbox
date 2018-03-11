package com.boostcamp.mytwitter.mytwitter.myorderlist.presenter;

import com.boostcamp.mytwitter.mytwitter.myorderlist.adapter.contract.OrderAdapterContract;

/**
 * Created by DaHoon on 2017-02-22.
 */

public interface OrderListPresenter {
    interface View {
        void displayDialog(int position);
    }

    interface Prsenter {
        void setView(OrderListPresenter.View view);

        void setOrderListAdapterView(OrderAdapterContract.View adapterView);

        void setOrderListAdapterModel(OrderAdapterContract.Model adapterModel);

        void loadOrderList();
    }
}
