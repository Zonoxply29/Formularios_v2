package com.example.formularios_v2;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFechaEntrada, editTextFechaSalida, editTextNumeroPersonas;
    private Calendar calendarEntrada, calendarSalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextFechaEntrada = findViewById(R.id.editTextDate);
        editTextFechaSalida = findViewById(R.id.editTextDate2);
        editTextNumeroPersonas = findViewById(R.id.editTextNumber2);
        Button buttonReservar = findViewById(R.id.button3);


        calendarEntrada = Calendar.getInstance();
        calendarSalida = Calendar.getInstance();


        editTextFechaEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDatePicker(editTextFechaEntrada, calendarEntrada);
            }
        });


        editTextFechaSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDatePicker(editTextFechaSalida, calendarSalida);
            }
        });


        buttonReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarYReservar();
            }
        });
    }

    private void abrirDatePicker(final EditText editText, final Calendar calendar) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        editText.setText(dateFormat.format(calendar.getTime()));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }


    private void validarYReservar() {
        String fechaEntrada = editTextFechaEntrada.getText().toString().trim();
        String fechaSalida = editTextFechaSalida.getText().toString().trim();
        String numeroPersonasStr = editTextNumeroPersonas.getText().toString().trim();

        if (fechaEntrada.isEmpty() || fechaSalida.isEmpty()) {
            Toast.makeText(this, "Por favor, selecciona las fechas de entrada y salida", Toast.LENGTH_SHORT).show();
            return;
        }

        if (calendarSalida.before(calendarEntrada)) {
            Toast.makeText(this, "La fecha de salida debe ser posterior a la de entrada", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validación del número de personas
        if (numeroPersonasStr.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa el número de personas", Toast.LENGTH_SHORT).show();
            return;
        }

        int numeroPersonas = Integer.parseInt(numeroPersonasStr);
        if (numeroPersonas < 1 || numeroPersonas > 4) {
            Toast.makeText(this, "El número de personas debe estar entre 1 y 4", Toast.LENGTH_SHORT).show();
            return;
        }

        String mensajeFinal = "Fecha de Entrada: " + fechaEntrada +
                "\nFecha de Salida: " + fechaSalida +
                "\nNúmero de Personas: " + numeroPersonas;

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Confirmación de Reserva")
                .setMessage(mensajeFinal)
                .setPositiveButton("Aceptar", null)
                .show();
    }
}