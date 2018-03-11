package com.boostcamp.mytwitter.mytwitter.myorderlist.presenter;

import com.boostcamp.mytwitter.mytwitter.listener.OnItemClickListener;
import com.boostcamp.mytwitter.mytwitter.myorderlist.adapter.contract.OrderAdapterContract;
import com.boostcamp.mytwitter.mytwitter.myorderlist.model.OrderListDTO;
import com.boostcamp.mytwitter.mytwitter.myorderlist.model.OrderListModel;

import java.util.List;

/**
 * Created by DaHoon on 2017-02-22.
 */

public class OrderListPresenterImpl implements OrderListPresenter.Prsenter, OnItemClickListener, OrderListModel.ModelDataChange {

    private OrderListModel model;

    private OrderListPresenter.View view;
    private OrderAdapterContract.View adapterView;
    private OrderAdapterContract.Model adapterModel;

    @Override
    public void setView(OrderListPresenter.View view) {
        this.view = view;
        model = new OrderListModel();
        model.setOnChangeListener(this);
    }

    @Override
    public void setOrderListAdapterView(OrderAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnItemClickListener(this);
    }

    @Override
    public void setOrderListAdapterModel(OrderAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void loadOrderList() {
        model.loadOrderList();
    }

    @Override
    public void onItemClick(int position) {
        view.displayDialog(position);
    }

    @Override
    public void update(List<OrderListDTO> list) {
        adapterModel.setListData(list);
        adapterView.notifyAdapter();
    }
}
