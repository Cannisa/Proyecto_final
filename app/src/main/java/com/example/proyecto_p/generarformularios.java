package com.example.proyecto_p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class generarformularios extends AppCompatActivity {
    private Button btngenerar, btnvolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generarformularios);

        Button btngenerar = findViewById(R.id.btngenerar);
        Button btnvolver = findViewById(R.id.btnvolver);
        btngenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirige a ThirdActivity al hacer clic en el botón
                Intent intent = new Intent(generarformularios.this, formularios.class);
                startActivity(intent);
            }
        });

        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirige a ThirdActivity al hacer clic en el botón
                Intent intent = new Intent(generarformularios.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // se supone que al apretar el boton "generar formulario" te dirije a un tercer activity que tiene un formulario pequeño para rellenar (cosa que si logra) y al
        // momento de apretar el boton guardar formulario este se guardara y se apilará en segundo activity debajo del boton generar formulario, hasta el infinito
        // (obviamente con un scrollview)
    }
}