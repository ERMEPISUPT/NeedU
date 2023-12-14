package ModeloDAO;

import Config.Conexion;
import Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SolicitudDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String fechaActual = dateFormat.format(Calendar.getInstance().getTime());
    
    public ResultSet verSolicitudes(int idoportunidad){
        String sql = "SELECT s.*,v.nombre,v.apellido,u.usuario FROM tbsolicitud s JOIN tbvoluntario v ON v.idvoluntario = s.idvoluntario JOIN tbusuario u ON u.idusuario = v.idusuario WHERE idoportunidad = ? and entregado = 0";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,idoportunidad);
            rs = ps.executeQuery();
            System.out.println("Si hay resultados de solicitudes");
            return rs;
        }catch(SQLException ex){
            System.out.println("ModeloDAO.SolicitudDAO.verSolicitudes()");
            return rs=null;
        }
    }
    
    public boolean aceptarSolicitud(int idoportunidad, int idvoluntario){
        String sql="Insert into tbparticipante(idoportunidad, idvoluntario, fecha, estado) values(?,?,?,1)";
        try{
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setInt(1, idoportunidad);
            ps.setInt(2, idvoluntario);
            ps.setString(3,fechaActual);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException e){
            System.out.println("No se pudo insertar el participante en AceptarSolicitud");
            return false;
        }
    }
    public boolean setearSolicitud(int idsolicitud){
        String sql ="update tbsolicitud set entregado = 1 where idsolicitud = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,idsolicitud);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected>0;
        }catch(SQLException e){
            System.out.println("Error al setear Solicitud");
            return false;
        }
    }
    public int obtenerIdvoluntario(int idsolicitud){
        String sql = "select idvoluntario from tbsolicitud where idsolicitud = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idsolicitud);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("idvoluntario");
            }
        }catch(SQLException e){
            System.out.println("No se pudo obtener el idvoluntario por idsolicitud");
        }
        return -1;
    }
    public int obtenerIdOpor(int idsolicitud){
        String sql = "select idoportunidad from tbsolicitud where idsolicitud = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idsolicitud);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("idoportunidad");
            }
        }catch(SQLException e){
            System.out.println("No se pudo obtener el idvoluntario por idsolicitud");
        }
        return -1;
    }
    public boolean existeSolicitud(int idVoluntario, int idOportunidad) {
        String sql = "SELECT * FROM tbsolicitud WHERE idvoluntario = ? AND idoportunidad = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVoluntario);
            ps.setInt(2, idOportunidad);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean crearSolicitud(Solicitud solicitud) {
        String sql = "INSERT INTO tbsolicitud (idoportunidad, idvoluntario, mensaje, entregado) VALUES (?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, solicitud.getIdoportunidad());
            ps.setInt(2, solicitud.getIdvoluntario());
            ps.setString(3, solicitud.getMensaje());
            ps.setInt(4, solicitud.getEntregado());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
