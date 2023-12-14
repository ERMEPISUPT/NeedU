/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

/**
 *
 * @author fichu
 */
public class clsEAdministrador {    
    private int idadmin;
    private String nombre;
    private String idusuario;
    private String clave;

    public clsEAdministrador() {
    }

    public clsEAdministrador(int idadmin, String nombre, String idusuario, String clave) {
        this.idadmin = idadmin;
        this.nombre = nombre;
        this.idusuario = idusuario;
        this.clave = clave;
    }

    public int getIdadmin() {
        return idadmin;
    }

    public void setIdadmin(int idadmin) {
        this.idadmin = idadmin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    
}
