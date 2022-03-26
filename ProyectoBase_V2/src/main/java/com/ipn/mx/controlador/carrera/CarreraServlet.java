package com.ipn.mx.controlador.carrera;

import com.ipn.mx.modelo.dao.CarreraDAO;
import com.ipn.mx.modelo.dto.CarreraDTO;
import com.ipn.mx.utils.HTMLUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "CarreraServlet", value = "/CarreraServlet")

public class CarreraServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        try(PrintWriter out = response.getWriter()){
            int accion = Integer.parseInt(request.getParameter("accion"));
            switch (accion){
                case 0:
                    crearCarrera(request.getParameter("nombreCarrera"),request.getParameter("descripcionCarrera"),out);
                    break;
                case 1:
                    listarCarreras(out);
                    break;
                case 2:
                    mostrarCarrera(Integer.parseInt(request.getParameter("idCarrera")),out);
                    break;
                case 3:
                    int id = Integer.parseInt(request.getParameter("idCarrera"));
                    String nombre = request.getParameter("nombreCarrera");
                    String descripcion = request.getParameter("descripcionCarrera");
                    actualizarCarrera(id,nombre,descripcion,out);
                    break;
                case 4:
                    eliminarCarrera(Integer.parseInt(request.getParameter("idCarrera")),out);
                    break;
            }
        }
    }

    private void crearCarrera(String nombre, String descripcion, PrintWriter out) {

        out.println(HTMLUtils.HTML_HEAD);
        out.println("<body>");
        out.println(HTMLUtils.HTML_NAV);
        out.println("<div class='container'>");

        CarreraDTO dto = new CarreraDTO();
        dto.getEntidad().setNombreCarrera(nombre);
        dto.getEntidad().setDescripcionCarrera(descripcion);
        CarreraDAO dao = new CarreraDAO();

        try {
            dao.create(dto);
            out.println("<div class='alert alert-primary' role='alert'>");
            out.println("<h2> Registro insertado satisfactoriamente </h2>");
            out.println("</div>");

        } catch (Exception ex) {
            out.println("<h1>Ha ocurrido un error</h1>");
        }


    }

    private void eliminarCarrera(int id, PrintWriter out){

        CarreraDAO dao = new CarreraDAO();
        CarreraDTO dto = new CarreraDTO();
        dto.getEntidad().setIdCarrera((long)id);

        try {
            dao.delete(dto);

            out.println("<div class='alert alert-primary' role='alert'>");
            out.println("<h2> Carrera eliminada satisfactoriamente </h2>");
            out.println("</div>");

        } catch (Exception ex) {
            out.println("<h1>Error al eliminar la carrera</h1>");
        }

        out.println("<a href='index.html' class='btn btn-primary'>Regresar</a>");
        out.println("</body>");
        out.println("</html>");
    }

    private void actualizarCarrera(int id, String nombre, String descripcion, PrintWriter out){
        out.println(HTMLUtils.HTML_HEAD);
        out.println("<body>");
        out.println(HTMLUtils.HTML_NAV);
        out.println("<div class='container'>");

        CarreraDTO dto = new CarreraDTO();
        dto.getEntidad().setNombreCarrera(nombre);
        dto.getEntidad().setDescripcionCarrera(descripcion);
        dto.getEntidad().setIdCarrera((long)id);

        CarreraDAO dao = new CarreraDAO();
        try{

            dao.update(dto);

            out.println("<div class='alert alert-primary' role='alert'>");
            out.println("<h2> Carrrera actualizada satisfactoriamente </h2>");
            out.println("</div>");

        }catch(Exception e){
            out.println("<h1>Ha ocurrido un error</h1>");
        }

        out.println("<a href='index.html' class='btn btn-primary'>Regresar</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    private void mostrarCarrera(int id, PrintWriter out){

        out.println(HTMLUtils.HTML_HEAD);
        out.println("<body>");
        out.println(HTMLUtils.HTML_NAV);
        out.println("<div class='container'>");

        CarreraDTO dto = new CarreraDTO();
        CarreraDAO dao = new CarreraDAO();

        out.println("<h1>Datos de la carrera</h1>");

        try {

            dto.getEntidad().setIdCarrera((long)id);
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

    private void listarCarreras(PrintWriter out){
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

        } catch (Exception ex) {
            out.println("<h1>Ha ocurrido un error</h1>");
        }
        out.println("</table>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
