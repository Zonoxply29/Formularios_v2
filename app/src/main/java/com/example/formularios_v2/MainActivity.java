package com.example.formularios_v2;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    private static final int MAX_CARACTERES = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextNombre = findViewById(R.id.editTextText6);
        EditText editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        EditText editTextComentario = findViewById(R.id.editTextTextMultiLine2);
        TextView textViewCounter = findViewById(R.id.textViewCounter);
        Button buttonEnviar = findViewById(R.id.button7);

        editTextComentario.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_CARACTERES)});

        editTextComentario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int caracteresActuales = s.length();
                textViewCounter.setText(caracteresActuales + "/" + MAX_CARACTERES);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = editTextNombre.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String comentario = editTextComentario.getText().toString().trim();


                if (nombre.isEmpty() || email.isEmpty() || comentario.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }


                String mensajeFinal = "Nombre: " + nombre + "\nEmail: " + email + "\nComentario: " + comentario;

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirmación de Comentario")
                        .setMessage(mensajeFinal)
                        .setPositiveButton("Aceptar", null)
                        .show();
            }
        });
    }
}