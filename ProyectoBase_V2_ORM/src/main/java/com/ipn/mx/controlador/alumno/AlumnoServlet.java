package com.ipn.mx.controlador.alumno;

import com.ipn.mx.modelo.dao.AlumnoDAO;
import com.ipn.mx.modelo.dto.AlumnoDTO;
import com.ipn.mx.utils.HTMLUtils;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AlumnoServlet", value = "/AlumnoServlet")

public class AlumnoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        try(PrintWriter out = response.getWriter()){
            int accion = Integer.parseInt(request.getParameter("accion"));
            switch (accion){
                case 1:
                    listarAlumno(out);
                    break;
                case 5:
                    mostrarAlumno(Long.parseLong(request.getParameter("idAlumno")),out);
                    break;
                case 6:
                    eliminarAlumno(Integer.parseInt(request.getParameter("idAlumno")),out);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        try(PrintWriter out = response.getWriter()){
            int accion = Integer.parseInt(request.getParameter("accion"));
            switch (accion){
                case 0:
                    crearAlumno(request.getParameter("nombreAlumno"),request.getParameter("paternoAlumno"), request.getParameter("maternoAlumno"), request.getParameter("emailAlumno"), request.getParameter("nombreCarrera"), out);
                    break;
                case 3:
                    int idAlumno = Integer.parseInt(request.getParameter("idAlumno"));
                    String nombreAlumno = request.getParameter("nombreAlumno");
                    String paternoAlumno = request.getParameter("paternoAlumno");
                    String maternoAlumno = request.getParameter("maternoAlumno");
                    String emailAlumno = request.getParameter("emailAlumno");
                    String nombreCarrera = request.getParameter("nombreCarrera");
                    actualizarAlumno(idAlumno,nombreAlumno,paternoAlumno, maternoAlumno, emailAlumno, nombreCarrera, out);
                    break;
            }
        }
    }

