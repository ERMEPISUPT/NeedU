package Controlador;

import ModeloDAO.OportunidadDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorBusqueda", urlPatterns = {"/ControladorBusqueda"})
public class ControladorBusqueda extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String terminoBusqueda = request.getParameter("terminoBusqueda");

        OportunidadDAO dao = new OportunidadDAO();
        List<Map<String, Object>> resultados = dao.buscarPorTituloOOrganizacion(terminoBusqueda);

        Gson gson = new Gson();
        String json = gson.toJson(resultados);
        response.getWriter().write(json);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}