/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ERICKPC
 */
public class DescripcionOrganizacion {
    private int iddescripcion;
    private String logo;
    private String imagen;
    private String informacion;
    private int idorganizacion;

    public DescripcionOrganizacion() {
    }

    public DescripcionOrganizacion(int iddescripcion, String logo, String imagen, String informacion, int idorganizacion) {
        this.iddescripcion = iddescripcion;
        this.logo = logo;
        this.imagen = imagen;
        this.informacion = informacion;
        this.idorganizacion = idorganizacion;
    }

    public int getIddescripcion() {
        return iddescripcion;
    }

    public void setIddescripcion(int iddescripcion) {
        this.iddescripcion = iddescripcion;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public int getIdorganizacion() {
        return idorganizacion;
    }

    public void setIdorganizacion(int idorganizacion) {
        this.idorganizacion = idorganizacion;
    }
    
}
