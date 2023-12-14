package entidad;

import java.util.Date;

public class clsECalificacion {
    private int idCalificacion;
    private int idUsuariocalificador;
    private int idUsuariocalificado;
    private int puntuacion;
    private String comentario;
    private String fecha;


    public clsECalificacion() {
    }

    public clsECalificacion(int idCalificacion, int idUsuariocalificador, int idUsuariocalificado, int puntuacion, String comentario, String fecha) {
        this.idCalificacion = idCalificacion;
        this.idUsuariocalificador = idUsuariocalificador;
        this.idUsuariocalificado = idUsuariocalificado;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public int getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public int getIdUsuariocalificador() {
        return idUsuariocalificador;
    }

    public void setIdUsuariocalificador(int idUsuariocalificador) {
        this.idUsuariocalificador = idUsuariocalificador;
    }

    public int getIdUsuariocalificado() {
        return idUsuariocalificado;
    }

    public void setIdUsuariocalificado(int idUsuariocalificado) {
        this.idUsuariocalificado = idUsuariocalificado;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
}
