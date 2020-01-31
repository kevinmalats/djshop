package com.example.dj_shop.adpater;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.example.dj_shop.R;

public class ProductoCursorAdapter extends CursorAdapter {
    public ProductoCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.item_product, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
