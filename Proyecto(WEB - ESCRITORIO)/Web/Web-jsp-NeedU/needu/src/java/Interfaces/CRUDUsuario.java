/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;
import Modelo.*;
import java.util.List;
/**
 *
 * @author ERICKPC
 */
public interface CRUDUsuario {
    public int registrarUsuario(Usuario usu);
    public List listarUsuarios();
    public int autenticarUsuario(String usuario, String clave);
    public Usuario listar(int idusuario);
    Usuario listarUsuActual(int idUsuarioActual);
   List<Usuario> obtenerTodosLosUsuarios();
   public int obtenerIdUsuarioPorIdOportunidad(int idoportunidad);


}
