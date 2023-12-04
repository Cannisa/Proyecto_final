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

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private Button btn1;
    private ProgressBar pb1;
    private EditText edtrut, edtnombre, edtapellido, edtcorreo;
    private static final String BROKER_URL = "mqtt://androidtestsiqq.cloud.shiftr.io:1883";
    private static final String CLIENT_ID = "Labor Time";
    private MqttHandler mqttHandler;
    protected void onDestroy() {
        mqttHandler.disconnect();
        super.onDestroy();
    }
    private void publishMessage(String topic, String message){
        Toast.makeText(this, "Publishing message: " + message, Toast.LENGTH_SHORT).show();
        mqttHandler.publish(topic,message);
    }

    private void subscribeToTopic(String topic){
        Toast.makeText(this, "Subscribing to topic"+ topic, Toast.LENGTH_SHORT).show();
        mqttHandler.subscribe(topic);
    }

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        btn1 = (Button) findViewById(R.id.btn1);
        pb1 = (ProgressBar) findViewById(R.id.pb1);
        edtrut = (EditText) findViewById(R.id.edtrut);
        edtnombre = (EditText) findViewById(R.id.edtnombre);
        edtapellido = (EditText) findViewById(R.id.edtapellido);
        edtcorreo = (EditText) findViewById(R.id.edtcorreo);


        //inicializarFirebase();
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
                        // Cambia a la nueva interfaz después de la carga
                        cambiarinterfaz();
                    }
                }, 3000);
            }
        });

        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID, getApplicationContext());
        subscribeToTopic("Tema1");
        publishMessage("Tema1", "hola");

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

    /*private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databasereference = firebaseDatabase.getReference();

        Usuario u = new Usuario();
        u.setUid(UUID.randomUUID().toString());
        u.setRut(rut);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setCorreo(correo);

    }

     */


    //aqui necesitare estar conectado a una base de datos con todos los datos de los usuarios que deben conectarse, cuande creé la base de datos implementare un comprobador
    // que compruebe los datos ingresados y permita o rechaze el ingreso al usuario.


}