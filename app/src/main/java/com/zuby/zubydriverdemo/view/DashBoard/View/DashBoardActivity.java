package com.zuby.zubydriverdemo.view.DashBoard.View;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.R;
import com.zuby.zubydriverdemo.Utils.PreferenceManager;
import com.zuby.zubydriverdemo.notification.model.ApplicationClass;
import com.zuby.zubydriverdemo.view.DashBoard.Presenter.DriverAvailablePresenter;
import com.zuby.zubydriverdemo.view.DashBoard.Presenter.ItemAdapter;
import com.zuby.zubydriverdemo.view.DashBoard.View.fragments.HomeFragment;
import com.zuby.zubydriverdemo.view.DashBoard.model.Item;
import com.zuby.zubydriverdemo.view.DocumentUpload.Presenter.VerifyPresenter;
import com.zuby.zubydriverdemo.view.Registration.View.SplashActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by citymapper-pc5 on 20/5/18.
 */

public class DashBoardActivity extends AppCompatActivity implements ItemAdapter.ItemListener
{
    private ActionBar mToolbar;
    private  BottomNavigationView mNavigationView;
    private BottomSheetBehavior mBottomSheetBehavior;
    private String mTokenid,mDriverid,mActive="",mStatus="";
    private Bundle mBundle;
    private String mDocument_id[],mDocument_name[];
    private Fragment fragment;
    private ItemAdapter mAdapter;
    private SwitchCompat mSwitchCompat;
    private ProgressBar progress;
    private int i;
    private NotificationCompat.Builder mNotificationCompat;
    private PreferenceManager mPrefeencemanager;
    private HashMap<String,String>map;
    private IntentFilter mIntentFilter;
    public static final String mBroadcastArrayListAction = "com.truiton.broadcast.string";
    BroadcastReceiver mReceiver;
    TextView available_text;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        View bottomSheet = findViewById( R.id.recyclerView);
        progress = findViewById(R.id.progress);

        mPrefeencemanager= new PreferenceManager(DashBoardActivity.this);

        mBundle=getIntent().getExtras();

        mIntentFilter = new IntentFilter("com.truiton.broadcast.string");

        map = mPrefeencemanager.getDeviceToken();
        String device_token= map.get("device_token");
         Log.e("Zy","device token"+" "+device_token);

        if(mBundle!=null)
        {
            mDocument_id=mBundle.getStringArray("document_id");
            mDocument_name=mBundle.getStringArray("document_name");
            mTokenid=mBundle.getString("tokenid");
            mDriverid=mBundle.getString("user_id");

            Log.e("Em","mDocumnet"+" "+mDocument_name[1]+ " " +mDocument_id.length+" "+mDriverid);
        }

//        CustomNotification();

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F9F9F9")));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.switch_on_off);
        View view =getSupportActionBar().getCustomView();

        mSwitchCompat = view.findViewById(R.id.switchForActionBar);
        available_text = view.findViewById(R.id.available_text);

        mSwitchCompat.setVisibility(View.GONE);

        Log.e("Zy","if verified"+" "+mActive+" "+mDocument_id.length);

        ApplicationClass applicationClass = new ApplicationClass();
        try {
            applicationClass.newData(device_token,"22.33","130.77","device_id");
        } catch (IOException e) {
            e.printStackTrace();
        }

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    Log.e("data activity", ":::::::::broadcast message::::::::;"+intent.getStringExtra("Data"));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Zy","error"+" "+e);
                }
            }
        };




        mSwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Log.e("Zuby","ischecked"+" "+isChecked);

                if(isChecked)
                {
                    progress.setVisibility(View.VISIBLE);
                    new DriverAvailablePresenter().show(new ResultInterface() {


                        @Override
                        public void onSuccess(String object) {

                        }

                        @Override
                        public void onSuccess(Object object) {
                            // as soon as the driver checks this switch that means he/she is online
                            available_text.setText("ONLINE");
                            mStatus = "online";
                            Log.e("Zuby","success"+" "+mActive);
                            progress.setVisibility(View.GONE);


                            fragment = new HomeFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("user_id",mDriverid);
                            bundle.putString("tokenid",mTokenid);
                            bundle.putString("flag",mActive);
                            bundle.putString("status",mStatus);
                            fragment.setArguments(bundle);
                            switchFrag(fragment);


                        }

                        @Override
                        public void onFailed(Object string) {
                            progress.setVisibility(View.GONE);

                        }
                    },DashBoardActivity.this,mDriverid,"123","online","Asia/Calcutta",mTokenid);
                }
                else
                { progress.setVisibility(View.VISIBLE);
                    new DriverAvailablePresenter().show(new ResultInterface() {
                        @Override
                        public void onSuccess(String object) {

                        }

                        @Override
                        public void onSuccess(Object object) {
                            Log.e("Zuby","success"+" "+mActive);

                            available_text.setText("OFFLINE");
                            progress.setVisibility(View.GONE);
            // as soon as the driver comes here he/she is offline
                            mStatus = "offline";
                            Log.e("Zuby","success");
                            progress.setVisibility(View.GONE);
                            fragment = new HomeFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("user_id",mDriverid);
                            bundle.putString("tokenid",mTokenid);
                            bundle.putString("flag",mActive);
                            bundle.putString("status",mStatus);
                            fragment.setArguments(bundle);
                            switchFrag(fragment);
                        }

                        @Override
                        public void onFailed(Object string) {
                            progress.setVisibility(View.GONE);
                        }
                    },DashBoardActivity.this,mDriverid,"123","offline","Asia/Calcutta",mTokenid);
                }
            }
        });

        mSwitchCompat.setVisibility(View.VISIBLE);
                    mActive="active";
                    progress.setVisibility(View.GONE);
                    fragment = new HomeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("user_id",mDriverid);
                    bundle.putString("tokenid",mTokenid);
                    bundle.putString("flag",mActive);
                    bundle.putString("status",mStatus);
                    fragment.setArguments(bundle);
                    switchFrag(fragment);


