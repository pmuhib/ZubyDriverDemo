package com.zuby.zubydriverdemo.Presenter.interfaces;

/**
 * Created by citymapper-pc5 on 17/5/18.
 */

public interface ResultInterface
{
    public void onSuccess(String object);
    public void onSuccess(Object object);

    public void onFailed(Object string);

//    public void onSuccessSplash(String countrycode,String mobileno,String loginsessionlogintype,String tokenresponse,String loginuserid,boolean value);
}
