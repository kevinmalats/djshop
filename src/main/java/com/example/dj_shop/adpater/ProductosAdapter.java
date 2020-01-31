package com.example.dj_shop.adpater;


import android.util.Log;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dj_shop.R;
import com.example.dj_shop.objects.Producto;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;


public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {
    public ArrayList<Producto> lista_vehiculo;

    public ProductosAdapter(ArrayList<Producto> lista) {
        lista_vehiculo = lista;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView marca, modelo, placa, fecha_registro;
        private CheckBox checket;

        public ViewHolder(View itemView) {
            super(itemView);
            placa = itemView.findViewById(R.id.txtNombre);
         //   checket= itemView.findViewById(R.id.checkItem);
         /*   checket.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                 public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                    Log.i("check", "checked");
                }
              }
            );*/


        }

        @Override
        public void onClick(View view) {
            //
        }
    }

    public ProductosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductosAdapter.ViewHolder holder, int position) {
        holder.placa.setText(lista_vehiculo.get(position).getNombre());


    }

    @Override
    public int getItemCount() {
        return lista_vehiculo.size();
    }
}
