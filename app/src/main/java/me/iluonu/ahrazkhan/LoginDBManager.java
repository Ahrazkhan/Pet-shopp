package me.iluonu.ahrazkhan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class LoginDBManager extends SQLiteOpenHelper {
    private static final String dbname = "Account";

    public LoginDBManager(@Nullable Context context) {
        super(context, dbname, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry = "create table Account ( id integer primary key autoincrement, username text,password text )";
        sqLiteDatabase.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String qry = "DROP TABLE IF EXISTS Account";
        sqLiteDatabase.execSQL(qry);
        onCreate(sqLiteDatabase);
    }

    public void clearAllDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + "Account");
    }

    public String AddRecord(String UserName, String Password) {
        String result;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("username", UserName);
        cv.put("password", Password);
        long r = db.insert("Account", null, cv);
        if (r == -1) {
            result = "Login failed";
        } else {
            result = "Logged in Successfully";
        }
        return result;

    }

    public Cursor readalldata() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Account", null);

        return cursor;
    }

    String CheckUsernameAndPassword(String UserName, String Password) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("select * from Account where username = ? and password = ?", new String[]{UserName, Password});
        if (cursor.getCount() > 0) {
            return "LogIn Successful";
        } else {
            return "Check UserName And Password";
        }
    }


}
