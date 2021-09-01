package com.example.salesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Cliente extends AppCompatActivity {
    private DatabaseReference mRootReference;


    private EditText nombre,id,telefono;
    private Button guardar_cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        mRootReference = FirebaseDatabase.getInstance().getReference();

        nombre =(EditText)findViewById(R.id.edit_nombre_cliente);
        id =(EditText)findViewById(R.id.edit_documento);
        telefono =(EditText)findViewById(R.id.edit_telefono);
        guardar_cliente =(Button) findViewById(R.id.guardar_cliente);



        guardar_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ID= Integer.parseInt( id.getText().toString());
                String NOMBRE= nombre.getText().toString();
                String TELEFONO= telefono.getText().toString();


                Map<String, Object> DatosCliente = new HashMap<>();
                DatosCliente.put("id",ID);
                DatosCliente.put("nombre",NOMBRE);
                DatosCliente.put("telefono",TELEFONO);

                mRootReference.child("clientes").push().setValue(DatosCliente);


            }
        });

    }
}