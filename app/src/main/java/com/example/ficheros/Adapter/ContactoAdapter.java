package com.example.ficheros.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.ficheros.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by PcCom on 09/11/2017.
 */

public abstract class ContactoAdapter extends BaseAdapter {
    private ArrayList<Contacto> contactos;
    private int R_layout_IdView;
    private Context contexto;

    public ContactoAdapter(Context contexto, int R_layout_IdView, ArrayList<Contacto> contactos) {
        this.contexto = contexto;
        this.contactos = contactos;
        this.R_layout_IdView = R_layout_IdView;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup pariente) {
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R_layout_IdView, null);
        }
        onEntrada (contactos.get(posicion), view);
        return view;
    }

    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Object getItem(int posicion) {
        return contactos.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    /** Devuelve cada una de las entradas con cada una de las vistas a la que debe de ser asociada
     * @param contacto La entrada que será la asociada a la view. La entrada es del tipo del paquete/handler
     * @param view View particular que contendrá los datos del paquete/handler
     */
    public abstract void onEntrada (Contacto contacto, View view);
}
