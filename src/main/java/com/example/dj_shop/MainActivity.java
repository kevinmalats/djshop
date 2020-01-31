package com.example.dj_shop;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.afollestad.materialdialogs.DialogAction;
import com.example.dj_shop.Data.ProductosContract;
import com.example.dj_shop.Data.ProductosHelper;
import com.example.dj_shop.adpater.ProductosAdapter;
import com.example.dj_shop.objects.Producto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.afollestad.materialdialogs.MaterialDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MaterialDialog form_vehiculo;
    ArrayList<Producto> listProduct;
    RecyclerView recPro;
    ProductosAdapter adapterProd;
    EditText producto;
    Button btnSave;
    ProductosHelper dbPro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recPro= findViewById(R.id.listCompras);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        listProduct=new ArrayList<Producto>();

        FloatingActionButton fab = findViewById(R.id.fab);
        adapterProd=new ProductosAdapter(listProduct);
         dbPro= new ProductosHelper(getApplicationContext());
        recPro.setLayoutManager(manager);
        recPro.setAdapter(adapterProd);
        getLista();
        btnSave=findViewById(R.id.btnsaveLista);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                dialogo();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               saveLista();
            }
        });
    }
    private  void saveLista(){

        SQLiteDatabase db = dbPro.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (Producto producto:listProduct){

            values.put(ProductosContract.ProductosEntry.ID, producto.getId());
            values.put(ProductosContract.ProductosEntry.NOMBRE, producto.getNombre());
            values.put(ProductosContract.ProductosEntry.COMPRADO, true);
            long newRowId = db.insert(ProductosContract.ProductosEntry.TABLE_NAME, null, values);
        }


    }
    private  void getLista(){
        SQLiteDatabase db = dbPro.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                ProductosContract.ProductosEntry.NOMBRE,
                ProductosContract.ProductosEntry.COMPRADO
        };

        String sortOrder =
                ProductosContract.ProductosEntry.NOMBRE + " DESC";
        Cursor cursor = db.query(
                ProductosContract.ProductosEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(ProductosContract.ProductosEntry._ID));
            String nombre=cursor.getString(
                    cursor.getColumnIndexOrThrow(ProductosContract.ProductosEntry.NOMBRE));
            String comprado=cursor.getString(
                    cursor.getColumnIndexOrThrow(ProductosContract.ProductosEntry.COMPRADO));
            boolean compradoB=Boolean.getBoolean(comprado);
            Producto producto= new Producto(nombre,compradoB);
          listProduct.add(producto);
        }
        adapterProd.notifyDataSetChanged();
        cursor.close();
    }
private  void dialogo (){
    final boolean[] success = {true};

    form_vehiculo = new MaterialDialog.Builder(MainActivity.this)

            .autoDismiss(false)
            .onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                     producto= dialog.getCustomView().findViewById(R.id.txtDialogNombre);

                      /*  if(modelo.getText().toString().equalsIgnoreCase("")){
                            modelo.setError("Seleccione Modelo de Vehiculo");
                            modelo.requestFocus();
                            success[0] = false;

                        }else{
                            success[0] = true;
                            nameModel=modelo.getText().toString();
                        }*/

                      if(producto.getText().toString().equalsIgnoreCase("")) {
                        producto.setError("Seleccione Marca de Vehiculo");
                        producto.requestFocus();
                        success[0] = false;
                    } else {
                        Toast.makeText(getApplicationContext(), "else", Toast.LENGTH_SHORT);
                        success[0] = true;

                    }
/*
                        if(placa.getText().toString().equalsIgnoreCase("")){
                            placa.setError("Digite Placa de Vehiculo");
                            placa.requestFocus();
                            success[0] = false;
                        }else{
                            success[0] = true;
                            namePlaca=placa.getText().toString();
                        }*/

                    // guardarVehiculo(dialog.getCustomView());
                    if (success[0]) {

                        dialog.dismiss();
                    } else {
                        Toast.makeText(MainActivity.this, "Escriba un nombre", Toast.LENGTH_SHORT).show();
                    }
                    addProduct();
                }
            })

            .onNegative(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    dialog.dismiss();
                }
            })

            .customView(R.layout.dialog_add_item, true)
            .positiveText("Guardar")
            .negativeText("Cancelar")
            //.build();
            .build();


    View customView = form_vehiculo.getCustomView();
    // modelo = customView.findViewById(R.id.at_modelo);
    //modelo.setError(null);
    form_vehiculo.show();

    }
    private  void addProduct(){
        listProduct.add(new Producto(producto.getText()+"", true));
        adapterProd.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
