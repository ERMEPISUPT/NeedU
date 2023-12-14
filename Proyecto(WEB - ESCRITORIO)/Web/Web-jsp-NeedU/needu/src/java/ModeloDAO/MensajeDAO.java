/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDMensaje;
import Modelo.Mensaje;
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
public class MensajeDAO implements CRUDMensaje{
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat timeFormatHora = new SimpleDateFormat("HH:mm");
    String soloHora = timeFormatHora.format(Calendar.getInstance().getTime());
    String fechaActual = dateFormat.format(Calendar.getInstance().getTime());
    String horaActual = timeFormat.format(Calendar.getInstance().getTime());
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String fechaYHoraActual = dateTimeFormat.format(Calendar.getInstance().getTime());
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public boolean enviarMensaje(int idChat, int emisor, int receptor, String mensaje) {
        String sql = "INSERT INTO tbmensaje(idchat,emisor,receptor,mensaje,fechahora) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idChat);
            ps.setInt(2,emisor );
            ps.setInt(3, receptor);
            ps.setString(4,mensaje);
            ps.setString(5,fechaYHoraActual);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

   

    @Override
    public List<Mensaje> obtenerMensajesChat(int idChat) {
    List<Mensaje> listaMensajes = new ArrayList<>();
    String sql = "SELECT m.idmensaje, m.idchat, m.emisor, m.receptor, m.mensaje, m.fechahora,emisor.usuario AS nombre_emisor, receptor.usuario AS nombre_receptor FROM tbmensaje m JOIN tbusuario emisor ON m.emisor = emisor.idusuario JOIN tbusuario receptor ON m.receptor = receptor.idusuario WHERE m.idchat = ? ORDER BY idmensaje ASC";
    
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idChat);
        rs = ps.executeQuery();
        
        while (rs.next()) {
            Mensaje mensaje = new Mensaje();
            mensaje.setIdmensaje(rs.getInt("idmensaje"));
            mensaje.setIdchat(rs.getInt("idchat"));
            mensaje.setEmisor(rs.getInt("emisor"));
            mensaje.setReceptor(rs.getInt("receptor"));
            mensaje.setMensaje(rs.getString("mensaje"));
            mensaje.setFechahora(rs.getString("fechahora"));
            mensaje.setNombre_emisor(rs.getString("nombre_emisor"));
            mensaje.setNombre_receptor(rs.getString("nombre_receptor"));
            mensaje.setHora(soloHora);
            listaMensajes.add(mensaje);
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
        return listaMensajes;
    }


    @Override
    public List<Mensaje> obtenerUltimosMensajesUsuario(int idUsuario) {
        List<Mensaje> mensajes = new ArrayList<>();
        String sql = "SELECT m.*, c.idusuario1 as usuario1, c.idusuario2 as usuario2 " +
                    "FROM mensaje m " +
                    "INNER JOIN chat c ON m.idchat = c.idchat " +
                    "WHERE (c.idusuario1 = ? OR c.idusuario2 = ?) " +
                    "ORDER BY m.fechahora DESC";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,idUsuario);
            ps.setInt(2,idUsuario);
            rs=ps.executeQuery();
            while(rs.next()){
                    Mensaje mensaje = new Mensaje();
                    mensaje.setIdmensaje(rs.getInt("idmensaje"));
                    mensaje.setIdchat(rs.getInt("idchat"));
                    mensaje.setEmisor(rs.getInt("emisor"));
                    mensaje.setReceptor(rs.getInt("receptor"));
                    mensaje.setMensaje(rs.getString("mensaje"));
                    mensaje.setFechahora(rs.getString("fechahora"));
                    mensajes.add(mensaje);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return mensajes;
    }

    @Override
    public boolean marcarMensajesLeidos(int idchat, int idusuario) {
        String sql = "UPDATE mensaje SET visto = 1 WHERE idchat = ? AND receptor = ? AND leido = 0";
        try {
                con = (Connection) cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1,idchat);
                ps.setInt(2,idusuario);
                
                int rowsAffected = ps.executeUpdate(); 
                return true;
            } catch (SQLException ex) {
                
            }
        return false;
    }
}
