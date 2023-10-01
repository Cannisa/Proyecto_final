package com.example.proyecto_p;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private TextView clocktextview;
    private Handler handler;
    private Button btn1;
    private ProgressBar pb1;
    private EditText edt1, edt2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clocktextview = (TextView) findViewById(R.id.clocktextview);
        handler = new Handler();
        btn1 = (Button) findViewById(R.id.btn1);
        pb1 = (ProgressBar) findViewById(R.id.pb1);
        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);

        startClockRunnable();
        updateClock();
        showProgressBar();
        hideProgressBar();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // se simula una carga de progresbar
                showProgressBar();

                // Simulamos un proceso de inicio de sesión
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressBar();
                        // Cambiar a la nueva interfaz después de la carga
                        cambiarinterfaz();
                    }
                }, 3000);
            }
        });
    }
    private void showProgressBar() {
        pb1.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        pb1.setVisibility(View.INVISIBLE);
    }

    private void cambiarinterfaz() {
        Intent i = new Intent(MainActivity.this, generarformularios.class);
        startActivity(i);
    }

    //aqui necesitare estar conectado a una base de datos con todos los datos de los usuarios que deben conectarse, cuande creé la base de datos implementare un comprobador
    // que compruebe los datos ingresados y permita o rechaze el ingreso al usuario.



    //RELOJ
    private void startClockRunnable() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                updateClock();
                handler.postDelayed(this, 1000); // Ejecutar cada segundo
            }
        });
    }

    private void updateClock() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        clocktextview.setText(currentTime);
    }

    //cabe aclarar que tiene 1-2 minutos de atraso el reloj.
}