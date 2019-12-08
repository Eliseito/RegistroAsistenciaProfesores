package com.example.registroasistenciaprofesores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registrodocente extends AppCompatActivity {

    private EditText et_cod,et_nom,et_ape,et_dni,et_cel,et_dire,et_uss,et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrodocente);

        et_cod=(EditText)findViewById(R.id.et_cod);
        et_nom=(EditText)findViewById(R.id.et_nom);
        et_ape=(EditText)findViewById(R.id.et_ape);
        et_dni=(EditText)findViewById(R.id.et_dni);
        et_cel=(EditText)findViewById(R.id.et_cel);
        et_dire=(EditText)findViewById(R.id.et_dire);
        et_uss =(EditText)findViewById(R.id.et_uss);
        et_pass =(EditText)findViewById(R.id.et_pass);
    }

//Metodo Guardar

    public void guardar(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_cod.getText().toString();
        String nombre = et_nom.getText().toString();
        String apellido = et_ape.getText().toString();
        String dni = et_dni.getText().toString();
        String celular = et_cel.getText().toString();
        String direccion = et_dire.getText().toString();
        String usuario = et_uss.getText().toString();
        String contraseña = et_pass.getText().toString();

        if(!codigo.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !dni.isEmpty() && !celular.isEmpty() && !direccion.isEmpty() && !usuario.isEmpty() && !contraseña.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("nombre",nombre);
            registro.put("apellido",apellido);
            registro.put("dni",dni);
            registro.put("celular",celular);
            registro.put("direccion",direccion);
            registro.put("usuario",usuario);
            registro.put("contraseña",contraseña);

            BaseDeDatos.insert("docente", null, registro);
            BaseDeDatos.close();

            Toast.makeText(this,"Docente Registrado Exitosamente", Toast.LENGTH_SHORT).show();

            et_cod.setText("");
            et_nom.setText("");
            et_ape.setText("");
            et_dni.setText("");
            et_cel.setText("");
            et_dire.setText("");
            et_uss.setText("");
            et_pass.setText("");
            et_cod.requestFocus();

        }else{
            Toast.makeText(this,"Debes de Ingresar Datos",Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo Buscar DOCENTE

    public void buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String codigo = et_cod.getText().toString();

        if(!codigo.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("select nombre, apellido, dni, celular, direccion, usuario, contraseña from docente where codigo=" + codigo, null);

            if (fila.moveToFirst()) {

                et_nom.setText(fila.getString(0));
                et_ape.setText(fila.getString(1));
                et_dni.setText(fila.getString(2));
                et_cel.setText(fila.getString(3));
                et_dire.setText(fila.getString(4));
                et_uss.setText(fila.getString(5));
                et_pass.setText(fila.getString(6));
                BaseDeDatos.close();
            }else{
                Toast.makeText(this, "El Docente no esta Registrado", Toast.LENGTH_SHORT).show();
                et_nom.setText("");
                et_ape.setText("");
                et_dni.setText("");
                et_cel.setText("");
                et_dire.setText("");
                et_uss.setText("");
                et_pass.setText("");
                et_cod.requestFocus();
                BaseDeDatos.close();
            }
        }else{
            Toast.makeText(this, "Ingresa el Còdigo del Docente", Toast.LENGTH_SHORT).show();
        }

    }

}


