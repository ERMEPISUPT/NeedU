/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDControl;
import Modelo.Control;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

/**
 *
 * @author ERICKPC
 */
public class ControlDAO implements CRUDControl{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Control c = new Control();
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String fechaActual = dateFormat.format(Calendar.getInstance().getTime());
    String horaActual = timeFormat.format(Calendar.getInstance().getTime());
    
    
    @Override
    public int registrarInicio(Control c) {
        String fechaYHoraActual = dateTimeFormat.format(Calendar.getInstance().getTime());
        String sql = "insert into tbcontrol(usuario, idusuario, hora_entrada) values(?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql,new String[]{"idcontrol"});
            ps.setString(1,c.getUsuario());
            ps.setInt(2,c.getIdusuario());
            ps.setString(3,fechaYHoraActual);
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected>0){
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    System.out.println("si hay auto incre: "+ generatedKeys.getInt(1));
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se generó un ID para el usuario.");
                }
            }else System.out.println("No se pudo registrar el control.");
        }catch(SQLException e){
            System.out.println("No se insertó ningun registro");
            return -1;
        }
        return -1;
    }

    @Override
    public boolean registrarSalida(Control c) {
        String fechaYHoraActual = dateTimeFormat.format(Calendar.getInstance().getTime());
        String sql = "Update tbcontrol set hora_salida = ? where idcontrol = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, fechaYHoraActual);
            ps.setInt(2, c.getIdcontrol());
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0 ){
                return true;
            }else System.out.println("No se modifico ninguna fila.");
        }catch (SQLException e){
            System.out.println("No se realizó el try.");
            return false;
        }
        return false;
    }

    @Override
    public List<Control> verRegistroUsuario(Control c) {
        ArrayList<Control> list = new ArrayList<>();
        String sql = "select * from tbcontrol where idusuario = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,c.getIdusuario());
            rs=ps.executeQuery();
            while(rs.next()){
                Control control = new Control();
                control.setIdcontrol(rs.getInt("idcontrol"));
                control.setIdusuario(rs.getInt("idusuario"));
                control.setUsuario(rs.getString("usuario"));
                control.setHora_entrada(rs.getString("hora_entrada"));
                control.setHora_salida(rs.getString("hora_salida"));
                list.add(control);
            }
        }catch(SQLException e){
            System.out.println("No se realizó el try de verRegistroUsuario");
        }
        return list;
    }

    @Override
    public List<Control> verTodosRegistros() {
        ArrayList<Control> list = new ArrayList<>();
        String sql = "select * from tbcontrol";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Control control = new Control();
                control.setIdcontrol(rs.getInt("idcontrol"));
                control.setIdusuario(rs.getInt("idusuario"));
                control.setUsuario(rs.getString("usuario"));
                control.setHora_entrada(rs.getString("hora_entrada"));
                control.setHora_salida(rs.getString("hora_salida"));
                list.add(control);
            }
        }catch(SQLException e){
            System.out.println("No se realizó el try de verTodosRegistros");
        }
        return list;
    }
    
}
