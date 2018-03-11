package com.boostcamp.mytwitter.mytwitter.timeline.adapter.holder;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.boostcamp.mytwitter.mytwitter.R;
import com.boostcamp.mytwitter.mytwitter.base.MyTwitterApplication;
import com.boostcamp.mytwitter.mytwitter.base.LoginInfo;
import com.boostcamp.mytwitter.mytwitter.listener.OnItemClickListener;
import com.boostcamp.mytwitter.mytwitter.listener.OnProfileItemClickListener;
import com.boostcamp.mytwitter.mytwitter.listener.OnReplyClickListener;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract.RecipeStatusDTO;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Created by DaHoon on 2017-02-15.
 */

public class TimelineImageHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private OnProfileItemClickListener mProfileItemClickListener;
    private OnReplyClickListener mReplyClickListener;

    @BindView(R.id.recipe_name)
    TextView recipeName;
    @BindView(R.id.recipe_enname)
    TextView recipeEnName;
    @BindView(R.id.recipe_price)
    TextView recipePrice;
    @BindView(R.id.recipe_image)
    ImageView tweetImage;
    @BindView(R.id.recipe_favorite)
    ToggleButton recipeFavorite;
    @BindView(R.id.recipe_inquery_count)
    TextView recipeInqueryCount;
    @BindView(R.id.recipe_favorite_count)
    TextView recipeFavoirteCount;

    private RequestManager mGlideRequestManager;
    private String profileImagePath;
    private String imageUrl;
    private long tweetId;
    private boolean firstFavoriteFlag;
    private final Animation animScale;

    public TimelineImageHolder(Context context, View itemView, OnItemClickListener listener, OnProfileItemClickListener profileListener,
                               OnReplyClickListener replyListener) {
        super(itemView);

        mContext = context;
        mOnItemClickListener = listener;
        mProfileItemClickListener = profileListener;
        mReplyClickListener = replyListener;
        mGlideRequestManager = Glide.with(mContext);
        firstFavoriteFlag = true;
        animScale = AnimationUtils.loadAnimation(mContext, R.anim.anim_scale_alpha);
        ButterKnife.bind(this, itemView);
    }

    public void onBind(final RecipeStatusDTO status, final int position) {

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });

        recipeFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 처음 좋아요 버튼 체크 여부는 무시
                if (firstFavoriteFlag) {
                    firstFavoriteFlag = false;
                    return;
                }

                if (isChecked) { // 처음 셋팅 좋아요 버튼 클릭은 제외시키기.

                } else {

                }
                buttonView.startAnimation(animScale);
            }
        });

        recipeName.setText(status.getRecipeName());

        // 트윗 이미지 세팅.
        imageUrl = status.getImageUrl();
        tweetImage.post(new Runnable() {
            @Override
            public void run() {
                mGlideRequestManager
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(tweetImage);
            }
        });

        recipeFavorite.setChecked(true);
        recipeEnName.setText(status.getRepEname());
        DecimalFormat format = new DecimalFormat("###,###");

        recipePrice.setText(format.format(status.getPrice()) + " 원");
        recipeFavoirteCount.setText(String.valueOf(status.getFavoriteCount()));
        recipeInqueryCount.setText(String.valueOf(status.getInqueryCount()));

    }

    // 좋아요를 위한 AsyncTask
    class FavoriteTask extends AsyncTask<Boolean, Void, Status> {

        private Twitter mTwit;
        private int count;

        @Override
        protected twitter4j.Status doInBackground(Boolean... params) {
            boolean favoriteFlag = params[0];

            if (favoriteFlag) {
                favoriteFunc();
            } else {
                destroyFavoriteFunc();
            }

            twitter4j.Status result = null;
            try {
                result = mTwit.showStatus(tweetId);
            } catch (TwitterException e) {
                e.printStackTrace();
            }

            return result;
        }

        private void favoriteFunc() {
            try {
                count = Integer.valueOf(recipeFavoirteCount.getText().toString()) + 1;
                mTwit.createFavorite(tweetId);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }

        private void destroyFavoriteFunc() {
            try {
                count = Integer.valueOf(recipeFavoirteCount.getText().toString()) - 1;
                mTwit.destroyFavorite(tweetId);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(twitter4j.Status status) {
            super.onPostExecute(status);
            MyTwitterApplication.getTwitterApplication().notifyObservers(status);
        }
    }

}
