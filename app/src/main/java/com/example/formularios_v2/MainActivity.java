package com.example.formularios_v2;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
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

        EditText editTextNumeroTarjeta = findViewById(R.id.editTextNumberSigned);
        EditText editTextFechaExpiracion = findViewById(R.id.editTextDate);
        EditText editTextCVV = findViewById(R.id.editTextNumber4);
        Button buttonPagar = findViewById(R.id.button8);

        editTextFechaExpiracion.setHint("MM/YY");
        editTextFechaExpiracion.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextFechaExpiracion.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});

        editTextFechaExpiracion.addTextChangedListener(new TextWatcher() {
            private boolean isFormatting;
            private boolean deletingSlash;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                deletingSlash = count > after && s.charAt(start) == '/';
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isFormatting || deletingSlash) {
                    return;
                }

                isFormatting = true;
                StringBuilder formatted = new StringBuilder(s);


                if (formatted.length() == 2 && !formatted.toString().contains("/")) {
                    formatted.append("/");
                }


                if (formatted.length() > 5) {
                    formatted.delete(5, formatted.length());
                }

                editTextFechaExpiracion.setText(formatted);
                editTextFechaExpiracion.setSelection(formatted.length());
                isFormatting = false;
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        
        editTextNumeroTarjeta.setHint("1234-5678-9101-1112");
        editTextNumeroTarjeta.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextNumeroTarjeta.setFilters(new InputFilter[]{new InputFilter.LengthFilter(19)});

        editTextNumeroTarjeta.addTextChangedListener(new TextWatcher() {
            private boolean isFormatting;
            private boolean deletingHyphen;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                deletingHyphen = count > after && s.charAt(start) == '-';
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isFormatting || deletingHyphen) return;

                isFormatting = true;
                String cleanText = s.toString().replace("-", "");

                StringBuilder formatted = new StringBuilder();
                for (int i = 0; i < cleanText.length(); i++) {
                    formatted.append(cleanText.charAt(i));
                    if ((i + 1) % 4 == 0 && i + 1 < cleanText.length()) {
                        formatted.append("-");
                    }
                }

                editTextNumeroTarjeta.setText(formatted);
                editTextNumeroTarjeta.setSelection(formatted.length());
                isFormatting = false;
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        editTextCVV.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});


        buttonPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String numeroTarjeta = editTextNumeroTarjeta.getText().toString().trim();
                String fechaExpiracion = editTextFechaExpiracion.getText().toString().trim();
                String cvv = editTextCVV.getText().toString().trim();


                if (numeroTarjeta.length() != 19) {
                    Toast.makeText(MainActivity.this, "El número de tarjeta debe tener 16 dígitos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!fechaExpiracion.matches("^(0[1-9]|1[0-2])/[0-9]{2}$")) {
                    Toast.makeText(MainActivity.this, "Formato de fecha inválido (debe ser MM/YY)", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (cvv.length() != 3) {
                    Toast.makeText(MainActivity.this, "El CVV debe tener 3 dígitos", Toast.LENGTH_SHORT).show();
                    return;
                }


                String mensajeFinal = "Número de Tarjeta: " + "**** **** **** " + numeroTarjeta.substring(14) +
                        "\nFecha de Expiración: " + fechaExpiracion +
                        "\nCVV: ***";

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirmación de Pago")
                        .setMessage(mensajeFinal)
                        .setPositiveButton("Aceptar", null)
                        .show();
            }
        });
    }
}