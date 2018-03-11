package com.boostcamp.mytwitter.mytwitter.orderprd.adapter.contract;

import com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract.RecipeStatusDTO;

import java.util.List;


/**
 * Created by DaHoon on 2017-07-31.
 */

public interface OrderPrdAdapterContract {
    interface View {
        void notifyAdapter();
    }

    interface Model {
        void setListData(List<ProductDTO> listItem);
    }
}
