/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Admin;
import Modelo.Organizacion;
import Modelo.Ubicacion;
import Modelo.Usuario;
import Modelo.Voluntario;
import ModeloDAO.AdminDAO;
import ModeloDAO.OrganizacionDAO;
import ModeloDAO.UbicacionDAO;
import ModeloDAO.UsuarioDAO;
import ModeloDAO.VoluntarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Palbert
 */
@WebServlet(name = "ControladorPerfil", urlPatterns = {"/ControladorPerfil"})
public class ControladorPerfil extends HttpServlet {
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
            out.println("<title>Servlet ControladorPerfil</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorPerfil at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            int idusuario = (int) session.getAttribute("idusuario");
            String rol = (String) session.getAttribute("rol");
            System.out.println("rol actual."+rol);
            System.out.println("id usuario."+idusuario);
            Usuario usu = usudao.listar(idusuario);
            String accion = request.getParameter("accion");


            if (idusuario > 0) {
                if (rol != null) {
                    if (rol.equals("voluntario")) {
                        Voluntario vol = voldao.listar(idusuario);

                        session.setAttribute("idvoluntario", vol.getIdvoluntario());
                        session.setAttribute("nombre", vol.getNombre());
                        session.setAttribute("apellido", vol.getApellido());
                        session.setAttribute("genero", vol.getGenero());
                        session.setAttribute("edad", vol.getEdad());
                        session.setAttribute("correo", vol.getCorreo());
                        session.setAttribute("fecharegistro", vol.getFecharegistro());
                        session.setAttribute("idusuario", vol.getIdusuario());
                        session.setAttribute("idhabilidad", vol.getIdhabilidad());
                        session.setAttribute("idinteres", vol.getIdinteres());
                        session.setAttribute("idubicacion", vol.getIdubicacion());
                        session.setAttribute("estado", usu.getEstado());
                        
                        
                        } else if (rol.equals("organizacion")) {
                            Organizacion org = orgdao.listar(idusuario);

                            session.setAttribute("idorganizacion", org.getIdorganizacion());
                            session.setAttribute("nombre", org.getNombre());
                            session.setAttribute("tipo", org.getTipo()); 
                            session.setAttribute("correo", org.getCorreo()); 
                            session.setAttribute("estado", usu.getEstado());

                        } else if (rol.equals("administrador")) {
                            Admin ad = admdao.listarPorUsuario(idusuario);

                            session.setAttribute("idadmin", ad.getIdadmin());
                            session.setAttribute("nombre", ad.getNombre());
                            session.setAttribute("idusuario", ad.getIdusuario());

                        }

                     
                    request.getRequestDispatcher("Usuario/PERFILUsuario/Perfil.jsp").forward(request, response);
                } else {
                }
            } else {
            }
        } else {
            System.out.println("La sesión no existe o no hay un ID de usuario válido.");
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
            String action = request.getParameter("accion");
            String acceso = null;
            HttpSession session = request.getSession(false);
            int idusuario = (int) session.getAttribute("idusuario");
            String rol = (String) session.getAttribute("rol");
            System.out.println("rol actual."+rol);
            System.out.println("id usuario."+idusuario);
            if (action != null) {
                if (action.equalsIgnoreCase("editarVoluntario")) {
                    
                     System.out.println("valor de session" + session);

                    if (session != null) {
                        String nombre = request.getParameter("nombre");
                        String apellido = request.getParameter("apellido");
                        String genero = request.getParameter("genero");
                        int edad = Integer.parseInt(request.getParameter("edad"));
                        String correo = request.getParameter("correo");
                        
                        int idVoluntario = (int) session.getAttribute("idvoluntario");
                            VoluntarioDAO voluntarioDAO = new VoluntarioDAO();
                            Voluntario voluntario = voluntarioDAO.listar(idusuario);
                            System.out.println("valor de voluntario es : " +voluntario);

                            if (voluntario != null) {
                                voluntario.setNombre(nombre);
                                voluntario.setApellido(apellido);
                                voluntario.setGenero(genero);
                                voluntario.setEdad(edad);
                                voluntario.setCorreo(correo);
                                voluntario.setIdusuario(idusuario);
                                System.out.println("dentro de editar vol NOMNRE " +nombre);
                                System.out.println("dentro de editar vol APELLIDO" +apellido);
                                System.out.println("dentro de editar vol GENERO " +genero);
                                System.out.println("dentro de editar vol EDAD" +edad);
                                System.out.println("dentro de editar vol CORREO" +correo);
                                System.out.println("dentro de editar vol idusauroi" +idusuario);
                            }
                         boolean actualizado = voluntarioDAO.editarVoluntario(voluntario);
                        if (actualizado) {
                            System.out.println("Actualizado con éxito.");
                            response.sendRedirect("ControladorPerfil?accion=perfil");
                        } else {
                            response.sendRedirect("error.jsp");
                        }
                    } else {
                        System.out.println("La sesión no existe.");
                    }
                    
                    
                    
                    
                } else if (action.equalsIgnoreCase("editarOrganizacion")) {
                    //HttpSession session = request.getSession(false);
                    //System.out.println("valor de session" + session);
                    if (session != null) {
                        
                        String nombre = request.getParameter("nombre");
                        String tipo = request.getParameter("tipo");
                        String correo = request.getParameter("correo");
                        int idOrganizacion = (int) session.getAttribute("idusuario");
                        System.out.println("valor de session" + session);

                        Usuario usu = new Usuario();

                        OrganizacionDAO organizacionDAO = new OrganizacionDAO();
                        Organizacion organizacion = organizacionDAO.listar(idusuario);
                         int idusuario2=0;
                         idusuario2=idusuario;
                        organizacion.setNombre(nombre);
                        organizacion.setTipo(tipo);
                        organizacion.setCorreo(correo);
                        organizacion.setIdorganizacion(idusuario);
                        System.out.println("dentro de editar org" +idusuario);

                        boolean actualizado = organizacionDAO.editarOrganizacion(organizacion);

                        if (actualizado) {
                            System.out.println("Perfil de organización actualizado con éxito.");
                            response.sendRedirect("ControladorPerfil?accion=perfil");
                        } else {
                            response.sendRedirect("error.jsp");
                        }
                    } else {
                        System.out.println("La sesión no existe.");
                    }  } 


                else if (action.equalsIgnoreCase("editarAdministrador")) {
                //HttpSession session = request.getSession(false);
                                if (session != null) {
                                    String nombre = request.getParameter("nombre");
                                    int idUsuario = (int) session.getAttribute("idusuario");

                                    AdminDAO adminDAO = new AdminDAO();

                                    Admin admin = adminDAO.obtenerAdminPorIDUsuario(idUsuario);

                                    if (admin != null) {
                                        admin.setNombre(nombre);
                                        boolean actualizado = adminDAO.editar(admin);

                                        if (actualizado) {
                                            System.out.println("Perfil del administrador actualizado con éxito.");
                                            response.sendRedirect("ControladorPerfil?accion=perfil");
                                        } else {
                                            response.sendRedirect("error.jsp");
                                        }
                                    } else {
                                        System.out.println("El administrador no fue encontrado.");
                                        response.sendRedirect("error.jsp");
                                    }
                                } else {
                                    System.out.println("La sesión no existe.");
                                    response.sendRedirect("error.jsp");
                                }
                            }
                            
                            
                    else if (action.equalsIgnoreCase("actualizarEstado")) {
                    int estado = Integer.parseInt(request.getParameter("estado"));

                    //HttpSession session = request.getSession(false);
                    if (session != null) {
                       // int idUsuario = (int) session.getAttribute("idusuario");

                        VoluntarioDAO voluntarioDAO = new VoluntarioDAO();
                        boolean actualizado = voluntarioDAO.actualizarEstado(idusuario, estado);

                        if (actualizado) {
                            System.out.println("Estado actualizado con éxito.");
                            response.sendRedirect("ControladorPerfil?accion=perfil");
                        } else {
                        }
                    } else {
                        System.out.println("La sesión no existe.");
                    }
                } else {
                    processRequest(request, response);
                }
                
            }
        }



    }

