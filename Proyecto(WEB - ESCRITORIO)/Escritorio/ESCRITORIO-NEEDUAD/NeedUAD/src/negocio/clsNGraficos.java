package negocio;

import Config.Conexion;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.data.general.PieDataset;
import java.util.List;
import org.jfree.data.general.DefaultPieDataset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.AttributedString;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;

public class clsNGraficos {
    private Conexion cn = new Conexion();
    private JButton btnBarras;
    private JButton btnPastel;
    private JButton btnBarras2;
    
public clsNGraficos(JButton btnBarras, JButton btnPastel, JButton btnBarras2) {
        this.btnBarras = btnBarras;
        this.btnPastel = btnPastel;
        this.btnBarras2 = btnBarras2;
    }

public CategoryDataset obtenerDatosCalificacionesPorUsuarioConNombres() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    Connection con = cn.getConnection();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        statement = con.prepareStatement(
                "SELECT u.usuario, c.idusuariocalificador, COUNT(c.idcalificacion) AS cantidad_calificaciones " +
                        "FROM tbcalificacion c " +
                        "JOIN tbusuario u ON c.idusuariocalificador = u.idusuario " +
                        "GROUP BY c.idusuariocalificador");
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String usuario = resultSet.getString("usuario");
            int idUsuario = resultSet.getInt("idusuariocalificador");
            int cantidadCalificaciones = resultSet.getInt("cantidad_calificaciones");

            dataset.addValue(cantidadCalificaciones, "Calificaciones", usuario);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeResources(con, statement, resultSet);
    }

    return dataset;
}

  public DefaultPieDataset obtenerDatosChatsPorUsuario() {
    DefaultPieDataset dataset = new DefaultPieDataset();

    Connection con = cn.getConnection();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        statement = con.prepareStatement(
                "SELECT CONCAT(u.usuario, ' (', u.usuario, ')') AS usuario_concatenado, COUNT(c.idchat) AS cantidad_chats " +
                        "FROM tbchat c " +
                        "JOIN tbusuario u ON c.idusuario1 = u.idusuario OR c.idusuario2 = u.idusuario " +
                        "GROUP BY u.idusuario, usuario_concatenado");
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String usuarioConcatenado = resultSet.getString("usuario_concatenado");
            int cantidadChats = resultSet.getInt("cantidad_chats");

            dataset.setValue(usuarioConcatenado, cantidadChats);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeResources(con, statement, resultSet);
    }

    return dataset;
}

