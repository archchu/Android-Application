package com.AndroidProject.womenSafety;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


public class Home extends AppCompatActivity {


    ImageButton btn;

    DatabaseHelper db;

    //accessing for torch-light
    Button btnFlashLight;
    boolean hasCameraFlash = false;
    private static final int CAMERA_REQUEST = 123;


    //access for panic sound
    ImageButton btn2;
    boolean playing;

    //access for camera
    ImageButton btn1;
    static final int REQUEST_VIDEO_CAPTURE = 1;
    VideoView result_video;


    //access imagebutton for emergency, information, comments and camera
    ImageButton myImageButton1;
    ImageButton myImageButton2;
    ImageButton myImageButton12;
    ImageButton myImageButton4;

    TextView textViewName;


    SharedPreferences loginPreferences;
    SharedPreferences.Editor loginPrefsEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db=new DatabaseHelper(this);

        textViewName = (TextView) findViewById(R.id.textView13);

        Intent intentLogin = new Intent(Home.this, login.class);

        loginPreferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        textViewName.setText(loginPreferences.getString("username", ""));

        if (loginPreferences.getString("username", "") == null) {

            startActivity(intentLogin);
            textViewName.setText("Session its working");
        }

        myImageButton12 = findViewById(R.id.imageButton12);
        // access facebook
        myImageButton12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToFacebookPage("115619586486089");

            }
        });

        //access for torch-light
        ActivityCompat.requestPermissions(Home.this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);

        hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        btnFlashLight = findViewById(R.id.btnFlashLightToggle);
        btnFlashLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasCameraFlash){
                    if (btnFlashLight.getText().toString().contains("OFF")){
                        btnFlashLight.setText("ON");
                        flashLightOff();

                    }
                    else{
                        btnFlashLight.setText("OFF");
                        flashLightOn();
                    }

                }
                else{
                    android.widget.Toast.makeText(Home.this, "No flash available on your device", Toast.LENGTH_SHORT).show();


                }
            }
        });


        //access camera
        btn1 = (ImageButton)findViewById(R.id.imageButton);

        //access for panic sound
        btn1 = (ImageButton)findViewById(R.id.imageButton2);

        final MediaPlayer mp=MediaPlayer.create(this, R.raw.alarm);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying() == true){
                    mp.pause();
                }else{
                    mp.start();
                }


            }
        });


        //access maps

        btn = (ImageButton) findViewById(R.id.imageButton9);

        //access imagebutton for emergency, information and comments

        myImageButton1 = (ImageButton) findViewById(R.id.imageButton11);
        myImageButton2= (ImageButton) findViewById(R.id.imageButton8);
        //myImageButton3= (ImageButton) findViewById(R.id.imageButton12);
        myImageButton4= (ImageButton) findViewById(R.id.imageButton10);



        myImageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Home.this, information.class);
                startActivity(intentLoadNewActivity);


            }
        });

        myImageButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Home.this, EmergencyAssistance.class);
                startActivity(intentLoadNewActivity);


            }
        });



        myImageButton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Home.this, SaftyPrecaution.class);
                startActivity(intentLoadNewActivity);


            }
        });


        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logo1);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    //access for torch-light
    private void flashLightOn(){
        CameraManager cameraManager =(CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
        }
        catch (CameraAccessException e){

        }

    }

    private void flashLightOff(){
        CameraManager cameraManager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
        }
        catch (CameraAccessException e){

        }

    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CAMERA_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);


                }
                else {
                    btnFlashLight.setEnabled(false);
                    android.widget.Toast.makeText(Home.this, "Permission Denied for the camera", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }


    //access for camera & SOS button


    public  void btnClick1(View v){


        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }



        loginPreferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        String username = (loginPreferences.getString("username", ""));
        if (loginPreferences.getString("username", "") == null) {

            Toast.makeText(getApplicationContext(),"You're not store Contact",Toast.LENGTH_LONG).show();

        }
        Cursor cursor = db.getData(username);
        final String[] arr= new String[14];
        int i = 0;
        while (cursor.moveToNext()){
            arr[i] = cursor.getString(0);
        }
        Uri uri = Uri.parse("smsto:" + arr[i]);

        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", "I am in danger, Please help me!!! https://maps.google.com/?q=6.983335,81.0697736");
        startActivity(it);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = intent.getData();
            result_video.setVideoURI(videoUri);
        }
    }


    //access for Safe Button
    public void btnClick7(View v)
    {

        loginPreferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        String username = (loginPreferences.getString("username", ""));
        if (loginPreferences.getString("username", "") == null) {

            Toast.makeText(getApplicationContext(),"You're not store Contact",Toast.LENGTH_LONG).show();

        }
        Cursor cursor = db.getData(username);
        final String[] arr= new String[14];
        int i = 0;
        while (cursor.moveToNext()){
            arr[i] = cursor.getString(0);
        }
        Uri uri = Uri.parse("smsto:" + arr[i]);

        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", "I am Safe Now!, https://maps.google.com/?q=6.983335,81.0697736");


        startActivity(it);
    }

    //access for launching maps
    public void btnClick(View v){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("geo:47.4925,19.0513"));
        Intent chooser = Intent.createChooser(i,"Launch Maps");
        startActivity(chooser);

    }



    private void logoutUser() {
        SharedPreferences.Editor editor = loginPreferences.edit();
        Intent intentLogin = new Intent(Home.this, login.class);
        editor.clear();
        editor.commit();
        startActivity(intentLogin);
        finish();
    }


    //access for menu list

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // int id = item.getItemId();
        // Handle action bar actions click
        switch (item.getItemId()) {

//        if (id == R.id.settings_id){
//            Intent myintent = new Intent(Home.this, Contacts.class);
//            startActivity(myintent);
//            return false;
//        }

            case R.id.logout_id:
                logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    //access facebook
    private void goToFacebookPage(String id){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/"+id));
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"+id));
            startActivity(intent);
        }

    }

}

