package Controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;


/**
 * Servlet implementation class NewPassword
 */
@WebServlet("/newPassword")
public class NewPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            String email = request.getParameter("email");
                String newPassword = request.getParameter("password");
                String confPassword = request.getParameter("confPassword");
                RequestDispatcher dispatcher = null;

                if (newPassword != null && confPassword != null && newPassword.equals(confPassword) && email != null) {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://161.132.47.59:3306/db_needu", "erick", "1234qwer");
                        


                        // Verificar si el correo existe en tborganizacion o tbvoluntario
                        String queryOrganizacion = "SELECT idusuario FROM tborganizacion WHERE correo = ?";
                        String queryVoluntario = "SELECT idusuario FROM tbvoluntario WHERE correo = ?";
                        PreparedStatement pst = con.prepareStatement(queryOrganizacion);
                        pst.setString(1, email);
                        

                        ResultSet rs = pst.executeQuery();
                        Integer userId = null;

                        if (rs.next()) {
                            userId = rs.getInt("idusuario");
                        } else {
                            pst = con.prepareStatement(queryVoluntario);
                            pst.setString(1, email);
                            rs = pst.executeQuery();
                            if (rs.next()) {
                                userId = rs.getInt("idusuario");
                            }
                        }

                        // Si se encuentra el usuario, actualizar la contraseña
                        if (userId != null) {
                            pst = con.prepareStatement("UPDATE tbusuario SET clave = ? WHERE idusuario = ?");
                            pst.setString(1, newPassword);
                            pst.setInt(2, userId);

                            int rowCount = pst.executeUpdate();
                            if (rowCount > 0) {
                                request.setAttribute("status", "resetSuccess");
                                dispatcher = request.getRequestDispatcher("login.jsp");
                            } else {
                                request.setAttribute("status", "resetFailed");
                                dispatcher = request.getRequestDispatcher("index.jsp");
                            }
                        } else {
                            // Usuario no encontrado
                            request.setAttribute("status", "userNotFound");
                            dispatcher = request.getRequestDispatcher("resetPassword.jsp");
                        }

                        dispatcher.forward(request, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

	}

}
