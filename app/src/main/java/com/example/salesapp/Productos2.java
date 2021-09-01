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
    private EditText id,nombre,cantidad,precio;
    private Button guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos2);

        mRootReference = FirebaseDatabase.getInstance().getReference();

        id =(EditText)findViewById(R.id.id_producto);
        nombre =(EditText)findViewById(R.id.nombre_producto);
        cantidad =(EditText)findViewById(R.id.cantidad_producto);
        precio=(EditText)findViewById(R.id.precio_producto);
        guardar=(Button) findViewById(R.id.guardar_producto);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ID= Integer.parseInt( id.getText().toString());
                String NOMBRE= nombre.getText().toString();
                int CANTIDAD= Integer.parseInt(cantidad.getText().toString());
                float PRECIO= Float.parseFloat( precio.getText().toString());


                Map<String, Object> DatosProducto = new HashMap<>();
                DatosProducto.put("id",ID);
                DatosProducto.put("nombre",NOMBRE);
                DatosProducto.put("cantidad","cantidad: "+CANTIDAD);
                DatosProducto.put("precio",PRECIO);


                mRootReference.child("producto").push().setValue(DatosProducto);


            }
        });



    }
}