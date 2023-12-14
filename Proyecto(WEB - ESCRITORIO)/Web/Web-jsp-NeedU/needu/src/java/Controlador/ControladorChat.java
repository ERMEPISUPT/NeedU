/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Chat;
import Modelo.Mensaje;
import ModeloDAO.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ERICKPC
 */
@WebServlet(name = "ControladorChat", urlPatterns = {"/ControladorChat"})
public class ControladorChat extends HttpServlet {
    String inicio = "inicio.jsp";
    String chat = "Usuario/chat.jsp";
    String allchats = "Usuario/allchats.jsp";
    UsuarioDAO usudao = new UsuarioDAO();
    ChatDAO chatdao = new ChatDAO();
    MensajeDAO mendao = new MensajeDAO();
    Mensaje men = new Mensaje();
    NotificacionDAO notdao = new NotificacionDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorChat</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorChat at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = null;
        String action = request.getParameter("accion");
        HttpSession session = request.getSession(false);
         
        if (session != null) {
            //Si hay sesiona iniciada dx
            int idusuario = (int) session.getAttribute("idusuario");
            String rol = (String) session.getAttribute("rol");
            String usuario = (String) session.getAttribute("usuario");
            String clave = (String) session.getAttribute("clave");
            int estado = (int) session.getAttribute("estado");
            
            //Aqui trabajar los if action
            if("allchats".equalsIgnoreCase(action)){
                //request.setAttribute("idusuario", idusuario);
                List<Chat> listaChats = chatdao.obtenerChatsUsuario(idusuario);
                request.setAttribute("listaChats", listaChats);
                acceso = allchats;
            }else if("chat".equalsIgnoreCase(action)){
                
                int idchat = Integer.parseInt(request.getParameter("idchat"));
                MensajeDAO mensajeDAO = new MensajeDAO();
                List<Mensaje> listaMensajes = mensajeDAO.obtenerMensajesChat(idchat);
                request.setAttribute("listaMensajes", listaMensajes);
                acceso = chat;
            }else if("addchat".equalsIgnoreCase(action)){
                int idoportunidad = Integer.parseInt(request.getParameter("idoportunidad"));
                System.out.println("idooportunidad: "+ idoportunidad);
                int idorganizacion = usudao.obtenerIdUsuarioPorIdOportunidad(idoportunidad);
                System.out.println("idusuario: "+ idusuario);
                System.out.println("idusuariooportunidad: "+ idorganizacion);
                if(!chatdao.existeChat(idusuario, idorganizacion)){
                    int idchat = chatdao.crearChat(idusuario, idorganizacion);
                    
                }
                response.sendRedirect("ControladorChat?accion=allchats");
                return;
            }
              
        } else {
            // La sesión no existe
            System.out.println("La sesión no existe");
        }
        
        if (acceso != null) {
            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = null;
        String action = request.getParameter("accion");
        HttpSession session = request.getSession(false);
        if (session != null) {
            //Si hay sesiona iniciada dx
            int idusuario = (int) session.getAttribute("idusuario");
            String rol = (String) session.getAttribute("rol");
            String usuario = (String) session.getAttribute("usuario");
            String clave = (String) session.getAttribute("clave");
            int estado = (int) session.getAttribute("estado");
            
            //Aqui trabajar los if action
            if ("send".equalsIgnoreCase(action)) {
                int idchat = Integer.parseInt(request.getParameter("idchat"));
                String mensaje = request.getParameter("txtmensaje");
                System.out.println("le di a enviar y el idchat es: " + idchat);

                int idusuario1 = Integer.parseInt(request.getParameter("idusuario1"));
                int idusuario2 = Integer.parseInt(request.getParameter("idusuario2"));
                String nombre1 = request.getParameter("nombre1");
                String nombre2 = request.getParameter("nombre2");
                String nombreEmisor = "";
                int idusuarioActual = (int) session.getAttribute("idusuario");

                int idReceptor;
                if (idusuario1 == idusuarioActual) {
                    idReceptor = idusuario2;
                    nombreEmisor = nombre1;
                } else {
                    idReceptor = idusuario1;
                    nombreEmisor = nombre2;
                }

                mendao.enviarMensaje(idchat, idusuarioActual, idReceptor, mensaje);
                MensajeDAO mensajeDAO = new MensajeDAO();
                List<Mensaje> listaMensajes = mensajeDAO.obtenerMensajesChat(idchat);
                request.setAttribute("listaMensajes", listaMensajes);
                request.setAttribute("idchat", idchat);
                System.out.println("el idchat que envio cuando le doy a send: " + idchat);
                response.sendRedirect("ControladorChat?accion=chat&idusuario1=" + idusuario1 + "&idusuario2=" + idusuario2 + "&idchat=" + idchat + "&nombre1=" + nombre1 + "&nombre2=" + nombre2 + "&nombreEmisor=" + nombreEmisor);
                notdao.add(idReceptor, nombreEmisor, 1);
                
            }
              
        } else {
            // La sesión no existe
            System.out.println("La sesión no existe");
        }
        
        if (acceso != null) {
            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
