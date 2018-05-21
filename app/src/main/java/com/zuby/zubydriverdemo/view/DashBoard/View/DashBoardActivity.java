package com.zuby.zubydriverdemo.view.DashBoard.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zuby.zubydriverdemo.R;

/**
 * Created by citymapper-pc5 on 20/5/18.
 */

public class DashBoardActivity extends Activity
{

    private ActionBar mToolbar;
    private  BottomNavigationView mNavigationView;
    private BottomSheetBehavior mBottomSheetBehavior;
    LinearLayout layoutBottomSheet;
    Button btnBottomSheet;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        View bottomSheet = findViewById( R.id.bottom_sheet );

        mNavigationView =findViewById(R.id.navigation);
        mNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
//        toolbar.setTitle("Home");
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setPeekHeight(40);
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset)
            {
            }
        });
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
    }

