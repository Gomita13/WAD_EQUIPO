/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ipn.mx.controlador.carrera;

import com.ipn.mx.modelo.dao.CarreraDAO;
import com.ipn.mx.modelo.dto.CarreraDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "EliminarCarreraServlet", value = "/EliminarCarreraServlet")
public class EliminarCarreraServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            CarreraDAO dao = new CarreraDAO();
            CarreraDTO dto = new CarreraDTO();
            dto.getEntidad().setIdCarrera(Long.parseLong(request.getParameter("id")));

            try {
                dao.delete(dto);
                out.println("<div class='alert alert-primary' role='alert'>");
                out.println("<h2> Carrera eliminada satisfactoriamente </h2>");
                out.println("</div>");
            } catch (SQLException ex) {
                out.println("<h1>Error al eliminar la carrera</h1>");
            }

            out.println("<a href='index.html' class='btn btn-primary'>Regresar</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
