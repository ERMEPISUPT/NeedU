/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

import java.util.Date;

/**
 *
 * @author Administrador
 */
public class clsEOportunidad {
    private int idoportunidad;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private int idubicacion;
    private int idorganizacion;
    private int idcausa;
    private int estado;

    public clsEOportunidad() {
    }

    public clsEOportunidad(int idoportunidad, String titulo, String descripcion, Date fecha, int idubicacion, int idorganizacion, int idcausa, int estado) {
        this.idoportunidad = idoportunidad;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.idubicacion = idubicacion;
        this.idorganizacion = idorganizacion;
        this.idcausa = idcausa;
        this.estado = estado;
    }

    public int getIdoportunidad() {
        return idoportunidad;
    }

    public void setIdoportunidad(int idoportunidad) {
        this.idoportunidad = idoportunidad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(int idubicacion) {
        this.idubicacion = idubicacion;
    }

    public int getIdorganizacion() {
        return idorganizacion;
    }

    public void setIdorganizacion(int idorganizacion) {
        this.idorganizacion = idorganizacion;
    }

    public int getIdcausa() {
        return idcausa;
    }

    public void setIdcausa(int idcausa) {
        this.idcausa = idcausa;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    
}
