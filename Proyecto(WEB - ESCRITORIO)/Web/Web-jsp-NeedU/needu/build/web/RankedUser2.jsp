<%@ page import="ModeloDAO.UsuarioDAO" %>
<%@ page import="Modelo.Usuario" %>
<%@ page import="ModeloDAO.CalificacionDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Perfil de Usuario</title>
</head>
<body>

<%
    String idUsuarioParam = request.getParameter("idUsuario");
    if (idUsuarioParam == null || !idUsuarioParam.matches("\\d+")) {
%>
        <h1>Error</h1>
        <p>Parámetro idUsuario no válido.</p>
<%
    } else {
        int idUsuario = Integer.parseInt(idUsuarioParam);

        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.obtenerUsuarioPorId(idUsuario);

            if (usuario != null) {
%>
                <h1>Perfil de Usuario</h1>
                <p>ID de Usuario: <%= usuario.getIdusuario() %></p>
                <p>Nombre de Usuario: <%= usuario.getUsuario() %></p>
                <p>Rol: <%= usuario.getRol() %></p>

                <div id="puntuacionContainer"></div>

            <script>
                var idUsuario = <%= idUsuario %>; // Asignar el valor a una variable JavaScript
                window.onload = function() {
                    mostrarPuntuaciones(idUsuario);
                };

                function mostrarPuntuaciones(idUsuario) {
                    var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function() {
                        if (this.readyState == 4 && this.status == 200) {
                            document.getElementById("puntuacionContainer").innerHTML = this.responseText;

                        }
                    };
                    xhttp.open("POST", "ControladorCalificarCalculo", true);
                    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xhttp.send("action=datos&idUsuario=" + idUsuario);
                }
            </script>

<%
            } else {
%>
                <h1>Error</h1>
                <p>No se encontró un usuario con el ID proporcionado.</p>
<%
            }
        } catch (Exception e) {
%>
            <h1>Error</h1>
            <p>Error al obtener datos del usuario: <%= e.getMessage() %></p>
<%
        }
    }
%>

</body>
</html>
