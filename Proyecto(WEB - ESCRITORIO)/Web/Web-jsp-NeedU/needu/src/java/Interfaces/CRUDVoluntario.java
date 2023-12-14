/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;
import java.util.List;
import Modelo.*;
/**
 *
 * @author ERICKPC
 */
public interface CRUDVoluntario {
    public List listarVoluntarios();
    public Voluntario listar(Voluntario vol);
    public boolean registrarVoluntario(Voluntario vol);
    public boolean eliminar(String id);
    public Voluntario datosVoluntario (int idusuario);
    public Voluntario listar(int idusuario);
    public boolean editarVoluntario(Voluntario vol);
    public boolean actualizarEstado(int idUsuario, int estado);
    public String otbtenerNombreVoluntario(int idvoluntario);
}
