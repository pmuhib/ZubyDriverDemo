package com.zuby.zubydriverdemo.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by citymapper-pc5 on 15/5/18.
 */

public class PreferenceManager
{
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";


    public PreferenceManager(Context context)
    {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void saveSomeData(String name,int id)
    {
        editor.putString("name",name);
        editor.putInt("id",id);
        editor.commit();
    }

    public void saveDriverId(String userid)
    {
        editor.putString("userid",userid);
        editor.commit();
    }

    public void saveLevel2Details(String countrycode,String mobileno,String access_type,String status,String userid)
    {
        editor.putString("country_code",countrycode);
        editor.putString("mobile_no",mobileno);
        editor.putString("access_type",access_type);
        editor.putString("status",status);
        editor.putString("user_id",userid);
        editor.commit();
    }

    public HashMap<String,String>getLevel2Details()
    {
        HashMap<String,String>map = new HashMap<>();
        map.put("country_code",pref.getString("country_code",null));
        map.put("mobile_no",pref.getString("mobile_no",null));
        map.put("first_name",pref.getString("first_name",null));
        map.put("last_name",pref.getString("last_name",null));
        map.put("access_type",pref.getString("access_type",null));
        map.put("status",pref.getString("status",null));
        map.put("userid",pref.getString("userid",null));

        return map;

    }

    public void saveLoginDetails(String session_id,String session_login_type,String userid)
    {
        editor.putString("session_id",session_id);
        editor.putString("session_login_type",session_login_type);
        editor.putString("user_id",userid);
        editor.commit();
    }

    public HashMap<String,String>getLoginDetails()
    {
        HashMap<String,String>map = new HashMap<>();
        map.put("session_id",pref.getString("session_id",null));
        map.put("session_login_type",pref.getString("session_login_type",null));
        map.put("user_id",pref.getString("user_id",null));
        return map;
    }

    public HashMap<String,String>getDriverId()
    {
        HashMap<String,String>map = new HashMap<>();
        map.put("userid",pref.getString("userid",null));
        return map;

    }

    public void saveDocumentDetails(String document_id, String document_name)
    {
//        StringBuilder sb_id = new StringBuilder();
//        sb_id.append(document_id);
//        StringBuilder sb_name = new StringBuilder();
//        sb_name.append(document_name);
        editor.putString("document_id",document_id.toString());
        editor.putString("document_name",document_name.toString());
        editor.commit();
    }

    public void saveArraySize(int size)
    {
        editor.putInt("size",size);
        editor.commit();
    }

    public HashMap getDocumentDetails()
    {
        HashMap<String,String>map= new HashMap<>();
        map.put("document_id",pref.getString("document_id",null));
        map.put("document_name",pref.getString("document_name",null));
        return map;
    }

    public HashMap getArraySize()
    {
        HashMap<String,Integer>map = new HashMap<>();
        map.put("size",pref.getInt("size",0));
        return map;
    }

}
