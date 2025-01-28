package com.example.formularios_v2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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

        EditText editTextNombre = findViewById(R.id.editTextText3);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        EditText editTextComentario = findViewById(R.id.editTextTextMultiLine);
        Button buttonEnviar = findViewById(R.id.button2);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener valores ingresados
                String nombre = editTextNombre.getText().toString().trim();
                float rating = ratingBar.getRating();
                String comentario = editTextComentario.getText().toString().trim();

                // Validaciones
                if (nombre.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, ingresa tu nombre", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rating == 0) {
                    Toast.makeText(MainActivity.this, "Por favor, selecciona un nivel de satisfacción", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convertir el rating en estrellas visuales
                String estrellas = "";
                for (int i = 0; i < (int) rating; i++) {
                    estrellas += "⭐";
                }

                // Mostrar los datos en un cuadro de diálogo
                String mensajeFinal = "Nombre: " + nombre +
                        "\nNivel de Satisfacción: " + estrellas +
                        "\nComentarios: " + (comentario.isEmpty() ? "Sin comentarios" : comentario);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirmación de Encuesta")
                        .setMessage(mensajeFinal)
                        .setPositiveButton("Aceptar", null)
                        .show();
            }
        });
    }
}