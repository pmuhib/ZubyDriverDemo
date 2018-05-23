package com.zuby.zubydriverdemo.view.DashBoard.model;

/**
 * Created by citymapper-pc5 on 22/5/18.
 */

public class Item {

    private int mDrawableRes;

    private String mTitle;

    public Item(int drawable, String title) {
        mDrawableRes = drawable;
        mTitle = title;
    }

    public int getDrawableResource() {
        return mDrawableRes;
    }

    public String getTitle() {
        return mTitle;
    }

}
