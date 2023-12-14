/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDChat;
import Modelo.Chat;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 *
 * @author ERICKPC
 */
public class ChatDAO implements CRUDChat{
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    
    String fechaActual = dateFormat.format(Calendar.getInstance().getTime());
    String horaActual = timeFormat.format(Calendar.getInstance().getTime());
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    

    @Override
    public int crearChat(int idusuario1, int idusuario2) {
        
        String no1 = obtenerNombreUsuario(idusuario1);
        String no2 = obtenerNombreUsuario(idusuario2);
        String sql = "insert into tbchat(idusuario1,idusuario2,fechacreacion,nombre1,nombre2) values(?,?,?,?,?)";
        try {
            System.out.println("entra al try");
            con = (Connection) cn.getConnection();
            ps = con.prepareStatement(sql, new String[]{"idchat"});
            
            ps.setInt(1, idusuario1);
            ps.setInt(2, idusuario2);
            ps.setString(3, fechaActual);
            ps.setString(4, no1);
            ps.setString(5, no2);
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0){
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    System.out.println("Si hay auto incre: " + generatedKeys.getInt(1));
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se generó un ID para el chat.");
                }
            }else return -9999999; // no se pudo crear el chat
        }catch (SQLException e){
                System.out.println("retornar -1");
            }
        return -1;
        }  

    @Override
    public List<Chat> obtenerChatsUsuario(int idusuario) {
        ArrayList<Chat> list = new ArrayList<>();
        String sql = "select * from tbchat where idusuario1=? or idusuario2=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,idusuario);
            ps.setInt(2,idusuario);
            rs=ps.executeQuery();
            while(rs.next())
            {
                Chat chat = new Chat();
                chat.setIdchat(Integer.parseInt(rs.getString("idchat")));
                chat.setIdusuario1(Integer.parseInt(rs.getString("idusuario1")));
                chat.setIdusuario2(Integer.parseInt(rs.getString("idusuario2")));
                chat.setNombre1(rs.getString("nombre1"));
                chat.setNombre2(rs.getString("nombre2"));
                chat.setFechacreacion(rs.getString("fechacreacion"));
                list.add(chat);
            }
        } catch (SQLException e){
            System.out.println();
        }
        return list;
    }

    
    @Override
    public String obtenerNombreUsuario(int idUsuario) {
        String sql = "SELECT nombre FROM tbvoluntario WHERE idusuario = ? UNION ALL SELECT nombre FROM tborganizacion WHERE idusuario = ?";
        System.out.println("el idusuario que estoy pasando a obtener nombre: " + idUsuario);
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,idUsuario);
            ps.setInt(2,idUsuario);
            rs=ps.executeQuery();
            while(rs.next())
            {
                return rs.getString("nombre");
            }
        } catch (SQLException e){
        }
        return null;
    }

    @Override
    public boolean existeChat(int idusuario1, int idusuario2) {
        String sql = "SELECT * FROM tbchat WHERE (idusuario1 = ? AND idusuario2 = ?) OR (idusuario1 = ? AND idusuario2 = ?)";

        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,idusuario1);
            ps.setInt(2,idusuario2);
            ps.setInt(3,idusuario2);
            ps.setInt(4,idusuario1);
            rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("si hay usuarios existentes");
                return true;
            }
        }catch(SQLException e){
            
        }
        return false;
    }

    @Override
    public List<Usuario> obtenerUsuarios(int idChat) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
