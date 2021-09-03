package com.example.salesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador2 extends RecyclerView.Adapter<Adadtador.ViewHolder> implements View.OnClickListener {


    ArrayList<HolderProducto> datos;
    private View.OnClickListener listener;

    public Adaptador2(ArrayList<HolderProducto> dato) {
        this.datos = dato;
    }

    @NonNull
    @Override
    public Adadtador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_lista,null,false);
        view.setOnClickListener(this);
        return new Adadtador.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adadtador.ViewHolder holder, int position) {
        holder.nombre.setText(datos.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) { listener.onClick(v); }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre,id,precio,tipo;
        ImageView foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre=(TextView)itemView.findViewById(R.id.tex_nombre);
            id=(TextView)itemView.findViewById(R.id.tex_id);
            precio=(TextView)itemView.findViewById(R.id.tex_precio);
            tipo=(TextView)itemView.findViewById(R.id.tex_cantidad);
        }
    }

}
