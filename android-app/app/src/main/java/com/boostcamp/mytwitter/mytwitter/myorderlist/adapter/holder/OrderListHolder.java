package com.boostcamp.mytwitter.mytwitter.myorderlist.adapter.holder;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.boostcamp.mytwitter.mytwitter.R;
import com.boostcamp.mytwitter.mytwitter.base.db.TwitterSchema;
import com.boostcamp.mytwitter.mytwitter.listener.OnItemClickListener;
import com.boostcamp.mytwitter.mytwitter.myorderlist.model.OrderListDTO;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DaHoon on 2017-02-21.
 */

public class OrderListHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.order_recipe_image)
    ImageView orderRecipeImage;
    @BindView(R.id.order_recipe_name)
    TextView orderRecipeName;
    @BindView(R.id.order_count)
    TextView orderCount;
    @BindView(R.id.order_price)
    TextView orderPrice;
    @BindView(R.id.order_date)
    TextView orderDate;

    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private RequestManager mGlideRequestManager;

    public OrderListHolder(Context context, View itemView, OnItemClickListener listener) {
        super(itemView);
        mContext = context;
        mOnItemClickListener = listener;
        mGlideRequestManager = Glide.with(mContext);
        ButterKnife.bind(this, itemView);
    }

    public void onBind(final OrderListDTO data, final int position) {

        Log.d("TEST", data.toString());

        final String imageUrl = data.getImgUrl();
        orderRecipeImage.post(new Runnable() {
            @Override
            public void run() {
                mGlideRequestManager
                        .load(imageUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(orderRecipeImage);
            }
        });

        orderRecipeName.setText(data.getRepNm());
        orderCount.setText(data.getCnt() + "개");

        DecimalFormat format = new DecimalFormat("###,###");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd a hh:mm");
        orderPrice.setText(format.format(data.getPrice()));
        orderDate.setText(dateFormat.format(data.getOrdDate()));
    }

}
