package com.zuby.zubydriverdemo.view.DocumentUpload.View;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
    private static final int PICK_FROM_GALLERY = 2;

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
                captureImage();
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

    private void captureImage()
    {
        final String[] option = new String[] { "Take from Camera",
                "Select from Gallery" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, option);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Select Option");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Log.e("Selected Item", String.valueOf(which));
                if (which == 0)
                {

                    if (ActivityCompat.checkSelfPermission(UploadProfilePhotoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(UploadProfilePhotoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_REQUEST);
                    } else {
                        callCamera();
                    }

                }
                if (which == 1)
                {

                    if (ActivityCompat.checkSelfPermission(UploadProfilePhotoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(UploadProfilePhotoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                    } else {
                        callGallery();
                    }
                }

            }
        });
        final AlertDialog dialog = builder.create();

        dialog.show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[],  int[] grantResults)
    {
        switch (requestCode) {
            case PICK_FROM_GALLERY:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callGallery();
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;

            case CAMERA_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callCamera();
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;
        }
    }


    public void callGallery()
    {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, PICK_FROM_GALLERY);

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

            case PICK_FROM_GALLERY:


                try {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };

                    Cursor cursor = this.getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();

                    Log.e("Em","picturePath"+" "+picturePath);

                    Bitmap compressedBitmap = BitmapFactory.decodeFile(picturePath);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    compressedBitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    byte[] byteArray = stream.toByteArray();
                    mBitmap  = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);

                    mProfile_image.setImageBitmap(mBitmap);


                    mTake_picture.setVisibility(View.GONE);
                    mContinuebutton.setVisibility(View.VISIBLE);


                    break;
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("Em","::::::exception:::::"+" "+e);
                }



        }
    }

}