public CategoryDataset obtenerDatosOportunidadesPorOrganizacion() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    Connection con = cn.getConnection();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        statement = con.prepareStatement(
                "SELECT o.idorganizacion, COUNT(o.idoportunidad) AS cantidad_oportunidades, " +
                        "org.nombre AS nombre_organizacion " +
                        "FROM tboportunidad o " +
                        "JOIN tborganizacion org ON o.idorganizacion = org.idorganizacion " +
                        "GROUP BY o.idorganizacion, nombre_organizacion");
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String nombreOrganizacion = resultSet.getString("nombre_organizacion");
            int cantidadOportunidades = resultSet.getInt("cantidad_oportunidades");

            dataset.addValue(cantidadOportunidades, "Oportunidades", nombreOrganizacion);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeResources(con, statement, resultSet);
    }

    return dataset;
}

    
    
    private void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
    try {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public void generarInformePDF(String savePath, String informeText) throws DocumentException, IOException {
    Document document = new Document();

    try {
        PdfWriter.getInstance(document, new FileOutputStream(savePath));
        document.open();
        document.newPage();
        agregarPortada(document);
                // PRIMER GRAFICO
                // PRIMER GRAFICO
                // PRIMER GRAFICO
        document.newPage();        
        document.add(new Paragraph(informeText));

        CategoryDataset barChartDataset = obtenerDatosCalificacionesPorUsuarioConNombres();
        JFreeChart barChart = ChartFactory.createBarChart(
                "Calificaciones por Usuario",
                "Usuario",
                "Cantidad de Calificaciones",
                barChartDataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        CategoryPlot barChartPlot = barChart.getCategoryPlot();
        BarRenderer barChartRenderer = (BarRenderer) barChartPlot.getRenderer();
        barChartRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", NumberFormat.getInstance()));
        barChartRenderer.setBaseItemLabelsVisible(true);

        BufferedImage barChartImage = barChart.createBufferedImage(560, 370);
        Image barChartImageElement = Image.getInstance(barChartImage, null);
        barChartImageElement.setAlignment(Element.ALIGN_CENTER);
        document.add(barChartImageElement);
        
        document.add(new Paragraph(obtenerInformacionCalificacionesPorUsuario()));
        int ConCali=contarCalificaciones();
        document.add(new Paragraph("Total de Calificaciones: " + ConCali));

        document.newPage();

                //SEGUNDO GRAFICO
                //SEGUNDO GRAFICO
                //SEGUNDO GRAFICO
                //SEGUNDO GRAFICO
        DefaultPieDataset pieChartDataset = obtenerDatosChatsPorUsuario();
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Chats por Usuario",
                pieChartDataset,
                true,
                true,
                false
        );

        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setLabelGenerator(new CustomPieSectionLabelGenerator());

        BufferedImage pieChartImage = pieChart.createBufferedImage(560, 370);
        Image pieChartImageElement = Image.getInstance(pieChartImage, null);
        pieChartImageElement.setAlignment(Element.ALIGN_CENTER);
        document.add(pieChartImageElement);

        document.add(new Paragraph(obtenerInformacionChatsPorUsuario()));

        document.newPage();

                //TERCER GRAFICO
                //TERCER GRAFICO
                //TERCER GRAFICO
                //TERCER GRAFICO
                //TERCER GRAFICO
        CategoryDataset opportunitiesDataset = obtenerDatosOportunidadesPorOrganizacion();
        JFreeChart opportunitiesBarChart = ChartFactory.createBarChart(
                "Oportunidades por Organización",
                "Organización",
                "Cantidad de Oportunidades",
                opportunitiesDataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        CategoryPlot opportunitiesBarChartPlot = opportunitiesBarChart.getCategoryPlot();
        BarRenderer opportunitiesBarChartRenderer = (BarRenderer) opportunitiesBarChartPlot.getRenderer();
        opportunitiesBarChartRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", NumberFormat.getInstance()));
        opportunitiesBarChartRenderer.setBaseItemLabelsVisible(true);

        BufferedImage opportunitiesBarChartImage = opportunitiesBarChart.createBufferedImage(560, 370);
        Image opportunitiesBarChartImageElement = Image.getInstance(opportunitiesBarChartImage, null);
        opportunitiesBarChartImageElement.setAlignment(Element.ALIGN_CENTER);
        document.add(opportunitiesBarChartImageElement);
        
        document.add(new Paragraph(obtenerInformacionOportunidadesPorOrganizacion()));
        int Oportunidades=contarOportunidades();
        document.add(new Paragraph("Total de oportunidades: " + Oportunidades));

        
         document.newPage();
         document.add(new Paragraph(Reporte()));
         document.newPage();
         document.add(new Paragraph(PortadaFinal()));


    } catch (DocumentException | FileNotFoundException e) {
        e.printStackTrace();
    } finally {
        if (document != null) {
            document.close();
        }
    }
}


public class CustomPieSectionLabelGenerator implements PieSectionLabelGenerator {

    @Override
    public String generateSectionLabel(PieDataset dataset, Comparable key) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);

        double total = 0.0;
        for (int i = 0; i < dataset.getItemCount(); i++) {
            total += dataset.getValue(i).doubleValue();
        }

        double value = dataset.getValue(key).doubleValue();
        double percent = value / total * 100.0;

        return String.format("%s: %.0f (%s%%)", key, value, numberFormat.format(percent));
    }

    @Override
    public AttributedString generateAttributedSectionLabel(PieDataset dataset, Comparable key) {
        return null;
    }

 }

