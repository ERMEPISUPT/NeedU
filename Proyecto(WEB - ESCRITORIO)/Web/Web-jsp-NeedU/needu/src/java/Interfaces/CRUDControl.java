/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Control;
import java.util.List;

/**
 *
 * @author ERICKPC
 */
public interface CRUDControl {
    public int registrarInicio(Control c);
    public boolean registrarSalida(Control c);
    public List<Control> verRegistroUsuario(Control c);
    public List<Control> verTodosRegistros();
}
