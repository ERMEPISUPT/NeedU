/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ERICKPC
 */
public class EliminacionTemporal {
    private int ideliminacion;
    private int idusuario;
    private String fecha;

    public EliminacionTemporal() {
    }

    public EliminacionTemporal(int ideliminacion, int idusuario, String fecha) {
        this.ideliminacion = ideliminacion;
        this.idusuario = idusuario;
        this.fecha = fecha;
    }

    public int getIdeliminacion() {
        return ideliminacion;
    }

    public void setIdeliminacion(int ideliminacion) {
        this.ideliminacion = ideliminacion;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