private void agregarPortada(Document document) throws DocumentException {
            com.itextpdf.text.Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);

            com.itextpdf.text.Font fontSubtitulo = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.GRAY);

        Paragraph titulo = new Paragraph("Informe Gráfico", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);

        Paragraph subtitulo = new Paragraph("Fecha: " + obtenerFechaDeHoyComoTexto(), fontSubtitulo);
        subtitulo.setAlignment(Element.ALIGN_CENTER);

        document.add(titulo);
        document.add(subtitulo);

        document.add(Chunk.NEWLINE);

      try {
        String imageName = "needu.png";

        String imagePath = "/imagenes/" + imageName;

        Image image = Image.getInstance(getClass().getResource(imagePath));

        image.setAlignment(Element.ALIGN_CENTER);

        document.add(image);

        document.add(Chunk.NEWLINE);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private String obtenerFechaDeHoyComoTexto() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate today = LocalDate.now();
    return formatter.format(today);
}



private String obtenerInformacionCalificacionesPorUsuario() {
    StringBuilder informacion = new StringBuilder();

    DefaultCategoryDataset dataset = (DefaultCategoryDataset) obtenerDatosCalificacionesPorUsuarioConNombres();
    int totalUsuarios = dataset.getRowCount();
    int totalCalificaciones = dataset.getColumnCount();

    int maxCalificaciones = 0;
    Comparable calificadorConMaxCalificaciones = "";

    List<String> nombresCalificadores = new ArrayList<>();

    for (int col = 0; col < totalCalificaciones; col++) {
        int calificaciones = 0;

        for (int row = 0; row < totalUsuarios; row++) {
            int cantidadCalificaciones = dataset.getValue(row, col).intValue();
            calificaciones += cantidadCalificaciones;

            if (cantidadCalificaciones > 0) {
                String nombreCalificador = dataset.getRowKey(row).toString(); // Obtener el nombre del calificador
                nombresCalificadores.add(nombreCalificador + " (" + dataset.getColumnKey(col) + ")");
            }
        }

        if (calificaciones > maxCalificaciones) {
            maxCalificaciones = calificaciones;
            calificadorConMaxCalificaciones = dataset.getColumnKey(col);
        }
    }

    informacion.append("A continuación se presenta un gráfico de barras que muestra la cantidad de calificaciones por usuario. ")
            .append("Cada barra representa a un usuario y la altura de la barra indica la cantidad de calificaciones recibidas por ese usuario.")
            .append("\n\nEn el gráfico anterior, cada barra representa a un usuario, y la altura de la barra indica la cantidad de calificaciones recibidas por ese usuario. ")
            .append("Los valores numéricos se muestran encima de las barras para mayor claridad.")
            .append("\n\nEl calificador con más calificaciones es: ").append(calificadorConMaxCalificaciones)
            .append("\nNombres de todos los calificadores:\n").append(String.join("\n    - ", nombresCalificadores))
            .append("\nTotal de usuarios: ").append(nombresCalificadores.size());

    return informacion.toString();
}










private String obtenerInformacionChatsPorUsuario() {
    StringBuilder informacion = new StringBuilder();

    DefaultPieDataset dataset = obtenerDatosChatsPorUsuario();


    List<String> usuariosConChats = new ArrayList<>();
    Connection con = cn.getConnection();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        statement = con.prepareStatement(
                "SELECT DISTINCT u.usuario " +
                        "FROM tbchat c " +
                        "JOIN tbusuario u ON c.idusuario1 = u.idusuario OR c.idusuario2 = u.idusuario");
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String usuario = resultSet.getString("usuario");
            usuariosConChats.add(usuario);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeResources(con, statement, resultSet);
    }

    String repeticionesMax = MAXIMONombre1();
    int cantidadFilasTbChat = CHATSABIERTOS();

    informacion.append("A continuación se presenta un gráfico de pastel que muestra la distribución de la cantidad de chats por usuario. ")
            .append("Cada sector del pastel representa a un usuario, y el tamaño del sector indica la cantidad de chats en los que ese usuario participa.")
            .append("\n\nEn el gráfico anterior, cada sector del pastel representa a un usuario, y el tamaño del sector indica la cantidad de chats en los que ese usuario participa. ")
            .append("Los valores numéricos y porcentajes se muestran junto a cada sector para mayor claridad.")
            .append("\n\nTotal de chats: ").append(cantidadFilasTbChat)
            .append("\nTotal de Usuarios usando el servicio de chat : ").append(cantidadFilasTbChat*2)
            .append("\n\nUsuarios que crearon chats: ").append(String.join(", ", usuariosConChats))
            .append("\nUsuario Con mas chats Abiertos: ").append(repeticionesMax);

    return informacion.toString();
}









