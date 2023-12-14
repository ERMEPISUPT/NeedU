package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection con = null;
    private String host = " 161.132.47.59";
    private int port = 3306;
    private String database = "db_needu";
    private String user = "erick";
    private String password = "1234qwer";

    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión.");
            System.out.println("URL de conexión: jdbc:mysql://" + host + ":" + port + "/" + database);
            System.out.println("Usuario: " + user);
            System.out.println("Contraseña: " + password);
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
