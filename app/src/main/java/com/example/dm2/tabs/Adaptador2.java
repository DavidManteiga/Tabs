package com.example.dm2.tabs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dm2 on 13/10/2015.
 */
public class Adaptador2 extends ArrayAdapter<Contacto>
{
    private TextView nombre;
    private TextView telefono;
   // private ImageButton llamada;
    private Contacto[] datos;

    public Adaptador2(Context context, Contacto[] datos) {
        super(context, R.layout.listitem_contactos, datos);

        this.datos=datos;


    }

public View getView(int position, View convertView, ViewGroup parent)
{
    View item = convertView;
    ViewHolderContactos holder;

    if(item == null)
    {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        item = inflater.inflate(R.layout.listitem_contactos, null);

        holder = new ViewHolderContactos();
        holder.nombre = (TextView)item.findViewById(R.id.nombre);
        holder.telefono = (TextView)item.findViewById(R.id.telefono);
       // holder.llamada=(ImageButton)item.findViewById(R.id.llamada);

        item.setTag(holder);
    }
    else
    {
        holder = (ViewHolderContactos)item.getTag();
    }

    holder.nombre.setText(datos[position].getNombre());
    holder.telefono.setText(datos[position].getTelefono());
    //holder.llamada.setImageDrawable(datos[position].getLlamada());



    return(item);
}
}