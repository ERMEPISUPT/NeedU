/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ERICKPC
 */
public class Mensaje {
    private int idmensaje;
    private int idchat;
    private int emisor;
    private int receptor;
    private String mensaje;
    private String fechahora;
    private String nombre_emisor;
    private String nombre_receptor;
    private String hora;
    public Mensaje() {
    }

    public int getIdmensaje() {
        return idmensaje;
    }

    public void setIdmensaje(int idmensaje) {
        this.idmensaje = idmensaje;
    }

    public int getIdchat() {
        return idchat;
    }

    public void setIdchat(int idchat) {
        this.idchat = idchat;
    }

    public int getEmisor() {
        return emisor;
    }

    public void setEmisor(int emisor) {
        this.emisor = emisor;
    }

    public int getReceptor() {
        return receptor;
    }

    public void setReceptor(int receptor) {
        this.receptor = receptor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFechahora() {
        return fechahora;
    }

    public void setFechahora(String fechahora) {
        this.fechahora = fechahora;
    }

    public String getNombre_emisor() {
        return nombre_emisor;
    }

    public void setNombre_emisor(String nombre_emisor) {
        this.nombre_emisor = nombre_emisor;
    }

    public String getNombre_receptor() {
        return nombre_receptor;
    }

    public void setNombre_receptor(String nombre_receptor) {
        this.nombre_receptor = nombre_receptor;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Mensaje(int idmensaje, int idchat, int emisor, int receptor, String mensaje, String fechahora, String nombre_emisor, String nombre_receptor, String hora) {
        this.idmensaje = idmensaje;
        this.idchat = idchat;
        this.emisor = emisor;
        this.receptor = receptor;
        this.mensaje = mensaje;
        this.fechahora = fechahora;
        this.nombre_emisor = nombre_emisor;
        this.nombre_receptor = nombre_receptor;
        this.hora = hora;
    }

    
    
}
