package com.example.dm2.tabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dm2 on 13/10/2015.
 */
public class Adaptador extends ArrayAdapter<MisWebs>
{
    private TextView titulo;
    private TextView url;
    private ImageView imagen;
    private MisWebs[] datos;

    public Adaptador(Context context, MisWebs[] datos) {
        super(context, R.layout.listitem_titular, datos);

        this.datos=datos;


    }
/*
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listitem_titular, null);

        titulo = (TextView)item.findViewById(R.id.titulo);
        titulo.setText(datos[position].getTitulo());

        url = (TextView)item.findViewById(R.id.url);
        url.setText(datos[position].getUrl());

        imagen=(ImageView)item.findViewById(R.id.imagen);
        imagen.setImageDrawable(datos[position].getImagen());

        return(item);
    }
    */
public View getView(int position, View convertView, ViewGroup parent)
{
    View item = convertView;
    ViewHolder holder;

    if(item == null)
    {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        item = inflater.inflate(R.layout.listitem_titular, null);

        holder = new ViewHolder();
        holder.titulo = (TextView)item.findViewById(R.id.titulo);
        holder.url = (TextView)item.findViewById(R.id.url);
        holder.imagen=(ImageView)item.findViewById(R.id.imagen);

        item.setTag(holder);
    }
    else
    {
        holder = (ViewHolder)item.getTag();
    }

    holder.titulo.setText(datos[position].getTitulo());
    holder.url.setText(datos[position].getUrl());
    holder.imagen.setImageDrawable(datos[position].getImagen());
    return(item);
}
}