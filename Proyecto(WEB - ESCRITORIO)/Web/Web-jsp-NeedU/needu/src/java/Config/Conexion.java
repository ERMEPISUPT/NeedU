/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

/**
 *
 * @author HP
 */
import java.sql.*;

public class Conexion {
    Connection con = null;
    
    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           //  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_needu", "root", "");
           con = DriverManager.getConnection("jdbc:mysql://161.132.47.59:3306/db_needu", "erick", "1234qwer");
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
    
    public Connection getConnection() {
        return con;
    }
}