<%@page import="java.util.List"%>
<%@page import="ModeloDAO.NotificacionDAO"%>
<%@page import="Modelo.Notificacion"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Notificaciones</title>
    <meta charset="UTF-8">
    <title>Notificaciones</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            width: 80%;
            margin: 20px auto;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .notification {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            padding: 15px;
        }
        .notification h3 {
            margin-top: 0;
            font-size: 18px;
            color: #555;
        }
        .notification-list {
            list-style: none;
            padding: 0;
            margin: 0;
        }
        .notification-item {
            border-bottom: 1px solid #eee;
            padding: 10px 0;
        }
        .notification-item:last-child {
            border-bottom: none;
        }
        .notification-item a {
            text-decoration: none;
            color: #007bff;
        }
        /* Estilos para la barra de menú */
.menu {
  background-color: #ffffff; /* Fondo blanco */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Sombra ligera */
  padding: 10px 20px; /* Espaciado interno */
  text-align: right; /* Alineación del texto a la derecha */
}

.menu a {
  font-family: Helvetica, Arial, sans-serif; /* Fuentes de sistema similares a Poppins */
  text-decoration: none; /* Sin subrayado en los enlaces */
  color: #666; /* Color de texto gris */
  margin-right: 50px; /* Espaciado entre elementos */
  font-weight: bold; /* Texto en negrita */
}

.menu a:hover {
  color: #333; /* Cambio de color al pasar el ratón por encima */
}
    </style>
    <link rel="icon" type="image/jpeg" href="imagenes\logo.jpeg"> 
</head>
<body>
    <div class="menu">
  <a href="ControladorLogin?accion=redirigirInicio">Inicio</a>
  <a href="ControladorChat?accion=allchats">Chat</a>
  <a href="ControladorNotificacion?accion=notificacion">Notificaciones</a>
  <a href="ControladorPerfil?accion=perfil">Perfil</a>
  <a href="ControladorUsuario?accion=control">Control de usuarios</a>
  <a href="ControladorLogin?accion=logout">Cerrar Sesión</a>
</div>
    <%
    int idusuario = (int) session.getAttribute("idusuario");
    NotificacionDAO dao = new NotificacionDAO();
    List<Notificacion> lista = dao.listarMensajes(idusuario);
%>
    <div class="container">
        <h1>Notificaciones</h1>
        <div class="notification">
            <h3>Mensajes    <h4><a href="ControladorNotificacion?accion=marcar">Marcar como leído todas</a></h4></h3>
            <ul class="notification-list">
                <% for (Notificacion not : lista) { %>
                    <li class="notification-item">
                        <strong><%= not.getFecha() %></strong><br>
                        <%= not.getMensaje() %><br>
                        <a href="ControladorChat?accion=allchats">Abrir</a>
                    </li>
                <% } %>
            </ul>
        </div>
        <div class="notification">
            <h3>Solicitudes</h3>
            <ul class="notification-list">
                <% lista = dao.listarSolicitudes(idusuario);
                for (Notificacion not : lista) { %>
                    <li class="notification-item">
                        <strong><%= not.getFecha() %></strong><br>
                        <%= not.getMensaje() %><br>
                        <a href="#">#</a>
                    </li>
                <% } %>
            </ul>
        </div>
        <div class="notification">
            <h3>Aprobaciones</h3>
            <ul class="notification-list">
                <% lista = dao.listarAprobaciones(idusuario);
                for (Notificacion not : lista) { %>
                    <li class="notification-item">
                        <strong><%= not.getFecha() %></strong><br>
                        <%= not.getMensaje() %><br>
                        <a href="#">#</a>
                    </li>
                <% } %>
            </ul>
        </div>
    </div>
</body>
</html>
