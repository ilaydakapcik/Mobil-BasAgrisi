package com.example.lenovo.mobilbasagrisi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lenovo.mobilbasagrisi.DbModel.KayitModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context,"kayit.db", null, 2);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        String sql= " CREATE TABLE kayit_table (Id INTEGER PRIMARY KEY AUTOINCREMENT , siddet TEXT ,bastarih TEXT,bittarih TEXT,tetikleyici TEXT,ilac TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS kayit_table");
        onCreate(db);
    }


    public  List<KayitModel> getKayit(){
        final List<KayitModel> array = new ArrayList<>();
        try{
            final Cursor cursor = db.rawQuery("Select  * from kayit_table",null);
            if(cursor !=null){
                for (cursor.moveToLast(); !cursor.isFirst(); cursor.moveToPrevious()){
                    final KayitModel model = new KayitModel();
                    model.setId(cursor.getInt(0));
                    model.setSiddet(cursor.getString(1));
                    model.setBasTarih(cursor.getString(2));
                    model.setSonTarih(cursor.getString(3));
                    model.setTetikleyici(cursor.getString(4));
                    model.setIlac(cursor.getString(5));
                    array.add(model);
                }
                cursor.close();
            }
            return array;
        }catch (Exception ex){
            return array;
        }
    }


    public boolean  kayitEkle(KayitModel model){
        final ContentValues values = new ContentValues();
        values.put("siddet",model.getSiddet());
        values.put("bastarih",model.getBasTarih());
        values.put("bittarih",model.getSonTarih());
        values.put("tetikleyici",model.getTetikleyici());
        values.put("ilac",model.getIlac());
        long  retVal =db.insert("kayit_table",null,values);
        return  retVal>0 ;
    }
}
