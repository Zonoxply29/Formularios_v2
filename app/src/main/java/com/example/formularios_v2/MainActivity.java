package com.example.formularios_v2;

import android.os.Bundle;
import android.util.Patterns;
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


        EditText editTextNombre = findViewById(R.id.editTextText);
        EditText editTextEmail = findViewById(R.id.editTextText2);
        Button buttonRegistrar = findViewById(R.id.button);


        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = editTextNombre.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();

                // Validaciones
                if (nombre.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, ingresa tu nombre", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(MainActivity.this, "Por favor, ingresa un email válido", Toast.LENGTH_SHORT).show();
                    return;
                }


                String mensajeFinal = "Nombre: " + nombre + "\nEmail: " + email;

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Registro Exitoso")
                        .setMessage(mensajeFinal)
                        .setPositiveButton("Aceptar", null)
                        .show();
            }
        });
    }
}