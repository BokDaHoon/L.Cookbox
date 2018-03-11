package com.boostcamp.mytwitter.mytwitter.orderprd.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boostcamp.mytwitter.mytwitter.R;
import com.boostcamp.mytwitter.mytwitter.orderprd.adapter.contract.OrderPrdAdapterContract;
import com.boostcamp.mytwitter.mytwitter.orderprd.adapter.contract.ProductDTO;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract.RecipeStatusDTO;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by DaHoon on 2017-07-31.
 */

public class OrderPrdAdapter extends RecyclerView.Adapter<OrderPrdViewHolder> implements
                                                OrderPrdAdapterContract.Model, OrderPrdAdapterContract.View {

    private Activity mActivity;
    private List<ProductDTO> mList = new ArrayList<ProductDTO>();

    public OrderPrdAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public OrderPrdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_choice_prd, parent, false);
        return new OrderPrdViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(OrderPrdViewHolder holder, int position) {
        holder.onBind(mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void setListData(List<ProductDTO> listItem) {
        if (mList != null) {
            mList.clear();
            mList.addAll(listItem);
        } else {
            mList = listItem;
        }
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }
}
