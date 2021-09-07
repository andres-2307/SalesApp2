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
import android.widget.Toast;

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
    private TextView costo;
    private EditText buscar;
    private Button  but_buscar;
    Adadtador adapta;
    Adaptador2 adapta2;
    float suma;


    RecyclerView recycler,recycler2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        mReference= FirebaseDatabase.getInstance().getReference();

        but_buscar = findViewById(R.id.but_buscar);
         buscar= findViewById(R.id.edit_buscar);
         costo=(TextView) findViewById(R.id.total);

        recycler=(RecyclerView) findViewById(R.id.idrecycler);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


        recycler2=(RecyclerView) findViewById(R.id.idrecycler2);
        recycler2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


        datos = new ArrayList<>();
        venta = new ArrayList<>();
        mostrar = new ArrayList<>();

        ver();





    }

    public void ver(){

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
                            costo.setText(String.valueOf(suma));

                            but_buscar.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    mostrar = new ArrayList<>();
                                    int i;
                                    for (i=0;i<datos.size();) {
                                        HolderProducto dat = datos.get(i);

                                        if (dat.getNombre().equals(buscar.getText().toString())) {

                                            mostrar.add(new HolderProducto(dat.getId(),dat.getNombre(), dat.getCantidad(), dat.getPrecio()));

                                            adapta = new Adadtador(mostrar);
                                            recycler.setAdapter(adapta);
                                        }

                                        i++;}


                                    adapta.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {


                                            venta.add(new HolderProducto(mostrar.get(recycler.getChildAdapterPosition(v)).getId(),
                                                    mostrar.get(recycler.getChildAdapterPosition(v)).getNombre(),
                                                    mostrar.get(recycler.getChildAdapterPosition(v)).getCantidad(),
                                                    mostrar.get(recycler.getChildAdapterPosition(v)).getPrecio()));

                                            suma= suma+datos.get(recycler.getChildAdapterPosition(v)).getPrecio();
                                            costo.setText(String.valueOf(suma));
                                            adapta2 = new Adaptador2(venta);
                                            recycler2.setAdapter(adapta2);

                                            adapta2.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                    suma= suma-venta.get(recycler2.getChildAdapterPosition(v)).getPrecio();
                                                    costo.setText(String.valueOf(suma));
                                                    venta.remove(recycler2.getChildAdapterPosition(v));
                                                    adapta2.notifyDataSetChanged();

                                                }
                                            });

                                        }
                                    });
                                }
                            });


                            adapta.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                    venta.add(new HolderProducto(datos.get(recycler.getChildAdapterPosition(v)).getId(),
                                            datos.get(recycler.getChildAdapterPosition(v)).getNombre(),
                                            datos.get(recycler.getChildAdapterPosition(v)).getCantidad(),
                                            datos.get(recycler.getChildAdapterPosition(v)).getPrecio()));

                                    suma= suma+datos.get(recycler.getChildAdapterPosition(v)).getPrecio();
                                    costo.setText(String.valueOf(suma));
                                    adapta2 = new Adaptador2(venta);
                                    recycler2.setAdapter(adapta2);

                                    adapta2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {


                                            suma= suma-venta.get(recycler2.getChildAdapterPosition(v)).getPrecio();
                                            costo.setText(String.valueOf(suma));
                                            venta.remove(recycler2.getChildAdapterPosition(v));
                                            adapta2.notifyDataSetChanged();

                                        }
                                    });




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