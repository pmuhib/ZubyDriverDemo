package com.zuby.zubydriverdemo.view.DocumentUpload.View;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zuby.zubydriverdemo.R;
import com.zuby.zubydriverdemo.view.DashBoard.View.DashBoardActivity;

import java.io.ByteArrayOutputStream;

/**
 * Created by citymapper-pc5 on 23/5/18.
 */

public class UploadProfilePhotoActivity extends AppCompatActivity
{
   private String mUser_id,mTokenid;
   private Bundle mBundle;
   private Button mTake_picture,mContinuebutton;
   private static final int CAMERA_REQUEST = 1;
   private de.hdodenhof.circleimageview.CircleImageView mProfile_image;
   private Bitmap mBitmap;
   private String mDocument_id[],mDocument_name[];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_photo);
        mTake_picture = findViewById(R.id.take_picture);
        mProfile_image = findViewById(R.id.profile_image);
        mContinuebutton = findViewById(R.id.continuebutton);

        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        mBundle = getIntent().getExtras();

        if(mBundle!=null)
        {
            mUser_id = mBundle.getString("user_id");
            mDocument_id = mBundle.getStringArray("document_id");
            mDocument_name = mBundle.getStringArray("document_name");
            mTokenid = mBundle.getString("tokenid");

            Log.e("Em","document"+" "+mDocument_id[1]+" "+mUser_id+" "+mTokenid);
         }

        mTake_picture.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                callCamera();
            }
        });

        mContinuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadProfilePhotoActivity.this, DashBoardActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user_id",mUser_id);
                bundle.putStringArray("document_id",mDocument_id);
                bundle.putStringArray("document_name",mDocument_name);
                bundle.putString("tokenid",mTokenid);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    public void callCamera()
    {
        Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra("crop", "true");
        cameraIntent.putExtra("aspectX", 0);
        cameraIntent.putExtra("aspectY", 0);
        cameraIntent.putExtra("outputX", 200);
        cameraIntent.putExtra("outputY", 150);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode != RESULT_OK)
            return;

        switch (requestCode)
        {
            case CAMERA_REQUEST:

                Bundle extras = data.getExtras();

                if (extras != null)
                {

                    Bitmap tobecompressedbitmap = extras.getParcelable("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    tobecompressedbitmap.compress(Bitmap.CompressFormat.JPEG,50,stream);
                    byte[] byteArray = stream.toByteArray();
                    mBitmap  = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);


                    mProfile_image.setImageBitmap(mBitmap);

                    Log.e("Em","::::bitmap::::"+" "+mBitmap);

                    mTake_picture.setVisibility(View.GONE);
                    mContinuebutton.setVisibility(View.VISIBLE);


                }
                break;

        }
    }

}
