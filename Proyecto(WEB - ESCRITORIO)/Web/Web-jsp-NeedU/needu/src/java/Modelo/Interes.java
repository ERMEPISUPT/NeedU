/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ERICKPC
 */
public class Interes {
    private int idinteres;
    private String nombre;
    private String descripcion;

    public Interes() {
    }

    public Interes(int idinteres, String nombre, String descripcion) {
        this.idinteres = idinteres;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdinteres() {
        return idinteres;
    }

    public void setIdinteres(int idinteres) {
        this.idinteres = idinteres;
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
