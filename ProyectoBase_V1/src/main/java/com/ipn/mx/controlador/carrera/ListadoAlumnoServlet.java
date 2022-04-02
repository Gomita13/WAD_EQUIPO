/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ipn.mx.controlador.carrera;

import com.ipn.mx.modelo.dao.Alumno;
import com.ipn.mx.modelo.dto.AlumnoDTO;
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
@WebServlet(name = "ListadoAlumnoServlet", value = "/ListadoAlumnoServlet")
public class ListadoAlumnoServlet extends HttpServlet {

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
            out.println("<title>Servlet ListadoServletAlumno</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' ></script>");
            out.println("<link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\"\n" + "rel=\"stylesheet\">\n");
            out.println("<!-- Google fonts-->\n" +
                    "        <link href=\"https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                    "        <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                    "        <!-- Core theme CSS (includes Bootstrap)-->\n" +
                    "        <link href=\"./carrera/css/styles.css\" rel=\"stylesheet\" />");
            out.println("</head>");

            out.println("<body style='background-color:#c0a0c3; overflow-x: hidden;'>");
            out.println("<div>" +
                    "<nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\n" +
                    "                <div class=\"container px-4 px-lg-5\">\n" +
                    "                    <a class=\"navbar-brand\" href=\"index.html\">Proyecto Base </a>\n" +
                    "                    <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                    "                        <span class=\"navbar-toggler-icon\"></span>\n" +
                    "                    </button>\n" +
                    "                    <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
                    "                        <ul class=\"navbar-nav ms-auto py-4 py-lg-0\">\n" +
                    "                            <li class=\"nav-item\">\n" +
                    "                                <a class=\"nav-link px-lg-3 py-3 py-lg-4\" aria-current=\"page\" href=\"index.html\">Home</a>\n" +
                    "                            </li>\n" +
                    "                            <li class=\"nav-item\">\n" +
                    "                                <a class=\"nav-link px-lg-3 py-3 py-lg-4\" href=\"./carrera/nuevo.html\">Carrera</a>\n" +
                    "                            </li>\n" +
                    "                            <li class=\"nav-item\">\n" +
                    "                                <a class=\"nav-link px-lg-3 py-3 py-lg-4\" href=\"ListadoServlet\">Lista de Carreras</a>\n" +
                    "                            </li>\n" +
                    "                            <li class=\"nav-item\">\n" +
                    "                                <a class=\"nav-link px-lg-3 py-3 py-lg-4\" href=\"ListadoAlumnoServlet\">Lista de Alumnos</a>\n" +
                    "                            </li>\n" +
                    "                        </ul>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </nav>" +
                    "         </div>");
            out.println("<br><div class=\"site-heading\">\n" +
                    "        <h2 style='text-align: center;'>Alumnos registrados</h2>\n" +
                    "    </div><br>\n");
            out.println("<div class='container'>");
            out.println("<table class='table table-striped shadow-sm p-3 mb-5 bg-white rounded' style=\"background-color:#FFFFFF;  border-radius: 3em;\">");
            out.println("<tr>");
            out.println("<th>Clave Alumno</th>");
            out.println("<th>Nombre del Alumno</th>");
            out.println("<th>Correo</th>");
            out.println("<th>Carrera</th>");
            out.println("<th>Eliminar</th>");
            out.println("<th>Actualizar</th>");
            out.println("</tr>");
            out.println("</div>");

            Alumno dao = new Alumno();
            AlumnoDTO dto = new AlumnoDTO();
            
            try {
                List lista = dao.readAll();
                for (int i = 0; i < lista.size(); i++) {
                    dto = (AlumnoDTO) lista.get(i);
                    out.println("<tr>");
                    out.println("<td><a href='VerCarreraServlet' class='btn btn-outline-warning'>"
                            + dto.getEntidad().getIdCarrera()
                            +"</a></td>");
                    out.println("<td>"+ dto.getEntidad().getPaternoAlumno() + " " + dto.getEntidad().getMaternoAlumno() + " " + dto.getEntidad().getNombreAlumno() + "</td>");
                    out.println("<td>"+ dto.getEntidad().getEmailAlumno()+"</td>");
                    out.println("<td>"+ dto.getEntidad().getIdCarrera()+"</td>");
                    out.println("<td> <a href='EliminarAlumnoServlet?idAlumno="+ dto.getEntidad().getIdAlumno() +"' class='btn btn-outline-danger'><span class='material-icons'> delete_outline </span></a></td>");
                    out.println("<td> <a href='ActualizarAlumnoServlet' class='btn btn-outline-success'><span class='material-icons'> update </span></a></td>"
                            + dto.getEntidad().getIdCarrera()+
                            "</a></td>");
                    out.println("</tr>");
                }

            } catch (SQLException ex) {
                Logger.getLogger(ListadoAlumnoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
