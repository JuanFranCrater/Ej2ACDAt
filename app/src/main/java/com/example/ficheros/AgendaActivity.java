package com.example.ficheros;

import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ficheros.Adapter.ContactoAdapter;
import com.example.ficheros.pojo.Contacto;

import java.net.CookieHandler;
import java.util.ArrayList;

public class AgendaActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAnadir;
    Button btnListar;
    ListView lista;
    Resultado listado;
    Memoria miMemoria;
    public final static String NOMBREFICHERO = "agenda.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        miMemoria = new Memoria(getApplicationContext());
        btnAnadir=(Button)findViewById(R.id.btnAnadir);
        btnListar=(Button)findViewById(R.id.btnListar);
        btnAnadir.setOnClickListener(this);
        btnListar.setOnClickListener(this);

    }
    void Listar()
        {
            final ArrayList<Contacto> contactos = new ArrayList<Contacto>();
            listado = miMemoria.leerExterna(NOMBREFICHERO,"UTF-8");
            if(miMemoria.disponibleLectura())
            {
            for(int i = 0; i<listado.getMensajeArray().size();i++)
            {
               contactos.add(new Contacto( listado.getMensajeArray().get(i),"",""));
            }
        lista = (ListView) findViewById(R.id.lstVAgenda);
        lista.setAdapter(new ContactoAdapter(this, R.layout.item_contacto, contactos) {

            @Override
            public void onEntrada(Contacto contacto, View view) {
                TextView textoNombre = (TextView) view.findViewById(R.id.txtNameValue);
                textoNombre.setText(contacto.getNombre());
                TextView textoTelefono = (TextView) view.findViewById(R.id.txtTelefonoValue);
                textoTelefono.setText(contacto.getTelefono());
                TextView textoMail = (TextView) view.findViewById(R.id.txtEmailValue);
                textoMail.setText(contacto.getEmail());
            }

        });
    }}

    @Override
    public void onClick(View view) {
        if(view==btnAnadir)
        {
           startActivity( new Intent(AgendaActivity.this,AnadirActivity.class));
        }
        if(view==btnListar)
        {
            Listar();
        }
    }

}

