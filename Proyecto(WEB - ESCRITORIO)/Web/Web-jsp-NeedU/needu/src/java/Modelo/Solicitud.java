/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ERICKPC
 */
public class Solicitud {
    private int idsolicitud;
    private int idoportunidad;
    private int idvoluntario;
    private String mensaje;
    private int entregado;

    public Solicitud(int idsolicitud, int idoportunidad, int idvoluntario, String mensaje, int entregado) {
        this.idsolicitud = idsolicitud;
        this.idoportunidad = idoportunidad;
        this.idvoluntario = idvoluntario;
        this.mensaje = mensaje;
        this.entregado = entregado;
    }

    public Solicitud() {
    }

    public int getIdsolicitud() {
        return idsolicitud;
    }

    public void setIdsolicitud(int idsolicitud) {
        this.idsolicitud = idsolicitud;
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getEntregado() {
        return entregado;
    }

    public void setEntregado(int entregado) {
        this.entregado = entregado;
    }
    
    
}
