package negocio;

import Config.Conexion;
import entidad.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class clsNAdministrador {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean autenticarUsuario(String nombre, String clave) {
        String sql = "SELECT idadmin FROM tbadmin WHERE nombre = ? AND clave = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, clave);
            rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error durante la autenticación: " + e.getMessage());
            return false;
        }
    }
    
    public ResultSet mtdListarVoluntarios(){
        String sql = "Select * from tbvoluntario join tbusuario on tbusuario.idusuario = tbvoluntario.idusuario";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            return ps.executeQuery();
        }catch(SQLException ex){
            System.out.println("No se pudo listar voluntarios");
            return rs = null;
        }
    }
    public ResultSet mtdListarOrganizaciones(){
        String sql = "Select * from tborganizacion join tbusuario on tbusuario.idusuario = tborganizacion.idusuario";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            return ps.executeQuery();
        }catch(SQLException ex){
            System.out.println("No se pudo listar organizacions");
            return rs = null;
        }
    }
    public void mtdCambiarEstado(int idusuario){
        String sql = "UPDATE tbusuario SET estado = (1 - estado) WHERE idusuario = ?;";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,idusuario);
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println("No se pudo actualizar estado");
        }
    }
}
