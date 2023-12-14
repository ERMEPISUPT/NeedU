<%-- 
    Document   : pdf
    Created on : 30 oct 2023, 12:11:52
    Author     : ORDENADOR
--%>
<%@page import="com.itextpdf.text.pdf.PdfPCell"%>
<%@page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@page import="Modelo.*"%>
<%@page import="ModeloDAO.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="com.itextpdf.text.*"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page contentType="application/pdf"%>
<%@page import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<%
    Connection con;
    Statement st;
    ResultSet rs;
    String varid = request.getParameter("id"); // Obtener el DNI del empleado a editar desde el parámetro

    try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbsosa", "root", "");

        // Consulta para obtener los datos del empleado a editar
        String query = "SELECT * FROM tbmaquinas WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, varid);
        rs = ps.executeQuery();

        if (rs.next()) {
            // Obtener los datos del empleado de la base de datos
            int id = rs.getInt("id");
            String dni = rs.getString("id_reserva");
            String numero = rs.getString("numero");
            String tipo = rs.getString("tipo");
            String estado = rs.getString("estado");
            String firma = rs.getString("firma");

            // Crear un nuevo documento PDF
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();

            // Configurar el contenido del PDF
            Paragraph paragraph = new Paragraph();
            paragraph.add("ALQUILER DE MAQUINARIA\n\n");
            paragraph.add("Se alquila la maquinaria descrita a continuación:\n");
            paragraph.add("MaquinaID:" + id + "\n");
            paragraph.add("Dni Cliente:" + dni + "\n");
            paragraph.add("Número de Maquina: " +numero+ "\n");
            paragraph.add("Tipo de maquinaria: " +tipo+ "\n");
            paragraph.add("Estado de la maquina: " +estado+ "\n");
            paragraph.add("\n");
            paragraph.add("\n");
            paragraph.add("\n");
            paragraph.add("                     "+dni+"                     "+"            "+"     "+"      "+"          "+" "+" "+"               "+firma+"            "+"\n");
            paragraph.add("__________"+"            "+"__________"+"\n");
            paragraph.add("UNIVERSIDAD PRIVADA DE TACNA"+"            "+"UNIVERSIDAD PRIVADA DE TACNA"+"\n");
            paragraph.add("  "+"FIRMA DEL CLIENTE"+"                 "+" "+" "+"            "+ "FIRMA DEL GERENTE"+"\n");
            
            
            
            document.add(paragraph);
            document.close();

            // Definir el nombre del archivo PDF a generar
            String pdfFileName = "ContratoEmpleado.pdf";

            // Preparar la respuesta para el navegador
            response.setHeader("Content-Disposition", "inline; filename=" + pdfFileName);

            // Obtener el flujo de salida para escribir el PDF
            OutputStream outStream = response.getOutputStream();
            baos.writeTo(outStream);
            outStream.flush();
            outStream.close();
        } else {
            // Manejar el caso en que no se encuentre el empleado
            out.println("Empleado no encontrado.");
        }
    } catch (Exception e) {
        // Manejar excepciones
        out.println("Error: " + e.getMessage());
    }
%>
</body>
</html>