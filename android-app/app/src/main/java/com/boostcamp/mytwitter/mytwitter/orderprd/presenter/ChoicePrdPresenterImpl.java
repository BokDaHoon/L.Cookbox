package com.boostcamp.mytwitter.mytwitter.orderprd.presenter;

import com.boostcamp.mytwitter.mytwitter.orderprd.adapter.contract.OrderDTO;
import com.boostcamp.mytwitter.mytwitter.orderprd.adapter.contract.OrderPrdAdapterContract;
import com.boostcamp.mytwitter.mytwitter.orderprd.adapter.contract.ProductDTO;
import com.boostcamp.mytwitter.mytwitter.orderprd.model.ChoicePrdModel;

import java.util.List;

/**
 * Created by DaHoon on 2017-07-27.
 */

public class ChoicePrdPresenterImpl implements ChoicePrdPresenter.Presenter, ChoicePrdModel.ModelDataChange {

    private OrderPrdAdapterContract.View adapterView;
    private OrderPrdAdapterContract.Model adapterModel;

    private ChoicePrdPresenter.View view;
    private ChoicePrdModel model;

    @Override
    public void setView(ChoicePrdPresenter.View view) {
        this.view = view;
        model = new ChoicePrdModel();
        model.setOnChangeListener(this);
    }

    @Override
    public void setTimelineListAdapterModel(OrderPrdAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setTimelineListAdapterView(OrderPrdAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void loadProductList(String id) {
        model.callProductList(id);
    }

    @Override
    public void orderProduct(OrderDTO orderDto) {
        model.orderProduct(orderDto);
    }

    @Override
    public void update(List<ProductDTO> list) {
        adapterModel.setListData(list);
        adapterView.notifyAdapter();
    }
}
