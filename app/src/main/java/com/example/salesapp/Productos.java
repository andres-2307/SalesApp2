package com.example.salesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class Productos extends AppCompatActivity {

    private DatabaseReference mReference;

    ArrayList<HolderProducto> datos;
    ArrayList<HolderProducto> mostrar;
    ArrayList<HolderProducto> venta;
    RecyclerView recycler,recycler2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        mReference= FirebaseDatabase.getInstance().getReference();

        Button but_buscar = findViewById(R.id.but_buscar);
        EditText buscar= findViewById(R.id.edit_buscar);
        TextView costo=(TextView) findViewById(R.id.total);

        recycler=(RecyclerView) findViewById(R.id.idrecycler);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


        recycler2=(RecyclerView) findViewById(R.id.idrecycler2);
        recycler2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        ver();

        but_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });
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
                            Adadtador adapta = new Adadtador(datos);
                            recycler.setAdapter(adapta);
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