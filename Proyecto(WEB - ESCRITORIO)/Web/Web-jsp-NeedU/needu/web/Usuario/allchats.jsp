<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Modelo.Chat" %>

<!DOCTYPE html>
<html>
<head>
    <title>Chats Disponibles</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        a {
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
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
  <a href="ControladorUsuario?accion=control">Control de usuario</a>
  <a href="ControladorLogin?accion=logout">Cerrar Sesión</a>
</div>
    <h1>Chats Disponibles</h1>

    <%-- Obtener la lista de chats del atributo de la solicitud --%>
    <% List<Chat> listaChats = (List<Chat>) request.getAttribute("listaChats");
    int idusuario = (int) session.getAttribute("idusuario");%>

    <table border="1">
        <thead>
            <tr>
                <th>Nombre 1</th>
                <th>Nombre 2</th>
                <th>Fecha</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <%-- Iterar sobre la lista de chats y mostrar la información --%>
            <% for (Chat chat : listaChats) { %>
                <tr>
                    <td><%= chat.getNombre1()%></td>
                    <td><%= chat.getNombre2()%></td>
                    <td><%= chat.getFechacreacion() %></td>
                    <td><a href="ControladorChat?accion=chat&idusuario1=<%= chat.getIdusuario1()%>&idusuario2=<%= chat.getIdusuario2()%>&idchat=<%= chat.getIdchat()%>&nombre1=<%= chat.getNombre1()%>&nombre2=<%= chat.getNombre2()%>">Ir al chat</a></td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
