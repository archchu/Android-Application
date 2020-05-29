package com.AndroidProject.womenSafety;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class information extends AppCompatActivity {

    Button btn5;
    Button btn6;
    Button btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        btn5 = findViewById(R.id.legal);
        btn6 = findViewById(R.id.inform);
        btn9 = findViewById(R.id.news);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(information.this, Law.class);
                startActivity(intentLoadNewActivity);


            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(information.this, self.class);
                startActivity(intentLoadNewActivity);


            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.bbc.com/news/topics/c340r11jgrzt/violence-against-women"));
                startActivity(browserIntent);

            }
        });


    }

}
