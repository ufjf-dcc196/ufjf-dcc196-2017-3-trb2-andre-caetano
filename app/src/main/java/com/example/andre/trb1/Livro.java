package com.example.andre.trb1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

class Livro implements Parcelable{
    private String titulo;
    private String editora;
    private Integer anoPublicacao;
    private ArrayList<Participante> participantes = new ArrayList<>();

    Livro(Parcel in) {
        titulo = in.readString();
        editora = in.readString();
        anoPublicacao = in.readInt();
    }

    Livro(String titulo, String editora, Integer anoPublicacao){
        this.titulo = titulo;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeString(editora);
        dest.writeInt(anoPublicacao);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Livro> CREATOR = new Creator<Livro>() {
        @Override
        public Livro createFromParcel(Parcel in) {
            return new Livro(in);
        }

        @Override
        public Livro[] newArray(int size) {
            return new Livro[size];
        }
    };

    @Override
    public String toString() {
        return titulo;
    }

    void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    void setEditora(String editora) {
        this.editora = editora;
    }

    void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    void addParticipante(Participante participante) {
        this.participantes.add(participante);
    }
}
