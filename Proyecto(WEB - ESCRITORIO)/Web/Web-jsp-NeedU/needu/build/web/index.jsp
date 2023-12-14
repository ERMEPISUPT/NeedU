<%@page import="Modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="ModeloDAO.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Needu</title>
    <link rel="stylesheet" type="text/css" href="estilos_indexx.css">
    <link rel="icon" type="image/jpeg" href="imagenes\logo.jpeg">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+Meroitic&family=Poppins:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    
<style>
    .proa-tribu-container {
        background-image: url('imagenes/fondo_unete.png');
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        padding: 50px 0; /* Ajusta este valor para la altura */
        display: flex;
        justify-content: flex-start; /* Alineación a la izquierda con espacio adicional */
        align-items: center;
        text-align: left; /* Alinear texto a la izquierda */
    }

    .proa-tribu-content {
        padding: 20px;
        padding-left: 37%; /* Mantén tu padding izquierdo */
        text-align: left; /* Alinear texto a la izquierda */
    }

    .proa-tribu-header img {
        max-width: 120px; /* Aumenta el tamaño de tu imagen según prefieras */
        margin-bottom: 20px; /* Espacio debajo de la imagen */
    }

    .proa-tribu-header h2 {
        font-family: 'Poppins', sans-serif;
        color: #000000; /* Color negro para 'Únete a la' */
        font-size: 1.8rem; /* Tamaño de fuente */
        margin: 5px 0; /* Menor separación entre los elementos */
        text-align: left;
    }

    .proa-tribu-header h1 {
        font-family: 'Poppins', sans-serif;
        color: #4EC2A7; /* Color #4EC2A7 para 'Proa Tribu' */
        font-size: 1.8rem; /* Tamaño de fuente igual para ambos */
        margin: 5px 0; /* Menor separación entre los elementos */
        text-align: left;
    }

    .proa-tribu-content p {
        color: #343a40;
        font-size: 18px;
        margin-top: 20px;
        text-align: left; /* Alinear texto a la izquierda */
        max-width: 600px; /* Limita el ancho de la descripción */
        font-family: ScandiaWeb,sans-serif;
    }
    
    
    @media (max-width: 1200px) {
        .proa-tribu-content {
            padding-left: 20%; /* Ajusta este valor según sea necesario */
        }
    }
    
/* SECCIÓN DE TARJETAS */
.ways-to-help-section {
  background-color: #f9d1c5;
  text-align: center;
  padding: 50px 20px;
}

.ways-to-help-title {
  color: #f1595c;
  font-size: 2rem;
  font-weight: bold;
  margin: 0;
  /* Asegura que los títulos estén en la misma línea */
}

/* Separación de las dos partes del subtítulo */
.ways-to-help-subtitle {
  color: #f1595c;
  font-size: 2rem;
  font-weight: bold;
  margin: 0;
  display: block; /* Ahora está en bloque para la separación */
  padding-top: 0.5rem; /* Espacio adicional para 'que puedes ayudar de tres formas:' */
  padding-bottom: 60px;
}

.cards-container {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 10px; /* Espacio reducido entre tarjetas */
}

.card {
  background-color: #fff;
  border-radius: 15px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 30px 30px 120px; /* Padding incrementado en la parte inferior */
  width: 250px;
  margin: auto;
  transition: transform 0.3s ease;
  position: relative;
  background-image: url('imagenes/manogracias.JPG');
  background-position: center 343px; /* Posicionamiento de la imagen */
  background-repeat: no-repeat;
  background-size: 100% auto;
  min-height: 290px; /* Altura mínima establecida */
}

.card:hover {
  transform: translateY(-5px);
}

.card h3 {
  margin-bottom: 20px;
  font-size: 1.5rem;
  color: #000; /* Títulos en negro */
  font-weight: normal; /* Asegura que no estén en negrita */
}

.card h3 span {
  color: #000000; /* Color para la parte específica del título */
  font-weight: bold; /* Solo esta parte en negrita */
}

.card p {
  color: #666;
  margin-bottom: 30px;
  line-height: 1.6;
}

.card button {
  background-color: #f1595c;
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  cursor: pointer;
  font-weight: bold;
  transition: background-color 0.3s;
}

.card button:hover {
  background-color: #c00;
}

/* Estilos para texto en negrita dentro de párrafos */
.bold {
  font-weight: bold;
}


