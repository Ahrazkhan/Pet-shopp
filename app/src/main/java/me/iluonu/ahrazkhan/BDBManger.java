package me.iluonu.ahrazkhan;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class BDBManger extends SQLiteOpenHelper {
    private static final String dbname = "BASKET";

    public BDBManger(@Nullable Context context) {
        super(context, dbname, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry = "create table Basket ( id integer primary key autoincrement, productName text, price text , img text)";
        sqLiteDatabase.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String qry = "DROP TABLE IF EXISTS Basket";
        sqLiteDatabase.execSQL(qry);
        onCreate(sqLiteDatabase);
    }

    public void clearAllDatabase(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ "Basket");
    }

    public void AddRecord(String productName, String price, String Img) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("productName", productName);
        cv.put("price", price);
        cv.put("img", Img);

        db.insert("Basket", null, cv);


    }

    public Cursor readalldata() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Basket", null);

        return cursor;
    }




    public void Card_Delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
       @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from Basket where id=?", new String[]{id});

       if (cursor.getCount() > 0) {
      db.delete("Basket","id=?",new String[]{id});
      db.close();

       }
    }


}
