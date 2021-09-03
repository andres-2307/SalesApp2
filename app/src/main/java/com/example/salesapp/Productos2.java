package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Productos2 extends AppCompatActivity {

    private DatabaseReference mRootReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos2);

        mRootReference = FirebaseDatabase.getInstance().getReference();

        EditText id_field = findViewById(R.id.id_producto);
        EditText nombre_field = findViewById(R.id.nombre_producto);
        EditText cantidad_field = findViewById(R.id.cantidad_producto);
        EditText precio_field = findViewById(R.id.precio_producto);
        Button guardar = findViewById(R.id.guardar_producto);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt(id_field.getText().toString());
                String nombre = nombre_field.getText().toString();
                int cantidad = Integer.parseInt(cantidad_field.getText().toString());
                float precio = Float.parseFloat( precio_field.getText().toString());

                Map<String, Object> DatosProducto = new HashMap<>();
                DatosProducto.put("id", id);
                DatosProducto.put("nombre", nombre);
                DatosProducto.put("cantidad", cantidad);
                DatosProducto.put("precio", precio);

                mRootReference.child("producto").push().setValue(DatosProducto);

            }
        });
    }
}