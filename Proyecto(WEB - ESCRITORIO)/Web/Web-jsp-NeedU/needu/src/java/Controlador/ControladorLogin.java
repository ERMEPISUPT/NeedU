/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ModeloDAO.*;
import Modelo.*;

/**
 *
 * @author ERICKPC
 */
@WebServlet(name = "ControladorLogin", urlPatterns = {"/ControladorLogin"})
public class ControladorLogin extends HttpServlet {
    UsuarioDAO usudao = new UsuarioDAO();
    Usuario usu = new Usuario();
    String index = "index.jsp";
    String login = "login.jsp";
    String inicio = "inicio.jsp";
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
            out.println("<title>Servlet ControladorLogin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorLogin at " + request.getContextPath() + "</h1>");
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
        System.out.println("valor de session" + session);
        if("loginusuario".equalsIgnoreCase(action)){
            acceso=login;
        }
        
        if (session != null) {
            if ("logout".equals(action)) {
                
                System.out.println("llega a logout de meth post");
                if (session != null) {
                    c.setIdcontrol((int) session.getAttribute("idcontrol"));
                    cdao.registrarSalida(c);
                    session.invalidate();
                    response.sendRedirect("index.jsp");
                }
            }
        }
        
        
        if (acceso != null) {
            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);
        }

        if ("redirigirInicio".equals(action)) {
            if (session != null && session.getAttribute("rol") != null) {
                String rol = (String) session.getAttribute("rol");
                if ("voluntario".equalsIgnoreCase(rol)) {
                    response.sendRedirect("inicio_Voluntario.jsp");
                } else if ("organizacion".equalsIgnoreCase(rol)) {
                    response.sendRedirect("inicio_Organizacion.jsp");
                } else {
                    response.sendRedirect("inicio.jsp"); 
                }
            } else {
                response.sendRedirect("login.jsp"); 
            }
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
        String directorioActual = System.getProperty("user.dir");
System.out.println("El directorio actual de trabajo es: " + directorioActual);
String rutaProyecto = request.getServletContext().getRealPath("/");
        System.out.println("Ruta del pryecto: " + rutaProyecto);
        String acceso = null;
        String action = request.getParameter("accion");
        if ("login".equals(action)) {
            String usuario = request.getParameter("txtusu");
            String clave = request.getParameter("txtcla");
            System.out.println("lelga a login post");
            int idusuario = usudao.autenticarUsuario(usuario,clave);
            System.out.println("el id usuario es: " + idusuario);
            if (idusuario>0) {
                System.out.println("credenciales  correctas");
                HttpSession session = request.getSession(true);
                Usuario usu = new Usuario();
                usu = usudao.listar(idusuario);
                if(usu.getEstado() == 1){
                    c.setIdusuario(idusuario);
                    c.setUsuario(usu.getUsuario());
                    int idcontrol = cdao.registrarInicio(c);
                    System.out.println("el id control es : "+ idcontrol);
                    session.setAttribute("idcontrol", idcontrol);
                    session.setAttribute("idusuario", usu.getIdusuario());
                    session.setAttribute("rol", usu.getRol());
                    session.setAttribute("usuario", usu.getUsuario());
                    session.setAttribute("clave", usu.getClave());
                    session.setAttribute("estado", usu.getEstado());
                    System.out.println("datos: " + usu.getIdusuario());
                    System.out.println("datos: " + usu.getRol());
                    System.out.println("usuario actual: "+ usu.getIdusuario());
                    if (usu.getRol().equalsIgnoreCase("admin")) {
                        response.sendRedirect("admin.jsp");
                    } else if (usu.getRol().equalsIgnoreCase("voluntario")) {
                        response.sendRedirect("inicio_Voluntario.jsp");
                    } else if (usu.getRol().equalsIgnoreCase("organizacion")) {
                        response.sendRedirect("inicio_Organizacion.jsp");
                    } else {
                        response.sendRedirect("inicio.jsp"); // para cualquier otro rol
                    }
                    return;
                }else{
                    acceso = login;
                }
                
            } else {
                acceso = login;
            }
        }
        
        if(acceso != null){
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
