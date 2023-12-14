/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.*;
import ModeloDAO.*;
import java.util.Date;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author ERICKPC
 */
@WebServlet(name = "ControladorRegistro", urlPatterns = {"/ControladorRegistro"})
public class ControladorRegistro extends HttpServlet {
    String registro="Registro/registro.jsp";
    String index = "Registro/index.jsp";
    Usuario usu= new Usuario();
    UsuarioDAO usudao = new UsuarioDAO();
    Voluntario vol = new Voluntario();
    VoluntarioDAO voldao = new VoluntarioDAO();
    Organizacion org = new Organizacion();
    OrganizacionDAO orgdao = new OrganizacionDAO();
    Ubicacion ubi = new Ubicacion();
    UbicacionDAO ubidao = new UbicacionDAO();
    
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
            out.println("<title>Servlet ControladorRegistro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorRegistro at " + request.getContextPath() + "</h1>");
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
        
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("registro")){
            acceso=registro;
        }
        
        if(acceso != null){
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
        String acceso="";
        String action = request.getParameter("accion");
        if(action.equalsIgnoreCase("registrar")){
            System.out.println("entró a accion registrar");
            usu.setUsuario(request.getParameter("txtusu"));
            usu.setClave(request.getParameter("txtcla"));
            usu.setRol(request.getParameter("selectrol"));
            usu.setEstado(1);
            int idusuario = usudao.registrarUsuario(usu);
            System.out.println("el id usuario es: "+ idusuario);
            ubi.setPais(request.getParameter("txtpai"));
            ubi.setCiudad(request.getParameter("txtciu"));
            ubi.setDireccion(request.getParameter("txtdir"));
            int idubicacion = ubidao.registrarUbicacion(ubi);
            System.out.println("el idubicacion es: "+ idubicacion);
            if(usu.getRol().equalsIgnoreCase("Voluntario")){
                System.out.println("entró al if de si es voluntario");
                vol.setNombre(request.getParameter("txtnomvol"));
                vol.setApellido(request.getParameter("txtapevol"));
                vol.setGenero(request.getParameter("selectgenvol"));
                vol.setEdad(Integer.parseInt(request.getParameter("txtedavol")));
                vol.setCorreo(request.getParameter("txtcorvol"));
                vol.setIdusuario(idusuario);
                vol.setIdubicacion(idubicacion);
                voldao.registrarVoluntario(vol);
                
            }else if(usu.getRol().equalsIgnoreCase("Organizacion")){
                System.out.println("entró al if de si es Organizacion");
                org.setNombre(request.getParameter("txtnomorg"));
                org.setTipo(request.getParameter("txttiporg"));
                org.setCorreo(request.getParameter("txtcororg"));
                org.setIdusuario(idusuario);
                org.setIdubicacion(idubicacion);
                orgdao.registrarOrganizacion(org);
            }
            acceso = index;
            System.out.println("acceso es: "+acceso);
        }
        
        if(acceso != null){
            response.sendRedirect(request.getContextPath() + "");
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
