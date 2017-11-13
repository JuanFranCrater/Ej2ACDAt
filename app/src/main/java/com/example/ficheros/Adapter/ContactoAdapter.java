package com.example.ficheros.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ficheros.R;
import com.example.ficheros.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by PcCom on 09/11/2017.
 */

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.SectorViewHolder>{
    private ArrayList<Contacto> contactos;
    public ArrayList<Contacto> getContactos()
    {
        return contactos;
    }
    private int R_layout_IdView;

    public ContactoAdapter() {
        this.contactos = contactos;
    }
    public ContactoAdapter(ArrayList<Contacto> contactos)
    {
        this.contactos=contactos;
    }
    @Override


    public SectorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //2.Inflar la vista Crea en memoria el objeto View con todos los Widget del xml: item_dependency.xml
        View viewHolder = inflater.inflate(R.layout.item_contacto, null);
        //null porque ya hemos establecio el viewGroup para nuestro item_dependency
        //3. Se crea el objetos SectorViewHolder a partir de la vista
        SectorViewHolder _sectorViewHolder = new SectorViewHolder(viewHolder);
        return _sectorViewHolder;
    }
    @Override
    public void onBindViewHolder(SectorViewHolder holder, int position) {
        holder.txvName.setText(contactos.get(position).getNombre());
        holder.txtEmail.setText(contactos.get(position).getEmail());
        holder.txtTelefono.setText(contactos.get(position).getTelefono());
    }

    /**
     * Se crearan tantos elementos SectorViewHolder como elementos haya en el ArrayList definido dentro de la clase
     * @return
     */
    @Override
    public int getItemCount() {
        return contactos.size();
    }



    public static class SectorViewHolder extends RecyclerView.ViewHolder{
        private TextView txvName;
        private TextView txtEmail;
        private TextView txtTelefono;

        public SectorViewHolder(View view)

        {super(view);

            txtEmail= view.findViewById(R.id.txtEmailValue);

            txvName= view.findViewById(R.id.txtNameValue);

            txtTelefono= view.findViewById(R.id.txtTelefono);
        }
    }
    /**
     * Devuelve el arrray de los sectores que el ususario ha modificado cuando la actividad estaba visible(estado dinamico) y que no
     * se ha guardaddo en la base de datos(persistente)
     *
     * @return Arraylist de sectores modificados
     */


}
