/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDVoluntario;
import Modelo.Voluntario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author HP
 */
public class VoluntarioDAO implements CRUDVoluntario{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Date fechaActual = new Date();
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    String fechaFormateada = formatoFecha.format(fechaActual);

    @Override
    public List listarVoluntarios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Voluntario listar(Voluntario vol) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean registrarVoluntario(Voluntario vol) {
        String sql = "Insert into tbvoluntario(nombre, apellido, genero, edad, correo, fecharegistro, idusuario, idubicacion) values(?,?,?,?,?,?,?,?)";
        try{
            con = (Connection) cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,vol.getNombre());
            ps.setString(2,vol.getApellido());
            ps.setString(3, vol.getGenero());
            ps.setInt(4, vol.getEdad());
            ps.setString(5, vol.getCorreo());
            ps.setString(6,fechaFormateada);
            ps.setInt(7, vol.getIdusuario());
            ps.setInt(8, vol.getIdubicacion());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean eliminar(String id) {
        String sql = "Update tbusuario SET  estado = 0 WHERE idusuario = ?";
        
        try {
            con = (Connection) cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ModeloDAO.VoluntarioDAO.eliminar()");
            return false;
        }
    }
    
    /**
     *
     * @param idusuario
     * @return
     */
        @Override

        public Voluntario datosVoluntario(int idusuario) {
            Voluntario voluntario = null;
        String sql = "SELECT * FROM tbvoluntario WHERE idusuario = ?";

        try {
            con = (Connection) cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                voluntario = new Voluntario();
                voluntario.setIdvoluntario(rs.getInt("idvoluntario"));
                voluntario.setNombre(rs.getString("nombre"));
                voluntario.setApellido(rs.getString("apellido"));
                voluntario.setGenero(rs.getString("genero"));
                voluntario.setEdad(rs.getInt("edad"));
                voluntario.setCorreo(rs.getString("correo"));
                voluntario.setFecharegistro(rs.getString("fecharegistro"));
                voluntario.setIdusuario(rs.getInt("idusuario"));
                voluntario.setIdhabilidad(rs.getInt("idhabilidad"));
                voluntario.setIdinteres(rs.getInt("idinteres"));
                voluntario.setIdubicacion(rs.getInt("idubicacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voluntario;
    }

    @Override
     public Voluntario listar(int idusuario) {
         Voluntario voluntario = null;
         String sql = "SELECT * FROM tbvoluntario WHERE idusuario = ?";

         try {
             con = cn.getConnection();
             ps = con.prepareStatement(sql);
             ps.setInt(1, idusuario);
             rs = ps.executeQuery();

             if (rs.next()) {
                 voluntario = new Voluntario();
                 voluntario.setIdvoluntario(rs.getInt("idvoluntario"));
                 voluntario.setNombre(rs.getString("nombre"));
                 voluntario.setApellido(rs.getString("apellido"));
                 voluntario.setGenero(rs.getString("genero"));
                 voluntario.setEdad(rs.getInt("edad"));
                 voluntario.setCorreo(rs.getString("correo"));
                 voluntario.setFecharegistro(rs.getString("fecharegistro"));
                 voluntario.setIdusuario(rs.getInt("idusuario"));
                 voluntario.setIdhabilidad(rs.getInt("idhabilidad"));
                 voluntario.setIdinteres(rs.getInt("idinteres"));
                 voluntario.setIdubicacion(rs.getInt("idubicacion"));
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return voluntario;
     }
        @Override
        public boolean editarVoluntario(Voluntario vol) {
            String sql = "UPDATE tbvoluntario SET nombre=?, apellido=?, genero=?, edad=?, correo=? WHERE idvoluntario=?";
            try {
                con = (Connection) cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, vol.getNombre());
                ps.setString(2, vol.getApellido());
                ps.setString(3, vol.getGenero());
                ps.setInt(4, vol.getEdad());
                ps.setString(5, vol.getCorreo());
                ps.setInt(6, vol.getIdvoluntario()); 
                int rowsAffected = ps.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        @Override
            public boolean actualizarEstado(int idUsuario, int estado) {
            boolean actualizado = false;
            Connection con = null;
            PreparedStatement ps = null;

            String sql = "UPDATE tbusuario SET estado = ? WHERE idusuario = ?";

            try {
                con = (Connection) cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, estado);
                ps.setInt(2, idUsuario);
                int rowsAffected = ps.executeUpdate();
                actualizado = rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
            }
            return actualizado;
        }
            public int obtenerIdVoluntarioPorIdUsuario(int idUsuario) {
        String sql = "SELECT idvoluntario FROM tbvoluntario WHERE idusuario = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("idvoluntario");
            }
        } catch (SQLException e) {
            System.out.println("Error en VoluntarioDAO.obtenerIdVoluntarioPorIdUsuario: " + e.getMessage());
        }
        return -1; 
    }
            public int obtenerIDUsuarioporIdVol(int idvoluntario){
                String sql = "SELECT idusuario FROM tbvoluntario WHERE idvoluntario = ?";
                try {
                    con = cn.getConnection();
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, idvoluntario);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        return rs.getInt("idusuario");
                    }
                } catch (SQLException e) {
                    System.out.println("No se pudo obtener idusuario de voluntario id" + e.getMessage());
                }
                return -1; 
            }
            @Override
            public String otbtenerNombreVoluntario(int idvoluntario) {
        String sql = "select nombre,apellido from tbvoluntario where idvoluntario = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,idvoluntario);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("nombre") +" " + rs.getString("apellido");
            }
        }catch(SQLException ex){
            System.out.println("No se pudo obeneter nobmre de organizacion");
        }
        return null;
    }
}



