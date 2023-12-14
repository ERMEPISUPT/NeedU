/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDOrganizacion;
import Modelo.Organizacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP
 */
public class OrganizacionDAO implements CRUDOrganizacion{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
        
    @Override
    public List listarOrganizaciones() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Organizacion listar(Organizacion org) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean registrarOrganizacion(Organizacion org) {
        String sql = "Insert into tborganizacion(nombre, tipo, correo, idusuario, idubicacion) values(?,?,?,?,?)";
        try{
            con = (Connection) cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,org.getNombre());
            ps.setString(2,org.getTipo());
            ps.setString(3, org.getCorreo());
            ps.setInt(4, org.getIdusuario());
            ps.setInt(5,org.getIdubicacion());
            int rowsAffected = ps.executeUpdate();
            System.out.println("si agrego datos con filas afectadas: "+ rowsAffected);
            return rowsAffected > 0;
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("tampoco entra al try de registrar organizacion");
            System.out.println("ModeloDAO.OrganizacionDAO.registrarOrganizacion()");
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
            System.out.println("ModeloDAO.OrganizacionDAO.eliminar()");
            return false;
        }
    }
    @Override
    public Organizacion listar(int idusuario) {
        Organizacion organizacion = null;
        String sql = "SELECT * FROM tborganizacion WHERE idusuario = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                organizacion = new Organizacion();
                organizacion.setIdorganizacion(rs.getInt("idorganizacion"));
                organizacion.setNombre(rs.getString("nombre"));
                organizacion.setTipo(rs.getString("tipo"));
                organizacion.setCorreo(rs.getString("correo"));
                organizacion.setIdusuario(rs.getInt("idusuario"));
                organizacion.setIdubicacion(rs.getInt("idubicacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return organizacion;
    }
        @Override
        public boolean editarOrganizacion(Organizacion organizacion) {
            boolean actualizado = false;
            String sql = "UPDATE tborganizacion SET nombre = ?, tipo = ?, correo = ? WHERE idusuario = ?";
                System.out.print("en eidtar organizacion la id usuario es : "+organizacion);
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, organizacion.getNombre());
                ps.setString(2, organizacion.getTipo());
                ps.setString(3, organizacion.getCorreo());
                ps.setInt(4, organizacion.getIdorganizacion());

                int rowsAffected = ps.executeUpdate();
                actualizado = rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return actualizado;
        }

    @Override
    public int obtenerIdorganizacion(int idusuario) {
        Organizacion organizacion = null;
        String sql = "select idorganizacion from tborganizacion where idusuario = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("idorganizacion");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int obtenerIdorganizacionPorOportunidad(int idoportunidad) {
        String sql = "select idorganizacion from tboportunidad where idoportunidad = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,idoportunidad);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("idorganizacion");
            }
        }catch(SQLException ex){
            System.out.println("No se pudo obtener idorganizacion atraves de idoportunidad");
        }
        return -1;
    }
    @Override
    public int idusuario(int idorganizacion) {
        String sql = "select idusuario from tborganizacion where idorganizacion = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,idorganizacion);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("idusuario");
            }
        }catch(SQLException ex){
            System.out.println("No se pudo obtener idusuaario de idogranizacion");
        }
        return -1;
    }
    

    @Override
    public String otbtenerNombreOrganizacion(int idorganizacion) {
        String sql = "select nombre from tborganizacion where idorganizacion = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,idorganizacion);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("nombre");
            }
        }catch(SQLException ex){
            System.out.println("No se pudo obeneter nobmre de organizacion");
        }
        return null;
    }

}
