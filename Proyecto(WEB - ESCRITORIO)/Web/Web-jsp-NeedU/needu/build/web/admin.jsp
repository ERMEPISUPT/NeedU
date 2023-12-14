<%@page import="Modelo.Oportunidad"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.OportunidadDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Needu</title>
    <link rel="stylesheet" type="text/css" href="estilos_inicio_Organizacion.css">
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
            <a href="inicio.jsp">Inicio</a>
            <a href="#">Panel Administrativo</a>
            <a href="ControladorUsuario?accion=control">Control de usuarios</a>
            <a href="ControladorLogin?accion=logout">Cerrar Sesión</a>
        </div>
    </div>

    <div id="menu-options" class="menu-options">
        <ul>
            <li><a href="#">Inicio</a></li>
            <li><a href="ControladorChat?accion=allchats">Chat</a></li>
            <li><a href="ControladorNotificacion?accion=notificacion">Notificaciones</a></li>
            <li><a href="ControladorPerfil?accion=perfil">Perfil</a></li>
            <li><a href="ControladorLogin?accion=logout">Cerrar Sesión</a></li>
            
        </ul>
    </div>

        <div class="seccion">
            <div class="fila">
                <div class="columna-mitad">
                    <div class="modulo texto fondo-claro">
                        <div class="texto-interior"><h4>Administrador</h4></div>
                    </div>
                    <div class="modulo texto fondo-claro">
                        <div class="texto-interior"><h1>Bienvenido Administrador</h1></div>
                    </div>

                    <!-- Agregar el enlace de descarga -->
                    <div class="modulo texto fondo-claro">
                        <div class="texto-interior">
                        <a href="https://drive.google.com/file/d/1w6f75yE6zvAimNNqP2YE2IYhrbNOaT-9/view?usp=sharing" target="_blank" rel="noopener noreferrer" style="color: white; text-decoration: none;">Descargar desde nuestro panel administrativo</a>
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