package  com.AndroidProject.womenSafety;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StaySafe.db";
    private static final int DATABASE_VERSION = 1;


    public  DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE users (name text, age text,phoneno text, nic text PRIMARY KEY,address text, email text,username text,password text, contact text)");


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");


    }


    public boolean insert (String name,String age, String phoneno,String nic, String address, String email, String username, String password, String contact) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nic", nic);
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phoneno", phoneno);
        contentValues.put("age", age);
        contentValues.put("address", address);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("contact", contact);
        long ins = db.insert("users", null, contentValues);
        if (ins == -1) return false;
        else return true;
    }

    public boolean chkemail(String email){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from users where email=?",new String[]{email});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    public boolean chkuname(String username){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from users where username=?",new String[]{username});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    public boolean userpassword(String username,String password){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery("select * from users where username=? and password=? ",new String[]{username,password});
        if(cursor.getCount()>0)return true;
        else return false;
    }

    public Cursor getData(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select contact  from users where username =?  ", new String[]{username} );
        return cursor;
    }
}
