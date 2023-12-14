/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ERICKPC
 */
public class Configuracion {
    private int idconfiguracion;
    private int idusuario;
    private String configuraciones;

    public Configuracion() {
    }

    public Configuracion(int idconfiguracion, int idusuario, String configuraciones) {
        this.idconfiguracion = idconfiguracion;
        this.idusuario = idusuario;
        this.configuraciones = configuraciones;
    }

    public int getIdconfiguracion() {
        return idconfiguracion;
    }

    public void setIdconfiguracion(int idconfiguracion) {
        this.idconfiguracion = idconfiguracion;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getConfiguraciones() {
        return configuraciones;
    }

    public void setConfiguraciones(String configuraciones) {
        this.configuraciones = configuraciones;
    }
    
}
