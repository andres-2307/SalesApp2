package com.example.salesapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        CardView btnProducto = findViewById(R.id.card_productos);
        CardView btnVenta = findViewById(R.id.card_ventas);
        CardView btnCliente = findViewById(R.id.card_clientes);
        CardView btnInventario = findViewById(R.id.card_inventario);


        btnProducto.setOnClickListener(v -> startActivity(new Intent(Inicio.this, Productos2.class)));

        btnVenta.setOnClickListener(v -> startActivity(new Intent(Inicio.this, Venta.class)));

        btnCliente.setOnClickListener(v -> startActivity(new Intent(Inicio.this, Cliente.class)));

        btnInventario.setOnClickListener(v -> startActivity(new Intent(Inicio.this, Inventario.class)));
    }

}