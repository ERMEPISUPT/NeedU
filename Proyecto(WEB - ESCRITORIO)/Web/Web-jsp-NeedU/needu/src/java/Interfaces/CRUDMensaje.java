/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Mensaje;
import java.util.List;

/**
 *
 * @author ERICKPC
 */
public interface CRUDMensaje {
    public boolean enviarMensaje(int idChat, int emisor, int receptor, String mensaje);
    public List<Mensaje> obtenerMensajesChat(int idChat);
    public List<Mensaje> obtenerUltimosMensajesUsuario(int idUsuario);
    public boolean marcarMensajesLeidos(int idChat, int idUsuario);
}
