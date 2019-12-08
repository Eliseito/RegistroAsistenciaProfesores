package com.example.registroasistenciaprofesores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_uss, et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     et_uss=(EditText)findViewById(R.id.et_uss);
     et_pass=(EditText)findViewById(R.id.et_pass);
    }

    //Metodo para el BOTON, que envia al Siguiente Activity

    public void ingresar(View view) {
        String usu = et_uss.getText().toString();
        String pass = et_pass.getText().toString();

        if (usu.equals("marcial") && pass.equals("123456")) {
            Intent i = new Intent(this, registrodocente.class);
            i.putExtra("dato", usu);
            startActivity(i);

        } else {
            Toast.makeText(this, "Usuario/Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
        }
    }

}
