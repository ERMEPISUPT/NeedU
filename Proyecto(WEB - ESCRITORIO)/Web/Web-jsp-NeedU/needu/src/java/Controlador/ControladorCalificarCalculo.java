/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import ModeloDAO.CalificacionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author Administrador
 */
@WebServlet(name = "ControladorCalificarCalculo", urlPatterns = {"/ControladorCalificarCalculo"})
public class ControladorCalificarCalculo extends HttpServlet {
      private static final long serialVersionUID = 1L;
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
            out.println("<title>Servlet ControladorCalificarCalculo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorCalificarCalculo at " + request.getContextPath() + "</h1>");
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
    String action = request.getParameter("action");
    if ("datos".equals(action)) {
        String idUsuarioParam = request.getParameter("idUsuario");
        if (idUsuarioParam != null && idUsuarioParam.matches("\\d+")) {
            int idUsuario = Integer.parseInt(idUsuarioParam);

            try {
                CalificacionDAO calificacionDAO = new CalificacionDAO();
                int puntuacionTotal = calificacionDAO.getPuntuacionTotalByIdUsuarioCalificado(idUsuario);

                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                // Supongamos que puntuacionTotal es una variable que contiene la puntuación calculada.

out.println("<p>Puntuación Total: " + puntuacionTotal + "</p>");

// Verificar la puntuación y mostrar mensajes de alerta según las condiciones.
if (puntuacionTotal < 0) {
    // Puntuación negativa: mostrar alerta para este usuario u organización.
    out.println("<script>alert('Puntuación negativa. Se requiere atención para este usuario u organización.');</script>");
} else if (puntuacionTotal >= 1 && puntuacionTotal <= 10) {
    // Puntuación entre 1 y 10: buena organización o voluntario, alerta mínima.
    out.println("<script>alert('Buena puntuación. Se recomienda estar alerta.');</script>");
} else if (puntuacionTotal > 10 && puntuacionTotal <= 20) {
    // Puntuación entre 11 y 20: alerta moderada.
    out.println("<script>alert('Puntuación moderada. Se recomienda mantener alerta.');</script>");
} else {
    // Puntuación mayor a 20: alta confianza.
    out.println("<script>alert('Alta puntuación. Confíe en este usuario u organización.');</script>");
}

            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("<p>Error al procesar la acción 'datos': " + e.getMessage() + "</p>");
            }
        } else {
            response.getWriter().println("<p>Parámetro idUsuario no válido.</p>");
        }
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
