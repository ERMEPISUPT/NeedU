<%@page import="Modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="ModeloDAO.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Por qué ser Voluntario</title>
    <link rel="stylesheet" type="text/css" href="css/estilos_Nosotros.css">
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
            <a href="#"></a>
        </div>
    </div>

    <div id="menu-options" class="menu-options">
        <ul>
            
            
        </ul>
    </div>
    
    
    
    
    <div class="container">
        <div class="header">
          <h1>¿Porque volverse un Voluntario?</h1>
          <p>Convertirse en voluntario ofrece una multitud de beneficios tanto para la comunidad como para el individuo que presta su tiempo y esfuerzos.</p>
        </div>

        <div class="steps">
          <div class="step">
              <img src="imagenes/Img01.JPG" alt="Set your goals">
            <h2>1. Contribución Comunitaria</h2>
            <p>El voluntariado permite a las personas hacer una diferencia tangible en su comunidad o en una causa que les importa.</p>
          </div>

          <div class="step">
              <img src="imagenes/Img02.JPG" alt="Name owners">
            <h2>2. Desarrollo de Habilidades</h2>
            <p>A través del voluntariado, las personas pueden aprender nuevas habilidades o mejorar las existentes, lo que puede ser útil en la vida personal y profesional.</p>
          </div>

          <div class="step">
              <img src="imagenes/Img03.JPG" alt="Get updates">
            <h2>3. Construcción de Redes</h2>
            <p>El voluntariado ofrece la oportunidad de conocer nuevas personas, lo que puede llevar a amistades duraderas y contactos profesionales valiosos.</p>
          </div>
        </div>
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


