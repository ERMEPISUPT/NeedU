/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Notificacion;
import java.util.List;

/**
 *
 * @author ERICKPC
 */
public interface CRUDNotificacion {
    public List listarMensajes(int idusuario);
    public List listarSolicitudes(int idusuario);
    public List listarAprobaciones(int idusuario);
    public boolean add(int idusuario, String Emisor, int idcategoria);
    public boolean vistearNotificacion(int idusuario);
}
