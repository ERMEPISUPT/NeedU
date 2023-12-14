<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="Modelo.Oportunidad"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.OportunidadDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilos_InfoOportunidad.css">
        <title>JSP Page</title>
    </head>
    <body>
    <div id="navbar">
        <div class="logo">Need U</div>
        <div class="menu-icon" onclick="toggleMenu()">
            <img src="imagenes/menuU.png" alt="Menú">
        </div>
        <div class="links">
            <a href="ControladorLogin?accion=redirigirInicio">Inicio</a>
            <a href="ControladorPerfil?accion=perfil">Perfil</a>
            <a href="ControladorLogin?accion=logout">Cerrar Sesión</a>
        </div>
    </div>

    <div id="menu-options" class="menu-options">
        <ul>
            <li><a href="Sobre_Nosotros.jsp">Por qué ser voluntario</a></li>
            <li><a href="ControladorLogin?accion=loginusuario">Inicio de Sesión</a></li>
            <li><a href="ControladorRegistro?accion=registro" class="registro-btn">Regístrate</a></li>
        </ul>
    </div>
        
        
    <div class="imagen-oportunidad">
        <img src="imagenes/imaGenerico.png" alt="Imagen de la Oportunidad">
    </div>

   <div class="info-oportunidad">
        <%
            String idOportunidadStr = request.getParameter("idoportunidad");
            if (idOportunidadStr != null && !idOportunidadStr.isEmpty()) {
                try {
                    int idOportunidad = Integer.parseInt(idOportunidadStr);
                    OportunidadDAO dao = new OportunidadDAO();
                    Oportunidad oportunidad = dao.getOportunidadById(idOportunidad);
                    if (oportunidad != null) {
        %>
                        <h1><%= oportunidad.getTitulo() %></h1>
                        <p><strong>Descripción:</strong> <%= oportunidad.getDescripcion() %></p>
                        <p><strong>Fecha:</strong> <%= oportunidad.getFecha() %></p>
                        <p><strong>ID Ubicación:</strong> <%= oportunidad.getIdubicacion() %></p>
                        <p><strong>Pais:</strong> <%= oportunidad.getPais()%></p>
                        <p><strong>Ciudad:</strong> <%= oportunidad.getCiudad()%></p>
                        <p><strong>Direccion:</strong> <%= oportunidad.getDireccion()%></p>
                        <p><strong>Organización:</strong> <%= oportunidad.getOrganizacion()%></p>
                        <p><strong>Causa:</strong> <%= oportunidad.getCausa()%></p>
                        <p><strong>Estado:</strong> <%= oportunidad.getEstado() == 1 ? "Activo" : "Inactivo" %></p>
                        
                        <p><a href="ControladorChat?accion=addchat&idoportunidad=<%= oportunidad.getIdoportunidad()%>">CHATEAR</a></p>
                            <!-- Agrega el formulario del botón "Ser Voluntario" -->
                        <form method="post" action="ControladorSolicitud?accion=solicitar" class="form-oportunidad">
                            <input type="hidden" name="idoportunidad" value="<%= Integer.toString(oportunidad.getIdoportunidad()) %>">
                            <button type='submit' class="btn-oportunidad">Ser Voluntario</button>
                        </form>
        <%
                    } else {
        %>
                        <p>Oportunidad no encontrada.</p>
        <%
                    }
                } catch (NumberFormatException e) {
        %>
                    <p>Error: El ID proporcionado no es válido.</p>
        <%
                }
            } else {
        %>
                <p>Error: No se proporcionó un ID de oportunidad.</p>
        <%
            }
        %>
    </div>




        
    
        
        <script>
        function toggleMenu() {
            var menuOptions = document.getElementById("menu-options");
            if (menuOptions.style.display === "none" || menuOptions.style.display === "") {
                menuOptions.style.display = "block";
                setTimeout(function() {
                    menuOptions.classList.add("active");
                }, 0);
            } else {
                menuOptions.classList.remove("active"); // Retira la clase "active" para ocultar el menú
                setTimeout(function() {
                    menuOptions.style.display = "none"; // Oculta el menú después de que se retire la animación
                }, 500); // Agrega un retraso para que la animación termine antes de ocultar el menú
            }
        }

        // Función para cerrar el menú cuando cambia el tamaño de la ventana
        function closeMenuOnResize() {
            var menuOptions = document.getElementById("menu-options");
            if (menuOptions.classList.contains('active')) {
                menuOptions.classList.remove('active');
                setTimeout(function() {
                    menuOptions.style.display = "none";
                }, 500);
            }
        }

        // Agregar un controlador de eventos al evento 'resize' para detectar cambios de tamaño de ventana
        window.addEventListener('resize', closeMenuOnResize);
    </script>
    
    
    
    
    </body>
</html>
