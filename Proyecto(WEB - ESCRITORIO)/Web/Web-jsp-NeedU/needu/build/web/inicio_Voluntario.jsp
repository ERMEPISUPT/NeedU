<%@page import="java.util.Map"%>
<%@page import="java.util.*"%>
<%@page import="Modelo.Oportunidad"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.*"%>
<%@page import="Modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Needu</title>
    <link rel="stylesheet" type="text/css" href="estilo_inicio_Voluntario.css">
    <link rel="icon" type="image/jpeg" href="imagenes\logo.jpeg">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+Meroitic&family=Poppins:wght@500&display=swap" rel="stylesheet">
</head>
<body>

    <div id="navbar">
        <div class="logo">Need U</div>
        <div class="menu-icon" onclick="toggleMenu()">
            <img src="imagenes/menuU.png" alt="Menú">
        </div>
        <div class="links">
            <a href="RankedUser.jsp">Ir a la página RankedUser</a>
            <a href="ControladorChat?accion=allchats">Chat</a>
            <a href="ControladorNotificacion?accion=notificacion">Notificaciones</a>
            <a href="ControladorPerfil?accion=perfil">Perfil</a>
            <a href="ControladorUsuario?accion=control">Control de usuario</a>
            <a href="ControladorLogin?accion=logout">Cerrar Sesión</a>
        </div>
    </div>

    <div id="menu-options" class="menu-options">
        <ul>
            <a href="RankedUser.jsp">Ir a la página RankedUser</a>
            <li><a href="ControladorChat?accion=allchats">Chat</a></li>
            <li><a href="ControladorNotificacion?accion=notificacion">Notificaciones</a></li>
            <li><a href="ControladorPerfil?accion=perfil">Perfil</a></li>
            <li><a href="ControladorUsuario?accion=control">Control de usuario</a></li>
            <li><a href="ControladorLogin?accion=logout">Cerrar Sesión</a></li>
            
        </ul>
    </div>

    <div class="seccion">
        <div class="fila">
            <div class="columna-mitad">
                <div class="modulo texto fondo-claro">
                    <div class="texto-interior"><h4>Voluntariado</h4></div>
                </div>
                <div class="modulo texto fondo-claro">
                    <div class="texto-interior"><h1>¡BIENVENIDO VOLUNTARIO!</h1></div>
                </div>
                <div class="modulo texto fondo-claro">
                    <div class="texto-interior">
                        <p>Tu compromiso es la fuerza que impulsa el cambio. Juntos, podemos marcar la diferencia en la vida de quienes más lo necesitan. Únete a nosotros y sé parte de esta causa solidaria.</p>
                    </div>
                </div>

            </div>
            <div class="columna-mitad ultimo">
                <div class="modulo imagen">
                    <span class="imagen-contenedor">
                        <img decoding="async" fetchpriority="high" width="801" height="801" src="imagenes/doodle_index.png" alt="doodle_index" title="doodle_index">
                    </span>
                </div>
            </div>
        </div>
    </div>
 
       <div id="fb-root"></div>
    <script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v18.0" nonce="ZLLX3Vcw"></script>
<div class="fb-comments" data-href="http://localhost:8080/NeedU_UPT/inicio.jsp" data-width="" data-numposts="5"></div>    
    
    
    
    <input type="text" id="campoBusqueda" placeholder="Busqueda Rapida...">

    <div id="resultadosBusqueda">
        <!-- Aquí se mostrarán los resultados de la búsqueda -->
    </div>

