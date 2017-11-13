package com.example.ficheros;

import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ficheros.Adapter.ContactoAdapter;
import com.example.ficheros.pojo.Contacto;

import java.lang.reflect.Array;
import java.net.CookieHandler;
import java.util.ArrayList;

public class AgendaActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAnadir;
    Button btnListar;
    RecyclerView lista;
    Resultado listado;
    Memoria miMemoria;
    private ContactoAdapter adapter;
     ArrayList<Contacto> contactos = new ArrayList<Contacto>();
    public final static String NOMBREFICHERO = "agenda.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        lista=findViewById(R.id.lstVAgenda);
        lista.setHasFixedSize(true);
        lista.setLayoutManager(new GridLayoutManager(this,1));
        adapter= new ContactoAdapter(contactos) ;
        lista.setAdapter(adapter);

        miMemoria = new Memoria(getApplicationContext());
        btnAnadir=(Button)findViewById(R.id.btnAnadir);
        btnListar=(Button)findViewById(R.id.btnListar);
        btnAnadir.setOnClickListener(this);
        btnListar.setOnClickListener(this);

    }
    void Listar()
        {
            String listaContactos;
            String nombre;
            String email;
            String telefono;
            ArrayList<String> valores = new ArrayList<String>();

            if(miMemoria.disponibleLectura())
            {
                listado = miMemoria.leerExterna(NOMBREFICHERO,"UTF-8");
                listaContactos= listado.getMensaje();
                for(int i = 0; i<listaContactos.length();i++)
                {

                }
                valores.add();

            for(int i = 0; i<valores.size();i++)
            {
               adapter.getContactos().add(new Contacto(nombre ,telefono,email));
               lista.setAdapter(adapter);
            }

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

