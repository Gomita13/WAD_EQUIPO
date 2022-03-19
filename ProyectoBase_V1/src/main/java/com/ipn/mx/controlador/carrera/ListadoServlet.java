/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ipn.mx.controlador.carrera;

import com.ipn.mx.modelo.dao.CarreraDAO;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darkdestiny
 */
@WebServlet(name = "ListadoServlet", value = "/ListadoServlet")

public class ListadoServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(HTMLUtils.HTML_HEAD);
            out.println("<body>");
            out.println(HTMLUtils.HTML_NAV);
            out.println("<div class='container'>");
            out.println("<table class='table table-striped'>");
            out.println("<tr>");
            out.println("<th>Clave Carrera</th>");
            out.println("<th>Nombre Carrera</th>");
            out.println("<th>Descripción Carrera</th>");
            out.println("<th>Eliminar</th>");
            out.println("<th>Actualizar</th>");
            out.println("</tr>");


            CarreraDAO dao = new CarreraDAO();
            CarreraDTO dto = new CarreraDTO();

            try {
                List lista = dao.readAll();
                for (int i = 0; i < lista.size(); i++) {
                    dto = (CarreraDTO) lista.get(i);
                    out.println("<tr>");
                    out.println("<td><a href='VerCarreraServlet' class='btn btn-outline-warning'>"
                            + dto.getEntidad().getIdCarrera()
                            +"</a></td>");
                    out.println("<td>"+ dto.getEntidad().getNombreCarrera()+"</td>");
                    out.println("<td>"+ dto.getEntidad().getDescripcionCarrera()+"</td>");
                    out.println("<td> <a href='EliminarCarreraServlet?id="+dto.getEntidad().getIdCarrera() +"' class='btn btn-outline-danger'>Eliminar</a></td>");
                    out.println("<td> <a href='VerCarreraServlet?id="+dto.getEntidad().getIdCarrera()+"' class='btn btn-outline-success'>Editar</a></td>");
                    out.println("</tr>");
                }

            } catch (SQLException ex) {
                Logger.getLogger(ListadoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</table>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
