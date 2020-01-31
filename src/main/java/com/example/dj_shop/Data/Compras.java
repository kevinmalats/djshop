package com.example.dj_shop.Data;

import com.example.dj_shop.objects.Producto;

import java.util.ArrayList;
import java.util.UUID;

public class Compras {
    private String id;
    private String fecha;
    private boolean estado;
    private ArrayList<Producto> lisproduct;

    public Compras( String nombre, boolean comprado) {
        this.id = UUID.randomUUID().toString();
        this.fecha = nombre;
        this.estado = comprado;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return fecha;
    }

    public boolean isComprado() {
        return estado;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.fecha = nombre;
    }

    public void setComprado(boolean comprado) {
        this.estado = comprado;
    }
}