private String obtenerInformacionOportunidadesPorOrganizacion() {
    StringBuilder informacion = new StringBuilder();

    DefaultCategoryDataset dataset = (DefaultCategoryDataset) obtenerDatosOportunidadesPorOrganizacion();
    int totalOrganizaciones = dataset.getRowCount();
    int totalOportunidades = dataset.getColumnCount();

    int maxOportunidades = 0;
    Comparable organizacionConMaxOportunidades = "";

    List<String> nombresOrganizaciones = new ArrayList<>();

    for (int col = 0; col < totalOportunidades; col++) {
        int oportunidades = 0;

        for (int row = 0; row < totalOrganizaciones; row++) {
            int cantidadOportunidades = dataset.getValue(row, col).intValue();
            oportunidades += cantidadOportunidades;

            if (cantidadOportunidades > 0) {
                String nombreOrganizacion = dataset.getRowKey(row).toString(); // Obtener el nombre de la organización
                nombresOrganizaciones.add(nombreOrganizacion + " (" + dataset.getColumnKey(col) + ")");
            }
        }

        if (oportunidades > maxOportunidades) {
            maxOportunidades = oportunidades;
            organizacionConMaxOportunidades = dataset.getColumnKey(col);
        }
    }

    informacion.append("A continuación se presenta un gráfico de barras que muestra la cantidad de oportunidades por organización. ")
            .append("Cada barra representa a una organización, y la altura de la barra indica la cantidad de oportunidades asociadas a esa organización.")
            .append("\n\nEn el gráfico anterior, cada barra representa a una organización, y la altura de la barra indica la cantidad de oportunidades asociadas a esa organización. ")
            .append("Los valores numéricos se muestran encima de las barras para mayor claridad.")
            .append("\n\nLa organización con más oportunidades es: ").append(organizacionConMaxOportunidades)
            .append("\nNombres de todas las organizaciones:\n").append(String.join("\n    - ", nombresOrganizaciones))
            .append("\nTotal de organizaciones: ").append(nombresOrganizaciones.size());
            

    return informacion.toString();
}










private String MAXIMONombre1() {
    Connection con = cn.getConnection();
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    String nombreUsuarioMasRepetido = null;

    try {
        statement = con.prepareStatement(
                "SELECT u.usuario " +
                        "FROM tbchat c " +
                        "JOIN tbusuario u ON c.idusuario1 = u.idusuario " +
                        "GROUP BY u.usuario " +
                        "ORDER BY COUNT(*) DESC " +
                        "LIMIT 1");
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            nombreUsuarioMasRepetido = resultSet.getString("usuario");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeResources(con, statement, resultSet);
    }

    return nombreUsuarioMasRepetido;
}


private int CHATSABIERTOS() {
    Connection con = cn.getConnection();
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    int cantidadFilas = 0;

    try {
        statement = con.prepareStatement("SELECT COUNT(*) AS cantidad_filas FROM tbchat");
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            cantidadFilas = resultSet.getInt("cantidad_filas");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeResources(con, statement, resultSet);
    }

    return cantidadFilas;
}
private int contarOportunidades() {
    Connection con = cn.getConnection();
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    int totalOportunidades = 0;

    try {
        statement = con.prepareStatement("SELECT COUNT(*) AS total FROM tboportunidad");
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            totalOportunidades = resultSet.getInt("total");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeResources(con, statement, resultSet);
    }

    return totalOportunidades;
}

private int contarCalificaciones() {
    Connection con = cn.getConnection();
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    int totalCalificaciones = 0;

    try {
        statement = con.prepareStatement("SELECT COUNT(*) AS total_calificaciones FROM tbcalificacion");
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            totalCalificaciones = resultSet.getInt("total_calificaciones");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeResources(con, statement, resultSet);
    }

    return totalCalificaciones;
}


