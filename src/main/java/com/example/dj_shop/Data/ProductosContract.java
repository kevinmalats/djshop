package com.example.dj_shop.Data;

import android.content.ContentValues;
import android.provider.BaseColumns;

public class ProductosContract {
    public static abstract class ProductosEntry implements BaseColumns {
        public static final String TABLE_NAME ="lawyer";

        public static final String ID = "id";
        public static final String NOMBRE = "name";
        public static final String COMPRADO = "false";
        public  static final  String PRECIO= "precio";

    }

}
