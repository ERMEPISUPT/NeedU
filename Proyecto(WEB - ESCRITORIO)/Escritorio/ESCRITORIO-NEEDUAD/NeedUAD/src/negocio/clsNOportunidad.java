package negocio;

import Config.Conexion;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class clsNOportunidad {
    private Conexion conexion;

    public clsNOportunidad() {
        conexion = new Conexion();
    }

    public void visualizarCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo CSV");
        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            String rutaArchivoCSV = fileChooser.getSelectedFile().getAbsolutePath();

            List<String> datos = leerDatosDesdeCSV(rutaArchivoCSV);
            JTextArea textArea = new JTextArea();
            for (String linea : datos) {
                textArea.append(linea + "\n");
            }

            JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "Datos del CSV", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void cargarCSVABaseDeDatos() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo CSV");
        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            String rutaArchivoCSV = fileChooser.getSelectedFile().getAbsolutePath();

            try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivoCSV))) {
                Connection con = conexion.getConnection();

                if (con != null) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] campos = line.split(",");
                        insertarOportunidad(campos);
                    }

                    JOptionPane.showMessageDialog(null, "Datos cargados a la base de datos", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    conexion.closeConnection();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void insertarOportunidad(String[] campos) {
        String sql = "INSERT INTO tboportunidad (titulo, descripcion, fecha, idubicacion, idorganizacion, idcausa, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexion.getConnection().prepareStatement(sql)) {
            for (int i = 0; i < campos.length && i < 7; i++) {
                pstmt.setString(i + 1, campos[i]);
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<String> leerDatosDesdeCSV(String rutaArchivoCSV) {
        List<String> listaDatos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivoCSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                listaDatos.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaDatos;
    }
}
