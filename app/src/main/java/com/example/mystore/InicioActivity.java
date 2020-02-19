package com.example.mystore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Button registrarProductos =findViewById(R.id.btnRegistrarProds);

        Button revisarInventario = findViewById(R.id.btnRevisarInventario);

        registrarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity = new Intent(InicioActivity.this, RegistroManualActivity.class);
                startActivity(registerActivity);

            }
        });



        revisarInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent revisarActivity = new Intent(InicioActivity.this, ListarProductosActivity.class);
                startActivity(revisarActivity);


            }
        });
    }
}
