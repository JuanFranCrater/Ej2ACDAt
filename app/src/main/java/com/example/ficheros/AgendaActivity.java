package com.example.ficheros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AgendaActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtNumero;
    private EditText edtNombre;
    private EditText edtCorreo;
    private TextView listado;
    private Button btnAnadir;
    private Button btnListar;

    Resultado resul;
    private Memoria miMemoria;

    private String contacto;

    private static final String RUTA = "agenda.txt";

    private static final String CODIFICACION = "UTF-8";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        inicializar();

        miMemoria = new Memoria(this);

    }

    @Override
    public void onClick(View view) {
    if(view == btnListar)
    {
        Listar();
    }
    if(view == btnAnadir)
    {
        Guardar();
    }
    }


    void inicializar() {
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtNumero = (EditText) findViewById(R.id.edtTelefono);
        edtCorreo = (EditText) findViewById(R.id.edtEmail);
        listado = (TextView) findViewById(R.id.listado);
        btnListar = (Button) findViewById(R.id.btnListar);
        btnAnadir = (Button) findViewById(R.id.btnAnadir);
        btnAnadir.setOnClickListener(this);
        btnListar.setOnClickListener(this);

    }
    private void Guardar()
    {
        if (edtNombre.getText().length() == 0 || edtNumero.getText().length() == 0 || edtCorreo.getText().length() == 0) {
            Toast.makeText(this, "Rellene los campos incompletos", Toast.LENGTH_SHORT).show();
        }
        else {
            contacto = edtNombre.getText().toString() + "; " + edtCorreo.getText().toString() + "; " + edtNumero.getText().toString() + "\n";
            miMemoria.escribirInterna(RUTA, contacto, true, CODIFICACION);
            Toast.makeText(this, "Guardando contacto", Toast.LENGTH_SHORT).show();
        }
    }
    private void Listar() {
        resul = miMemoria.leerInterna(RUTA, CODIFICACION);
        if (resul.getCodigo()) {
            listado.setText(resul.getContenido());
            Toast.makeText(this, "Se han listado los contactos", Toast.LENGTH_SHORT).show();
        }
        else
        {
            listado.setText("");
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }


}
