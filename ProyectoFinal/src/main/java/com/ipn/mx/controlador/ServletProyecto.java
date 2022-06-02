package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.ProyectoDAO;
import com.ipn.mx.modelo.dao.TareaDAO;
import com.ipn.mx.modelo.entidades.Persona;
import com.ipn.mx.modelo.entidades.Proyecto;
import com.ipn.mx.modelo.entidades.Tarea;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletProyecto", value = "/ServletProyecto")
public class ServletProyecto extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "proyectos":
                    this.mostrarMisProyectos(request, response);
                    break;
                case "detalles":
                    this.detallesProyecto(request, response);
                    break;
                default:
                    System.out.println("Aqui algo valió madre");
                    this.mostrarMisProyectos(request, response);
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {

        } else  {
            response.sendRedirect("error.jsp");
        }
    }

    private void mostrarMisProyectos (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Persona persona = new Persona(email);
        List<Proyecto> misProyectos = new ProyectoDAO().selectAll(persona);
        request.setAttribute("misProyectos", misProyectos);
        System.out.println("Estos son tus proyectos we");
        for(Proyecto proyecto: misProyectos) {
            System.out.println(proyecto.getNombreProyecto());
            System.out.println(proyecto.getInicio());
            System.out.println(proyecto.getFin());
        }
        request.getRequestDispatcher("projects.jsp").forward(request, response);
    }

    private void detallesProyecto (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String emailEncargado = (String) session.getAttribute("email");
        Persona encargado = new Persona(emailEncargado);
        String nombreProyecto = request.getParameter("nombreProyecto");
        Proyecto proyecto = new Proyecto(nombreProyecto);
        Proyecto proyectoRes = new ProyectoDAO().selectOne(proyecto);
        long diasRestantes = this.calcularDiasRestantes(proyectoRes);
        List<Tarea> tareas = new TareaDAO().selectTareas(proyectoRes);
        List<Tarea> tareasCompletadas = new ArrayList<>();
        List<Tarea> tareasNoCompletadas = new ArrayList<>();
        for(Tarea tarea: tareas) {
            if(tarea.isCompletada()) {
                tareasCompletadas.add(tarea);
            } else {
                tareasNoCompletadas.add(tarea);
            }
        }
        List<Tarea> misTareas = new TareaDAO().selectTareasEncargadoProyecto(encargado, proyectoRes);
        request.setAttribute("proyectoRes", proyectoRes);
        request.setAttribute("diasRestantes", diasRestantes);
        request.setAttribute("tareasCompletadas", tareasCompletadas);
        request.setAttribute("tareasNoCompletadas", tareasNoCompletadas);
        request.setAttribute("misTareas", misTareas);
        request.getRequestDispatcher("project_details.jsp").forward(request, response);
    }

    private long calcularDiasRestantes(Proyecto proyecto) {
        LocalDate fechaHoy = LocalDate.now();
        LocalDate fechaProyecto = proyecto.getFin().toLocalDate();
        return ChronoUnit.DAYS.between(fechaHoy, fechaProyecto);
    }
}