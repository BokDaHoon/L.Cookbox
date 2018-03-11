package com.boostcamp.mytwitter.mytwitter.myorderlist.adapter;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boostcamp.mytwitter.mytwitter.R;
import com.boostcamp.mytwitter.mytwitter.base.db.TwitterSchema;
import com.boostcamp.mytwitter.mytwitter.listener.OnItemClickListener;
import com.boostcamp.mytwitter.mytwitter.myorderlist.adapter.contract.OrderAdapterContract;
import com.boostcamp.mytwitter.mytwitter.myorderlist.adapter.holder.OrderListHolder;
import com.boostcamp.mytwitter.mytwitter.myorderlist.model.OrderListDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DaHoon on 2017-02-21.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListHolder>
                          implements OrderAdapterContract.Model, OrderAdapterContract.View{

    private Activity activity;
    private static Cursor mCursor;
    private OnItemClickListener mOnItemClickListener;
    private int mCount;

    private List<OrderListDTO> mList = new ArrayList<>();

    public OrderListAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public OrderListHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order_list, parent, false);
        OrderListHolder viewHolder = new OrderListHolder(parent.getContext(), view, mOnItemClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OrderListHolder holder, int position) {

        if (holder == null) {
            return;
        }

        holder.onBind(mList.get(position), position);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void swapCursor(Cursor data){
        mCursor = data;

        if (data != null) {
            mCount = data.getCount();
            this.notifyDataSetChanged();
        } else {
            mCount = 0;
        }
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public long getDataId(int position) {
        if (mCursor.moveToPosition(position)) {
            int tweetIdIndex = mCursor.getColumnIndex(TwitterSchema.COLUMN_TWEET_ID);
            return mCursor.getLong(tweetIdIndex);
        } else{
            return -1;
        }
    }

    @Override
    public void setListData(List<OrderListDTO> listItem) {
        if (mList != null) {
            mList.clear();
            mList.addAll(listItem);
        } else {
            mList = listItem;
        }

        notifyDataSetChanged();
    }
}