/*QUE BENEFICIOS BRINDA*/
.benefits-section {
  text-align: center;
  padding: 50px;
  font-family: 'Poppins', sans-serif;
  background-color: #ffffff; /* Fondo blanco para la sección */
}

.benefits-title {
  color: #000;
  margin-bottom: 20px;
  font-size: 24px;
  display: inline-block; /* Para que el h2 no ocupe el ancho completo */
  position: relative; /* Para el posicionamiento de la pseudo-elemento */
}

.benefits-title::after {
  content: ''; /* Se necesita para generar el pseudo-elemento */
  display: block; /* Trata al pseudo-elemento como un bloque para que pueda tener ancho y alto */
  width: 100%; /* La barra roja se extiende a lo ancho del título */
  height: 4px; /* Altura de la barra roja */
  background-color: #f1595c; /* El color rojo de tu elección */
  position: absolute; /* Posicionamiento absoluto respecto al título */
  left: 0; /* Alinea la barra al lado izquierdo del título */
  bottom: -10px; /* Posiciona la barra debajo del título */
}

.benefits-title span::after {
  content: '';
  display: block; /* Esto hace que el pseudo-elemento sea como un div debajo del span */
  height: 4px; /* Altura de la barra roja */
  background-color: #f1595c; /* El color rojo para la barra */
  position: absolute; /* Posiciona la barra respecto al span */
  left: 0;
  right: 0;
  bottom: 0; /* Justo debajo del texto */
}



.benefits-container {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  max-width: 1200px; /* Ancho máximo para el contenedor */
  margin: 0 auto; /* Centrar el contenedor en la página */
  padding-top: 50px; /* Espacio superior añadido */
}

.benefit {
  width: 200px; /* O ajusta según sea necesario */
  margin: 0 10px; /* Espacio horizontal para separar los elementos */
  display: flex; /* Usar flexbox para centrar */
  flex-direction: column; /* Orientar los elementos verticalmente */
  align-items: center; /* Centrar elementos horizontalmente */
}

.benefit img {
  width: 80%; /* Imágenes más pequeñas */
  margin-bottom: 10px;
}

.benefit h3 {
  color: #000;
  font-size: 20px; /* Tamaño reducido para los subtítulos */
  margin-bottom: 5px;
}

.benefit p {
  color: #666;
  font-size: 14px; /* Tamaño reducido para el párrafo */
}

/* Respuestas para pantallas más pequeñas */
@media (max-width: 768px) {
  .benefits-container {
    flex-direction: column;
    align-items: center;
  }

  .benefit {
    width: 100%; /* Ocupa toda la anchura disponible */
    max-width: 300px; /* Para evitar que las tarjetas sean demasiado anchas en pantallas más grandes */
  }

  .benefit img {
    width: 60%; /* Imágenes aún más pequeñas para dispositivos móviles */
  }
}



</style>


