/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDUbicacion;
import Modelo.Ubicacion;
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
public class UbicacionDAO implements CRUDUbicacion{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public int registrarUbicacion(Ubicacion ubi) {
        String sql = "Insert into tbubicacion(pais,ciudad,direccion) values(?,?,?)";
        try {
            con = (Connection) cn.getConnection();
            ps = con.prepareStatement(sql, new String[]{"idubicacion"}); // para obtener el id autoincrementable
            ps.setString(1,ubi.getPais());
            ps.setString(2,ubi.getCiudad());
            ps.setString(3, ubi.getDireccion());
            
            int rowsAffected = ps.executeUpdate();
            System.out.println("anda dentro del try de ubi");
            if (rowsAffected > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    System.out.println("si hay auto incre para ubi: "+ generatedKeys.getInt(1));
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se generó un ID para la ubicacion.");
                }
            } else {
                throw new SQLException("No se pudo insertar la ubicacion.");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    
    
    public List<Ubicacion> listarUbicaciones() {
        List<Ubicacion> listaUbicaciones = new ArrayList<>();
        String sql = "SELECT * FROM tbubicacion";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ubicacion ubi = new Ubicacion();
                ubi.setIdubicacion(rs.getInt("idubicacion"));
                ubi.setPais(rs.getString("pais"));
                ubi.setCiudad(rs.getString("ciudad"));
                ubi.setDireccion(rs.getString("direccion"));
                listaUbicaciones.add(ubi);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaUbicaciones;
    }
    
    
    
    
    
    
    
    
    
}
