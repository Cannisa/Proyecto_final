package com.example.proyecto_p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class listaformularios extends AppCompatActivity {
    private TextView tvListaFormularios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaformularios);

        tvListaFormularios = findViewById(R.id.tvListaFormularios);

        // Obtener la lista de formularios almacenada en SharedPreferences
        List<Formulario> listaFormularios = obtenerListaFormularios();

        // Mostrar la lista de formularios en un TextView o en otro componente según tu diseño
        mostrarListaFormularios(listaFormularios);
    }

    private List<Formulario> obtenerListaFormularios() {
        // Obtener la referencia a SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("ListaFormularios", MODE_PRIVATE);

        // Obtener la lista de formularios almacenada en SharedPreferences
        String listaFormulariosJson = sharedPreferences.getString("listaFormularios", null);

        // Convertir la lista de formularios de JSON a una lista de objetos
        if (listaFormulariosJson != null) {
            Type listaFormulariosType = new TypeToken<List<Formulario>>() {}.getType();
            return new Gson().fromJson(listaFormulariosJson, listaFormulariosType);
        }

        return new ArrayList<>();
    }

    private void mostrarListaFormularios(List<Formulario> listaFormularios) {
        // Mostrar la lista de formularios en un TextView o en otro componente según tu diseño
        StringBuilder stringBuilder = new StringBuilder();
        for (Formulario formulario : listaFormularios) {
            stringBuilder.append(formulario.toString()).append("\n");
        }
        tvListaFormularios.setText(stringBuilder.toString());
    }
}