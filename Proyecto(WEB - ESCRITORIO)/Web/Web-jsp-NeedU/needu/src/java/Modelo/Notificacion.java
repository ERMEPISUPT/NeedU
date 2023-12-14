/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ERICKPC
 */
public class Notificacion {
    private int idnotificacion;
    private int idusuario;
    private String mensaje;
    private String fecha;
    private int visto;
    private int idcategoria;

    public Notificacion() {
    }

    public Notificacion(int idnotificacion, int idusuario, String mensaje, String fecha, int visto, int idcategoria) {
        this.idnotificacion = idnotificacion;
        this.idusuario = idusuario;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.visto = visto;
        this.idcategoria = idcategoria;
    }

    public int getIdnotificacion() {
        return idnotificacion;
    }

    public void setIdnotificacion(int idnotificacion) {
        this.idnotificacion = idnotificacion;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getVisto() {
        return visto;
    }

    public void setVisto(int visto) {
        this.visto = visto;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }
    
}
