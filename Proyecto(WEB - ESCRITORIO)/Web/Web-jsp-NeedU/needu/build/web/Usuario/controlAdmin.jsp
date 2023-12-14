<%@page import="Modelo.Control"%>
<%@page import="java.util.List" %>
<%@page import="ModeloDAO.ControlDAO" %>
<title>Needu</title>
<style>
    /* Estilos para el botón "Generar Informe" */
.btn {
    display: inline-block;
    padding: 10px 20px;
    margin-bottom: 20px;
    text-decoration: none;
    background-color: #fff; /* Fondo blanco */
    color: #000; /* Letras negras */
    border: 2px solid #000; /* Borde negro */
    border-radius: 5px;
    transition: background-color 0.3s ease, color 0.3s ease;
    font-weight: bold;
}

.btn:hover {
    background-color: #f5f5f5; /* Color de fondo al pasar el cursor */
}


/* Estilos para las tablas de registros */
.registro-table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

.registro-table th, .registro-table td {
    padding: 8px;
    border: 1px solid #ccc;
    text-align: left;
}

.registro-table th {
    background-color: #f2f2f2;
    font-weight: bold;
}

.registro-table tr:hover {
    background-color: #f5f5f5;
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
<!-- Lógica de Java para obtener las listas -->
<%
// Obtener listas del request
List<Control> registrosUsuario = (List<Control>) request.getAttribute("registrosUsuario");
List<Control> todosRegistros = (List<Control>) request.getAttribute("todosRegistros");

// Verificar si las listas no están vacías
if (registrosUsuario != null && todosRegistros != null) {
%>

<%
    // Convertir la lista de registros a una cadena de consulta para enviarla en la URL
    String listaQueryString = "";
    if (registrosUsuario != null && !registrosUsuario.isEmpty()) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Control control : registrosUsuario) {
            stringBuilder.append("idcontrol=").append(control.getIdcontrol()).append("&");
            stringBuilder.append("idusuario=").append(control.getIdusuario()).append("&");
            stringBuilder.append("usuario=").append(control.getUsuario()).append("&");
            stringBuilder.append("hora_entrada=").append(control.getHora_entrada()).append("&");
            stringBuilder.append("hora_salida=").append(control.getHora_salida()).append("&");
        }
        listaQueryString = stringBuilder.toString();
        listaQueryString = listaQueryString.substring(0, listaQueryString.length() - 1); // Eliminar el último '&'
    }
%>
<div class="menu">
  <a href="ControladorLogin?accion=redirigirInicio">Inicio</a>
  <a href="ControladorChat?accion=allchats">Chat</a>
  <a href="ControladorNotificacion?accion=notificacion">Notificaciones</a>
  <a href="ControladorPerfil?accion=perfil">Perfil</a>
  <a href="ControladorUsuario?accion=control">Control de usuario</a>
  <a href="ControladorLogin?accion=logout">Cerrar Sesión</a>
</div>
<a href="ControladorAdmin?accion=generarInforme" class="btn">Generar Informe</a>

<h2>Todos sus inicios de sesion personal:</h2>
<table class="registro-table">
    <tr>
        <th>ID Control</th>
        <th>ID Usuario</th>
        <th>Usuario</th>
        <th>Hora de entrada</th>
        <th>Hora de salida</th>
    </tr>
    <% for (Control control : registrosUsuario) { %>
    <tr>
        <td><%= control.getIdcontrol() %></td>
        <td><%= control.getIdusuario() %></td>
        <td><%= control.getUsuario() %></td>
        <td><%= control.getHora_entrada() %></td>
        <td><%= control.getHora_salida() %></td>
    </tr>
    <% } %>
</table>

<!-- HTML/JSP para mostrar la lista de todos los registros -->
<h2>Todos los registros:</h2>
<table class="registro-table">
    <tr>
        <th>ID Control</th>
        <th>ID Usuario</th>
        <th>Usuario</th>
        <th>Hora de entrada</th>
        <th>Hora de salida</th>
    </tr>
    <% for (Control control : todosRegistros) { %>
    <tr>
        <td><%= control.getIdcontrol() %></td>
        <td><%= control.getIdusuario() %></td>
        <td><%= control.getUsuario() %></td>
        <td><%= control.getHora_entrada() %></td>
        <td><%= control.getHora_salida() %></td>
    </tr>
    <% } %>
</table>

<%
} else {
    out.println("No se han encontrado registros.");
}
%>
