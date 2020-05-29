package com.AndroidProject.womenSafety;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText e1;
    EditText e2;
    Button btn;
    Button btn1;
    DatabaseHelper db;
    SharedPreferences sp;
    SharedPreferences.Editor loginPrefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db=new DatabaseHelper(this);
        e1=(EditText) findViewById(R.id.uname);
        e2=(EditText) findViewById(R.id.pass);
        btn = (Button)findViewById(R.id.button2);
        btn1 = (Button)findViewById(R.id.button);

        sp = getSharedPreferences("loginPrefs",MODE_PRIVATE);
        loginPrefsEditor = sp.edit();

        if(sp.getBoolean("logged",false)){
            goToMainActivity();
        }


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=e1.getText().toString();
                String password=e2.getText().toString();

                boolean chklogin = db.userpassword(username,password);

                if(chklogin == true){
                    loginPrefsEditor.putString("username",username);
                    loginPrefsEditor.putString("password",password);
                    loginPrefsEditor.commit();

                    Toast.makeText(getApplicationContext(),"Successfully Login",Toast.LENGTH_LONG).show();
                    goToMainActivity();
                    sp.edit().putBoolean("logged",true).apply();

                }
                else{
                    Toast.makeText(getApplicationContext(),"Some error is occur",Toast.LENGTH_LONG).show();
                }
            }
        });




        //Register button

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(login.this,reg.class);
                startActivity(intentLoadNewActivity);
            }
        });

    }

    public void goToMainActivity(){
        Intent i = new Intent(this,Home.class);
        startActivity(i);
    }
}
