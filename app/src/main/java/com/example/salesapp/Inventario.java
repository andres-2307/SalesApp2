package com.example.salesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Inventario extends AppCompatActivity {

    private DatabaseReference mReference;

    ArrayList<HolderProducto> datos;
    private RecyclerView recycler;
    Adadtador adapta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        mReference= FirebaseDatabase.getInstance().getReference();

        Button btn_regresar = findViewById(R.id.btn_back);

        recycler = findViewById(R.id.recycler_inventario);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        ver();

        btn_regresar.setOnClickListener(e -> startActivity(new Intent(Inventario.this, Inicio.class)));

    }

    public void ver(){
        datos = new ArrayList<>();

        mReference.child("producto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(final DataSnapshot Snapshot: dataSnapshot.getChildren()){

                    mReference.child("producto").child(Snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                            HolderProducto hol = Snapshot.getValue(HolderProducto.class);

                            String nombre = hol.getNombre();
                            int id = hol.getId();
                            float precio = hol.getPrecio();
                            int cantidad = hol.getCantidad();

                            datos.add(new HolderProducto(id,nombre,cantidad,precio));
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
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {}
                    });
                }
            }

            @Override
            public  void  onCancelled ( DatabaseError  databaseError ) {}
        });




    }


}