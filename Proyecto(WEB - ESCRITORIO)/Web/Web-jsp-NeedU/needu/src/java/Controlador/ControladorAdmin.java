/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Control;
import ModeloDAO.AdminDAO;
import ModeloDAO.ControlDAO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "ControladorAdmin", urlPatterns = {"/ControladorAdmin"})
public class ControladorAdmin extends HttpServlet {
    String control = "Usuario/control.jsp";
    Control c = new Control();
    ControlDAO cdao = new ControlDAO();
    String inicio = "inicio.jsp";
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
            out.println("<title>Servlet ControladorAdmin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorAdmin at " + request.getContextPath() + "</h1>");
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
            
            if ("generarInforme".equalsIgnoreCase(action)) {
                AdminDAO addao = new AdminDAO();
                ControlDAO controlDAO = new ControlDAO();
                List<Control> todosRegistros = controlDAO.verTodosRegistros();
                String nombreArchivo = addao.generarInforme(todosRegistros);

                File file = new File(nombreArchivo);
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
                FileInputStream fis = new FileInputStream(file);
                OutputStream os = response.getOutputStream();
                byte[] buffer = new byte[4096];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                fis.close();
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
