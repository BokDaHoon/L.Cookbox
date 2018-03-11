package com.boostcamp.mytwitter.mytwitter.orderprd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boostcamp.mytwitter.mytwitter.R;
import com.boostcamp.mytwitter.mytwitter.orderprd.adapter.contract.ProductDTO;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DaHoon on 2017-07-31.
 */

class OrderPrdViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.product_image)
    ImageView prdImage;
    @BindView(R.id.product_origin)
    TextView prdOrigin;
    @BindView(R.id.product_name)
    TextView prdName;
    private RequestManager mGlideRequestManager;
    private Context mContext;

    public OrderPrdViewHolder(View itemView, Context context) {
        super(itemView);
        mContext = context;
        mGlideRequestManager = Glide.with(context);
        ButterKnife.bind(this, itemView);
    }

    public void onBind(final ProductDTO data, int position) {
        prdImage.post(new Runnable() {
            @Override
            public void run() {
                mGlideRequestManager
                    .load(data.getPrdImgUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(prdImage);
            }
        });
        prdName.setText(data.getPrdNm());
        prdOrigin.setText(data.getOrigin());
    }

}