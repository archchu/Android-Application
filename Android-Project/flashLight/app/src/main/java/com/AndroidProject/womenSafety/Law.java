package com.AndroidProject.womenSafety;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;




public class Law extends AppCompatActivity {

    Button Button1;
    Button Button2;
    Button Button3;
    Button Button4;
    Button Button5;
    Button Button6;
    Button Button7;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button1 = findViewById(R.id.btn1);
        Button2 = findViewById(R.id.btn2);
        Button3 = findViewById(R.id.btn3);
        Button4 = findViewById(R.id.btn4);
        Button5 = findViewById(R.id.btn5);
        Button6 = findViewById(R.id.btn6);
        Button7 = findViewById(R.id.btn7);



        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Law.this, domestic.class);
                startActivity(intentLoadNewActivity);


            }




        });
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Law.this, incest.class);
                startActivity(intentLoadNewActivity);


            }




        });
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Law.this, sexualharsmment.class);
                startActivity(intentLoadNewActivity);


            }




        });
        Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Law.this, cybersexual.class);
                startActivity(intentLoadNewActivity);


            }




        });
        Button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Law.this, childsexual.class);
                startActivity(intentLoadNewActivity);


            }




        });
        Button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Law.this, gravesexual.class);
                startActivity(intentLoadNewActivity);


            }




        });

        Button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Law.this, rape.class);
                startActivity(intentLoadNewActivity);


            }




        });

    }
}
