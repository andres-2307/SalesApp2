package com.example.salesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterProductos extends RecyclerView.Adapter<AdapterProductos.ViewHolder> implements View.OnClickListener{

    private ArrayList<HolderProducto> productos;
    private View.OnClickListener listener;

    public AdapterProductos(ArrayList<HolderProducto> productos){
        this.productos = productos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.productos_recycler,null,false);
        view.setOnClickListener((View.OnClickListener) this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name_producto.setText(productos.get(position).getNombre());

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name_producto, text_precio;
        private ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_producto = itemView.findViewById(R.id.name_producto);
            text_precio = itemView.findViewById(R.id.text_precio);
            img = itemView.findViewById(R.id.img);
        }
    }
}
