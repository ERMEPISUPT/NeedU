/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Modelo.Admin;
import Modelo.Calificacion;
import Modelo.Organizacion;
import Modelo.Ubicacion;
import Modelo.Usuario;
import Modelo.Voluntario;
import ModeloDAO.AdminDAO;
import ModeloDAO.CalificacionDAO;
import ModeloDAO.OrganizacionDAO;
import ModeloDAO.UbicacionDAO;
import ModeloDAO.UsuarioDAO;
import ModeloDAO.VoluntarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
 * @author fichu
 */
@WebServlet(name = "ControladorCalificar", urlPatterns = {"/ControladorCalificar"})
public class ControladorCalificar extends HttpServlet {
    Usuario usu = new Usuario();
    UsuarioDAO usudao = new UsuarioDAO();
    Voluntario vol = new Voluntario();
    VoluntarioDAO voldao = new VoluntarioDAO();
    Organizacion org = new Organizacion();
    OrganizacionDAO orgdao = new OrganizacionDAO();
    Ubicacion ubi = new Ubicacion();
    Admin adm = new Admin();
    AdminDAO admdao = new AdminDAO();
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
            out.println("<title>Servlet ControladorCalificar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Califiacion enviada correctamente " + ""+ "</h1>");
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
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession(false);
            String accion = request.getParameter("accion");
            int idusuario = (int) session.getAttribute("idusuario");

            if (accion != null && accion.equals("listarPerfiles")) {
                List<Usuario> listaUsuarios = usudao.obtenerTodosLosUsuarios();
                Usuario usu = usudao.listar(idusuario);

                    for (Usuario usuario : listaUsuarios) {
                    System.out.println("ID: " + usuario.getIdusuario());
                    System.out.println("Rol: " + usuario.getRol());
                    System.out.println("Usuario: " + usuario.getUsuario());
                    System.out.println("Clave: " + usuario.getClave());
                    System.out.println("Estado: " + usuario.getEstado());
                    
                    System.out.println("-------------------");
                    }
                request.setAttribute("usuarios", listaUsuarios);
                request.getRequestDispatcher("Usuario/PERFILUsuario/ListarPerfiles.jsp").forward(request, response);
            }  
            else {
                processRequest(request, response); 
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
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String accion = request.getParameter("accion");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            HttpSession session = request.getSession();
            Integer idUsuarioCalificador = (Integer) session.getAttribute("idusuario");
            System.out.println("Estado: " + idUsuarioCalificador);

            if (idUsuarioCalificador != null) {
                int idUsuarioCal = idUsuarioCalificador;

                if (accion != null) {
                    if (accion.startsWith("pulgarArriba_")) {
                        int idUsuarioCado = Integer.parseInt(accion.split("_")[1]);

                        Calificacion calificacion = new Calificacion();
                        calificacion.setIdusuariocalifacador(idUsuarioCal);
                        calificacion.setIdusuariocalificado(idUsuarioCado);
                        calificacion.setPuntuacion(5);
                        calificacion.setComentario("¡Buen trabajo!");
                        String fechaActual = formatter.format(new java.util.Date());
                        calificacion.setFecha(fechaActual);

                        CalificacionDAO caldao = new CalificacionDAO();
                        if (caldao.agregarCalificacion(calificacion)) {
                        } else {
                        }

                    } else if (accion.startsWith("pulgarAbajo_")) {
                        int idUsuarioCado = Integer.parseInt(accion.split("_")[1]);

                        Calificacion calificacion = new Calificacion();
                        calificacion.setIdusuariocalifacador(idUsuarioCal);
                        calificacion.setIdusuariocalificado(idUsuarioCado);
                        calificacion.setPuntuacion(-2);
                        calificacion.setComentario("¡Mejoremos Juntos!");
                        String fechaActual = formatter.format(new java.util.Date());
                        calificacion.setFecha(fechaActual);

                        CalificacionDAO caldao = new CalificacionDAO();
                        if (caldao.agregarCalificacion(calificacion)) {
                        } else {
                        }
                    } else if (accion.equals("calificacionManual_")) {
                        response.sendRedirect("Usuario/PERFILUsuario/CalificarPerfiles.jsp"); // Redirige a una página de éxito
                    } else if (accion.startsWith("Manual_")) {
                        int idCalificador = Integer.parseInt(request.getParameter("idCalificador"));
                        int idCalificado = Integer.parseInt(request.getParameter("idCalificado"));
                        int puntuacion = Integer.parseInt(request.getParameter("puntuacion"));
                        String comentario = request.getParameter("comentario");

                        Calificacion calificacion = new Calificacion();
                        calificacion.setIdusuariocalifacador(idCalificador);
                        calificacion.setIdusuariocalificado(idCalificado);
                        calificacion.setPuntuacion(puntuacion);
                        calificacion.setComentario(comentario);
                        String fechaActual = formatter.format(new java.util.Date());
                        calificacion.setFecha(fechaActual);

                        CalificacionDAO caldao = new CalificacionDAO();
                        if (caldao.agregarCalificacion(calificacion)) {
                            response.sendRedirect("ruta_de_exito.jsp"); 
                        } else {
                            response.sendRedirect("ruta_de_error.jsp"); 
                        }
                    } else {
                    }
                    processRequest(request, response); 
                } else {
                    
                }
            } else {
                
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
