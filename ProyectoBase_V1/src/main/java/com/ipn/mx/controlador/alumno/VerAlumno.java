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

@WebServlet(name = "VerAlumno", value = "/VerAlumno")

public class VerAlumno extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println(HTMLUtils.HTML_HEAD);
            out.println("<body>");
            out.println(HTMLUtils.HTML_NAV);
            out.println("<div class='container'>");

            AlumnoDTO dto = new AlumnoDTO();
            AlumnoDAO dao = new AlumnoDAO();

            out.println("<h1>Datos del alumno</h1>");

            try {
                long idAlumno = Long.parseLong(request.getParameter("id"));
                dto.getEntidad().setIdAlumno(idAlumno);
                AlumnoDTO res = dao.select(dto);

                String carrera = dao.selectCarrera(res.getEntidad().getIdCarrera());

                out.println("<form action='ActualizarAlumno' method='post'>");

                out.println("<label for='nombreAlumno' class='form-label'>Nombre del alumno:</label>");
                out.println("<input name='nombreAlumno' id='nombreAlumno' value='"+res.getEntidad().getNombreAlumno()+"' maxlength='100' required class='form-control' />");

                out.println("<label for='paternoAlumno' class='form-label'>Apellido paterno:</label>");
                out.println("<input name='paternoAlumno' id='paternoAlumno' value='"+res.getEntidad().getPaternoAlumno()+"' maxlength='100' required class='form-control' />");

                out.println("<label for='maternoAlumno' class='form-label'>Apellido materno:</label>");
                out.println("<input name='maternoAlumno' id='maternoAlumno' value='"+res.getEntidad().getMaternoAlumno()+"' maxlength='100' required class='form-control' />");

                out.println("<label for='emailAlumno' class='form-label'>Email del alumno:</label>");
                out.println("<input name='emailAlumno' id='emailAlumno' value='"+res.getEntidad().getEmailAlumno()+"' maxlength='100' required class='form-control' />");

                out.println("<label for='nombreCarrera' class='form-label'>Nombre de la carrera:</label>");
                out.println("<input name='nombreCarrera' id='nombreCarrera' value="+carrera+" maxlength='100' required class='form-control' />");

                out.println("<input type='hidden' name='idAlumno' value='"+res.getEntidad().getIdAlumno()+"' readonly>");

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
