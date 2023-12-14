/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDUsuario;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ERICKPC
 */
public class UsuarioDAO implements CRUDUsuario{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Usuario usu = new Usuario();
    
    @Override
public int registrarUsuario(Usuario usu) {
    String sql = "Insert into tbusuario(rol, usuario, clave, estado) values(?,?,?,?)";
    try {
        con = (Connection) cn.getConnection();
        ps = con.prepareStatement(sql, new String[]{"idusuario"}); 
        ps.setString(1, usu.getRol());
        ps.setString(2, usu.getUsuario());
        ps.setString(3, usu.getClave());
        ps.setInt(4, usu.getEstado());

        int rowsAffected = ps.executeUpdate();
        System.out.println("anda dentro del try");
        if (rowsAffected > 0) {
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println("si hay auto incre: "+ generatedKeys.getInt(1));
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("No se generó un ID para el usuario.");
            }
        } else {
            throw new SQLException("No se pudo insertar el usuario.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("ModeloDAO.UsuarioDAO.registrarUsuario()");
    } finally {

    }
    return -1;
}


    @Override
    public List listarUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario listar(int idusuario) {
        String sql = "Select * from tbusuario where idusuario = ?";
        try{
            con = (Connection) cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,idusuario);
            rs = ps.executeQuery();
            if(rs.next()){
                usu = new Usuario();
                usu.setIdusuario(Integer.parseInt(rs.getString("idusuario")));
                usu.setRol(rs.getString("rol"));
                usu.setUsuario(rs.getString("usuario"));
                usu.setClave(rs.getString("clave"));
                usu.setEstado(Integer.parseInt(rs.getString("estado")));
            }
        }catch(SQLException e){
            //
        }
        return usu;
    }

    @Override
    public int autenticarUsuario(String usuario, String clave) {
        String sql = "SELECT idusuario FROM tbusuario WHERE usuario = ? and clave = ?";
        try {
            System.out.println("entra al try");
            con = (Connection) cn.getConnection();
            ps = con.prepareStatement(sql, new String[]{"idusuario"});
            
            ps.setString(1, usuario);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            if(rs.next()){
                return Integer.parseInt(rs.getString("idusuario"));
            }
            }catch (SQLException e){
                System.out.println("retornar -1");
            }
        return -1;
        }
    
        public int obtenerIdOrganizacion(int idUsuario) {
        int idOrganizacion = -1; // Valor predeterminado si no se encuentra
        String sql = "SELECT idorganizacion FROM tborganizacion WHERE idusuario = ?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            
            rs = ps.executeQuery();
            if (rs.next()) {
                idOrganizacion = rs.getInt("idorganizacion");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return idOrganizacion;
    }
        
    @Override
    public Usuario listarUsuActual(int idUsuarioActual) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM tbusuario";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setRol(rs.getString("rol"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setClave(rs.getString("clave"));
                usuario.setEstado(rs.getInt("estado"));

                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaUsuarios;
        
        
        
    }

    @Override
    public int obtenerIdUsuarioPorIdOportunidad(int idoportunidad) {
        String sql = "SELECT o.idusuario FROM tborganizacion o JOIN tboportunidad i ON o.idorganizacion = i.idorganizacion WHERE i.idoportunidad = ?";
    
    try {
        con = (Connection) cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idoportunidad);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            return rs.getInt("idusuario");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
    }
    
    return -1;
    }
public Usuario obtenerUsuarioPorId(int idUsuario) {
    Usuario usuario = null;
    String sql = "SELECT * FROM tbusuario WHERE idusuario = ?";

    try {
        con = (Connection) cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        rs = ps.executeQuery();

        if (rs.next()) {
            usuario = new Usuario();
            usuario.setIdusuario(rs.getInt("idusuario"));
            usuario.setRol(rs.getString("rol"));
            usuario.setUsuario(rs.getString("usuario"));
            usuario.setClave(rs.getString("clave"));
            usuario.setEstado(rs.getInt("estado"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Cerrar recursos
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return usuario;
}

    



}