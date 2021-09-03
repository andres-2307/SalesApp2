package com.example.salesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Cliente extends AppCompatActivity {

    private DatabaseReference mRootReference;
    private EditText nombre_field, id_field, telefono_field;
    private Button guardar_cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        mRootReference = FirebaseDatabase.getInstance().getReference();

        nombre_field = findViewById(R.id.edit_nombre_cliente);
        id_field = findViewById(R.id.edit_documento);
        telefono_field = findViewById(R.id.edit_telefono);
        guardar_cliente = findViewById(R.id.guardar_cliente);

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
        this.telefono_field.setText("");
        Snackbar snackbar = Snackbar.make(findViewById(R.id.snackbar), "Cliente guardado", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}