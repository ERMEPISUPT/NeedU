/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;
import com.itextpdf.text.pdf.PdfPCell;
import Config.Conexion;
import Interfaces.CRUDAdmin;
import Modelo.Admin;
import Modelo.Control;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.Chunk;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ERICKPC
 */
public class AdminDAO implements CRUDAdmin{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String fechaActual = dateFormat.format(Calendar.getInstance().getTime());
    @Override
    public List listarAdministradores() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Admin list(Admin adm) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean registrarAdmin(Admin adm) {
        String sql = "Insert into tbadmin(nombre, idusuario) values(?,?)";
        try{
            con = (Connection) cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,adm.getNombre());
            ps.setInt(2,adm.getIdusuario());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("ModeloDAO.AdminDAO.registrarAdmin()");
            return false;
        }
    }

    @Override
    public boolean edit(Admin adm) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            System.out.println("ModeloDAO.AdminDAO.eliminar()");
            return false;
        }
    }



    @Override
    public Admin listarPorUsuario(int idUsuario) {
                Admin admin = null;
        String sql = "SELECT * FROM tbadmin WHERE idusuario = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setIdadmin(rs.getInt("idadmin"));
                admin.setNombre(rs.getString("nombre"));
                admin.setIdusuario(rs.getInt("idusuario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
    @Override
    public Admin obtenerAdminPorIDUsuario(int idUsuario) {
        Admin admin = null;
        String sql = "SELECT * FROM tbadmin WHERE idusuario = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setIdadmin(rs.getInt("idadmin"));
                admin.setNombre(rs.getString("nombre"));
                admin.setIdusuario(rs.getInt("idusuario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    
        @Override
        public boolean editar(Admin adm) {
            boolean actualizado = false;
            String sql = "UPDATE tbadmin SET nombre = ? WHERE idadmin = ?";

            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, adm.getNombre());
                ps.setInt(2, adm.getIdadmin());

                int rowsAffected = ps.executeUpdate();
                actualizado = rowsAffected > 0;

                if (actualizado) {
                    System.out.println("¡Nombre actualizado con éxito!");
                } else {
                    System.out.println("¡La actualización falló!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("¡Error al ejecutar la actualización!");
            }
            return actualizado;
            
        }

    @Override
    public String generarInforme(List<Control> controles) {
        String nombreArchivo = "informe.pdf";
        try {
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            
            PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));

            document.open();

            // Encabezado
            Paragraph header = new Paragraph("Informe de Sesiones de Usuarios");
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);

            // Texto informativo
            Paragraph info = new Paragraph("Este informe detalla las sesiones de usuarios realizadas en la página." + "               " + fechaActual);
            document.add(info);
            document.add(Chunk.NEWLINE);

            // Texto simulado del informe
            Paragraph texto = new Paragraph(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vitae lectus ut dolor porta hendrerit. "
                + "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. "
                + "Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; "
                + "Fusce venenatis felis eu mauris tincidunt, vel faucibus augue malesuada. "
                + "Duis consequat tincidunt est. Nullam lacinia odio vitae velit interdum convallis. "
                + "Sed tristique est vel odio convallis, ac interdum ipsum facilisis. "
                + "Vestibulum vel nunc tristique, feugiat odio at, malesuada dui."
            );
            document.add(texto);
            document.add(Chunk.NEWLINE);

            // Estadísticas simuladas
            int cantidadSesiones = controles.size();
            Paragraph estadisticas = new Paragraph("Total de sesiones registradas: " + cantidadSesiones);
            document.add(estadisticas);
            document.add(Chunk.NEWLINE);

            LineSeparator separador = new LineSeparator();
            document.add(separador);
            document.add(Chunk.NEWLINE);
            
            PdfPTable table = new PdfPTable(5); 

            table.addCell("ID Control");
            table.addCell("ID Usuario");
            table.addCell("Usuario");
            table.addCell("Hora de entrada");
            table.addCell("Hora de salida");

            for (Control control : controles) {
                table.addCell(String.valueOf(control.getIdcontrol()));
                table.addCell(String.valueOf(control.getIdusuario()));
                table.addCell(control.getUsuario());
                table.addCell(control.getHora_entrada());
                table.addCell(control.getHora_salida());
            }

            document.add(table);
            
            Paragraph firma = new Paragraph("_____________");
            Paragraph autor = new Paragraph("Administrador");
            
            firma.setAlignment(Element.ALIGN_RIGHT);
            autor.setAlignment(Element.ALIGN_RIGHT);
            document.add(firma);
            document.add(autor);

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            System.out.println("no se generó el pdf");
            e.printStackTrace();
        }
        return nombreArchivo;
    }
    




    
}
