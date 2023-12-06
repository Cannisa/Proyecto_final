package com.example.proyecto_p;

import android.widget.EditText;

public class Formulario {
    private String fecha;
    private String hora;
    private String turno;
    private String inicio;
    private String termino;

    public Formulario(EditText fecha, EditText hora, EditText turno, EditText inicio, EditText termino) {
        this.fecha = fecha;
        this.hora = hora;
        this.turno = turno;
        this.inicio = inicio;
        this.termino = termino;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getTurno() {
        return turno;
    }

    public String getInicio() {
        return inicio;
    }

    public String getTermino() {
        return termino;
    }

    @Override
    public String toString() {
        return "Fecha: " + fecha + "\nHora: " + hora + "\nTurno: " + turno + "\nInicio: " + inicio + "\nTermino: " + termino;
    }
}
