/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.*;
import ModeloDAO.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
@WebServlet(name = "ControladorSolicitud", urlPatterns = {"/ControladorSolicitud"})
public class ControladorSolicitud extends HttpServlet {
    String inicioV = "inicio_Organizacion.jsp";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("accion");
        String acceso = null;
        
        if (session != null) {
            int idusuario = (int) session.getAttribute("idusuario");
            String rol = (String) session.getAttribute("rol");
            String usuario = (String) session.getAttribute("usuario");
            String clave = (String) session.getAttribute("clave");
            int estado = (int) session.getAttribute("estado");
            
            if ("solicitar".equalsIgnoreCase(action)) {
                int idOportunidad = Integer.parseInt(request.getParameter("idoportunidad"));
                VoluntarioDAO voluntarioDao = new VoluntarioDAO();
                int idVoluntario = voluntarioDao.obtenerIdVoluntarioPorIdUsuario(idusuario);
                if (idVoluntario == -1) {
                    // No redirecciona, solo muestra un mensaje de error en consola
                    System.out.println("Error: No se encontró el id del voluntario.");
                    return;
                }
                SolicitudDAO solicitudDao = new SolicitudDAO();
                if (solicitudDao.existeSolicitud(idVoluntario, idOportunidad)) {
                    // No redirecciona, solo muestra un mensaje en consola
                    System.out.println("La solicitud ya existe.");
                    response.sendRedirect("inicio_Voluntario.jsp");
                    return;
                }
                String mensaje = "Interesado en la oportunidad";
                Solicitud solicitud = new Solicitud();
                solicitud.setIdoportunidad(idOportunidad);
                solicitud.setIdvoluntario(idVoluntario);
                solicitud.setMensaje(mensaje);
                solicitud.setEntregado(0);
                boolean exito = solicitudDao.crearSolicitud(solicitud);
                if (exito) {
                    NotificacionDAO notidao = new NotificacionDAO();
                    OrganizacionDAO orgdao = new OrganizacionDAO();
                    VoluntarioDAO volddao = new VoluntarioDAO();
                    int idorganizacion = orgdao.obtenerIdorganizacionPorOportunidad(idOportunidad);
                    int idusuarioORG = orgdao.idusuario(idorganizacion);
                    if(idusuarioORG>0){
                        String nombreVol = volddao.otbtenerNombreVoluntario(idVoluntario);
                        if(nombreVol!=null){
                            System.out.println("idorganizacion = "+ idorganizacion);
                            System.out.println("idusuarioOrg = "+ idusuarioORG);
                            if(notidao.add(idusuarioORG, nombreVol, 2)){
                            System.out.println("Se creó una nueva notificacion");
                            response.sendRedirect("inicio_Voluntario.jsp");
                            return;
                            }else System.out.println("FALLO AL CREAR NOTIFICACION SOLICITUD");
                        }else System.out.println("Nombre nulo");
                        
                    }else System.out.println("No existe la id organizacion");
                    
                } else {
                    System.out.println("Error al crear la solicitud.");
                }
                acceso = inicioV;
            }
        } else {
            System.out.println("La sesión no existe");
        }
        
        if (acceso != null) {
            RequestDispatcher vista = request.getRequestDispatcher(acceso);
            vista.forward(request, response);
        }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("accion");
        String acceso = null;
        
        if (session != null) {
            int idusuario = (int) session.getAttribute("idusuario");
            String rol = (String) session.getAttribute("rol");
            String usuario = (String) session.getAttribute("usuario");
            String clave = (String) session.getAttribute("clave");
            int estado = (int) session.getAttribute("estado");
            SolicitudDAO solicitudDAO = new SolicitudDAO();
            
            if("verDetalles".equalsIgnoreCase(action)){
                System.out.println("reconoce");
                
                
                int idOportunidad = Integer.parseInt(request.getParameter("idoportunidad"));
                session.setAttribute("idOportunidad", idOportunidad);
                System.out.println("aaa");
                ResultSet rs = solicitudDAO.verSolicitudes(idOportunidad);
                
                request.setAttribute("Solicitudes", rs);
                ParticipanteDAO partDAO = new ParticipanteDAO();
                rs = partDAO.listarParticipantes(idOportunidad);
                request.setAttribute("Participantes", rs);
                acceso = "solicitudes.jsp";
                System.out.println("Hasta aqui todo bien");
            }else if("aceptar".equalsIgnoreCase(action)){
                int idsolicitud = Integer.parseInt(request.getParameter("idsolicitud"));
                int idVoluntario = solicitudDAO.obtenerIdvoluntario(idsolicitud);
                int idOportunidad = solicitudDAO.obtenerIdOpor(idsolicitud);
                if(solicitudDAO.aceptarSolicitud(idOportunidad, idVoluntario)){
                    solicitudDAO.setearSolicitud(idsolicitud);
                    VoluntarioDAO voldao = new VoluntarioDAO();
                    int idUsuarioVoluntario = voldao.obtenerIDUsuarioporIdVol(idVoluntario);
                    NotificacionDAO notdao = new NotificacionDAO();
                    OportunidadDAO opodao = new OportunidadDAO();
                    String titulo = opodao.obtenerTituloOpo(idsolicitud);
                    System.out.println("idUsuarioVoluntario: " + idUsuarioVoluntario);
                    System.out.println("titulo: " + titulo);
                    if(notdao.add(idUsuarioVoluntario, titulo, 3)){
                        System.out.println("Se creo nueva notificacion de aprobacion");
                        solicitudDAO.setearSolicitud(idsolicitud);
                    }else System.out.println("No se creo una nueva notificacion, posiblemete ocurrio un error.");
                }
                int idoportunidad = (int) session.getAttribute("idOportunidad");
                response.sendRedirect(request.getContextPath() + "/ControladorSolicitud?accion=verDetalles&idoportunidad=" + idoportunidad);
            return;
            }else if("rechazar".equalsIgnoreCase(action)){
                int idsolicitud = Integer.parseInt(request.getParameter("idsolicitud"));
                solicitudDAO.setearSolicitud(idsolicitud);
                int idoportunidad = (int) session.getAttribute("idOportunidad");
                response.sendRedirect(request.getContextPath() + "/ControladorSolicitud?accion=verDetalles&idoportunidad=" + idoportunidad);
            return;
            }
        } else {
            System.out.println("La sesión no existe");
        }
        if (acceso != null) {
                    RequestDispatcher vista = request.getRequestDispatcher(acceso);
                    vista.forward(request, response);
                }

    }


}
