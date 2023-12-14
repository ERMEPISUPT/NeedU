<%@page import="ModeloDAO.CalificacionDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Modelo.Usuario" %>
<%@ page import="ModeloDAO.UsuarioDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
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
            background-color: #008577;
        }
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.7);
            padding-top: 50px;
        }

        .modal-content {
            background-color: #fefefe;
            margin: 5% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>

    <% 
        int idUsuario = -1; 
        String nombreUsuario = "Sin nombre"; 

        Object idUsuarioObj = session.getAttribute("idusuario");
        Object nombreUsuarioObj = session.getAttribute("usuario");

        if (idUsuarioObj != null && idUsuarioObj instanceof Integer) {
            idUsuario = (Integer) idUsuarioObj;
        }

        if (nombreUsuarioObj != null && nombreUsuarioObj instanceof String) {
            nombreUsuario = (String) nombreUsuarioObj;
        }
    %>

   <p>ID del usuario de la sesión: <%= idUsuario %></p>
    <p>Su nombre es: <%= nombreUsuario %></p>

    <h2>Lista de Usuarios</h2>

    <label for="filtro">Filtrar:</label>
    <input type="text" id="filtro" oninput="filtrarTabla()">

    <table border="1" id="tablaUsuarios">
        <!-- Encabezados de la tabla -->
        <tr>
            <th>ID</th>
            <th>Usuario</th>
            <th>Rol</th>
            <th>Visualizar Calificacion</th>
        </tr>

        <%
            try {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                List<Usuario> listaUsuarios = usuarioDAO.obtenerTodosLosUsuarios();

                for (Usuario usuario : listaUsuarios) {
        %>
                    <tr>
                        <td><%= usuario.getIdusuario() %></td>
                        <td><%= usuario.getUsuario() %></td>
                        <td><%= usuario.getRol() %></td>
                        <td>
                        <button class="btn-visualizar" onclick="enviarDatosYRedirigir(<%= usuario.getIdusuario() %>, 'RankedUser2.jsp')">Visualizar Calificación</button>
                        </td>
                    </tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </table>

    <div id="modalCalificacion" class="modal">
        <div class="modal-content">
            <span class="close" onclick="cerrarModalCalificacion()">&times;</span>
            <h3 id="nombreUsuarioModal"></h3>
            <p id="puntuacionUsuarioModal"></p>
        </div>
    </div>

    <script>
        function filtrarTabla() {
            var filtro = document.getElementById("filtro").value.toUpperCase();
            var tabla = document.getElementById("tablaUsuarios");
            var filas = tabla.getElementsByTagName("tr");
            var seEncontroCoincidencia = false;

            for (var i = 1; i < filas.length; i++) {
                var celdas = filas[i].getElementsByTagName("td");
                var mostrarFila = false;

                for (var j = 0; j < celdas.length; j++) {
                    if (celdas[j]) {
                        var textoCelda = celdas[j].innerText || celdas[j].textContent;
                        if (textoCelda.toUpperCase().indexOf(filtro) > -1) {
                            mostrarFila = true;
                            seEncontroCoincidencia = true;
                            break;
                        }
                    }
                }

                filas[i].style.display = mostrarFila ? "" : "none";
            }

            tabla.style.display = (!seEncontroCoincidencia || filtro === "") ? "none" : "";
        }

        function mostrarCalificacion(idUsuario) {
        console.log("ID del usuario seleccionado: " + idUsuario);

        window.location.href = "RankedUser2.jsp?idUsuario=" + idUsuario;
        }

        function cerrarModalCalificacion() {
            var modal = document.getElementById('modalCalificacion');
            modal.style.display = 'none';
        }
        </script>
        <script>
    function enviarDatosYRedirigir(idUsuario, destino) {
        // Crear un formulario dinámicamente
        var form = document.createElement('form');
        form.method = 'POST';  // Puedes cambiarlo a 'GET' si prefieres
        form.action = destino + '?idUsuario=' + idUsuario;  // Agregar datos a la URL

        // Crear un campo de entrada para el idUsuario
        var input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'idUsuario';
        input.value = idUsuario;

        // Adjuntar el campo de entrada al formulario
        form.appendChild(input);

        // Agregar el formulario al cuerpo del documento
        document.body.appendChild(form);

        // Enviar el formulario
        form.submit();
    }
</script>

</body>
</html>
