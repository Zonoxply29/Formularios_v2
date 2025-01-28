package com.example.formularios_v2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los elementos
        Spinner spinner = findViewById(R.id.spinner);
        Button buttonBuscar = findViewById(R.id.button5);

        // Opciones del Spinner
        String[] opciones = {"Selecciona un producto", "Electrónica", "Ropa", "Hogar"};

        // Adaptador para el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String seleccion = spinner.getSelectedItem().toString();


                if (seleccion.equals("Selecciona un producto")) {
                    Toast.makeText(MainActivity.this, "Por favor, selecciona un producto válido", Toast.LENGTH_SHORT).show();
                    return;
                }

                
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Búsqueda de Productos")
                        .setMessage("Has seleccionado la categoría: " + seleccion)
                        .setPositiveButton("Aceptar", null)
                        .show();
            }
        });
    }
}