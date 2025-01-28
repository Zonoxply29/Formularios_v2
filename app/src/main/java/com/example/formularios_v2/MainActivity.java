package com.example.formularios_v2;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST = 1;
    private TextView textViewRuta;
    private Uri fileUri; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewRuta = findViewById(R.id.textViewRuta);
        Button btnSeleccionarArchivo = findViewById(R.id.selec_archivo);


        btnSeleccionarArchivo.setOnClickListener(v -> openFilePicker());


        textViewRuta.setOnClickListener(v -> {
            if (fileUri != null) {
                openFilePreview(fileUri);
            } else {
                Toast.makeText(MainActivity.this, "No hay archivo seleccionado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*"); // Permite seleccionar cualquier tipo de archivo
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, PICK_FILE_REQUEST); // Lanza la actividad con código de solicitud
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null) {
            fileUri = data.getData(); // Guarda la URI del archivo seleccionado
            String fileName = getFileNameFromUri(fileUri);
            textViewRuta.setText(fileName); // Muestra el nombre del archivo
        }
    }

    private String getFileNameFromUri(Uri uri) {
        String fileName = "Archivo no encontrado";
        if (uri != null) {
            try (Cursor cursor = getContentResolver()
                    .query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (nameIndex != -1) {
                        fileName = cursor.getString(nameIndex);
                    }
                }
            }
        }
        return fileName;
    }

    private void openFilePreview(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, getContentResolver().getType(uri));
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No hay aplicación para abrir este archivo", Toast.LENGTH_SHORT).show();
        }
    }
}