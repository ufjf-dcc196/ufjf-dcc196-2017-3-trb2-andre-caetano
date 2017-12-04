package com.example.andre.trb1;

import android.os.Parcel;
import android.os.Parcelable;

public class Participante implements Parcelable {
    private Long id;
    private String nome;
    private String email;
    private String horaEntrada;
    private String horaSaida;


    public Participante(Parcel in) {
        nome = in.readString();
        email = in.readString();
        horaEntrada = in.readString();
        horaSaida = in.readString();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
