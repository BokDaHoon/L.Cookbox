package com.boostcamp.mytwitter.mytwitter.myorderlist.adapter.contract;

import com.boostcamp.mytwitter.mytwitter.listener.OnItemClickListener;
import com.boostcamp.mytwitter.mytwitter.myorderlist.model.OrderListDTO;

import java.util.List;

/**
 * Created by DaHoon on 2017-02-22.
 */

public interface OrderAdapterContract {

    interface View {
        void notifyAdapter();

        void setOnItemClickListener(OnItemClickListener listener);
    }

    interface Model {
        void setListData(List<OrderListDTO> listItem);
    }
}
