package com.AndroidProject.womenSafety;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class reg extends AppCompatActivity {

    EditText n1;
    EditText n2;
    EditText n3;
    EditText n4;
    EditText n5;
    public EditText n6;
    EditText n7;
    EditText n8;
    Button b1;
    Button b2;
    private static final int RESULT_PICK_CONTACT =1;
    private EditText contact;
    private Button select;
    DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        db=new DatabaseHelper(this);
        n1=(EditText) findViewById(R.id.name);
        n2=(EditText) findViewById(R.id.age);
        n3=findViewById(R.id.phone);
        n4=findViewById(R.id.nic);
        n5=findViewById(R.id.address);
        n6=findViewById(R.id.email);
        n7=findViewById(R.id.uname);
        n8=findViewById(R.id.password);

        b1=(Button) findViewById(R.id.button);
        b2=(Button) findViewById(R.id.button2);

        contact = findViewById (R.id.contact);
        select = findViewById (R.id.select);

        select.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent in = new Intent (Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult (in, RESULT_PICK_CONTACT);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(reg.this,login.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=n1.getText().toString();
                String s2=n2.getText().toString();
                String s3=n3.getText().toString();
                String s4=n4.getText().toString();
                String s5=n5.getText().toString();
                String s6=n6.getText().toString();
                String s7=n7.getText().toString();
                String s8=n8.getText().toString();
                String s9=contact.getText().toString();







                if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")||s6.equals("")||s7.equals("")||s8.equals("")||s9.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are Empty",Toast.LENGTH_LONG).show();
                }
                else {
                    boolean chkemail=db.chkemail(s6);
                    boolean chkuname=db.chkuname(s7);

                    if(chkemail==true ) {

                        if(!(s4.trim().matches("^[0-9]{9}[vVxX]$")))
                        {
                            Toast.makeText(getApplicationContext(), "Invalid NIC", Toast.LENGTH_LONG).show();
                        }else{
                            if (chkuname == true) {
                                //checkDataEntered();
                                Boolean insert = db.insert(s1, s2, s3, s4,s5,s6,s7,s8,s9);
                                if (insert == true) {
                                    Toast.makeText(getApplicationContext(), "Registered Success", Toast.LENGTH_LONG).show();
                                }


                            } else {
                                Toast.makeText(getApplicationContext(), "Username Already Exist", Toast.LENGTH_LONG).show();
                            }

                        }


                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Email Already Exist",Toast.LENGTH_LONG).show();
                    }

                }

            }



        });
    }


    boolean isEmail(EditText text) {

        CharSequence n6 = text.getText().toString();
        return (!TextUtils.isEmpty(n6) && Patterns.EMAIL_ADDRESS.matcher(n6).matches());
    }

    void checkDataEntered(){

        if (isEmail(n6) == false){
            n6.setError("Enter Valid Email!");
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        if(resultCode==RESULT_OK)
        {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked (data);
                    break;
            }
        }
        else
        {
            Toast.makeText (this, "Failed To pick contact", Toast.LENGTH_SHORT).show ();
        }
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;

        try {
            String phoneNo = null;
            Uri uri = data.getData ();
            cursor = getContentResolver ().query (uri, null, null,null,null);
            cursor.moveToFirst ();
            int phoneIndex = cursor.getColumnIndex (ContactsContract.CommonDataKinds.Phone.NUMBER);

            phoneNo = cursor.getString (phoneIndex);

            contact.setText (phoneNo);


        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
}
