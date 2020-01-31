package com.example.dj_shop.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dj_shop.objects.Producto;

public class ProductosHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Compras.db";
    private Producto producto;

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ProductosHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + ProductosContract.ProductosEntry.TABLE_NAME + " ("
                + ProductosContract.ProductosEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ProductosContract.ProductosEntry.NOMBRE + " TEXT NOT NULL,"
                + ProductosContract.ProductosEntry.COMPRADO + " BOOLEAN NOT NULL" +
                ","

                + "UNIQUE (" + ProductosContract.ProductosEntry._ID + "))");

        //mockLawyer(sqLiteDatabase,producto);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
