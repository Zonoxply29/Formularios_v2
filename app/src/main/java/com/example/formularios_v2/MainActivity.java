package com.example.formularios_v2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.view.View;
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

        EditText editTextNombre = findViewById(R.id.editTextText8);
        EditText editTextEmail = findViewById(R.id.editTextText9);
        RadioButton radioSi = findViewById(R.id.radioButton);
        RadioButton radioNo = findViewById(R.id.radioButton2);
        Button buttonInscribirse = findViewById(R.id.button9);

        buttonInscribirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = editTextNombre.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String asistencia = radioSi.isChecked() ? "Sí" : (radioNo.isChecked() ? "No" : "No seleccionado");


                if (nombre.isEmpty() || email.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {

                    String mensajeFinal = "Nombre: " + nombre + "\nEmail: " + email + "\nAsistirá: " + asistencia;


                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Confirmación de Inscripción")
                            .setMessage(mensajeFinal)
                            .setPositiveButton("Aceptar", null)
                            .show();
                }
            }
        });
    }
}