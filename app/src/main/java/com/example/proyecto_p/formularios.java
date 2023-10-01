package com.example.proyecto_p;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class formularios extends AppCompatActivity {
    private Button btnsaveFormulario;
    private EditText turno, horainicio, horatermino;
    private DatePicker datePicker;
    private TimePicker timePicker;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formularios);

        turno = (EditText) findViewById(R.id.turno);
        horainicio = (EditText) findViewById(R.id.horainicio);
        horatermino = (EditText) findViewById(R.id.horatermino);
        btnsaveFormulario = (Button) findViewById(R.id.btnsaveFormulario);

        btnsaveFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guarda los datos del formulario
                saveFormData();

                // Regresa a SecondActivity después de guardar el formulario
                finish();
            }
        });
    }

    private void saveFormData() {
        String Turno = turno.getText().toString();
        String Horainicio = horainicio.getText().toString();
        String Horatermino = horatermino.getText().toString();
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1; // Sumar 1 porque los meses se cuentan desde 0
        int year = datePicker.getYear();
        String date = String.format("%02d/%02d/%d", day, month, year);
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        String time = String.format("%02d:%02d", hour, minute);

        // Puedes guardar estos datos en SharedPreferences o en algún otro lugar
        // En este ejemplo, se usará SharedPreferences
        SharedPreferences preferences = getSharedPreferences("formulario_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("date", date);
        editor.putString("time", time);
        editor.putString("turno", String.valueOf(turno));
        editor.putString("horainicio", String.valueOf(horainicio));
        editor.putString("horatermino", String.valueOf(horainicio));


        // Puedes agregar más datos según sea necesario
        editor.apply();

        Toast.makeText(this, "Formulario guardado", Toast.LENGTH_SHORT).show();
    }

}

//aqui se deberia guardar el formulario acoplandolo a un linear layout con un scrollview para seguir bajando a medida que se guardan los distintos registros hechos por el usuario.