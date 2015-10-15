package com.example.dm2.tabs;

import android.graphics.drawable.Drawable;

/**
 * Created by dm2 on 13/10/2015.
 */
public class MisWebs
{
    private String titulo;
    private String url;
    private Drawable imagen;

    public MisWebs(String titulo,String url,Drawable imagen) {
        this.titulo = titulo;
        this.url=url;
        this.imagen=imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Drawable getImagen() {
        return imagen;
    }

    public void setImagen(Drawable imagen) {
        this.imagen = imagen;
    }
}
