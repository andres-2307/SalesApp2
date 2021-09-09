package com.example.salesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mRootReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mRootReference = FirebaseDatabase.getInstance().getReference();

        Button iniciar = findViewById(R.id.btn_login);

        EditText usuario = findViewById(R.id.field_user);
        EditText pass = findViewById(R.id.field_pass);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usuario.getText().toString();
                String passw = pass.getText().toString();
                if (!user.isEmpty() && !passw.isEmpty()) {
                    startActivity(new Intent(MainActivity.this, Inicio.class));
                }else{
                    Toast.makeText(MainActivity.this, "El usuario no existe", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*public void ver() {
        datos = new ArrayList<>();

        mReference.child("producto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (final DataSnapshot Snapshot : dataSnapshot.getChildren()) {

                    mReference.child("producto").child(Snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                            HolderProducto hol = Snapshot.getValue(HolderProducto.class);

                            String nombre = hol.getNombre();
                            int id = hol.getId();
                            float precio = hol.getPrecio();
                            int cantidad = hol.getCantidad();

                            datos.add(new HolderProducto(id, nombre, cantidad, precio));
                            adapta = new Adadtador(datos);
                            recycler.setAdapter(adapta);

                            adapta.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    datos.remove(recycler.getChildAdapterPosition(v));
                                    adapta.notifyDataSetChanged();
                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }*/

}