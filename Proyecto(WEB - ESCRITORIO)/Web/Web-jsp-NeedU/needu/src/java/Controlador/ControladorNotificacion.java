/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Notificacion;
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
@WebServlet(name = "ControladorNotificacion", urlPatterns = {"/ControladorNotificacion"})
public class ControladorNotificacion extends HttpServlet {
    Notificacion not = new Notificacion();
    NotificacionDAO notdao = new NotificacionDAO();
    String notificacion = "notificaciones/notificaciones.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorNotificacion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorNotificacion at " + request.getContextPath() + "</h1>");
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
            if("notificacion".equalsIgnoreCase(action)){
                //request.setAttribute("idusuario", idusuario);
                acceso = notificacion;
            }else if("marcar".equalsIgnoreCase(action)){
                notdao.vistearNotificacion(idusuario);
                response.sendRedirect("ControladorChat?accion=allchats");
                return;
            }else if ("crear".equalsIgnoreCase(action)) {
                int idOportunidad = Integer.parseInt(request.getParameter("idoportunidad"));
                String mensaje = "Nueva oportunidad de voluntariado enviada para la oportunidad " + idOportunidad;

                // El valor de idCategoria es 2 según tu especificación
                int idCategoria = 2;

                boolean exito = notdao.add(idusuario, mensaje, idCategoria);

                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print("{");
                out.print("\"success\":" + exito);
                out.print(", \"message\":\"" + (exito ? "Notificación creada con éxito." : "Error al crear la notificación.") + "\"");
                out.print("}");
                out.flush();
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
        processRequest(request, response);
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