private String Reporte() {
    StringBuilder informacion = new StringBuilder();
    informacion.append("### Validación de Datos ###\n");

    int totalCalificacionesUsuarios = contarCalificaciones();
    informacion.append("El total de calificacion de usuario : ").append(totalCalificacionesUsuarios).append("\n\n");
    if (totalCalificacionesUsuarios > 100) {
        informacion.append("¡Alerta! El total de calificaciones de usuarios ha excedido el límite esperado (100). Verifique los datos para asegurar la precisión.\n\n\n");
    } else if (totalCalificacionesUsuarios < 5) {
        informacion.append("¡Alerta! El total de calificaciones de usuarios es menor de lo esperado (5). Puede haber un problema con la cantidad de datos proporcionados.\n\n\n");
    } else {
        informacion.append("El total de calificaciones de usuarios está dentro de los límites esperados. Los datos parecen estar en buen estado.\n\n\n");
    }

    int totalOportunidades = contarOportunidades();
    informacion.append("El total de oportunidades creadas es: ").append(totalOportunidades).append("\n\n");

    if (totalOportunidades > 150) {
        informacion.append("¡Alerta! El total de oportunidades ha excedido el límite esperado (150). Esto podría indicar un alto nivel de actividad en el sistema.\n\n\n");
    } else if (totalOportunidades < 10) {
        informacion.append("¡Alerta! El total de oportunidades es menor de lo esperado (10). Esto podría indicar una baja actividad en la plataforma.\n\n\n");
    } else {
        informacion.append("El total de oportunidades está dentro de los límites esperados. Los datos parecen estar en buen estado.\n\n\n");
    }

    int totalChats = CHATSABIERTOS();
    informacion.append("El total de chats creados es: ").append(totalChats).append("\n\n");

    if (totalChats > 100) {
        informacion.append("¡Alerta! El total de chats ha excedido el límite esperado (100). Esto podría indicar un uso inusual del sistema. Se recomienda verificar la actividad de usuarios.\n\n\n");
    } else if (totalChats < 5) {
        informacion.append("¡Alerta! El total de chats es menor de lo esperado (5). Esto podría indicar un problema de falta de actividad en el sistema. Verificar si los usuarios están utilizando la plataforma.\n\n\n");
    } else {
        informacion.append("El total de chats está dentro de los límites esperados. Los datos parecen estar en buen estado.\n\n\n");
    }
    
    return informacion.toString();
}


private String PortadaFinal() {
    StringBuilder informacion = new StringBuilder();

    informacion.append("Portada Final\n\n");
    informacion.append("Este informe presenta un análisis detallado utilizando gráficos JFreeChart y consultas a la base de datos.\n\n");

    informacion.append("### Uso de JFreeChart ###\n");
    informacion.append("JFreeChart es una poderosa biblioteca de código abierto para la creación de gráficos en Java.\n");
    informacion.append("En este informe, hemos empleado JFreeChart para visualizar datos de manera efectiva, utilizando gráficos de barras y de pastel.\n\n");

    informacion.append("### Consultas en la Base de Datos ###\n");
    informacion.append("Para obtener información actualizada y precisa, hemos realizado consultas a la base de datos utilizando el lenguaje SQL.\n");
    informacion.append("Estas consultas nos permiten extraer datos relevantes de las tablas, como la cantidad de chats por usuario o la distribución de oportunidades por organización.\n\n");

   


    informacion.append("### Otras Tecnologías Utilizadas ###\n");
    informacion.append("Además de JFreeChart y SQL, nuestro sistema utiliza tecnologías como Java y la plataforma JDBC para la conexión y manipulación de datos en la base de datos.\n");
    informacion.append("La estructura del código sigue patrones de diseño que facilitan la mantenibilidad y escalabilidad del sistema.\n\n");

    informacion.append("### Conclusiones y Agradecimientos ###\n");
    informacion.append("En conclusión, la combinación de tecnologías como JFreeChart y consultas SQL nos ha permitido generar visualizaciones impactantes y análisis detallados.\n");
    informacion.append("Agradecemos el apoyo del equipo de desarrollo y la colaboración de todos los usuarios que han contribuido a la generación de datos para este análisis.\n\n");

    informacion.append("¡Gracias por revisar este informe! Esperamos que la información presentada sea clara y útil para el análisis de datos en nuestro sistema.\n");

    return informacion.toString();
}






}
