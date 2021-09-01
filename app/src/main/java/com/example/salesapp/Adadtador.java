package com.example.salesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adadtador extends RecyclerView.Adapter<Adadtador.ViewHolder> implements View.OnClickListener {


    ArrayList<Producto> datos;
    private View.OnClickListener listener;

    public Adadtador(ArrayList<Producto> dato) {
        this.datos = dato;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_lista, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nombre.setText(datos.get(position).getNombre());
        holder.id.setText(datos.get(position).getId());
        holder.precio.setText(datos.get(position).getPrecio());
        holder.cantidad.setText(datos.get(position).getCantidad());



    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, id, precio, cantidad;
        ImageView foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.tex_nombre);
            id = (TextView) itemView.findViewById(R.id.tex_id);
            precio = (TextView) itemView.findViewById(R.id.tex_precio);
            cantidad = (TextView) itemView.findViewById(R.id.tex_cantidad);

        }


    }

}
