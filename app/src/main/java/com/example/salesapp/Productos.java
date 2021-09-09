package com.example.salesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import java.util.HashMap;
import java.util.Map;

public class Productos extends AppCompatActivity {

    private DatabaseReference mReference;

    ArrayList<HolderProducto> datos;
    ArrayList<HolderProducto> mostrar;
    ArrayList<HolderProducto> venta;
    ArrayList<Venta> venta_carga;
    private TextView costo;
    private EditText buscar, id_cliente, nombre_cliente;
    private Button but_buscar, btn_next;
    Adadtador adapta;
    Adadtador adaptaMostrar;
    Adadtador adapta2;
    float suma = 0;


    RecyclerView recycler, recycler2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        mReference = FirebaseDatabase.getInstance().getReference();

        but_buscar = findViewById(R.id.but_buscar);
        btn_next = findViewById(R.id.btn_next);
        buscar = findViewById(R.id.edit_buscar);
        costo = (TextView) findViewById(R.id.total);
        id_cliente = findViewById(R.id.id_cliente);
        nombre_cliente = findViewById(R.id.nombre_cliente);

        adapta = new Adadtador(null);
        adaptaMostrar = new Adadtador(null);
        ;
        adapta2 = new Adadtador(null);

        recycler = (RecyclerView) findViewById(R.id.idrecycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        recycler2 = (RecyclerView) findViewById(R.id.idrecycler2);
        recycler2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        datos = new ArrayList<>();
        venta = new ArrayList<>();
        mostrar = new ArrayList<>();
        venta_carga = new ArrayList<>();

        ver();

        btn_next.setOnClickListener(v -> generar_venta());
    }

    public void ver() {

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
                            costo.setText(String.valueOf(suma));

                            //Función buscar un elemento
                            but_buscar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mostrar = new ArrayList<>();
                                    int err = 0;
                                    for (int i = 0; i < datos.size(); i++) {
                                        HolderProducto dat = datos.get(i);

                                        if (dat.getNombre().equalsIgnoreCase(buscar.getText().toString())) {

                                            mostrar.add(new HolderProducto(dat.getId(), dat.getNombre(), dat.getCantidad(), dat.getPrecio()));

                                            adaptaMostrar = new Adadtador(mostrar);
                                            recycler.setAdapter(adaptaMostrar);

                                        } else {
                                            err++;
                                        }
                                    }

                                    if (err == datos.size()) {
                                        Toast.makeText(Productos.this, "No se encuentra el elemento", Toast.LENGTH_LONG).show();
                                        recycler.setAdapter(adapta);
                                        err = 0;
                                    }

                                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                                    adaptaMostrar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            venta.add(new HolderProducto(mostrar.get(recycler.getChildAdapterPosition(v)).getId(),
                                                    mostrar.get(recycler.getChildAdapterPosition(v)).getNombre(),
                                                    mostrar.get(recycler.getChildAdapterPosition(v)).getCantidad(),
                                                    mostrar.get(recycler.getChildAdapterPosition(v)).getPrecio()));

                                            suma += datos.get(recycler.getChildAdapterPosition(v)).getPrecio();
                                            costo.setText(String.valueOf(suma));
                                            adapta2 = new Adadtador(venta);
                                            recycler2.setAdapter(adapta2);

                                            adapta2.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    suma -= venta.get(recycler2.getChildAdapterPosition(v)).getPrecio();
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

                                    suma += datos.get(recycler.getChildAdapterPosition(v)).getPrecio();
                                    costo.setText(String.valueOf(suma));
                                    adapta2 = new Adadtador(venta);
                                    recycler2.setAdapter(adapta2);

                                    adapta2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            suma -= venta.get(recycler2.getChildAdapterPosition(v)).getPrecio();
                                            costo.setText(String.valueOf(suma));
                                            venta.remove(recycler2.getChildAdapterPosition(v));
                                            adapta2.notifyDataSetChanged();
                                        }
                                    });
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
    }

    public void generar_venta() {
        String id_c = id_cliente.getText().toString();
        String nombre_c = nombre_cliente.getText().toString();
        float cos = Float.parseFloat(costo.getText().toString());

        for (int i = 0; i < venta.size(); i++) {
            venta_carga.add(new Venta(venta.get(i).getId(), venta.get(i).getNombre(), nombre_c, id_c, venta.get(i).getPrecio(), cos, venta.get(i).getCantidad()));
        }

        guardar_venta();

        suma = 0;
        vaciar();
    }

    public void vaciar() {
        id_cliente.setText("");
        nombre_cliente.setText("");
        costo.setText(String.valueOf(suma));
        venta.clear();
        adapta2 = new Adadtador(venta);
        adapta2.notifyDataSetChanged();
        recycler2.setAdapter(adapta2);
        Toast.makeText(Productos.this, "La venta ha sido generada", Toast.LENGTH_SHORT).show();
    }

    public void guardar_venta() {

        for (int i = 0; i < venta_carga.size(); i++) {

            int id = venta_carga.get(i).getId();
            String nombre_v_p = venta_carga.get(i).getNombre();
            String id_c = venta_carga.get(i).getId_cliente();
            String nombre_c = venta_carga.get(i).getNombre_cliente();
            float v_uni = venta_carga.get(i).getValor_unitario();
            float v_total = venta_carga.get(i).getValor_total();
            int cant = venta_carga.get(i).getCantidad();

            Map<String, Object> DatosProducto = new HashMap<>();
            DatosProducto.put("id_venta", id);
            DatosProducto.put("nombre_venta_producto", nombre_v_p);
            DatosProducto.put("id_cliente", id_c);
            DatosProducto.put("nombre_cliente", nombre_c);
            DatosProducto.put("valor_unitario", v_uni);
            DatosProducto.put("valor_total", v_total);
            DatosProducto.put("cantidad", cant);

            mReference.child("ventas").push().setValue(DatosProducto);
        }
    }
}