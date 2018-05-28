package com.zuby.zubydriverdemo.view.DashBoard.Presenter.routefinder;

/**
 * Created by citymapper-pc5 on 26/5/18.
 */

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;


public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
