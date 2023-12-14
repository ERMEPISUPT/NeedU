/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;
import ModeloDAO.*;
import Modelo.*;
import java.util.List;
/**
 *
 * @author ERICKPC
 */
public interface CRUDOportunidad {
    public boolean crearOportunidad(Oportunidad opo);
    public boolean editarOportunidad(Oportunidad opo);
    public boolean eliminarOportunidad(int idOportunidad);
    public List<Oportunidad> busqueda();
}