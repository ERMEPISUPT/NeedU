<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Modelo.Usuario" %>
<%@ page import="Modelo.Calificacion" %>
<%@ page import="ModeloDAO.CalificacionDAO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Perfiles</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        .highlight {
            background-color: #ffffcc;
        }

        .options {
            display: flex;
            flex-direction: column;
        }

        button {
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
    <h2>Lista de Perfiles</h2>
    <form action="ControladorCalificar" method="POST">
        <input type="hidden" name="accion" value="listarPerfiles">
        <table>
            <tr>
                <th>Usuario</th>
                <th>Rol</th>
                <th>Comentarios</th>
                <th>Puntuación</th> <!-- Nueva columna -->
                <th>Acción</th>
            </tr>
            <% 
            List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
            CalificacionDAO calificacionDAO = new CalificacionDAO();
            int idusuario = (int) session.getAttribute("idusuario");
            %>
            <p>El ID del usuario actual es: <%= idusuario %></p>
            <% for (Usuario user : usuarios) { %>
                <tr <% if (user.getIdusuario() == idusuario) { %>class="highlight"<% } %>>
                    <td><%= user.getUsuario() %> <% if (user.getIdusuario() == idusuario) { %>(Yo)<% } %></td>
                    <td><%= user.getRol() %></td>
                    <td>
                        <%-- Aquí es donde se cargarán los comentarios específicos del usuario --%>
                        <% 
                            List<String> comentarios = calificacionDAO.getComentariosByUsuarioId(user.getIdusuario());
                            if (comentarios != null && !comentarios.isEmpty()) {
                                for (String comentario : comentarios) {
                        %>
                        <p><%= comentario %></p>
                        <%
                                }
                            } else {
                        %>
                        <p>Sin comentarios</p>
                        <%
                            }
                        %>
                    </td>
                    <td>
                        <%-- Obtener la calificación del usuario actual --%>
                        <%
                            Calificacion calificacion = calificacionDAO.getCalificacionByUsuarioId(user.getIdusuario());
                            if (calificacion != null) {
                        %>
                        <%= calificacion.getPuntuacion() %>
                        <%
                            }
                            else {
                        %>
                        Sin puntuación
                        <%
                            }
                        %>
                    </td>


                    <td>
                        <form action="ControladorCalificar" method="POST">
                            <input type="hidden" name="accion" value="pulgarArriba_<%= user.getIdusuario() %>">
                            <button type="submit" <% if (user.getIdusuario() == idusuario) { %>disabled<% } %>>Pulgar arriba</button>
                        </form>
                        <form action="ControladorCalificar" method="POST">
                            <input type="hidden" name="accion" value="pulgarAbajo_<%= user.getIdusuario() %>">
                            <button type="submit" <% if (user.getIdusuario() == idusuario) { %>disabled<% } %>>Pulgar abajo</button>
                        </form>
                    </td>
                </tr>
            <% } %>
        </table>
    </form>
    <script>
        setInterval(function() {
            location.reload();
        }, 5000);
    </script>
</body>
</html>
