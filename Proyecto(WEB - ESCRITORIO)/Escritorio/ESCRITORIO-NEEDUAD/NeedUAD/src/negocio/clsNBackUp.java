package negocio;

import Config.Conexion;
import java.sql.DatabaseMetaData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class clsNBackUp {

    public static void exportarTablas(Conexion conexion, String savePath) {
        try {
            Connection connection = conexion.getConnection();
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(savePath)));

            writer.println("CREATE DATABASE IF NOT EXISTS `db_needu`;");
            writer.println("USE `db_needu`;");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SHOW TABLES");

            while (resultSet.next()) {
                String tableName = resultSet.getString(1);
                scriptTabla(connection, tableName, writer);
            }

            writer.close();
            System.out.println("Backup exitoso. Guardado en: " + savePath);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            conexion.closeConnection();
        }
    }

    private static void scriptTabla(Connection connection, String tableName, PrintWriter writer) {
        try {
            Statement statement = connection.createStatement();
            ResultSet tableMetadata = statement.executeQuery("SHOW CREATE TABLE " + tableName);
            tableMetadata.next();
            String createTableQuery = tableMetadata.getString(2);

            writer.println(createTableQuery + ";");

            writer.println("-- Data for table: " + tableName);
            writer.println("DELETE FROM " + tableName + ";");

            ResultSet tableData = statement.executeQuery("SELECT * FROM " + tableName);

            while (tableData.next()) {
                StringBuilder replaceStatement = new StringBuilder("REPLACE INTO " + tableName + " VALUES (");

                int columnCount = tableData.getMetaData().getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    Object value = tableData.getObject(i);
                    if (value == null) {
                        replaceStatement.append("NULL");
                    } else if (value instanceof String) {
                        replaceStatement.append("'").append(value).append("'");
                    } else {
                        replaceStatement.append(value);
                    }

                    if (i < columnCount) {
                        replaceStatement.append(", ");
                    }
                }

                replaceStatement.append(");");
                writer.println(replaceStatement.toString());
            }

            writer.println(); 

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     public static void reemplazarDatosTablas(Conexion conexion, String savePath) {
        try {
            Connection connection = conexion.getConnection();
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(savePath, true))); 

            writer.println("CREATE DATABASE IF NOT EXISTS `db_needu`;");
            writer.println("USE `db_needu`;");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SHOW TABLES");

            while (resultSet.next()) {
                String tableName = resultSet.getString(1);
                ScriptDatosTabla(connection, tableName, writer);
            }

            writer.close();
            System.out.println("Reemplazo de datos exitoso. Guardado en: " + savePath);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            conexion.closeConnection();
        }
    }

    private static void ScriptDatosTabla(Connection connection, String tableName, PrintWriter writer) {
        try {
            Statement statement = connection.createStatement();
            ResultSet tableData = statement.executeQuery("SELECT * FROM " + tableName);

            writer.println("-- Data for table: " + tableName);
            writer.println("DELETE FROM " + tableName + ";");

            while (tableData.next()) {
                StringBuilder replaceStatement = new StringBuilder("REPLACE INTO " + tableName + " VALUES (");

                int columnCount = tableData.getMetaData().getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    Object value = tableData.getObject(i);
                    if (value == null) {
                        replaceStatement.append("NULL");
                    } else if (value instanceof String) {
                        replaceStatement.append("'").append(value).append("'");
                    } else {
                        replaceStatement.append(value);
                    }

                    if (i < columnCount) {
                        replaceStatement.append(", ");
                    }
                }

                replaceStatement.append(");");
                writer.println(replaceStatement.toString());
            }

            writer.println(); 

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
public static List<String> listarTablas(Connection connection) {
    List<String> nombresTablas = new ArrayList<>();
    ResultSet resultSet = null;

    try {
        DatabaseMetaData metaData = connection.getMetaData();
        resultSet = metaData.getTables(null, null, "%", new String[]{"TABLE"});

        while (resultSet.next()) {
            String tableName = resultSet.getString("TABLE_NAME");
            nombresTablas.add(tableName);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return nombresTablas;
}


}
