package com.example.dj_shop.objects;

import android.content.ContentValues;

import com.example.dj_shop.Data.ProductosContract;

import java.util.UUID;

public class Producto {
    private  String nombre;
    private String id;
    private boolean comprado;
    private int precio;
    public Producto( String nombre, boolean comprado) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.comprado = comprado;
    }

    public String getNombre() {
        return nombre;
    }

    public Producto(String nombre) {
        this.nombre = nombre;
    }

    public Producto() {
    }
    public String getId() {
        return id;
    }



    public boolean isComprado() {
        return comprado;
    }

    public void setId(String id) {
        this.id = id;
    }



    public void setComprado(boolean comprado) {
        this.comprado = comprado;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(ProductosContract.ProductosEntry.ID, this.id);
        values.put(ProductosContract.ProductosEntry.NOMBRE, this.nombre);
        values.put(String.valueOf(ProductosContract.ProductosEntry.COMPRADO), true);

        return values;
    }

}
