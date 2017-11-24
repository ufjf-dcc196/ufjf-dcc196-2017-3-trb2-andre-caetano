package com.example.andre.trb1;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.util.Date;

class Participante implements Parcelable {
    private String nome;
    private String email;
    private String horaEntrada;
    private String horaSaida;


    Participante(Parcel in) {
        nome = in.readString();
        email = in.readString();
        horaEntrada = in.readString();
        horaSaida = in.readString();
    }

    Participante(String nome, String email){
        this.nome = nome;
        this.email = email;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(email);
        dest.writeString(horaEntrada);
        dest.writeString(horaSaida);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Participante> CREATOR = new Creator<Participante>() {
        @Override
        public Participante createFromParcel(Parcel in) {
            return new Participante(in);
        }

        @Override
        public Participante[] newArray(int size) {
            return new Participante[size];
        }
    };

    @Override
    public String toString() {
        return nome;
    }

    void setNome(String nome) {
        this.nome = nome;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getHoraEntrada() {
        return horaEntrada;
    }

    String getHoraSaida() {
        return horaSaida;
    }

    void registraHora(Date date){
        if( horaEntrada == null && horaSaida == null) {
            this.horaEntrada = DateFormat.getTimeInstance().format(date);
        }
        else if(horaSaida == null){
            this.horaSaida = DateFormat.getTimeInstance().format(date);
        }
        else{
            this.horaEntrada = null;
            this.horaSaida = null;
        }
    }
}
