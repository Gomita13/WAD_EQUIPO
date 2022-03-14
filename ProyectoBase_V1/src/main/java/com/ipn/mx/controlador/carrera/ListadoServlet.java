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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darkdestiny
 */
@WebServlet(name = "ListadoServlet", value = "/ListadoServlet")
public class ListadoServlet extends HttpServlet {

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
            out.println("<title>Servlet ListadoServlet</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");

            out.println("</head>");
            out.println("<body>");
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
                    out.println("<td> <a href='ActualizarCarreraServlet' class='btn btn-outline-success'>"
                            + dto.getEntidad().getIdCarrera()+
                            "</a></td>");
                    out.println("</tr>");
                }
                
                
                //out.println(dao.readAll());
                //dao.create(dto);
                //dao.delete(dto);
            } catch (SQLException ex) {
                Logger.getLogger(ListadoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</table>");
            out.println("</div>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
