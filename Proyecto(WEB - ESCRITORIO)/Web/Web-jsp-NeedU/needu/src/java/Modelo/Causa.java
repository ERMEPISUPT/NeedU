/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ERICKPC
 */
public class Causa {
    private int idcausa;
    private String nombre;
    private String descripcion;

    public Causa() {
    }

    public Causa(int idcausa, String nombre, String descripcion) {
        this.idcausa = idcausa;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdcausa() {
        return idcausa;
    }

    public void setIdcausa(int idcausa) {
        this.idcausa = idcausa;
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
