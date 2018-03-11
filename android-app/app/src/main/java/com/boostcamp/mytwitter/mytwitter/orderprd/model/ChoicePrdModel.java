package com.boostcamp.mytwitter.mytwitter.orderprd.model;

import android.os.AsyncTask;
import android.util.Log;

import com.boostcamp.mytwitter.mytwitter.orderprd.adapter.contract.OrderDTO;
import com.boostcamp.mytwitter.mytwitter.orderprd.adapter.contract.ProductDTO;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract.RecipeStatusDTO;
import com.boostcamp.mytwitter.mytwitter.util.Define;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by DaHoon on 2017-07-27.
 */

public class ChoicePrdModel {

    private List<ProductDTO> mProductList;
    private ChoicePrdModel.ModelDataChange mModelDataChange;

    public void orderProduct(OrderDTO orderDto) {
        new OrderTask().execute(orderDto);
    }

    public interface ProductService {
        @GET("/product/list")
        Call<List<ProductDTO>> getProductList(@Query("id") String id);

        @Headers({"Content-Type: application/json"})
        @POST("/order/recipe")
        Call<OrderDTO> orderRecipe(@Body OrderDTO post);
    }
    public interface ModelDataChange {
        void update(List<ProductDTO> list);
    }

    public void callProductList(String id) {
        new BindTimelineTask().execute(id);
    }

    public void setOnChangeListener(ChoicePrdModel.ModelDataChange dataChange) {
        mModelDataChange = dataChange;
    }

    class OrderTask extends AsyncTask<OrderDTO, Void, Void> {

        @Override
        protected Void doInBackground(OrderDTO... params) {

            OrderDTO order = params[0];

            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Define.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ChoicePrdModel.ProductService service = retrofit.create(ChoicePrdModel.ProductService.class);
                Call<OrderDTO> call = service.orderRecipe(order);

                call.enqueue(new Callback<OrderDTO>() {
                    @Override
                    public void onResponse(Call<OrderDTO> call, Response<OrderDTO> response) {

                    }

                    @Override
                    public void onFailure(Call<OrderDTO> call, Throwable t) {

                    }
                });

            } catch (Exception ex) {
                ex.printStackTrace();
                Log.d("Timeline Activity", "실패");

            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }


    }

    class BindTimelineTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {

            String id = params[0];

            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Define.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ChoicePrdModel.ProductService service = retrofit.create(ChoicePrdModel.ProductService.class);
                Call<List<ProductDTO>> call = service.getProductList(id);

                call.enqueue(new Callback<List<ProductDTO>>() {

                    @Override
                    public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
                        if (response.isSuccessful()) {
                            mProductList = response.body();

                            if (mModelDataChange != null) {
                                mModelDataChange.update(mProductList);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<List<ProductDTO>> call, Throwable t) {
                        Log.d("ERROR CODE", t.toString());
                    }
                });

            } catch (Exception ex) {
                ex.printStackTrace();
                Log.d("Timeline Activity", "실패");

            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }


    }
}
