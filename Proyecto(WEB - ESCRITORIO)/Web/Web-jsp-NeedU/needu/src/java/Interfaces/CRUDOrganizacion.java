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
public interface CRUDOrganizacion {
    public List listarOrganizaciones();
    public Organizacion listar(Organizacion org);
    public boolean registrarOrganizacion(Organizacion org);
    public boolean eliminar(String id);
    public Organizacion listar(int idorganizacion);
    boolean editarOrganizacion(Organizacion organizacion);
    int obtenerIdorganizacion(int idusuario);
    int obtenerIdorganizacionPorOportunidad(int idoportunidad);
    String otbtenerNombreOrganizacion(int idorganizacion);
    public int idusuario(int idorganizacion);
}
