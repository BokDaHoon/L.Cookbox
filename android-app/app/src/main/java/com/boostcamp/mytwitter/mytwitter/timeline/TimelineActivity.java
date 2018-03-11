package com.boostcamp.mytwitter.mytwitter.timeline;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.boostcamp.mytwitter.mytwitter.R;
import com.boostcamp.mytwitter.mytwitter.base.SharedPreferenceHelper;
import com.boostcamp.mytwitter.mytwitter.base.LoginInfo;
import com.boostcamp.mytwitter.mytwitter.listener.OnSearchClickListener;
import com.boostcamp.mytwitter.mytwitter.myorderlist.OrderListActivity;
import com.boostcamp.mytwitter.mytwitter.orderprd.ChoicePrdActivity;
import com.boostcamp.mytwitter.mytwitter.login.LoginActivity;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.TimelineAdapter;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract.RecipeStatusDTO;
import com.boostcamp.mytwitter.mytwitter.timeline.dialog.CustomDialog;
import com.boostcamp.mytwitter.mytwitter.timeline.presenter.TimelinePresenter;
import com.boostcamp.mytwitter.mytwitter.timeline.presenter.TimelinePresenterImpl;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tsengvn.typekit.TypekitContextWrapper;
import com.twitter.sdk.android.tweetui.Timeline;

import java.util.Iterator;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import twitter4j.User;

public class TimelineActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TimelinePresenter.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.tweet_timeline)
    RecyclerView timeline;
    @BindView(R.id.timeline_actionbar_search)
    ImageButton searchBtn;

    TextView twitterProfileId;
    TextView twitterProfileName;
    ImageView twitterProfileImage;

    private TimelinePresenterImpl presenter;
    private TimelineAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private static final int REQUEST_TWEET = 100;
    public static final String DETAIL_STATUS_KEY = "detail_status_key";
    public static final String VIEWHOLDER_TYPE = "viewholder_type";
    private CustomDialog mCustomDialog;

    @Override
    protected void onResume() {
        super.onResume();

        String ename = getIntent().getStringExtra("recipe");
        Log.d("TEST", "ename : " + ename);

        Bundle extras = getIntent().getExtras();
        String value = "";
        if(extras != null) {
            value = extras.getString("recipe");

            Set<String> extrass =  extras.keySet();
            Iterator<String> iter = extrass.iterator();
            while (iter.hasNext()) {
                Log.d("TEST", "value : " + iter.next());
            }
        }


        if (ename == null || ename.equals("")) {
            presenter.updateRecipeData();
        } else {
            searchRecipeData(ename);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferenceHelper.getInstance(this).loadProperties();

        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        init();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    void init() {

        twitterProfileId = (TextView) navigationView.getHeaderView(0).findViewById(R.id.profile_id);
        twitterProfileName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.profile_name);

        FirebaseMessaging.getInstance().subscribeToTopic("ALL");
        FirebaseInstanceId.getInstance().getToken();

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCM_Token", token);

        mLayoutManager = new LinearLayoutManager(this);
        timeline.setLayoutManager(mLayoutManager);

        mAdapter = new TimelineAdapter(this);
        timeline.setAdapter(mAdapter);

        twitterProfileId.setText(LoginInfo.MEMBER_ID);
        twitterProfileName.setText(LoginInfo.MEMBER_NAME);

        presenter = new TimelinePresenterImpl();
        presenter.setView(this);
        presenter.setTimelineListAdapterModel(mAdapter);
        presenter.setTimelineListAdapterView(mAdapter);

        mCustomDialog = new CustomDialog(TimelineActivity.this, "[다이얼로그 제목]", listener);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomDialog.show();
            }
        });

    }

    private OnSearchClickListener listener = new OnSearchClickListener() {

        @Override
        public void onItemClick(RecipeStatusDTO recipeStatusDTO) {
            searchRecipeData(recipeStatusDTO.getRepEname());
            mCustomDialog.dismiss();
        }
    };

    void searchRecipeData(String ename) {
        presenter.updateRecipeData(ename);
        Toast.makeText(TimelineActivity.this, "성공적으로 조회하였습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_order) {
            moveToOrderListMenu();
        } else if (id == R.id.nav_logout) {
            SharedPreferenceHelper.getInstance(this).clearProperties(); // 로그아웃
            finish();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Sidebar Navigation Data Set
     * @param user
     */
    @Override
    public void initSidebarNavigation(User user) {
        LoginInfo.TwitUser = user;
    }

    /**
     * Check List More Load
     */
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = mLayoutManager.getChildCount();
            int totalItemCount = mLayoutManager.getItemCount();
            int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

            //presenter.checkListViewPositionBottom(visibleItemCount, totalItemCount, firstVisibleItemPosition);
        }
    };

    @Override
    public void moveToDetail(int position) {
        Intent intent = new Intent(this, ChoicePrdActivity.class);
        Bundle bundle = new Bundle();
        RecipeStatusDTO data = mAdapter.getListData(position);
        data.setInqueryCount(data.getInqueryCount() + 1);
        bundle.putSerializable(DETAIL_STATUS_KEY, data);
        intent.putExtra(DETAIL_STATUS_KEY, bundle);
        startActivity(intent);
    }

    public void moveToProfile(long id) {
    }

    @Override
    public void moveToReply(long statusId) {
    }

    private void moveToProfileMenu() {
    }

    private void moveToOrderListMenu() {
        Intent intent = new Intent(this, OrderListActivity.class);
        startActivity(intent);
    }

    @Override protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}
