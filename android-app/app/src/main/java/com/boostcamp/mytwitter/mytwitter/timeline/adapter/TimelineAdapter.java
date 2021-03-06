package com.boostcamp.mytwitter.mytwitter.timeline.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boostcamp.mytwitter.mytwitter.R;
import com.boostcamp.mytwitter.mytwitter.listener.OnItemClickListener;
import com.boostcamp.mytwitter.mytwitter.listener.OnProfileItemClickListener;
import com.boostcamp.mytwitter.mytwitter.listener.OnReplyClickListener;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract.RecipeStatusDTO;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract.TimelineAdapterContract;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.holder.TimelineCardviewHolder;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.holder.TimelineImageHolder;
import com.boostcamp.mytwitter.mytwitter.util.Define;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;

/**
 * Created by DaHoon on 2017-02-11.
 */

public class TimelineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
                             implements TimelineAdapterContract.View, TimelineAdapterContract.Model {

    private OnItemClickListener mOnItemClickListener;
    private OnProfileItemClickListener mProfileItemClickListener;
    private OnReplyClickListener mOnReplyClickListener;
    private Activity mActivity;

    private List<RecipeStatusDTO> mList = new ArrayList<>();

    public TimelineAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {

            case Define.TIMELINE_IMAGE_TYPE :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_timeline_image, parent, false);
                viewHolder = new TimelineImageHolder(parent.getContext(), view, mOnItemClickListener, mProfileItemClickListener, mOnReplyClickListener);
                break;
            case Define.TIMELINE_CARDVIEW_TYPE :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_timeline_cardview, parent, false);
                viewHolder = new TimelineCardviewHolder(parent.getContext(), view, mOnItemClickListener, mProfileItemClickListener, mOnReplyClickListener);
                break;

        }

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mList.size() <= 0) {
            return;
        }

        if (holder instanceof TimelineImageHolder) {
            ((TimelineImageHolder) holder).onBind(mList.get(position), position);
        } else if (holder instanceof TimelineCardviewHolder) {
            ((TimelineCardviewHolder) holder).onBind(mList.get(position), position);
        }


    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }

        return mList.size();
    }


    public void updateItem(Status data) {
        /*if (mList != null) {

            for (int pos = 0; pos < mList.size(); pos++) {
                if (mList.get(pos).getId() == data.getId()) {
                    mList.set(pos, data);
                }
            }

        }*/
    }

    @Override
    public int getItemViewType(int position) {
        /*Status status = mList.get(position);
        URLEntity[] urlResult = status.getURLEntities();

        // Tweet에 이미지가 있는 경우
        if (status.getMediaEntities().length != 0) {
            return Define.TIMELINE_IMAGE_TYPE;

        // Tweet에 CardView가 있는 경우
        } else if (urlResult.length != 0 && urlResult[0].getExpandedURL() != null) {
            return Define.TIMELINE_CARDVIEW_TYPE;

        // Tweet에 이미지와 CardView가 없는 경우
        } else {
            return Define.TIMELINE_COMMON_TYPE;
        }*/

        if (position % 2 == 0) {
            return Define.TIMELINE_IMAGE_TYPE;
        } else {
            return Define.TIMELINE_CARDVIEW_TYPE;
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

    @Override
    public void setOnProfileItemClickListener(OnProfileItemClickListener profileListener) {
        mProfileItemClickListener = profileListener;
    }

    @Override
    public void setOnReplyClickListener(OnReplyClickListener replyListener) {
        mOnReplyClickListener = replyListener;
    }


    @Override
    public void setListData(List<RecipeStatusDTO> listItem) {
        if (mList != null) {
            mList.clear();
            mList.addAll(listItem);
        } else {
            mList = listItem;
        }

        notifyDataSetChanged();
    }

    public RecipeStatusDTO getListData(int position) {
        return mList.get(position);
    }
}
