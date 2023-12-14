/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.Date;
/**
 *
 * @author ERICKPC
 */
public class Voluntario {
    private int idvoluntario;
    private String nombre;
    private String apellido;
    private String genero;
    private int edad;
    private String correo;
    private String fecharegistro;
    private int idusuario;
    private int idhabilidad;
    private int idinteres;
    private int idubicacion;

    public Voluntario() {
    }

    public Voluntario(int idvoluntario, String nombre, String apellido, String genero, int edad, String correo, String fecharegistro, int idusuario, int idhabilidad, int idinteres, int idubicacion) {
        this.idvoluntario = idvoluntario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.edad = edad;
        this.correo = correo;
        this.fecharegistro = fecharegistro;
        this.idusuario = idusuario;
        this.idhabilidad = idhabilidad;
        this.idinteres = idinteres;
        this.idubicacion = idubicacion;
    }

    public int getIdvoluntario() {
        return idvoluntario;
    }

    public void setIdvoluntario(int idvoluntario) {
        this.idvoluntario = idvoluntario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(String fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdhabilidad() {
        return idhabilidad;
    }

    public void setIdhabilidad(int idhabilidad) {
        this.idhabilidad = idhabilidad;
    }

    public int getIdinteres() {
        return idinteres;
    }

    public void setIdinteres(int idinteres) {
        this.idinteres = idinteres;
    }

    public int getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(int idubicacion) {
        this.idubicacion = idubicacion;
    }
    
    
    
    
}
