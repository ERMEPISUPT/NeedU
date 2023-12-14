/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Control;
import ModeloDAO.ControlDAO;
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
@WebServlet(name = "ControladorUsuario", urlPatterns = {"/ControladorUsuario"})
public class ControladorUsuario extends HttpServlet {
    String control = "Usuario/control.jsp";
    String controlAdmin = "Usuario/controlAdmin.jsp";
    Control c = new Control();
    ControlDAO cdao = new ControlDAO();

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
            out.println("<title>Servlet ControladorUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorUsuario at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false);
        String action = request.getParameter("accion");
        String acceso = "";
        if (session != null) {
            int idusuario = (int) session.getAttribute("idusuario");
            String rol = (String) session.getAttribute("rol");
            String usuario = (String) session.getAttribute("usuario");
            String clave = (String) session.getAttribute("clave");
            int estado = (int) session.getAttribute("estado");
            if ("control".equalsIgnoreCase(action)) {
                // Código para obtener las listas desde tu DAO
                ControlDAO controlDAO = new ControlDAO();

                // Obtener la lista de registros para un usuario específico
                Control c = new Control();
                c.setIdusuario(idusuario);
                List<Control> registrosUsuario = controlDAO.verRegistroUsuario(c);

                // Obtener la lista de todos los registros
                List<Control> todosRegistros = controlDAO.verTodosRegistros();

                // Agregar las listas al request para enviarlas a la página JSP
                request.setAttribute("registrosUsuario", registrosUsuario);
                request.setAttribute("todosRegistros", todosRegistros);

                // Redirigir a la página control.jsp
                acceso = control;
                if(rol.equalsIgnoreCase("admin")){
                    acceso = controlAdmin;
                }else acceso = control;
            }
        } else {
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
        HttpSession session = request.getSession(false);
        if (session != null) {
            //Si hay sesiona iniciada dx
            int idusuario = (int) session.getAttribute("idusuario");
            String rol = (String) session.getAttribute("rol");
            String usuario = (String) session.getAttribute("usuario");
            String clave = (String) session.getAttribute("clave");
            int estado = (int) session.getAttribute("estado");
            
            //Aqui trabajar los if action
            //if(xasdasd.equalsIgnoreCase(action))
            
            
        } else {
            // La sesión no existe
            System.out.println("La sesión no existe");
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
