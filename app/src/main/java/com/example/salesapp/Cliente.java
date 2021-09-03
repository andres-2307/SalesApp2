package com.example.salesapp;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        mRootReference = FirebaseDatabase.getInstance().getReference();

        EditText nombre_field = findViewById(R.id.edit_nombre_cliente);
        EditText id_field = findViewById(R.id.edit_documento);
        EditText telefono_field = findViewById(R.id.edit_telefono);
        Button guardar_cliente = findViewById(R.id.guardar_cliente);

        guardar_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt( id_field.getText().toString());
                String nombre = nombre_field.getText().toString();
                String telefono = telefono_field.getText().toString();

                Map<String, Object> DatosCliente = new HashMap<>();
                DatosCliente.put("id", id);
                DatosCliente.put("nombre", nombre);
                DatosCliente.put("telefono", telefono);

                mRootReference.child("clientes").push().setValue(DatosCliente);
            }
        });
    }
}