<%@ page import="ModeloDAO.*" %>
<%@ page import="java.util.*" %>
<%@ page import="Modelo.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Perfil de Usuario</title>
    <link rel="stylesheet" type="text/css" href="Usuario/PERFILUsuario/EstilosPerfil.css">



</form>
     <style>


        <% if (session.getAttribute("rol") != null && session.getAttribute("rol").equals("voluntario")) { %>
            body {
                background-color: #add8e6; 
            }
            
        <% } else if (session.getAttribute("rol") != null && session.getAttribute("rol").equals("organizacion")) { %>
            body {
                background-color: #d3d3d3;
            }
        <% } else if (session.getAttribute("rol") != null && session.getAttribute("rol").equals("administrador")) { %>
            body {
                background-color: #fffacd;
            }
        <% } %>

        
    </style>
    <link rel="icon" type="image/jpeg" href="imagenes\logo.jpeg"> 
</head>
<body>
    <div class="menu">
    <div class="menu-title">Perfil de usuario</div>
    <a href="ControladorLogin?accion=redirigirInicio">Inicio</a>
    <a href="ControladorChat?accion=allchats">Chat</a>
    <a href="ControladorNotificacion?accion=notificacion">Notificaciones</a>
    <a href="ControladorPerfil?accion=perfil">Perfil</a>
    <a href="ControladorUsuario?accion=control">Control de usuarios</a>
    <a href="ControladorLogin?accion=logout">Cerrar Sesión</a>
  </div>
    
    <div class="main-content">
        <div class="bienvenida">
            <% String nombreUsuario = (session.getAttribute("nombre") != null) ? (String) session.getAttribute("nombre") : "Usuario"; %>
            <label>Bienvenido, <strong><%= nombreUsuario %></strong></label><br><br>
        </div>

        <% if (session.getAttribute("rol") != null) { %>
            <% if (session.getAttribute("rol").equals("voluntario")) { %>
                <label>Rol: Voluntario</label><br><br>
            <% } else if (session.getAttribute("rol").equals("organizacion")) { %>
                <label>Rol: Organización</label><br><br>
            <% } else if (session.getAttribute("rol").equals("administrador")) { %>
                <label>Rol: Administrador</label><br><br>
            <% } %>
        <% } %>
            <% 
            int estado = (session.getAttribute("estado") != null) ? (int)session.getAttribute("estado") : 0;
        %>
        <p class='<%= (estado == 0) ? "red" : "lightgreen" %>'>Estado: <%= (estado == 0) ? "Inactivo" : "Activo" %></p>


        <div class="content">
        <% if (session.getAttribute("rol") != null && session.getAttribute("rol").equals("voluntario")) { %>
            <div class="voluntario-profile">
                <label>ID Voluntario:</label>
                <input type="text" value="<%= (session.getAttribute("idvoluntario") != null) ? session.getAttribute("idvoluntario") : "" %>" disabled><br>
                    <form method="post" action="ControladorPerfil?accion=editarVoluntario">
                    
                    <div class="input-row">
                        <div class="input-group">
                            <label>Nombre:</label>
                            <input type="text" value="<%= (session.getAttribute("nombre") != null) ? session.getAttribute("nombre") : "" %>" name="nombre" required>
                        </div>
                        
                        <div class="input-group">
                            <label>Edad:</label>
                            <input type="number" value="<%= (session.getAttribute("edad") != null) ? session.getAttribute("edad") : "" %>" name="edad" required>
                        </div>
                    </div>
                    
                    <div class="input-row">
                        <div class="input-group">
                            <label>Apellido:</label>
                            <input type="text" value="<%= (session.getAttribute("apellido") != null) ? session.getAttribute("apellido") : "" %>" name="apellido" required>
                        </div>
                        
                        <div class="input-group">
                            <label>Correo:</label>
                            <input type="email" value="<%= (session.getAttribute("correo") != null) ? session.getAttribute("correo") : "" %>" name="correo" required>
                        </div>
                    </div>
                        
                        <label>Género:</label>
                        <select name="genero" required>
                            <option value="Hombre" <%= (session.getAttribute("genero") != null && session.getAttribute("genero").equals("Hombre")) ? "selected" : "" %>>Hombre</option>
                            <option value="Mujer" <%= (session.getAttribute("genero") != null && session.getAttribute("genero").equals("Mujer")) ? "selected" : "" %>>Mujer</option>
                            <option value="Prefiero no decirlo" <%= (session.getAttribute("genero") != null && session.getAttribute("genero").equals("Prefiero no decirlo")) ? "selected" : "" %>>Prefiero no decirlo</option>
                        </select>
                        
                        
                        <input type="submit" value="Actualizar Perfil" class="btn-actualizar"><br>
  
                    </form>
                                              
                    <form action="ControladorCalificar" method="GET">
                    <input type="hidden" name="accion" value="listarPerfiles">
                    <button type="submit" class="btn-listar">Listar Perfiles</button>
                    </form>
            </div>
        </div>
        
       

    
        
            
    <% } else if (session.getAttribute("rol") != null && session.getAttribute("rol").equals("organizacion")) { %>
    <div class="perfil-organizacion">
            <div class="datos-organizacion">
    <label>ID Organización:</label>
    <input type="text" value="<%= (session.getAttribute("idorganizacion") != null) ? session.getAttribute("idorganizacion") : "" %>" disabled><br><br>
    <form method="post" action="ControladorPerfil?accion=editarOrganizacion">
        <label>Nombre de la Organización:</label>
        <input type="text" value="<%= (session.getAttribute("nombre") != null) ? session.getAttribute("nombre") : "" %>" name="nombre" required><br><br>

        <label>Tipo:</label>
        <input type="text" value="<%= (session.getAttribute("tipo") != null) ? session.getAttribute("tipo") : "" %>" name="tipo" required><br><br>

        <label>Correo:</label>
        <input type="email" value="<%= (session.getAttribute("correo") != null) ? session.getAttribute("correo") : "" %>" name="correo" required><br><br>

        <input type="submit" value="Actualizar Perfil" class="btn-actualizar"><br>
        <br>
    </form>
        <form action="ControladorCalificar" method="GET">
            <input type="hidden" name="accion" value="listarPerfiles">
            <button type="submit" class="btn-listar">Listar Perfiles</button>
        </form>
     </div>
    <div class="agregar-oportunidad">
    <h1>Agregar Oportunidad:</h1>  
    <!-- Agregar Oportunidad-->
<div class="agregar-oportunidad">
    <!-- Formulario Agregar Oportunidad -->
    <form action="ControladorOportunidad" method="post">

        <!-- Título -->
        <label for="titulo">Título:</label>
        <input type="text" name="titulo" id="titulo" required><br>

        <!-- Descripción -->
        <label for="descripcion">Descripción:</label>
        <textarea name="descripcion" id="descripcion" required></textarea><br>

        <!-- Fecha -->
        <label for="fecha">Fecha:</label>
        <input type="date" name="fecha" id="fecha" required><br>

        <!-- Ubicación -->
        <label for="idubicacion">Ubicación:</label>
        <select name="idubicacion" id="idubicacion" required>
            <% 
            UbicacionDAO ubicacionDao = new UbicacionDAO();
            List<Ubicacion> ubicaciones = ubicacionDao.listarUbicaciones();
            for (Ubicacion ubi : ubicaciones) {
                out.println("<option value='" + ubi.getIdubicacion() + "'>" + ubi.getPais() + ", " + ubi.getCiudad() + " - " + ubi.getDireccion() + "</option>");
            }
            %>
        </select><br>

        <!-- Causa -->
        <label for="idcausa">Causa:</label>
        <select name="idcausa" id="idcausa" required>
            <% 
            CausaDAO causaDao = new CausaDAO();
            List<Causa> causas = causaDao.listarCausas();
            for (Causa caus : causas) {
                out.println("<option value='" + caus.getIdcausa() + "'>" + caus.getNombre() + "</option>");
            }
            %>
        </select><br>

        <!-- Campo oculto para la acción "crear" -->
        <input type="hidden" name="accion" value="crear">

        <!-- Botón de enviar -->
        <input type="submit" value="Crear Oportunidad">
    </form>
</div>

    <br>
    <div class="oportunidades-organizacion">
    <h1>Todas las Oportunidades creadas por su Organizacion:</h1>
    <div>
        <%
            Integer idOrganizacion = null;
            if (session.getAttribute("idorganizacion") != null) {
                idOrganizacion = (Integer) session.getAttribute("idorganizacion");

                OportunidadDAO oportunidadDao = new OportunidadDAO();
                List<Oportunidad> oportunidadesOrganizacion = oportunidadDao.listarOportunidadesPorOrganizacion(idOrganizacion);

                if (oportunidadesOrganizacion != null && !oportunidadesOrganizacion.isEmpty()) {
                    for (Oportunidad opo : oportunidadesOrganizacion) {
        %>
                        <div class='oportunidad'>
                            <h2><%= opo.getTitulo() %></h2>
                            <p>Descripción: <%= opo.getDescripcion() %></p>
                            <p>Fecha: <%= opo.getFecha() %></p>

                            <!-- Botón de eliminar oportunidad con confirmación -->
                            <a href="ControladorSolicitud?accion=verDetalles&idoportunidad=<%= opo.getIdoportunidad() %>">Ver detalles</a>
                            
                        </div>
        <%
                    }
                } else {
                    out.print("<p>No hay oportunidades disponibles para tu organización.</p>");
                }
            } else {
                // Redirigir al inicio de sesión si no hay una sesión válida
                response.sendRedirect("login.jsp");
                return;
            }
        %>
    </div>


        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    <% } else if (session.getAttribute("rol") != null && session.getAttribute("rol").equals("administrador")) { %>
    <label>ID Administrador:</label>
    <input type="text" value="<%= (session.getAttribute("idadmin") != null) ? session.getAttribute("idadmin") : "" %>" disabled><br><br>
    <form method="post" action="ControladorPerfil?accion=editarAdministrador">
        <label>Nombre del Administrador:</label>
        <input type="text" value="<%= (session.getAttribute("nombre") != null) ? session.getAttribute("nombre") : "" %>" name="nombre" required><br><br>

  
        
        <input type="submit" value="Actualizar Perfil" class="btn-actualizar"><br>
                <form action="ControladorCalificar" method="GET">
            <input type="hidden" name="accion" value="listarPerfiles">
            <button type="submit" class="btn-listar">Listar Perfiles</button>
        </form>
    </form>
    <% } %>


    <form method="post" action="ControladorPerfil?accion=actualizarEstado" onsubmit="return confirm('¿Está seguro de realizar esta acción?');">
        <button type="submit" name="estado" value="1">Activar Cuenta</button>
        <button type="submit" name="estado" value="0">Desactivar Cuenta</button>
    </form>

    <p class='<%= (estado == 0) ? "red" : "lightgreen" %>'>Estado: <%= (estado == 0) ? "Inactivo" : "Activo" %></p>
    
    <script>
        function checkState() {
            let currentState = <%= (int)session.getAttribute("estado") %>;

            if (currentState === 0) {
                let countdown = 4; 
                let timer = setInterval(function() {
                    countdown--;
                    if (countdown === 0) {
                        clearInterval(timer);
                        document.getElementById("message").innerText = "Cuenta desactivada. Cerrando sesión...";
                        setTimeout(() => {
                            window.location.href = 'index.jsp'; 
                        }, 1000);
                    }
                }, 1000); 
            }
        }

        checkState(); 
    </script>

    <p id="message"></p>
    
    
    
    <script>
        window.onload = function() {
            var gender = '<%= session.getAttribute("genero") %>';
            var profileImg = document.getElementById('profile-img');

            if (gender === 'Mujer') {
                profileImg.src = 'ruta/a/mujerperfil.png';
            } else {
                profileImg.src = 'ruta/a/hombreperfil.png';
            }
        };
    </script>

</body>
</html>