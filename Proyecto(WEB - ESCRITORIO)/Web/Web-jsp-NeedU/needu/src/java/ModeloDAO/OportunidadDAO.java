package ModeloDAO;

import Config.Conexion;
import Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OportunidadDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    
    public String obtenerTituloOpo(int idsolicitud){
        String sql = "SELECT o.titulo FROM tboportunidad o JOIN tbsolicitud s ON s.idoportunidad = o.idoportunidad WHERE idsolicitud = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,idsolicitud);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("titulo");
            }
        }catch(SQLException e){
            System.out.println("No se pudo obtener titulo por idsolicitud");
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public boolean crearOportunidad(Oportunidad opo) {
        String sql = "INSERT INTO tboportunidad (titulo, descripcion, fecha, idubicacion, idorganizacion, idcausa, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, opo.getTitulo());
            ps.setString(2, opo.getDescripcion());
            ps.setString(3, opo.getFecha());
            ps.setInt(4, opo.getIdubicacion());
            ps.setInt(5, opo.getIdorganizacion());
            ps.setInt(6, opo.getIdcausa());
            ps.setInt(7, opo.getEstado());

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear oportunidad: " + e.getMessage());
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
    
    
    
    public List<Map<String, Object>> listarOportunidades() {
        List<Map<String, Object>> lista = new ArrayList<>();
        String sql = "SELECT o.*, org.nombre as nombreOrganizacion FROM tboportunidad o "
                   + "JOIN tborganizacion org ON o.idorganizacion = org.idorganizacion "
                   + "WHERE o.estado = 0";

        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> opo = new HashMap<>();
                opo.put("idoportunidad", rs.getInt("idoportunidad"));
                opo.put("titulo", rs.getString("titulo"));
                opo.put("descripcion", rs.getString("descripcion"));
                opo.put("fecha", rs.getString("fecha"));
                opo.put("idubicacion", rs.getInt("idubicacion"));
                opo.put("idorganizacion", rs.getInt("idorganizacion"));
                opo.put("idcausa", rs.getInt("idcausa"));
                opo.put("estado", rs.getInt("estado"));
                opo.put("nombreOrganizacion", rs.getString("nombreOrganizacion"));
                lista.add(opo);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return lista;
    }
    
    public List<Oportunidad> listarOportunidadesPorOrganizacion(int idOrganizacion) {
        List<Oportunidad> oportunidades = new ArrayList<>();
        String sql = "SELECT * FROM tboportunidad WHERE idorganizacion = ? AND estado = 0"; // Asumiendo que 'estado = 0' significa activo
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idOrganizacion);
            rs = ps.executeQuery();

            while (rs.next()) {
                Oportunidad opo = new Oportunidad();
                opo.setIdoportunidad(rs.getInt("idoportunidad"));
                opo.setTitulo(rs.getString("titulo"));
                opo.setDescripcion(rs.getString("descripcion"));
                opo.setFecha(rs.getString("fecha")); // Asegúrate de que este campo coincida con tu base de datos
                opo.setIdubicacion(rs.getInt("idubicacion"));
                opo.setIdorganizacion(rs.getInt("idorganizacion"));
                opo.setIdcausa(rs.getInt("idcausa"));
                opo.setEstado(rs.getInt("estado"));

                oportunidades.add(opo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return oportunidades;
    }  
    
    public boolean eliminarOportunidad(int idOportunidad) {
        String sql = "DELETE FROM tboportunidad WHERE idoportunidad = ?";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idOportunidad);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar la oportunidad: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    public List<Map<String, Object>> buscarPorTituloOOrganizacion(String terminoBusqueda) {
        List<Map<String, Object>> lista = new ArrayList<>();
            String sql = "SELECT o.*, org.nombre as nombreOrganizacion FROM tboportunidad o "
                       + "JOIN tborganizacion org ON o.idorganizacion = org.idorganizacion "
                       + "WHERE o.titulo LIKE ? OR org.nombre LIKE ?";
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + terminoBusqueda + "%");
                ps.setString(2, "%" + terminoBusqueda + "%");
                rs = ps.executeQuery();

                while (rs.next()) {
                    Map<String, Object> opo = new HashMap<>();
                    opo.put("idoportunidad", rs.getInt("idoportunidad"));
                    opo.put("titulo", rs.getString("titulo"));
                    opo.put("descripcion", rs.getString("descripcion"));
                    opo.put("fecha", rs.getString("fecha"));
                    opo.put("idubicacion", rs.getInt("idubicacion"));
                    opo.put("idorganizacion", rs.getInt("idorganizacion"));
                    opo.put("idcausa", rs.getInt("idcausa"));
                    opo.put("estado", rs.getInt("estado"));
                    opo.put("nombreOrganizacion", rs.getString("nombreOrganizacion"));
                    lista.add(opo);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
            }
            return lista;
        }

    public Oportunidad getOportunidadById(int id) {
        String sql = "SELECT *,g.nombre AS nombre_org  FROM tboportunidad AS o INNER JOIN tbubicacion AS u ON u.idubicacion = o.idubicacion INNER JOIN tbcausa AS c ON c.idcausa = o.idcausa INNER JOIN tborganizacion g ON g.idorganizacion = o.idorganizacion WHERE o.idoportunidad = ?";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            System.out.println("ID que se está buscando: " + id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Oportunidad(
                        rs.getInt("idoportunidad"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("fecha"),
                        rs.getInt("idubicacion"),
                        rs.getInt("idorganizacion"),
                        rs.getInt("idcausa"),
                        rs.getInt("estado"),
                        rs.getString("descripcion"),
                        rs.getString("pais"),
                        rs.getString("ciudad"),
                            rs.getString("direccion"),
                            rs.getString("nombre_org")
                    );
                } else {
                    System.out.println("Oportunidad no encontrada en la base de datos.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;
    }

    
    

}
