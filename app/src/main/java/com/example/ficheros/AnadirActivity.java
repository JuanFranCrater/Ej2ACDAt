package com.example.ficheros;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ficheros.pojo.Contacto;

import java.io.File;

public class AnadirActivity extends AppCompatActivity implements View.OnClickListener {
    Memoria miMemoria;
    Button btnAnadirContacto;
    EditText edtName,edtPhone,edtMail;
    public final static String NOMBREFICHERO = "agenda.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);
        miMemoria= new Memoria(getApplication());
        btnAnadirContacto=(Button)findViewById(R.id.btnAnadirContacto);
        btnAnadirContacto.setOnClickListener(this);
        edtName=(EditText)findViewById(R.id.edtNombre);
        edtPhone=(EditText)findViewById(R.id.edtTelefono);
        edtMail=(EditText)findViewById(R.id.edtEmail);
    }


    @Override
    public void onClick(View view) {
        if(view==btnAnadirContacto){
        String contacto=edtName.getText().toString()+","+edtPhone.getText().toString()+","+edtMail.getText().toString();
        contacto.concat("\n");
        if(miMemoria.disponibleEscritura()) {
            if (miMemoria.escribirExterna(NOMBREFICHERO, contacto, true, "UTF-8")) {
            startActivity(new Intent(AnadirActivity.this,AgendaActivity.class));
        }else {
            //Error al escribir
        }}else {
            //Error: Memoria no disponible
        }}
    }
}
