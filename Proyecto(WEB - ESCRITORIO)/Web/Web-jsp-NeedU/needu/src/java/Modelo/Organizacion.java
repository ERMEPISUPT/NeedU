/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ERICKPC
 */
public class Organizacion {
    private int idorganizacion;
    private String nombre;
    private String tipo;
    private String correo;
    private int idusuario;
    private int idubicacion;

    public Organizacion() {
    }

    public Organizacion(int idorganizacion, String nombre, String tipo, String correo, int idusuario, int idubicacion) {
        this.idorganizacion = idorganizacion;
        this.nombre = nombre;
        this.tipo = tipo;
        this.correo = correo;
        this.idusuario = idusuario;
        this.idubicacion = idubicacion;
    }

    public int getIdorganizacion() {
        return idorganizacion;
    }

    public void setIdorganizacion(int idorganizacion) {
        this.idorganizacion = idorganizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(int idubicacion) {
        this.idubicacion = idubicacion;
    }
    
}
