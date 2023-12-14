/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ERICKPC
 */
public class Oportunidad {
    private int idoportunidad;
    private String titulo;
    private String descripcion;
    private String fecha;
    private int idubicacion;
    private int idorganizacion;
    private int idcausa;
    private int estado;
    private String causa;
    private String pais;
    private String ciudad;
    private String direccion;
    private String organizacion;

    public Oportunidad() {
    }

    public Oportunidad(int idoportunidad, String titulo, String descripcion, String fecha, int idubicacion, int idorganizacion, int idcausa, int estado, String causa, String pais, String ciudad, String direccion, String organizacion) {
        this.idoportunidad = idoportunidad;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.idubicacion = idubicacion;
        this.idorganizacion = idorganizacion;
        this.idcausa = idcausa;
        this.estado = estado;
        this.causa = causa;
        this.pais = pais;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.organizacion = organizacion;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    
    
}
