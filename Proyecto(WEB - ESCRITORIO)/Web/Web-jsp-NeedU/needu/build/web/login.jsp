<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/estilos_login.css">
    <link rel="icon" type="image/jpeg" href="imagenes\logo.jpeg">
    <title>Login</title>  
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    <script>
        function onSubmit(event) {
            var response = grecaptcha.getResponse();
            if (response.length === 0) {
                event.preventDefault(); // Evitar el envío del formulario si el reCAPTCHA no está completado
                alert('Por favor, completa el reCAPTCHA.');
            }
        }
    </script>
</head>
<body>
    <video autoplay muted loop id="videoBackground">
        <source src="imagenes/export_login.mp4" type="video/mp4">
        Tu navegador no soporta videos HTML5.
    </video>
    <div class="container">
        <div class="box">
            <div class="left">
                <img src="imagenes/voluntario_login.jpg" alt="Voluntarios limpiando la playa">
            </div>
            <div class="right">
                <form method="post" action="ControladorLogin?accion=login" onsubmit="return onSubmit(event);">
                    <h2 style="text-align: center; color: #000;">Inicio de sesión</h2>
                    <label for="txtusu" style="color: #000;">Ingrese Usuario:</label>
                    <input type="text" name="txtusu" id="txtusu" required>
                    <label for="txtcla" style="color: #000;">Ingrese Clave:</label>
                    <input type="password" name="txtcla" id="txtcla" required>
                    <div class="g-recaptcha" data-sitekey="6LdMEgopAAAAAKFqTzfLzxq5unYZrKSBJJ3t89Zx"></div>
                    <input type="submit" value="Iniciar sesión">
                </form>
                <a href="forgotPassword.jsp">Contraseña olvidada? Haz click aqui</a>
            </div>
        </div>
    </div>
</body>
</html>
