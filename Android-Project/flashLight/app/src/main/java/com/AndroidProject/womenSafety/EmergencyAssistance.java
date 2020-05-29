package com.AndroidProject.womenSafety;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EmergencyAssistance extends AppCompatActivity {
    Button b;
    Button b1;
    Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencyassistance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        b = (Button)findViewById(R.id.idbtnCall);
        b1 = (Button)findViewById(R.id.idbtnCall1);
        b2 = (Button)findViewById(R.id.idbtnCall2);
    }

    public void btnClick(View v){
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:+94774003562"));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Please grant the permission to call", Toast.LENGTH_SHORT).show();
            requestPermission();

        }
        else{
            startActivity(i);

        }

    }

    public void btnClick1(View v){
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:+94771847763"));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Please grant the permission to call", Toast.LENGTH_SHORT).show();
            requestPermission();

        }
        else{
            startActivity(i);

        }

    }

    public void btnClick2(View v){
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:+94769006515"));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Please grant the permission to call", Toast.LENGTH_SHORT).show();
            requestPermission();

        }
        else{
            startActivity(i);

        }

    }




    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new  String[]{Manifest.permission.CALL_PHONE}, 1);}


}


