package com.example.proyecto_p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private Button btn1;
    private ProgressBar pb1;
    private EditText etRut;
    private EditText etContraseña;

    private Usuario usuario1;
    private Usuario usuario2;
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

        usuario1 = new Usuario("rut1", "contraseña1");
        usuario2 = new Usuario("rut2", "contraseña2");

        handler = new Handler();
        btn1 = (Button) findViewById(R.id.btn1);
        pb1 = (ProgressBar) findViewById(R.id.pb1);
        etRut = (EditText) findViewById(R.id.etRut);
        etContraseña = (EditText) findViewById(R.id.etContraseña);


        //inicializarFirebase();
        showProgressBar();
        hideProgressBar();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String rutIngresado = etRut.getText().toString();
               String contraseñaIngresada = etContraseña.getText().toString();
               if (usuario1.getRut().equals(rutIngresado) && usuario1.getContraseña().equals(contraseñaIngresada)){
                   Toast.makeText(MainActivity.this, "inicio de sesión exitoso para usuario1", Toast.LENGTH_SHORT).show();
               } else if (usuario2.getRut().equals(rutIngresado) && usuario2.getContraseña().equals(contraseñaIngresada)){
                   Toast.makeText(MainActivity.this, "inicio de sesion exitoso para usuario2", Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
               }
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