/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ERICKPC
 */
public class Control {
    private int idcontrol;
    private String usuario;
    private int idusuario;
    private String hora_entrada;
    private String hora_salida;

    public Control() {
    }

    public Control(int idcontrol, String usuario, int idusuario, String hora_entrada, String hora_salida) {
        this.idcontrol = idcontrol;
        this.usuario = usuario;
        this.idusuario = idusuario;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
    }

    public int getIdcontrol() {
        return idcontrol;
    }

    public void setIdcontrol(int idcontrol) {
        this.idcontrol = idcontrol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }
    
}