</head>
<body>

    <div id="navbar">
        <div class="logo">Need U</div>
        <div class="menu-icon" onclick="toggleMenu()">
            <img src="imagenes/menuU.png" alt="Menú">
        </div>
        <div class="links">
            <a href="Sobre_Nosotros.jsp">Por qué ser voluntario</a>
            <a href="ControladorLogin?accion=loginusuario">Inicio de Sesión</a>
            <a href="ControladorRegistro?accion=registro" class="registro-btn">Regístrate</a>

        </div>
    </div>

    <div id="menu-options" class="menu-options">
        <ul>
            <li><a href="Sobre_Nosotros.jsp">Por qué ser voluntario</a></li>
            <li><a href="ControladorLogin?accion=loginusuario">Inicio de Sesión</a></li>
            <li><a href="ControladorRegistro?accion=registro" class="registro-btn">Regístrate</a></li>
        </ul>
    </div>

    <div class="wow animate__animated animate__fadeInDown" data-wow-offset="50">
        <div class="seccion">
            <div class="fila">
                <div class="columna-mitad">
                    <div class="modulo texto fondo-claro">
                        <div class="texto-interior"><h4>Bienvenido</h4></div>
                    </div>
                    <div class="modulo texto fondo-claro">
                        <div class="texto-interior"><h1>Conviértete en voluntario o ayuda con un evento</h1></div>
                    </div>
                    <div class="modulo texto fondo-claro">
                        <div class="texto-interior">
                            <p>Únete a nuestra comunidad comprometida y haz la diferencia. Al convertirte en voluntario, contribuyes directamente a mejorar la vida de quienes más lo necesitan. Suspendisse imperdiet condimentum eleifend. Nuestra misión es crear un impacto positivo a través de eventos significativos y acciones solidarias. Tu participación es fundamental para construir un mundo mejor.</p>
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
    </div>
    
    <div class="wow animate__animated animate__fadeInDown" data-wow-offset="50">
        <div class="seccion-cuadrados">
            <div class="cuadrado" style="background-color: #EF8451;">
                <h2>Encuentra alguna oportunidad cerca de ti!</h2>
                <button onclick="location.href='ControladorRegistro?accion=registro'">Buscar</button>
            </div>
            <div class="cuadrado" style="background-color: #264C4E;">
                <h2>Quieres volverte voluntario?</h2>
                <button onclick="location.href='ControladorRegistro?accion=registro'">Ponerse en Contacto</button>
            </div>
        </div>
    </div>
    

    <div class="wow animate__animated animate__fadeInDown" data-wow-offset="100">
        <div class="proa-tribu-container">
          <div class="proa-tribu-content">
            <div class="proa-tribu-header">
              <img src="imagenes/unete.png" alt="Icono">
              <h2>Únete a la</h2>
              <h1>Comunidad NeedU</h1>
            </div>
            <p>La Comunidad NeedU es un grupo solidario comprometido con el cambio. A través de la colaboración de voluntarios y donantes, hemos impactado la vida de innumerables personas y apoyado a diversas organizaciones sociales en su misión.</p> 
          </div>
        </div>
    </div>


    <div class="wow animate__animated animate__fadeInDown" data-wow-offset="50">
        <div class="ways-to-help-section">
            <h2 class="ways-to-help-title">Ser de la Proa Tribu significa</h2>
            <h2 class="ways-to-help-subtitle">que puedes ayudar de tres formas:</h2>
            <div class="cards-container">
                <div class="card">
                    <h3><span>Quiero ser</span><br><span style="color: #4ec2a7;">voluntario</span></h3>
                    <p>Proa te <span class="bold">conecta</span> con programas de voluntariado de <span class="bold">más de 400</span> organizaciones sociales.<br> <span class="bold">¡Elige la causa ideal para ti!</span></p>
                    <button onclick="window.location.href='ControladorRegistro?accion=registro'">Ser voluntario</button>
                </div>
                <div class="card">
                    <h3><span>Quiero</span><br><span style="color: #4ec2a7;">apoyar</span></h3>
                    <p>Conoce las formas en las que puedes apoyar a NeedU. <span class="bold">Juntos</span> podemos marcar la <br> <span class="bold">diferencia.</span></p>
                    <button onclick="window.location.href='ControladorRegistro?accion=registro'">Ser voluntario</button>
                </div>
                <div class="card">
                    <h3><span>Quiero</span><br><span style="color: #4ec2a7;">aprender mas</span></h3>
                    <p>Explora nuestro sitio para obtener información adicional sobre NeedU, nuestra<span class="bold">Mision</span>  y cómo puedes <span class="bold">involucrarte</span>. en nuestra comunidad<span class="bold"> solidaria.</span></p>
                    <button onclick="window.location.href='ControladorRegistro?accion=registro'">Más información</button>
                </div>
            </div>
        </div>
    </div>

    <div class="wow animate__animated animate__fadeInDown" data-wow-offset="50">
        <div class="benefits-section">
          <h2 class="benefits-title">¿Qué beneficios brinda la plataforma de NeedU?</h2>
          <div class="benefits-container">
            <!-- Beneficio 1 -->
            <div class="benefit">
              <img src="imagenes/beneficio1.png" alt="Sencilla y Amigable">
              <h3>Sencilla y Amigable</h3>
              <p>La plataforma es fácil de usar, rápida e intuitiva.</p>
            </div>
            <!-- Beneficio 2 -->
            <div class="benefit">
              <img src="imagenes/beneficio2.png" alt="Confiable">
              <h3>Confiable</h3>
              <p>Las organizaciones pasan por un filtro previo realizado Proa.</p>
            </div>
            <!-- Beneficio 3 -->
            <div class="benefit">
              <img src="imagenes/beneficio3.png" alt="Diversidad de causas">
              <h3>Diversidad de causas</h3>
              <p>Tienes a tu disposición proyectos para donar según tu interés.</p>
            </div>
            <!-- Beneficio 4 -->
            <div class="benefit">
              <img src="imagenes/beneficio4.png" alt="Continuidad">
              <h3>Continuidad</h3>
              <p>Damos seguimiento del proyecto para aumentar la transparencia.</p>
            </div>
          </div>
        </div>
    </div>


    
    
