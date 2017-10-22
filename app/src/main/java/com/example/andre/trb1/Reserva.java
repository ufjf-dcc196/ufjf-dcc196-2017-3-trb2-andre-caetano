package com.example.andre.trb1;

import android.os.Parcel;
import android.os.Parcelable;

class Reserva implements Parcelable{
    private String participante;
    private String livro;

    Reserva(Parcel in) {
        participante = in.readString();
        livro = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(participante);
        dest.writeString(livro);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Reserva> CREATOR = new Creator<Reserva>() {
        @Override
        public Reserva createFromParcel(Parcel in) {
            return new Reserva(in);
        }

        @Override
        public Reserva[] newArray(int size) {
            return new Reserva[size];
        }
    };

    void setParticipante(String participante) {
        this.participante = participante;
    }

    void setLivro(String livro) {
        this.livro = livro;
    }
}
