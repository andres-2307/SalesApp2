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


        AdapterProductos adapter = new AdapterProductos(productos);
        recyclerViewProductos.setAdapter(adapter);

        siguiente.setOnClickListener(v -> startActivity(new Intent(Venta.this, Cliente_Venta.class)));

    }
}