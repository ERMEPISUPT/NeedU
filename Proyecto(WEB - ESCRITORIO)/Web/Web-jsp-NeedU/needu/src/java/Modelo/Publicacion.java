/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.Date;

/**
 *
 * @author ERICKPC
 */
public class Publicacion {
    private int idpublicacion;
    private int idorganizacion;
    private String titulo;
    private int idoportunidad;
    private Date fecha;

    public Publicacion() {
    }

    public Publicacion(int idpublicacion, int idorganizacion, String titulo, int idoportunidad, Date fecha) {
        this.idpublicacion = idpublicacion;
        this.idorganizacion = idorganizacion;
        this.titulo = titulo;
        this.idoportunidad = idoportunidad;
        this.fecha = fecha;
    }

    public int getIdpublicacion() {
        return idpublicacion;
    }

    public void setIdpublicacion(int idpublicacion) {
        this.idpublicacion = idpublicacion;
    }

    public int getIdorganizacion() {
        return idorganizacion;
    }

    public void setIdorganizacion(int idorganizacion) {
        this.idorganizacion = idorganizacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIdoportunidad() {
        return idoportunidad;
    }

    public void setIdoportunidad(int idoportunidad) {
        this.idoportunidad = idoportunidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
