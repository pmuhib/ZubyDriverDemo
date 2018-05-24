package com.zuby.zubydriverdemo.view.DashBoard.View;

import android.app.Activity;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.R;
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
    private String mDocument_name,mDocument_id,mTokenid,mDriverid;
    private Bundle mBundle;
    String [] document_id = new String[3];
    String [] document_name = new String[3];
    Fragment fragment;
    private ItemAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        View bottomSheet = findViewById( R.id.recyclerView);

        document_id[0]="123";
        document_id[1]="456";
        document_id[2]="789";

        document_name[0]="test0";
        document_name[1]="test1";
        document_name[2]="test2";

        mBundle=getIntent().getExtras();

        if(mBundle!=null)
        {
            mDocument_id=mBundle.getString("document_id");
            mDocument_name=mBundle.getString("document_name");
            mTokenid=mBundle.getString("tokenid");
            mDriverid=mBundle.getString("user_id");
        }

        fragment = new HomeFragment();
        switchFrag(fragment);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ItemAdapter(createItems(), DashBoardActivity.this);
        recyclerView.setAdapter(mAdapter);

        for(int i=0;i<document_id.length;i++)
        {
            Log.e("Em","document name"+" "+document_id[i]);

            new VerifyPresenter().show(new ResultInterface() {
                @Override
                public void onSuccess(String object)
                {

                }
                @Override
                public void onSuccess(Object object)
                {
                    Log.e("Em","valid docs");
                }

                @Override
                public void onFailed(Object string)
                {
                    Log.e("Em","invlaid docs!!");
                }
            },DashBoardActivity.this,mDriverid,document_id[i],document_name[i]);
        }



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




