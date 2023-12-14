<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Solicitudes y Participantes</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        table {
            border-collapse: collapse;
            width: 45%;
            margin: 20px;
            float: left;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        h2 {
            clear: both;
        }
    </style>
</head>
<body>
    <div>
        <h2>Solicitudes Pendientes</h2>
        <table>
            <thead>
                <tr>
                    <th>ID Solicitud</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Usuario</th>
                    <th>Accion</th>
                </tr>
            </thead>
            <tbody>
                <% 
                ResultSet rs = (ResultSet) request.getAttribute("Solicitudes");
                while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("idsolicitud") %></td>
                    <td><%= rs.getString("nombre") %></td>
                    <td><%= rs.getString("apellido") %></td>
                    <td><%= rs.getString("usuario") %></td>
                    <td>
                        <a href="ControladorSolicitud?accion=aceptar&idsolicitud=<%= rs.getInt("idsolicitud") %>">Aceptar</a>
                        <a href="ControladorSolicitud?accion=rechazar&idsolicitud=<%= rs.getInt("idsolicitud") %>">Rechazar</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    
    <div>
        <h2>Participantes Activos</h2>
        <table>
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Edad</th>
                    <th>Correo</th>
                    <th>Fecha de registro</th>
                </tr>
            </thead>
            <tbody>
                <% 
                rs = (ResultSet) request.getAttribute("Participantes");
                while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getString("nombre") %></td>
                    <td><%= rs.getString("apellido") %></td>
                    <td><%= rs.getInt("edad") %></td>
                    <td><%= rs.getString("correo") %></td>
                    <td><%= rs.getString("fecharegistro") %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
