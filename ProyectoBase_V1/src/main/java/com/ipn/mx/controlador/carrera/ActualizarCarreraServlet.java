/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ipn.mx.controlador.carrera;

import com.ipn.mx.modelo.dao.AlumnoDAO;
import com.ipn.mx.modelo.dao.CarreraDAO;
import com.ipn.mx.modelo.dto.AlumnoDTO;
import com.ipn.mx.modelo.dto.CarreraDTO;
import com.ipn.mx.utils.HTMLUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


/**
 *
 * @author darkdestiny
 */
@WebServlet(name = "ActualizarCarreraServlet", value = "/ActualizarCarreraServlet")
public class ActualizarCarreraServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println(HTMLUtils.HTML_HEAD);
            out.println("<body>");
            out.println(HTMLUtils.HTML_NAV);
            out.println("<div class='container'>");

            CarreraDTO dto = new CarreraDTO();
            dto.getEntidad().setNombreCarrera(request.getParameter("nombreCarrera"));
            dto.getEntidad().setDescripcionCarrera(request.getParameter("descripcionCarrera"));
            dto.getEntidad().setIdCarrera(Long.parseLong(request.getParameter("idCarrera")));

            CarreraDAO dao = new CarreraDAO();

            try {

                dao.update(dto);

                out.println("<div class='alert alert-primary' role='alert'>");
                out.println("<h2> Carrrera actualizada satisfactoriamente </h2>");
                out.println("</div>");

            } catch (SQLException ex) {
                ex.printStackTrace();
                out.println("<div><h1>ERROR</h1></div>");
            }

            out.println("<a href='index.html' class='btn btn-primary'>Regresar</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
