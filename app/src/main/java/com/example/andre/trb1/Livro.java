package com.example.andre.trb1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Livro implements Parcelable{
    private int id;
    private String titulo;
    private String editora;
    private Integer anoPublicacao;
    private ArrayList<Participante> participantes = new ArrayList<>();

    public Livro(Parcel in) {
        titulo = in.readString();
        editora = in.readString();
        anoPublicacao = in.readInt();
        participantes = in.createTypedArrayList(Participante.CREATOR);
    }

    /*Livro(String titulo, String editora, Integer anoPublicacao){
        this.titulo = titulo;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
    }*/

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeString(editora);
        dest.writeInt(anoPublicacao);
        dest.writeTypedList(participantes);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    /*public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public void addParticipante(Participante participante) {
        participantes.add(participante);
    }*/

    @Override
    public String toString() {
        return getTitulo();
    }
}
