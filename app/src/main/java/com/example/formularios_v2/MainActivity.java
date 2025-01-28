package com.example.formularios_v2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextNombre = findViewById(R.id.editTextText10);
        EditText editTextTelefono = findViewById(R.id.editTextPhone);
        EditText editTextMensaje = findViewById(R.id.editTextTextMultiLine3);
        Button buttonEnviar = findViewById(R.id.button10);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener valores de los EditText
                String nombre = editTextNombre.getText().toString().trim();
                String telefono = editTextTelefono.getText().toString().trim();
                String mensaje = editTextMensaje.getText().toString().trim();

                // Verificar que no estén vacíos
                if (nombre.isEmpty() || telefono.isEmpty() || mensaje.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Mostrar mensaje con los datos ingresados
                    String mensajeFinal = "Nombre: " + nombre + "\nTeléfono: " + telefono + "\nMensaje: " + mensaje;
                    Toast.makeText(MainActivity.this, mensajeFinal, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}