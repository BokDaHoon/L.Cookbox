package com.boostcamp.mytwitter.mytwitter.orderprd;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.boostcamp.mytwitter.mytwitter.R;
import com.boostcamp.mytwitter.mytwitter.base.LoginInfo;
import com.boostcamp.mytwitter.mytwitter.orderprd.adapter.OrderPrdAdapter;
import com.boostcamp.mytwitter.mytwitter.orderprd.adapter.contract.OrderDTO;
import com.boostcamp.mytwitter.mytwitter.orderprd.presenter.ChoicePrdPresenter;
import com.boostcamp.mytwitter.mytwitter.orderprd.presenter.ChoicePrdPresenterImpl;
import com.boostcamp.mytwitter.mytwitter.timeline.TimelineActivity;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract.RecipeStatusDTO;
import com.boostcamp.mytwitter.mytwitter.util.Define;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.dionsegijn.steppertouch.OnStepCallback;

public class ChoicePrdActivity extends AppCompatActivity implements ChoicePrdPresenter.View {

    private OrderPrdAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private RecipeStatusDTO mRecipeStatusDTO;
    private ChoicePrdPresenterImpl presenter;

    @BindView(R.id.choice_prd_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.btn_cancel)
    Button mButtonCancle;
    @BindView(R.id.btn_order)
    Button mButtonOrder;
    @BindView(R.id.stepperTouch)
    nl.dionsegijn.steppertouch.StepperTouch mStepperTouch;

    int cal = 0;
    String delType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_prd);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    void init() {
        presenter = new ChoicePrdPresenterImpl();
        mAdapter = new OrderPrdAdapter(this);
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        presenter.setView(this);
        presenter.setTimelineListAdapterView(mAdapter);
        presenter.setTimelineListAdapterModel(mAdapter);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(TimelineActivity.DETAIL_STATUS_KEY);
        mRecipeStatusDTO = (RecipeStatusDTO) bundle.getSerializable(TimelineActivity.DETAIL_STATUS_KEY);

        presenter.loadProductList(mRecipeStatusDTO.getRecipeId());

        final DecimalFormat format = new DecimalFormat("###,###");
        money.setText(format.format(mRecipeStatusDTO.getPrice()));

        mStepperTouch.stepper.setMin(1);
        mStepperTouch.stepper.setValue(1);
        mStepperTouch.stepper.addStepCallback(new OnStepCallback() {

            @Override
            public void onStep(int value, boolean positive) {
                cal = mRecipeStatusDTO.getPrice() * value;
                money.setText(format.format(cal));
            }
        });

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

    void showSuccessOrder() {
        Toast.makeText(this, "주문이 완료되었습니다.", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_cancel)
    void clickCancleButton() {
        finish();
    }

    @OnClick(R.id.btn_order)
    void clickOrderButton() {
        final CharSequence[] items = {"스마트픽", "직접배송"};
        ContextThemeWrapper cw = new ContextThemeWrapper( this, R.style.AlertDialogTheme );
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

        alertDialogBuilder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                switch(id) {
                    case Define.TWEET_DIRECT :
                        delType = "DEV002";
                        //dialog.dismiss();
                        break;
                    case Define.TWEET_SCHEDULE :
                        delType = "DEV001";
                        //dialog.dismiss();
                        break;
                    default :
                        dialog.dismiss();
                        break;
                }

            }
        });

        alertDialogBuilder.setTitle("배송 방식을 선택하십시오.")
                          .setPositiveButton("선택",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        presenter.orderProduct(new OrderDTO().setMbrId(LoginInfo.MEMBER_ID)
                                                                             .setRepId(mRecipeStatusDTO.getRecipeId())
                                                                             .setCnt(mStepperTouch.stepper.getValue())
                                                                             .setDevType(delType)
                                        );
                                        showSuccessOrder();
                                        // 프로그램을 종료한다
                                        ChoicePrdActivity.this.finish();
                                    }
                                })
                          .setNegativeButton("취소",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 다이얼로그를 취소한다
                                        dialog.cancel();
                                    }
                                });

        final AlertDialog alertDialog = alertDialogBuilder.create(); // 다이얼로그 생성
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(Dialog.BUTTON_POSITIVE).setTextSize(15);
                alertDialog.getButton(Dialog.BUTTON_NEGATIVE).setTextSize(15);
            }
        });
        alertDialog.show(); // 다이얼로그 보여주기
    }
}
