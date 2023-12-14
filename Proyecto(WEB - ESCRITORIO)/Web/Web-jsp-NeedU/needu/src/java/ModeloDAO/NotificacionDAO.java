/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDNotificacion;
import Modelo.Notificacion;
import Modelo.Oportunidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author ERICKPC
 */
public class NotificacionDAO implements CRUDNotificacion{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Notificacion not = new Notificacion();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String fechaActual = dateFormat.format(Calendar.getInstance().getTime());
    @Override
    public List listarMensajes(int idusuario) {
        String sql = "select * from tbnotificacion where visto = 0 and idcategoria = 1 and idusuario = ?";
        ArrayList<Notificacion> list = new ArrayList<>();
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,idusuario);
            rs=ps.executeQuery();
            
            while(rs.next())
            {
                Notificacion not=new Notificacion();
                not.setFecha(rs.getString("fecha"));
                not.setMensaje(rs.getString("mensaje"));
                list.add(not);
            }
        } catch (SQLException e){
            System.out.println();
        }
        return list;
    }

    @Override
    public List listarSolicitudes(int idusuario) {
        String sql = "select * from tbnotificacion where visto = 0 and idcategoria = 2 and idusuario = ?";
        ArrayList<Notificacion> list = new ArrayList<>();
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,idusuario);
            rs=ps.executeQuery();
            while(rs.next())
            {
                Notificacion not=new Notificacion();
                not.setFecha(rs.getString("fecha"));
                not.setMensaje(rs.getString("mensaje"));
                list.add(not);
            }
        } catch (SQLException e){
            System.out.println();
        }
        return list;
    }

    @Override
    public List listarAprobaciones(int idusuario) {
        String sql = "select * from tbnotificacion where visto = 0 and idcategoria = 3 and idusuario = ?";
        ArrayList<Notificacion> list = new ArrayList<>();
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,idusuario);
            rs=ps.executeQuery();
            while(rs.next())
            {
                Notificacion not=new Notificacion();
                not.setFecha(rs.getString("fecha"));
                not.setMensaje(rs.getString("mensaje"));
                list.add(not);
            }
        } catch (SQLException e){
            System.out.println();
        }
        return list;
    }

    @Override
    public boolean add(int idusuario, String Emisor, int idcategoria) {
        System.out.println("valor de Emisor : " + Emisor);
        String sql = "insert into tbnotificacion(idusuario, mensaje, fecha, visto, idcategoria) values(?,?,?,?,?)";
        String mensaje = null;
        switch (idcategoria) {
            case 1:
                mensaje = "Mensajes de " + Emisor + " sin leer";
                break;
            case 2:
                mensaje = "Nueva solicitud pendiente de " + Emisor + ".";
                break;
            case 3:
                mensaje = "Aprobacion aceptada para participar en " + Emisor + ".";
                break;
            default:
                break;
        }
        System.out.println("vbababa");
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            ps.setString(2, mensaje);
            ps.setString(3, fechaActual);
            ps.setInt(4, 0);
            ps.setInt(5, idcategoria);
            int rowsAffected = ps.executeUpdate();
            System.out.println("se agregó una nueva notificacion");
            return rowsAffected > 0;
        }catch(SQLException e){
            System.out.println("ModeloDAO.NotificacionDAO.add()");
           return false; 
        }
    }

    @Override
    public boolean vistearNotificacion(int idusuario) {
        String sql = "update tbnotificacion set visto = 1 where idusuario = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,idusuario);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0 ;
        }catch(SQLException e){
        return false;
        }
    }
    
    
    
}
