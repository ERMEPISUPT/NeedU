/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Chat;
import Modelo.Usuario;
import java.util.List;

/**
 *
 * @author ERICKPC
 */
public interface CRUDChat {
    public int crearChat(int idusuario1, int idusuario2);
    public List<Chat> obtenerChatsUsuario(int idUsuario);
    public String obtenerNombreUsuario(int idUsuario);
    public boolean existeChat(int idusuario1, int idusuario2);
    public List<Usuario> obtenerUsuarios(int idChat);
}
