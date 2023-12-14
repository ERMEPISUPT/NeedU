/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ERICKPC
 */
public class Participante {
    private int idoportunidad;
    private int idvoluntario;
    private String fecha;
    private int estado;

    public Participante(int idoportunidad, int idvoluntario, String fecha, int estado) {
        this.idoportunidad = idoportunidad;
        this.idvoluntario = idvoluntario;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Participante() {
    }

    public int getIdoportunidad() {
        return idoportunidad;
    }

    public void setIdoportunidad(int idoportunidad) {
        this.idoportunidad = idoportunidad;
    }

    public int getIdvoluntario() {
        return idvoluntario;
    }

    public void setIdvoluntario(int idvoluntario) {
        this.idvoluntario = idvoluntario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
}
