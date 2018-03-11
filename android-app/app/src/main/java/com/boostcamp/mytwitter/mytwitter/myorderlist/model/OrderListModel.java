package com.boostcamp.mytwitter.mytwitter.myorderlist.model;

import android.os.AsyncTask;
import android.util.Log;

import com.boostcamp.mytwitter.mytwitter.base.LoginInfo;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract.RecipeStatusDTO;
import com.boostcamp.mytwitter.mytwitter.timeline.model.TimelineModel;
import com.boostcamp.mytwitter.mytwitter.util.Define;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DaHoon on 2017-08-02.
 */

public class OrderListModel {

    private List<OrderListDTO> mOrderList;
    private ModelDataChange mModelDataChange;

    public void setOnChangeListener(ModelDataChange dataChange) {
        mModelDataChange = dataChange;
    }

    public interface ModelDataChange {
        void update(List<OrderListDTO> list);
    }

    public interface OrderListService {
        @GET("/order/list")
        Call<List<OrderListDTO>> getOrderList(@Query("id") String id);
    }

    public void loadOrderList() {
        new BindTimelineTask().execute();
    }

    class BindTimelineTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            String id = LoginInfo.MEMBER_ID;

            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Define.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                OrderListService service = retrofit.create(OrderListService.class);
                Call<List<OrderListDTO>> call = service.getOrderList(id);

                call.enqueue(new Callback<List<OrderListDTO>>() {

                    @Override
                    public void onResponse(Call<List<OrderListDTO>> call, Response<List<OrderListDTO>> response) {
                        if (response.isSuccessful()) {
                            mOrderList = response.body();

                            if (mModelDataChange != null) {
                                mModelDataChange.update(mOrderList);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<List<OrderListDTO>> call, Throwable t) {
                        Log.d("ERROR CODE", t.toString());
                    }
                });

            } catch (Exception ex) {
                ex.printStackTrace();
                Log.d("OrderListModel Activity", "실패");

            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }


    }
}
