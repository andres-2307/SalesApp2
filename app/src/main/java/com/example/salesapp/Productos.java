package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Productos extends AppCompatActivity {
    ArrayList<Producto> datos;
    RecyclerView recycler;
    private Button but_buscar;

    private EditText buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        but_buscar =(Button)findViewById(R.id.but_buscar);
        buscar=(EditText) findViewById(R.id.edit_buscar);


        recycler=(RecyclerView) findViewById(R.id.idrecycler);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        datos= new ArrayList<>();

        datos.add(0, new Producto("azucar", "$ 5.000", "ID 1003","de caña", R.drawable.ic_azucar));
        datos.add(1, new Producto("azucar", "$ 7.000", "ID 1004","de coco", R.drawable.ic_azucar));



        but_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ArrayList<Producto> mostrar;
                mostrar = new ArrayList<>();

                    int i;
                    for (i=0;i<datos.size();) {
                        Producto dat = datos.get(i);

                        if (dat.getNombre().equals(buscar.getText().toString())) {

                            mostrar.add(new Producto(dat.getNombre(), dat.getPrecio(), dat.getId(),dat.getTipo(), dat.getFoto()));

                            Adadtador adata = new Adadtador(mostrar);
                            recycler.setAdapter(adata);

                        } else {
                            Toast.makeText(Productos.this, buscar.getText()+" NO SE ENCONTRO", Toast.LENGTH_SHORT).show();

                        }
                        i++;}

            }});



    }
}