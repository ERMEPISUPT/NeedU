<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
        <style>
            body, html {
                height: 100%;
                margin: 0;
                font-family: Arial, sans-serif;
                display: block;
                justify-content: center;
                align-items: center;
                background-color: #e9e9e9; /* Puedes cambiar el color de fondo si lo deseas */
            }

            .container {
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .box {
                background-color: #fff;
                border: 1px solid #ddd;
                padding: 20px;
                display: flex;
                flex-direction: column;
                gap: 20px;
                box-shadow: 0 0 50px rgba(0, 0, 0, 0.1);
                border-radius: 15px;
                width: 30%;
                overflow: auto;
                animation: fadeIn 0.5s ease;
            }


            h3 {
                color: #000;
                text-align: center;
            }
            
            h5 {
                font-weight: bold;
                font-size: 15px;
                margin-top: 15px;
                margin-bottom: 5px;
            }

            form {
                display: flex;
                flex-direction: column;
                gap: 10px; /* Espaciado entre los campos */
            }

            input[type=text], input[type=password], select {
                width: 90%;
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 8px;
            }

            input[type=submit] {
                background-color: #7eb1bb;
                color: white;
                padding: 14px 20px;
                border: none;
                cursor: pointer;
                border-radius: 20px;
                font-weight: bold;
                width: 30%;
                margin-left: auto;
                margin-right: auto;
                display: block;
                transition: background-color 0.3s ease;
            }
            
            input[type=submit]:hover {
                background-color: #65999a; /* Nuevo color de fondo para el estado hover */
            }
            
            
            #rol {
                width: 25%;
            }
            
            #genero{
                width: 20%;
            }
            
            
            
            #camposVoluntario, #camposOrganizacion {
                opacity: 0;
                transition: opacity 0.5s ease;
                display: none;
            }

            #camposVoluntario.visible, #camposOrganizacion.visible {
                opacity: 1;
                display: block;
            }
            
            
            @keyframes fadeIn {
                from {
                    opacity: 0;
                    transform: translateY(-10px);
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }

            
            
        </style>
              <link rel="icon" type="image/jpeg" href="imagenes\logo.jpeg"> 
    </head>
    <body>      
    <div class="container">
        <div class="box">
                <h3>Registrarse</h3>
                <form method="post" action="ControladorRegistro?accion=registrar">
                    Ingrese usuario<input type="text" name="txtusu" required>
                    Ingrese clave<input type="text" name="txtcla" required>
                    <label for="rol">Rol:</label>
                    
                    <select name="selectrol" id="rol" onchange="mostrarCampos(this.value)" required>
                        <option value="" disabled selected hidden>Seleccione un rol</option>
                        <option value="voluntario">Voluntario</option>
                        <option value="organizacion">Organización</option>
                    </select>

                    
                    <div id="camposVoluntario" style="display: none;">
                        Nombres<input type="text" name="txtnomvol"><br>
                        Apellidos<input type="text" name="txtapevol"><br>
                        <label for="genero">Género:</label>
                        <select name="selectgenvol" id="genero">
                            <option value="Masculino">Masculino</option>
                            <option value="Femenino">Femenino</option>
                        </select><br>
                        Edad<input type="text" name="txtedavol" oninput="validarEdad(this)" maxlength="3"><br>
                        <span id="mensajeError" style="color: red;"></span><br>
                        Correo<input type="text" name="txtcorvol" ><br>
                    </div>
                    <script>
                    function validarEdad(input) {
                        input.value = input.value.replace(/\D/g, '');

                        var edad = parseInt(input.value, 10);

                        if (edad > 120) {
                            input.value = '120';
                            document.getElementById('mensajeError').innerText = 'La edad no puede ser mayor a 120.';
                        } else {
                            document.getElementById('mensajeError').innerText = '';
                        }
                    }
                </script>
                    <div id="camposOrganizacion" style="display: none;">
                        Nombre Organizacion<input type="text" name="txtnomorg" ><br>
                        Tipo de Organizacion<input type="text" name="txttiporg" >
                        Correo<input type="text" name="txtcororg" ><br>
                    </div>
                    <h5>Ubicacion</h5>
                    Pais<input type="text" name="txtpai" >
                    Ciudad<input type="text" name="txtciu" >
                    Direccion<input type="text" name="txtdir" >
                    <br>
                    <input type="submit" value="Guardar Cambios">
                </form>
        </div>
    </div>

        
        
        
        
        <script>
        function mostrarCampos(valor) {
            var camposVoluntario = document.getElementById('camposVoluntario');
            var camposOrganizacion = document.getElementById('camposOrganizacion');

            // Inicialmente ocultar ambos campos
            camposVoluntario.style.display = 'none';
            camposOrganizacion.style.display = 'none';
            camposVoluntario.style.opacity = '0';
            camposOrganizacion.style.opacity = '0';

            // Mostrar el campo correspondiente
            if (valor === 'voluntario') {
                camposVoluntario.style.display = 'block';
                setTimeout(function() { camposVoluntario.style.opacity = '1'; }, 10);
            } else if (valor === 'organizacion') {
                camposOrganizacion.style.display = 'block';
                setTimeout(function() { camposOrganizacion.style.opacity = '1'; }, 10);
            }
        }
        </script>
    </body>
</html>