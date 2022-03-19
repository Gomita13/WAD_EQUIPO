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

@WebServlet(name = "ActualizarAlumno", value = "/ActualizarAlumno")

public class ActualizarAlumno extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println(HTMLUtils.HTML_HEAD);
            out.println("<body>");
            out.println(HTMLUtils.HTML_NAV);
            out.println("<div class='container'>");

            AlumnoDTO dto = new AlumnoDTO();
            dto.getEntidad().setNombreAlumno(request.getParameter("nombreAlumno"));
            dto.getEntidad().setPaternoAlumno(request.getParameter("paternoAlumno"));
            dto.getEntidad().setMaternoAlumno(request.getParameter("maternoAlumno"));
            dto.getEntidad().setEmailAlumno(request.getParameter("emailAlumno"));
            dto.getEntidad().setIdAlumno(Long.parseLong(request.getParameter("idAlumno")));

            AlumnoDAO dao = new AlumnoDAO();

            try {

                int idCarrera = dao.selectCarrera(request.getParameter("nombreCarrera"));
                dto.getEntidad().setIdCarrera(idCarrera);
                dao.update(dto);

                out.println("<div class='alert alert-primary' role='alert'>");
                out.println("<h2> Alumno actualizado satisfactoriamente </h2>");
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
