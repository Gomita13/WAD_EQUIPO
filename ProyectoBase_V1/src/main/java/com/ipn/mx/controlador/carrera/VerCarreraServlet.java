/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ipn.mx.controlador.carrera;

import com.ipn.mx.modelo.dao.AlumnoDAO;
import com.ipn.mx.modelo.dao.CarreraDAO;
import com.ipn.mx.modelo.dto.AlumnoDTO;
import com.ipn.mx.modelo.dto.CarreraDTO;
import com.ipn.mx.modelo.entidades.Carrera;
import com.ipn.mx.utils.HTMLUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(name = "VerCarreraServlet", value = "/VerCarreraServlet")
public class VerCarreraServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println(HTMLUtils.HTML_HEAD);
            out.println("<body>");
            out.println(HTMLUtils.HTML_NAV);
            out.println("<div class='container'>");

            CarreraDTO dto = new CarreraDTO();
            CarreraDAO dao = new CarreraDAO();

            out.println("<h1>Datos de la carrera</h1>");

            try {

                dto.getEntidad().setIdCarrera(Long.parseLong(request.getParameter("id")));
                CarreraDTO res = dao.read(dto);

                out.println("<form action='ActualizarCarreraServlet' method='post'>");

                out.println("<div class='mb-3'><label for='nombreCarrera' class='form-label'> Nombre Carrera</label>");
                out.println("<input type='text' name='nombreCarrera' id='nombreCarrera' value='"+res.getEntidad().getNombreCarrera()+"' class='form-control' required maxlength='50'/></div>");

                out.println("<div class='mb-3'><label for='descripcionCarrera' class='form-label'> Descripcion Carrera</label>");
                out.println("<input type='text' name='descripcionCarrera' id='descripcionCarrera' value='"+res.getEntidad().getDescripcionCarrera()+"' class='form-control' required maxlength='50'/></div>");

                out.println("<input type='hidden' name='idCarrera' id='idCarrera' value='"+res.getEntidad().getIdCarrera()+"' class='form-control' required maxlength='50'/></div>");

                out.println("<input type='submit' name='cmdEnviar' value='Actualizar' class='btn btn-outline-primary'/>");
                out.println("</form>");

                out.println("<a href='index.html' class='btn btn-primary'>Cancelar</a>");

            } catch (SQLException ex) {
                ex.printStackTrace();
                out.println("<div><h1>ERROR</h1></div>");
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
