package ModeloDAO;

import Config.Conexion;
import Modelo.Causa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CausaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Causa> listarCausas() {
        List<Causa> listaCausas = new ArrayList<>();
        String sql = "SELECT * FROM tbcausa";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Causa causa = new Causa();
                causa.setIdcausa(rs.getInt("idcausa"));
                causa.setNombre(rs.getString("nombre"));
                causa.setDescripcion(rs.getString("descripcion"));
                listaCausas.add(causa);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaCausas;
    }

}
