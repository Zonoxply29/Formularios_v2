package com.example.formularios_v2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        EditText editTextColorFavorito = findViewById(R.id.editTextText4);
        EditText editTextComidaFavorita = findViewById(R.id.editTextText5);
        Button buttonEnviar = findViewById(R.id.button6);
        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String colorFavorito = editTextColorFavorito.getText().toString().trim();
                String comidaFavorita = editTextComidaFavorita.getText().toString().trim();

                // Validaciones
                if (colorFavorito.isEmpty() || comidaFavorita.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Mostrar los datos en un cuadro de diálogo
                String mensajeFinal = "Color Favorito: " + colorFavorito +
                        "\nComida Favorita: " + comidaFavorita;

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Encuesta de Preferencias")
                        .setMessage(mensajeFinal)
                        .setPositiveButton("Aceptar", null)
                        .show();
            }
        });
    }
}