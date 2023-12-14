/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Admin;
import Modelo.Control;
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 *
 * @author ERICKPC
 */
public interface CRUDAdmin {
    public List listarAdministradores();
    public Admin list(Admin adm);
    public boolean registrarAdmin(Admin adm);
    public boolean edit(Admin adm);
    public boolean eliminar(String id);
    Admin listarPorUsuario(int idUsuario);
    public boolean editar(Admin adm);
    Admin obtenerAdminPorIDUsuario(int idUsuario);
    public String generarInforme(List<Control> controles);
    
}