<div class="wow animate__animated animate__fadeInDown" data-wow-offset="50">
    <div class="faq-container">
        <div class="faq-section">
            <h2>Preguntas Frecuentes</h2>
            <div class="faq-item">
                <div class="faq-question">
                    <h3>¿Cómo puedo obtener reconocimiento por mi labor como voluntario en NeedU?</h3>
                    <span class="faq-toggle">▼</span>
                </div>
                <div class="faq-answer">
                    <p>En NeedU, valoramos y reconocemos la dedicación y el esfuerzo de nuestros voluntarios. Ofrecemos certificaciones y reconocimientos en base a tus contribuciones y logros en nuestra comunidad.</p>
                </div>
            </div>
            <!-- Agrega una nueva pregunta aquí -->
            <div class="faq-item">
                <div class="faq-question">
                    <h3>¿Existen programas de voluntariado a tiempo completo en NeedU que proporcionen apoyo financiero?</h3>
                    <span class="faq-toggle">▼</span>
                </div>
                <div class="faq-answer">
                    <p>Algunos de nuestros programas de voluntariado a tiempo completo en NeedU ofrecen estipendios para ayudar a cubrir los gastos de subsistencia durante tu servicio.</p>
                </div>
            </div>
            <!-- Continúa agregando preguntas según sea necesario -->
            <div class="faq-item">
                <div class="faq-question">
                    <h3>¿Es posible hacer voluntariado desde casa con NeedU?</h3>
                    <span class="faq-toggle">▼</span>
                </div>
                <div class="faq-answer">
                    <p>Sí, en NeedU ofrecemos oportunidades de voluntariado que pueden realizarse de forma remota desde la comodidad de tu hogar.</p>
                </div>
            </div>
        </div>
    </div>
</div>


    
    <footer class="site-footer">
        <div class="footer-content">
            <div class="footer-section">
                <h4>Acerca de NEEDU</h4>
                <p>AFS es una organización internacional, voluntaria, no gubernamental, sin fines de lucro, que promueve oportunidades de aprendizaje intercultural para ayudar a las personas a desarrollar el conocimiento, las destrezas y el entendimiento necesarios para crear un mundo más justo y pacífico.</p>
            </div>
            <div class="footer-section">
                <h4>Contáctenos</h4>
                <p>Para contactar a NEEDU Programas Interculturales Perú llámenos al: +51 976 359 635 o escríbanos al: +51 999 850 227.<br>
                También puedes visitarnos en Av. Javier Prado Este 596 Of. 302. Tacna – Perú</p>
            </div>
            <div class="footer-section">
                <p>NEEDU apoya los Objetivos Mundiales de las Naciones Unidas</p>
                <img src="imagenes/ods01.jpg" alt="Objetivo de Desarrollo Sostenible 1" />
                <img src="imagenes/ods10.jpg" alt="Objetivo de Desarrollo Sostenible 10" />
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; NEEDU Perú 2023 | International Terms of Use & Policies | Política de cookies</p>
        </div>
    </footer>
    
    
        <script>
            document.querySelectorAll('.faq-question').forEach(question => {
                question.addEventListener('click', () => {
                    const answer = question.nextElementSibling;
                    const toggle = question.querySelector('.faq-toggle');

                    answer.classList.toggle('open');
                    toggle.classList.toggle('open');
                });
            });
        </script>
    

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
    document.addEventListener('DOMContentLoaded', function () {
        const elements = document.querySelectorAll('.custom-animation');

        function isVisible(element) {
            const rect = element.getBoundingClientRect();
            const viewHeight = Math.max(document.documentElement.clientHeight, window.innerHeight);
            return !(rect.bottom < 0 || rect.top - viewHeight >= 0);
        }

        function scanDocument() {
            elements.forEach(function (element) {
                if (isVisible(element) && !element.classList.contains('animated')) {
                    element.classList.add('animated', 'fadeInDown');
                }
            });
        }

        window.addEventListener('scroll', scanDocument);
        scanDocument();
    });
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/wow/1.1.2/wow.min.js"></script>

<script>
  new WOW().init();
</script>

    
    
</body>
</html>