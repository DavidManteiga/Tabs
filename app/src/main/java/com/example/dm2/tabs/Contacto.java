package com.example.dm2.tabs;

import android.graphics.drawable.Drawable;
import android.widget.ImageButton;

/**
 * Created by Sloter on 15/10/2015.
 */
public class Contacto
{
    private String telefono;
    private String nombre;
  //  private Drawable llamada;

    public Contacto(String telefono, String nombre) {
        this.telefono = telefono;
        this.nombre = nombre;
       // this.llamada=llamada;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
