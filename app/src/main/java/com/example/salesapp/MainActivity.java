package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button iniciar = findViewById(R.id.btn_login);


        iniciar.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Inicio.class)));
    }
}