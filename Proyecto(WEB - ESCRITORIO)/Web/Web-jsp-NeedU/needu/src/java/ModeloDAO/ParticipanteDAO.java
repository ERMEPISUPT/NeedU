/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;
import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author ERICKPC
 */
public class ParticipanteDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public ResultSet listarParticipantes(int idoportunidad){
        String sql = "select * from tbparticipante p JOIN tbvoluntario v ON v.idvoluntario = p.idvoluntario WHERE idoportunidad = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,idoportunidad);
            return rs=ps.executeQuery();
        }catch(SQLException ex){
            System.out.println("No se pudo listar participantes");
            return rs=null;
        }
    }
}
