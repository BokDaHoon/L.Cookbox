package com.boostcamp.mytwitter.mytwitter.timeline.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.boostcamp.mytwitter.mytwitter.R;
import com.boostcamp.mytwitter.mytwitter.listener.OnSearchClickListener;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract.RecipeStatusDTO;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class CustomDialog extends Dialog {

    private Button scrapSearchBtn;
    private MaterialEditText contentSearch;

    private SimpleDateFormat dateFormatter;

    private OnSearchClickListener mOnClickListener;
    private Context mContext;
    private RecipeStatusDTO mRecipeStatusDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현 
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.dialog_scrap);

        scrapSearchBtn = (Button) findViewById(R.id.scrap_search_btn);
        contentSearch = (MaterialEditText) findViewById(R.id.search_content);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

        contentSearch.setText("");

        // 클릭 이벤트 셋팅
        scrapSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickListener != null) {
                    mRecipeStatusDTO.setRepEname(contentSearch.getText().toString());

                    mOnClickListener.onItemClick(mRecipeStatusDTO);
                }
            }
        });

    }

    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public CustomDialog(Context context, String title, OnSearchClickListener singleListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        mContext = context;
        mRecipeStatusDTO = new RecipeStatusDTO();
        this.mOnClickListener = singleListener;
    }

}