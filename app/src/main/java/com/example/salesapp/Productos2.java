package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Productos2 extends AppCompatActivity {

    private DatabaseReference mRootReference;
    private EditText id_field, nombre_field, cantidad_field, precio_field;
    private Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos2);

        mRootReference = FirebaseDatabase.getInstance().getReference();

        id_field = findViewById(R.id.id_producto);
        nombre_field = findViewById(R.id.nombre_producto);
        cantidad_field = findViewById(R.id.cantidad_producto);
        precio_field = findViewById(R.id.precio_producto);
        guardar = findViewById(R.id.guardar_producto);


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

                vaciar_campos();

                //Ocultar Teclado
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });
    }

    public void vaciar_campos(){
        this.id_field.setText("");
        this.nombre_field.setText("");
        this.cantidad_field.setText("");
        this.precio_field.setText("");
        Snackbar snackbar = Snackbar.make(findViewById(R.id.snackbar), "Producto guardado", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}