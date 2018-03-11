package com.boostcamp.mytwitter.mytwitter.timeline.model;

import android.os.AsyncTask;
import android.util.Log;

import com.boostcamp.mytwitter.mytwitter.base.LoginInfo;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract.RecipeStatusDTO;
import com.boostcamp.mytwitter.mytwitter.util.Define;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 * Created by DaHoon on 2017-02-10.
 */

public class TimelineModel {

    private List<RecipeStatusDTO> mRecipeList;
    private ModelDataChange mModelDataChange;

    public interface ModelDataChange {
        void update(List<RecipeStatusDTO> list);
    }

    public interface RecipeService {
        @GET("/recipe/list")
        Call<List<RecipeStatusDTO>> getRecipeList();

        @GET("/recipe/search")
        Call<List<RecipeStatusDTO>> getSearchRecipe(@Query("ename") String ename);
    }

    /**
     * Search Github Open API
     */
    public void callTimelineList() {
        new BindTimelineTask().execute("");
    }


    public void callTimelineList(String ename) {
        new BindTimelineTask().execute(ename);
    }

    /**
     * Set Listener
     * @param dataChange
     */
    public void setOnChangeListener(ModelDataChange dataChange) {
        mModelDataChange = dataChange;
    }

    class BindTimelineTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {

            String ename = params[0];

            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Define.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RecipeService service = retrofit.create(RecipeService.class);
                Call<List<RecipeStatusDTO>> call = null;
                // 특정 레시피 조회
                if ((ename == null) || ename == "") {
                    call = service.getRecipeList();

                // 전체 레시피 조회
                } else {
                    call = service.getSearchRecipe(ename);

                }

                call.enqueue(new Callback<List<RecipeStatusDTO>>() {

                    @Override
                    public void onResponse(Call<List<RecipeStatusDTO>> call, Response<List<RecipeStatusDTO>> response) {
                        if (response.isSuccessful()) {
                            mRecipeList = response.body();
                            Log.d("TEST", mRecipeList.toString());
                            if (mModelDataChange != null) {
                                mModelDataChange.update(mRecipeList);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<List<RecipeStatusDTO>> call, Throwable t) {
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