//        for(int i=1;i<=mDocument_id.length;i++)
//        {
//
//            new VerifyPresenter().show(new ResultInterface() {
//                @Override
//                public void onSuccess(String object)
//                {
//
//                }
//                @Override
//                public void onSuccess(Object object)
//                {
//                    // as soon as driver is active switchcompat button will be visible otherwise it will dissapear
//
//
//                    // Here also it will switch to Homefragment but without any status information it will only show image of city in next page and welcome sign
//                    Log.e("Em","valid docs");
//                    mSwitchCompat.setVisibility(View.VISIBLE);
//                    mActive="active";
//                    progress.setVisibility(View.GONE);
//                    fragment = new HomeFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("user_id",mDriverid);
//                    bundle.putString("tokenid",mTokenid);
//                    bundle.putString("flag",mActive);
//                    bundle.putString("status",mStatus);
//                    fragment.setArguments(bundle);
//                    switchFrag(fragment);
//
//                }
//
//                @Override
//                public void onFailed(Object string)
//                {
//                    Log.e("Em","invlaid docs!!");
//
////                    mActive="active";
//                    mSwitchCompat.setVisibility(View.GONE);
//                    mSwitchCompat.setVisibility(View.VISIBLE);
//                    fragment = new HomeFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("user_id",mDriverid);
//                    bundle.putString("tokenid",mTokenid);
//                    bundle.putString("flag",mActive);
//                    fragment.setArguments(bundle);
//                    switchFrag(fragment);
//
//                }
//            },DashBoardActivity.this,mDriverid,mDocument_id[i],mTokenid);
//        }


//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        mAdapter = new ItemAdapter(createItems(), DashBoardActivity.this);
//        recyclerView.setAdapter(mAdapter);


        mNavigationView =findViewById(R.id.navigation);
        mNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
//        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

//        mBottomSheetBehavior.setHideable(true);//Important to add
//        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//        mBottomSheetBehavior.setPeekHeight(200);
//
//        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(View bottomSheet, int newState)
//            {
//                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
//                    mBottomSheetBehavior.setPeekHeight(200);
//                }
//            }
//
//            @Override
//            public void onSlide(View bottomSheet, float slideOffset)
//            {
//            }
//        });
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu, menu);
//        MenuItem item = menu.findItem(R.id.myswitch);
//        item.setActionView(R.layout.switch_on_off);
////        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
//        TextView textBtn = getTextButton("testing");
//        return true;
//    }



    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId())
                    {
                        case R.id.home:
                            Toast.makeText(getApplication(),"Home",Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.rat:
                            Toast.makeText(getApplication(),"Rating",Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.payment:
                            Toast.makeText(getApplication(),"Payment",Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.account:
                            Toast.makeText(getApplication(),"Account",Toast.LENGTH_SHORT).show();
                            return true;
                    }
                    return false;
                }
            };



    public void switchFrag(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame_container,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public List<Item> createItems() {

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(R.mipmap.ic_launcher, "Item 1"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 2"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 3"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 4"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 5"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 6"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 7"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 8"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 9"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 10"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 11"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 12"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 13"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 14"));
        items.add(new Item(R.mipmap.ic_launcher, "Item 15"));


        return items;
    }


    @Override
    public void onItemClick(Item item)
    {
//        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //unregister our receiver
        this.unregisterReceiver(this.mReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }


}




