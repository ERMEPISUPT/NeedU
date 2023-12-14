<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Modelo.Mensaje" %>

<!DOCTYPE html>
<html>
<head>
    <title>Chat</title>
    <link rel="icon" type="image/jpeg" href="../imagenes/logo.jpeg">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f3f3f3;
        }

        .chat-container {
            max-width: 600px;
            margin: 20px auto;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .chat-header {
            background-color: #075e54;
            color: #fff;
            padding: 15px;
            border-top-left-radius: 8px;
            border-top-right-radius: 8px;
        }

        .chat-messages {
            max-height: 400px;
            overflow-y: auto;
            padding: 10px;
        }

        .mensaje {
            margin-bottom: 15px;
            clear: both;
            overflow: auto;
        }

        .emisor {
            text-align: right;
        }

        .receptor {
            text-align: left;
        }

        .mensaje-info {
            font-size: 12px;
            color: #888;
            margin-bottom: 5px;
        }

        .contenido-mensaje {
            background-color: #f1f0f0;
            padding: 8px 12px;
            border-radius: 15px;
            display: inline-block;
            max-width: 70%;
            word-wrap: break-word;
        }

        .input-container {
            padding: 10px;
            border-top: 1px solid #ccc;
        }

        input[type="text"] {
            width: calc(100% - 70px);
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-right: 5px;
        }

        button {
            padding: 8px 15px;
            background-color: #128c7e;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #075e54;
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
        .chat-header h4 {
            text-align: right;
            margin-bottom: 20px; /* Ajusta el margen inferior según sea necesario */
        }

        .chat-header h4::after {
            content: ""; /* Contenido vacío para forzar el salto de línea */
            display: block;
            clear: both;
        }

        .chat-header h4 span {
            display: inline-block;
            max-width: 45%; /* Ajusta el ancho máximo según sea necesario */
        }

        .chat-header h4 span.emisor {
            float: right;
        }

        .chat-header h4 span.receptor {
            float: left;
        }

    </style>
    <link rel="icon" type="image/jpeg" href="imagenes\logo.jpeg"> 
</head>
<body>
    <div class="menu">
  <a href="ControladorLogin?accion=redirigirInicio">Inicio</a>
  
  <a href="ControladorChat?accion=allchats">Chat</a>
  <a href="ControladorNotificacion?accion=notificacion">Notificaciones</a>
  <a href="ControladorPerfil?accion=perfil">Perfil</a>
  <a href="ControladorUsuario?accion=control">Control de usuario</a>
  <a href="ControladorLogin?accion=logout">Cerrar Sesión</a>
</div>
    <div class="chat-container">
        <div class="chat-header">
            <h2>Chat</h2>
        </div>
        <h4>
            <span class="receptor">Organizacion: <a href="RankedUser2.jsp?idUsuario=<%= request.getParameter("idusuario2")%>"><%= request.getParameter("nombre2")%></a></span>
            <span class="emisor" style="margin-right: 60px;">TÚ: <a href="RankedUser2.jsp?idUsuario=<%= request.getParameter("idusuario1")%>"><%= request.getParameter("nombre1")%></a></span>
        </h4>


        <div class="chat-messages" id="chat-messages">
            <%
                List<Mensaje> listaMensajes = (List<Mensaje>) request.getAttribute("listaMensajes");
                int idusuario = (int) session.getAttribute("idusuario");
                out.println("idusuario: " + idusuario);
                String nombreUsuario = (String) session.getAttribute("nombreUsuario");
                
                for (Mensaje mensaje : listaMensajes) {
                    String claseMensaje = (mensaje.getEmisor() == idusuario) ? "emisor" : "receptor";
            %>
                    <div class="mensaje <%= claseMensaje %>">
                        <div class="mensaje-info">
                            <span><%= mensaje.getNombre_emisor() %></span>
                            <span class="hora-pequena"><%= mensaje.getHora() %></span>
                        </div>
                        <div class="contenido-mensaje">
                            <%= mensaje.getMensaje() %>
                        </div>
                    </div>
            <%
                }
            %>
        </div>

        <div class="input-container">   
            <form method="post" action="ControladorChat?accion=send">
                <input type="hidden" name="idchat" value="<%= request.getParameter("idchat") %>">
                <input type="hidden" name="idusuario1" value="<%= request.getParameter("idusuario1") %>">
                <input type="hidden" name="idusuario2" value="<%= request.getParameter("idusuario2") %>">
                <input type="hidden" name="nombre1" value="<%= request.getParameter("nombre1")%>">
                <input type="hidden" name="nombre2" value="<%= request.getParameter("nombre2")%>">
                <input type="text" name="txtmensaje" placeholder="Escribe tu mensaje..." required>
                <button type="submit">Enviar</button>
            </form>
        </div>
    </div>

    <script>
        // Auto scroll to the bottom
        var chatMessages = document.getElementById('chat-messages');
        chatMessages.scrollTop = chatMessages.scrollHeight;
    </script>
</body>
</html>
