package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Venta extends AppCompatActivity {

    private ArrayList<Producto> productos;
    private RecyclerView recyclerViewProductos;
    private Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        siguiente = findViewById(R.id.btn_next);

        recyclerViewProductos = findViewById(R.id.recycler);
        recyclerViewProductos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


        productos = new ArrayList<>();

        productos.add(new Producto("Azucar", "1.800", "0", "de caña", R.drawable.ic_azucar));
        productos.add(new Producto("Carne", "10.000", "1", "Vaca", R.drawable.ic_carne));
        productos.add(new Producto("Leche", "1.800", "2", "Vaca", R.drawable.ic_leche));


        AdapterProductos adapter = new AdapterProductos(productos);
        recyclerViewProductos.setAdapter(adapter);

        siguiente.setOnClickListener(v -> startActivity(new Intent(Venta.this, Cliente_Venta.class)));

    }
}