    private void crearAlumno(String nombreAlumno, String paternoAlumno, String maternoAlumno, String emailAlumno, String nombreCarrera, PrintWriter out) {

        out.println(HTMLUtils.HTML_HEAD);
        out.println("<body>");
        out.println(HTMLUtils.HTML_NAV);
        out.println("<div class='container'>");

        AlumnoDTO dto = new AlumnoDTO();
        dto.getEntidad().setNombreAlumno(nombreAlumno);
        dto.getEntidad().setPaternoAlumno(paternoAlumno);
        dto.getEntidad().setMaternoAlumno(maternoAlumno);
        dto.getEntidad().setEmailAlumno(emailAlumno);
        AlumnoDAO dao = new AlumnoDAO();

        try {
            int idCarrera = dao.selectCarrera(nombreCarrera);
            dto.getEntidad().setIdCarrera(idCarrera);
            dao.create(dto);
            out.println("<div class='alert alert-primary' role='alert'>");
            out.println("<h2> Registro insertado satisfactoriamente </h2>");
            out.println("</div>");

        } catch (Exception ex) {
            out.println("<h1>Ha ocurrido un error</h1>");
            out.println("<p>"+ex+"</p>");
        }
        out.println("<a href='index.html' class='btn btn-primary'>Regresar</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

    }

    private void eliminarAlumno(int idAlumno, PrintWriter out){

        out.println(HTMLUtils.HTML_HEAD);
        out.println("<body>");
        out.println(HTMLUtils.HTML_NAV);
        out.println("<div class='container'>");

        AlumnoDAO dao = new AlumnoDAO();
        AlumnoDTO dto = new AlumnoDTO();
        dto.getEntidad().setIdAlumno((long)idAlumno);

        try {
            dao.delete(dto);

            out.println("<div class='alert alert-primary' role='alert'>");
            out.println("<h2> Alumno eliminado satisfactoriamente </h2>");
            out.println("</div>");

        } catch (Exception ex) {
            out.println("<h1>Error al eliminar alumno</h1>");
        }

        out.println("<a href='index.html' class='btn btn-primary'>Regresar</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    private void actualizarAlumno(int idAlumno, String nombreAlumno, String paternoAlumno, String maternoAlumno, String emailAlumno, String nombreCarrera, PrintWriter out){
        out.println(HTMLUtils.HTML_HEAD);
        out.println("<body>");
        out.println(HTMLUtils.HTML_NAV);
        out.println("<div class='container'>");

        AlumnoDTO dto = new AlumnoDTO();
        dto.getEntidad().setNombreAlumno(nombreAlumno);
        dto.getEntidad().setPaternoAlumno(paternoAlumno);
        dto.getEntidad().setMaternoAlumno(maternoAlumno);
        dto.getEntidad().setEmailAlumno(emailAlumno);
        dto.getEntidad().setIdAlumno((long)idAlumno);
        AlumnoDAO dao = new AlumnoDAO();

        try{
            int idCarrera = dao.selectCarrera(nombreCarrera);
            dto.getEntidad().setIdCarrera(idCarrera);
            dao.update(dto);

            out.println("<div class='alert alert-primary' role='alert'>");
            out.println("<h2> Alumno actualizado satisfactoriamente </h2>");
            out.println("</div>");

        }catch(Exception e){
            out.println("<h1>Ha ocurrido un error</h1>");
            out.println("<p>"+e+"</p>");
        }

        out.println("<a href='index.html' class='btn btn-primary'>Regresar</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    private void mostrarAlumno(long idAlumno, PrintWriter out){

        out.println(HTMLUtils.HTML_HEAD);
        out.println("<body>");
        out.println(HTMLUtils.HTML_NAV);
        out.println("<div class='container'>");

        AlumnoDTO dto = new AlumnoDTO();
        AlumnoDAO dao = new AlumnoDAO();

        out.println("<h1>Datos del alumno</h1>");

        try {
            dto.getEntidad().setIdAlumno(idAlumno);
            AlumnoDTO res = dao.read(dto);
            String carrera=dao.selectCarrera(res.getEntidad().getIdCarrera());

            out.println("<form action='AlumnoServlet' method='post'>");
            out.println("<link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\"\n" + "rel=\"stylesheet\">\n");

            out.println("<input type='hidden' name='accion' value='3'>");
            out.println("<div class='mb-3'><label for='nombreAlumno' class='form-label'> Nombre alumno</label>");
            out.println("<input type='text' name='nombreAlumno' id='nombreAlumno' value='"+res.getEntidad().getNombreAlumno()+"' class='form-control' required maxlength='50'/></div>");

            out.println("<div class='mb-3'><label for='paternoAlumno' class='form-label'> Apellido paterno:</label>");
            out.println("<input type='text' name='paternoAlumno' id='paternoAlumno' value='"+res.getEntidad().getPaternoAlumno()+"' class='form-control' required maxlength='50'/></div>");

            out.println("<div class='mb-3'><label for='maternoAlumno' class='form-label'> Apellido materno:</label>");
            out.println("<input type='text' name='maternoAlumno' id='maternoAlumno' value='"+res.getEntidad().getMaternoAlumno()+"' class='form-control' required maxlength='50'/></div>");

            out.println("<div class='mb-3'><label for='emailAlumno' class='form-label'> Correo de alumno:</label>");
            out.println("<input type='text' name='emailAlumno' id='emailAlumno' value='"+res.getEntidad().getEmailAlumno()+"' class='form-control' required maxlength='50'/></div>");

            out.println("<div class='mb-3'><label for='nombreCarrera' class='form-label'> Carrera en la que esta:</label>");
            out.println("<input type='text' name='nombreCarrera' id='nombreCarrera' value='"+carrera+"' class='form-control' required maxlength='50'/></div>");

            out.println("<input type='hidden' name='idAlumno' id='idAlumno' value='"+res.getEntidad().getIdAlumno()+"' class='form-control' required maxlength='50'/></div>");

            out.println("<div class='text-center'>");
            out.println("<input type='submit' name='cmdEnviar' value='Actualizar' class='btn btn-outline-primary'/>");
            out.println("<a href='index.html' class='btn btn-primary'>Cancelar</a>");
            out.println("</div>");

            out.println("</form>");

        } catch (SQLException ex) {
            ex.printStackTrace();
            out.println("<div><h1>ERROR</h1></div>");
        }

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

    }

    private void listarAlumno(PrintWriter out){
        out.println(HTMLUtils.HTML_HEAD);
        out.println("<body>");
        out.println(HTMLUtils.HTML_NAV);
        out.println("<div class='container'>");
        out.println("<br>");
        out.println("<h2 style='text-align: center;'>Alumnos inscritos</h2>\n");
        out.println("<table class='table table-striped'>");
        out.println("<tr>");
        out.println("<th>Clave Alumno</th>");
        out.println("<th>Nombre del Alumno</th>");
        out.println("<th>Correo</th>");
        out.println("<th>Carrera</th>");
        out.println("<th>Eliminar</th>");
        out.println("<th>Actualizar</th>");
        out.println("</tr>");
        out.println("</div>");


        AlumnoDAO dao = new AlumnoDAO();
        AlumnoDTO dto = new AlumnoDTO();

        try {
            List lista = dao.readAll();
            out.println("<link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\"\n" +
                    "      rel=\"stylesheet\"");
            for (int i = 0; i < lista.size(); i++) {
                dto = (AlumnoDTO) lista.get(i);
                out.println("<tr>");
                out.println("<td><a href='AlumnoServlet?accion=5&idAlumno="+dto.getEntidad().getIdAlumno()+"' class='btn btn-outline-warning'>"
                        + dto.getEntidad().getIdAlumno()
                        +"</a></td>");
                out.println("<td>"+ dto.getEntidad().getPaternoAlumno() + " " + dto.getEntidad().getMaternoAlumno() + " " + dto.getEntidad().getNombreAlumno() + "</td>");
                out.println("<td>"+ dto.getEntidad().getEmailAlumno()+"</td>");
                out.println("<td>"+ dto.getEntidad().getIdCarrera()+"</td>");
                out.println("<td> <a href='AlumnoServlet?accion=6&idAlumno="+dto.getEntidad().getIdAlumno() +"' class='btn btn-outline-danger'><span class='material-icons'> delete_outline </span></a></td>");
                out.println("<td> <a href='AlumnoServlet?accion=5&idAlumno="+dto.getEntidad().getIdAlumno()+"' class='btn btn-outline-success'><span class='material-icons'> update </span></a></td>"+dto.getEntidad().getIdCarrera()+"</a></td>");
                out.println("</tr>");
            }
        }catch (Exception ex) {
            out.println("<h1>Ha ocurrido un error</h1>");
        }
        out.println("</table>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}

