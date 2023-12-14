/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ERICKPC
 */
public class Calificacion {
    private int idcalificacion;
    private int idusuariocalifacador;
    private int idusuariocalificado;
    private int puntuacion;
    private String comentario;
    private String fecha;

    public Calificacion() {
    }

    public Calificacion(int idcalificacion, int idusuariocalifacador, int idusuariocalificado, int puntuacion, String comentario, String fecha) {
        this.idcalificacion = idcalificacion;
        this.idusuariocalifacador = idusuariocalifacador;
        this.idusuariocalificado = idusuariocalificado;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public int getIdcalificacion() {
        return idcalificacion;
    }

    public void setIdcalificacion(int idcalificacion) {
        this.idcalificacion = idcalificacion;
    }

    public int getIdusuariocalifacador() {
        return idusuariocalifacador;
    }

    public void setIdusuariocalifacador(int idusuariocalifacador) {
        this.idusuariocalifacador = idusuariocalifacador;
    }

    public int getIdusuariocalificado() {
        return idusuariocalificado;
    }

    public void setIdusuariocalificado(int idusuariocalificado) {
        this.idusuariocalificado = idusuariocalificado;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