<script>
    document.getElementById('campoBusqueda').addEventListener('input', function(e) {
        var terminoBusqueda = e.target.value;
        if (terminoBusqueda.length > 0) {
            fetch('ControladorBusqueda?terminoBusqueda=' + terminoBusqueda)
                .then(response => response.json())
                .then(data => {
                    var resultadosHTML = '';
                    data.forEach(function(opo) {
                        resultadosHTML += '<div class="resultado-busqueda">';
                        resultadosHTML += '<h3>' + opo.titulo + '</h3>';
                        resultadosHTML += '<p>' + opo.descripcion + '</p>';
                        resultadosHTML += '<p><strong>Organización:</strong> ' + opo.nombreOrganizacion + '</p>'; // Nombre de la organización
                        resultadosHTML += '<a href="InfoOportunidad.jsp?idoportunidad=' + opo.idoportunidad + '" class="btn-ver-mas">Ver más acerca de esta Oportunidad</a>';
                        resultadosHTML += '<button class="btn-chatear"><a href="ControladorChat?accion=addchat&idoportunidad=' + opo.idoportunidad + '">Chatear</a></button>';
                        resultadosHTML += '</div>';
                    });
                    document.getElementById('resultadosBusqueda').innerHTML = resultadosHTML;
                })
                .catch(error => console.error('Error:', error));
        } else {
            document.getElementById('resultadosBusqueda').innerHTML = '';
        }
    });
</script>








<h1>Todas las Oportunidades:</h1>
    <div class="contenedor-oportunidades">
        <% 
            OportunidadDAO oportunidadDao = new OportunidadDAO();
            List<Map<String, Object>> oportunidades = oportunidadDao.listarOportunidades();
            if (!oportunidades.isEmpty()) {
                for (Map<String, Object> opo : oportunidades) {
        %>
            <!-- Cada oportunidad es un 'card' en la cuadrícula -->
            <div class='card-oportunidad'>
                <img src="imagenes/imaGenerico.png" alt="Imagen genérica" class="imagen-oportunidad">
                <div class="info-oportunidad">
                    <h2><%= opo.get("titulo").toString() %></h2>
                    <p>Descripción: <%= opo.get("descripcion").toString() %></p>
                    <p>Fecha: <%= opo.get("fecha").toString() %></p>
                    <p>Organización: <%= opo.get("nombreOrganizacion").toString() %></p>

                    <!-- Botón de compartir en Facebook -->
                    <div class="fb-share-button" 
                        data-href="<%= request.getRequestURL() %>" 
                        data-layout="button_count" 
                        data-size="small"
                        data-description="<%= opo.get("descripcion").toString() %>">
                        <a target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=<%= request.getRequestURL() %>&amp;src=sdkpreparse" class="fb-xfbml-parse-ignore">Compartir</a>
                    </div>

                    <!-- Botón de compartir en Twitter -->
                    <div>
                        <a href="https://twitter.com/share?text=<%= "¡Únete a esta oportunidad en NeedU_UPT!%0A%0ATítulo: " + opo.get("titulo").toString() + "%0ADescripción: " + opo.get("descripcion").toString().replaceAll("\\n", "%0A") %>" class="twitter-share-button" data-show-count="false">Tweet</a>
                        <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
                    </div>

                    <a href="InfoOportunidad.jsp?idoportunidad=<%= opo.get("idoportunidad").toString() %>" class="btn-ver-mas">Ver más acerca de esta Oportunidad</a> 
                    <br><br><a href="ControladorChat?accion=addchat&idoportunidad=<%= opo.get("idoportunidad").toString() %>" class="boton-chat">Iniciar Chat</a>

                    <!-- Formulario para ser voluntario -->                           
                    <form method="post" action="ControladorSolicitud?accion=solicitar" class="form-oportunidad">
                        <input type="hidden" name="idoportunidad" value="<%= opo.get("idoportunidad").toString() %>">
                        <button type='submit' class="btn-oportunidad">Ser Voluntario</button>
                    </form>
                        
                </div>
            </div>
        <%
                }
            } else {
        %>
            <p>No hay oportunidades disponibles</p>
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
    
    
    <script>
        document.addEventListener("DOMContentLoaded", function() {
        const observer = new IntersectionObserver((entries) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.classList.add("visible");
                }
            });
        }, {
            rootMargin: "0px",
            threshold: 0.1
        });

        const oportunidades = document.querySelectorAll('.card-oportunidad');
        oportunidades.forEach(opo => {
            observer.observe(opo);
        });
    });
    </script>
<a href="RankedUser.jsp">Ir a la página RankedUser</a>

</body>
</html>