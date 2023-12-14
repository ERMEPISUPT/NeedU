/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ERICKPC
 */
public class Habilidad {
    private int idhabilidad;
    private String nombre;
    private String descripcion;

    public Habilidad() {
    }

    public Habilidad(int idhabilidad, String nombre, String descripcion) {
        this.idhabilidad = idhabilidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdhabilidad() {
        return idhabilidad;
    }

    public void setIdhabilidad(int idhabilidad) {
        this.idhabilidad = idhabilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
