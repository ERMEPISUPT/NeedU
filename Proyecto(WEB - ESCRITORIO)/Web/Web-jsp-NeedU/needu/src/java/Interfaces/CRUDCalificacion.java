/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;
import Modelo.Calificacion;
import java.util.List;

/**
 *
 * @author ERICKPC
 */
import java.util.List;
import java.util.Map;

public interface CRUDCalificacion {
    boolean agregarCalificacion(Calificacion calificacion);
    List<String> getComentariosByUsuarioId(int usuarioId);
    Map<Integer, List<String>> getComentariosDeTodosLosUsuarios();
    Map<Integer, Map<String, Object>> getComentariosDeTodosLosUsuariosConPuntuacion();
}

