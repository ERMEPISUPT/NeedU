/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ERICKPC
 */
public class Chat {
    private int idchat;
    private int idusuario1;
    private int idusuario2;
    private String nombre1;
    private String nombre2;
    private String fechacreacion;

    public Chat() {
    }

    public Chat(int idchat, int idusuario1, int idusuario2, String nombre1, String nombre2, String fechacreacion) {
        this.idchat = idchat;
        this.idusuario1 = idusuario1;
        this.idusuario2 = idusuario2;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.fechacreacion = fechacreacion;
    }

    public int getIdchat() {
        return idchat;
    }

    public void setIdchat(int idchat) {
        this.idchat = idchat;
    }

    public int getIdusuario1() {
        return idusuario1;
    }

    public void setIdusuario1(int idusuario1) {
        this.idusuario1 = idusuario1;
    }

    public int getIdusuario2() {
        return idusuario2;
    }

    public void setIdusuario2(int idusuario2) {
        this.idusuario2 = idusuario2;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    
    
}
