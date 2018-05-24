package com.zuby.zubydriverdemo.view.DashBoard.View;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.R;
import com.zuby.zubydriverdemo.view.DashBoard.Presenter.DriverAvailablePresenter;
import com.zuby.zubydriverdemo.view.DashBoard.Presenter.ItemAdapter;
import com.zuby.zubydriverdemo.view.DashBoard.View.fragments.HomeFragment;
import com.zuby.zubydriverdemo.view.DashBoard.model.Item;
import com.zuby.zubydriverdemo.view.DocumentUpload.Presenter.VerifyPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by citymapper-pc5 on 20/5/18.
 */

public class DashBoardActivity extends AppCompatActivity implements ItemAdapter.ItemListener
{
    private ActionBar mToolbar;
    private  BottomNavigationView mNavigationView;
    private BottomSheetBehavior mBottomSheetBehavior;
    private String mTokenid,mDriverid,mActive="";
    private Bundle mBundle;
    private String mDocument_id[],mDocument_name[];
    private Fragment fragment;
    private ItemAdapter mAdapter;
    private SwitchCompat mSwitchCompat;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        View bottomSheet = findViewById( R.id.recyclerView);
        mSwitchCompat = findViewById(R.id.switchForActionBar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F9F9F9")));


        mBundle=getIntent().getExtras();

        if(mBundle!=null)
        {
            mDocument_id=mBundle.getStringArray("document_id");
            mDocument_name=mBundle.getStringArray("document_name");
            mTokenid=mBundle.getString("tokenid");
            mDriverid=mBundle.getString("user_id");

            Log.e("Em","mDocumnet"+" "+mDocument_name[1]+ " " +mDocument_id.length);
        }

        mSwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
//                    new DriverAvailablePresenter().show(new ResultInterface() {
//                        @Override
//                        public void onSuccess(String object) {
//
//                        }
//
//                        @Override
//                        public void onSuccess(Object object) {
//
//                        }
//
//                        @Override
//                        public void onFailed(Object string) {
//
//                        }
//                    },DashBoardActivity.this,);
                }
            }
        });


        for(int i=0;i<mDocument_id.length;i++)
        {
            Log.e("Em","document name"+" "+mDocument_id[i]);

            new VerifyPresenter().show(new ResultInterface() {
                @Override
                public void onSuccess(String object)
                {

                }
                @Override
                public void onSuccess(Object object)
                {
//                    Log.e("Em","valid docs");
//                    mActive="active";
//                    fragment = new HomeFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("user_id",mDriverid);
//                    bundle.putString("tokenid",mTokenid);
//                    bundle.putString("flag",mActive);
//                    fragment.setArguments(bundle);
//                    switchFrag(fragment);

                }

                @Override
                public void onFailed(Object string)
                {
                    Log.e("Em","invlaid docs!!");

                    mActive="active";
                    mSwitchCompat.setVisibility(View.VISIBLE);
                    fragment = new HomeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("user_id",mDriverid);
                    bundle.putString("tokenid",mTokenid);
                    bundle.putString("flag",mActive);
                    fragment.setArguments(bundle);
                    switchFrag(fragment);

                }
            },DashBoardActivity.this,mDriverid,mDocument_id[i],mTokenid);
        }


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ItemAdapter(createItems(), DashBoardActivity.this);
        recyclerView.setAdapter(mAdapter);



        mNavigationView =findViewById(R.id.navigation);
        mNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState)
            {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setPeekHeight(200);
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset)
            {
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.myswitch);
        item.setActionView(R.layout.switch_on_off);
//        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        TextView textBtn = getTextButton("testing");
        return true;
    }

    public TextView getTextButton (String btn_title) {
        TextView textBtn = new TextView(this);
        textBtn.setText(btn_title);
        textBtn.setTextColor(Color.BLACK);
        textBtn.setTextSize(18);
        textBtn.setTypeface(Typeface.create("sans-serif-light", Typeface.BOLD));
        textBtn.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
//        Drawable img = btn_image;
//        img.setBounds(0, 0, 30, 30);
        textBtn.setCompoundDrawables(null, null, null, null);
        // left,top,right,bottom. In this case icon is right to the text

        return textBtn;
    }

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


}




