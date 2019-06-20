package com.example.prueba.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

@SuppressWarnings("Juego")
public class Juego implements Parcelable {
    private String titulo;
    private String plataforma;
    private String posesion;
    private String nuevo;
    private String avance;
    private String veces;

    public Juego(){

    }

    public Juego(String titulo, String plataforma, String posesion, String nuevo, String avance, String veces) {
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.posesion = posesion;
        this.nuevo = nuevo;
        this.avance = avance;
        this.veces = veces;
    }

    protected Juego(Parcel in) {
        titulo = in.readString();
        plataforma = in.readString();
        posesion = in.readString();
        nuevo = in.readString();
        avance = in.readString();
        veces = in.readString();
    }

    public static final Creator<Juego> CREATOR = new Creator<Juego>() {
        @Override
        public Juego createFromParcel(Parcel in) {
            return new Juego(in);
        }

        @Override
        public Juego[] newArray(int size) {
            return new Juego[size];
        }
    };

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getPosesion() {
        return posesion;
    }

    public void setPosesion(String posesion) {
        this.posesion = posesion;
    }

    public String getNuevo() {
        return nuevo;
    }

    public void setNuevo(String nuevo) {
        this.nuevo = nuevo;
    }

    public String getAvance() {
        return avance;
    }

    public void setAvance(String avance) {
        this.avance = avance;
    }

    public String getVeces() {
        return veces;
    }

    public void setVeces(String veces) {
        this.veces = veces;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeString(plataforma);
        dest.writeString(posesion);
        dest.writeString(nuevo);
        dest.writeString(avance);
        dest.writeString(veces);
    }
}
