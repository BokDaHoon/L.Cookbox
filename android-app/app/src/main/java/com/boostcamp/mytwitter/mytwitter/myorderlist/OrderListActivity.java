package com.boostcamp.mytwitter.mytwitter.myorderlist;

import android.content.ContentUris;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.boostcamp.mytwitter.mytwitter.R;
import com.boostcamp.mytwitter.mytwitter.base.SharedPreferenceHelper;
import com.boostcamp.mytwitter.mytwitter.base.db.TwitterSchema;
import com.boostcamp.mytwitter.mytwitter.myorderlist.adapter.OrderListAdapter;
import com.boostcamp.mytwitter.mytwitter.myorderlist.presenter.OrderListPresenter;
import com.boostcamp.mytwitter.mytwitter.myorderlist.presenter.OrderListPresenterImpl;


import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderListActivity extends AppCompatActivity implements OrderListPresenter.View {

    @BindView(R.id.order_list)
    RecyclerView orderList;

    ImageButton scrapSearch;

    private OrderListAdapter adapter;
    private OrderListPresenterImpl presenter;
    private static final int TASK_LOADER_ID = 100;
    private long selectTweetId;

    private static final int ERROR_START_DATE_MORE_THAN = 201;
    private static final int ERROR_NOT_INPUT_START_DATE = 202;
    private static final int ERROR_NOT_INPUT_END_DATE = 203;


    @Override
    public void onResume() {
        super.onResume();

        SharedPreferenceHelper.getInstance(this).loadProperties();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new OrderListAdapter(this);
        orderList.setLayoutManager(layoutManager);
        orderList.setAdapter(adapter);

        presenter = new OrderListPresenterImpl();
        presenter.setView(this);
        presenter.setOrderListAdapterView(adapter);
        presenter.setOrderListAdapterModel(adapter);

        presenter.loadOrderList();
    }

    private void displaySuccessDelete() {
        Toast.makeText(this, "성공적으로 삭제되었습니다.", Toast.LENGTH_SHORT).show();
    }

    private void displayError(int errorType) {

        switch (errorType) {
            case ERROR_START_DATE_MORE_THAN :
                Toast.makeText(this, "시작날짜가 종료날짜보다 앞섭니다.", Toast.LENGTH_SHORT).show();
                break;
            case ERROR_NOT_INPUT_START_DATE :
                Toast.makeText(this, "시작날짜를 입력하세요.", Toast.LENGTH_SHORT).show();
                break;
            case ERROR_NOT_INPUT_END_DATE :
                Toast.makeText(this, "종료날짜를 입력하세요.", Toast.LENGTH_SHORT).show();
                break;

        }

    }

    @Override
    public void displayDialog(int position) {

        selectTweetId = adapter.getDataId(position);
        if (selectTweetId == -1) {
            return;
        }

        final CharSequence[] items = {"삭제"};
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // BackKey눌렀을 때 꺼지도록 셋팅.
        alertDialogBuilder
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    public boolean onKey(DialogInterface dialog,
                                         int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            finish();
                            dialog.dismiss();
                            return true;
                        }
                        return false;
                    }
                });

        // 제목셋팅
        alertDialogBuilder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                switch(id) {
                    case 0 :
                        getContentResolver().delete(ContentUris.withAppendedId(TwitterSchema.CONTENT_URI, selectTweetId),
                                null,
                                null);
                        displaySuccessDelete();
                        break;
                    default :
                        break;
                }

                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create(); // 다이얼로그 생성
        alertDialog.show(); // 다이얼로그 보여주기
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class SearchTask extends AsyncTask<Uri, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Uri... params) {
            Uri queryUri = params[0];

            Cursor cursor = getContentResolver().query(queryUri,
                                    null,
                                    null,
                                    null,
                                    null);
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            adapter.swapCursor(cursor);
        }
    }

}
