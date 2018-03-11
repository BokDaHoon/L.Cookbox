package com.boostcamp.mytwitter.mytwitter.timeline.adapter.holder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.boostcamp.mytwitter.mytwitter.R;
import com.boostcamp.mytwitter.mytwitter.base.MyTwitterApplication;
import com.boostcamp.mytwitter.mytwitter.listener.OnItemClickListener;
import com.boostcamp.mytwitter.mytwitter.listener.OnProfileItemClickListener;
import com.boostcamp.mytwitter.mytwitter.listener.OnReplyClickListener;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract.RecipeStatusDTO;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Created by DaHoon on 2017-02-15.
 */

public class TimelineCardviewHolder extends RecyclerView.ViewHolder {

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
    @BindView(R.id.recipe_reply)
    ImageButton recipeReply;
    @BindView(R.id.recipe_favorite)
    ToggleButton recipeFavorite;
    @BindView(R.id.recipe_inquery_count)
    TextView recipeInqueryCount;
    @BindView(R.id.recipe_favorite_count)
    TextView recipeFavoirteCount;
    @BindView(R.id.cooking_card)
    CardView cookingCard;
    @BindView(R.id.og_image)
    ImageView ogTagImage;
    @BindView(R.id.og_title)
    TextView ogTagTitle;
    @BindView(R.id.og_url)
    TextView ogTagUrl;

    private RequestManager mGlideRequestManager;
    private String profileImagePath;
    private String metaOgImageUrl;
    private long tweetId;
    private final Animation animScale;
    private boolean firstFavoriteFlag; // 처음 상태표시를 위한 좋아요 버튼 체크 여부는 무시하기 위한 Flag
    private RecipeStatusDTO data;


    public TimelineCardviewHolder(Context context, View itemView, OnItemClickListener listener,
                                  OnProfileItemClickListener profileListener, OnReplyClickListener replyListener) {
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

        data = status;

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

        recipeFavorite.setChecked(true);

        new SetCardViewTask().execute(status.getMovieUrl()); // TwitterCard 세팅

        recipeEnName.setText(status.getRepEname());

        DecimalFormat format = new DecimalFormat("###,###");
        recipePrice.setText(format.format(status.getPrice()) + " 원");
        recipeFavoirteCount.setText(String.valueOf(status.getFavoriteCount()));
        recipeInqueryCount.setText(String.valueOf(status.getInqueryCount()));

    }

    void moveToProfile(long id) {
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

    // TwitterCard UI를 세팅하기 위한 AsnycTask
    class SetCardViewTask extends AsyncTask<String, Void, Document> {

        private String url = "";

        @Override
        protected Document doInBackground(String... params) {

            url = params[0];
            Connection conn = Jsoup.connect(url).timeout(5000);
            Document doc = null;
            try {
                doc = conn.get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {
            super.onPostExecute(doc);

            // 접속 실패 시
            if (doc == null) {
                ogTagImage.setImageResource(R.drawable.twitter_card_default);
                ogTagTitle.setText("Title");
                ogTagUrl.setText(url);
                return;
            }

            // TwitterCard 제목 설정
            Elements metaOgTitle = doc.select("meta[property=og:title]");
            if (metaOgTitle != null && metaOgTitle.attr("content") != "") {
                ogTagTitle.setText(metaOgTitle.attr("content"));
            }
            else {
                ogTagTitle.setText(doc.title());
            }


            // TwitterCard URL 설정
            Elements metaOgUrl = doc.select("meta[property=og:url]");
            if (metaOgUrl != null && metaOgUrl.attr("content") != "") {
                ogTagUrl.setText(metaOgUrl.attr("content"));
            }
            else {
                ogTagUrl.setText(url);
            }


            // TwitterCard Image 설정
            String imageUrl = null;
            Elements metaOgImage = doc.select("meta[property=og:image]");

            if (metaOgImage != null && metaOgImage.attr("content") != "") {
                metaOgImageUrl = metaOgImage.attr("content");
                ogTagImage.post(new Runnable() {
                    @Override
                    public void run() {
                        mGlideRequestManager
                            .load(metaOgImageUrl)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .crossFade()
                            .into(ogTagImage);
                    }
                });

            } else {
                ogTagImage.post(new Runnable() {
                    @Override
                    public void run() {
                        mGlideRequestManager
                                .load(data.getImageUrl())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .crossFade()
                                .into(ogTagImage);
                    }
                });
            }

            // TwitterCard Link 설정
            cookingCard.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    mContext.startActivity(intent);
                }
            });

        }

    }
}
