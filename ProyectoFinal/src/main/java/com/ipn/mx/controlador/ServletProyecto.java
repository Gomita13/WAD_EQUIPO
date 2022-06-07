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
import java.sql.Date;
import java.text.ParseException;
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
                    mostrarMisProyectos(request, response);
                    break;
                case "detalles":
                    detallesProyecto(request, response);
                    break;
                case "editar":
                    formEditarProyecto(request, response);
                    break;
                case "eliminar":
                    eliminarProyecto(request, response);
                    break;
                default:
                    System.out.println("Aqui algo valió madre");
                    mostrarMisProyectos(request, response);
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "nuevo":
                    try {
                        nuevoProyecto(request, response);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "editarP":
                    editarProyecto(request, response);
                    break;
                default:
                    System.out.println("Algo valió madre");
                    mostrarMisProyectos(request, response);
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    public static void mostrarMisProyectos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Persona persona = new Persona(email);
        List<Proyecto> misProyectos = new ProyectoDAO().selectAll(persona);
        request.setAttribute("misProyectos", misProyectos);
        request.getRequestDispatcher("projects.jsp").forward(request, response);
    }

    public static void detallesProyecto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String emailEncargado = (String) session.getAttribute("email");
        Persona encargado = new Persona(emailEncargado);
        String nombreProyecto = request.getParameter("nombreProyecto");
        Proyecto proyecto = new Proyecto(nombreProyecto);
        Proyecto proyectoRes = new ProyectoDAO().selectOne(proyecto);
        long diasRestantes = calcularDiasRestantes(proyectoRes);
        List<Persona> colaboradores = new ProyectoDAO().selectColaboradores(proyectoRes);
        List<Tarea> tareas = new TareaDAO().selectTareas(proyectoRes);
        List<Tarea> tareasCompletadas = new ArrayList<>();
        List<Tarea> tareasNoCompletadas = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.isCompletada()) {
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
        request.setAttribute("colaboradores", colaboradores);
        request.getRequestDispatcher("project_details.jsp").forward(request, response);
    }

    private void nuevoProyecto (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        HttpSession session = request.getSession();
        String nombreProyecto = request.getParameter("nombreProyecto");
        String inicioStr = request.getParameter("inicio");
        String finStr = request.getParameter("fin");
        String administrador = (String) session.getAttribute("email");
        Date inicio = parseDate(inicioStr);
        Date fin = parseDate(finStr);
        Proyecto proyecto = new Proyecto(nombreProyecto, inicio, fin, administrador);
        Persona persona = new Persona(administrador);
        int registrosModificados = new ProyectoDAO().insert(proyecto);
        int registros = new ProyectoDAO().insertColaborador(proyecto, persona);
        System.out.println("Resgistros modificados " + registrosModificados + " colaborador registrado " + registros);
        mostrarMisProyectos(request, response);
    }

    private void formEditarProyecto (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreProyecto = request.getParameter("nombreProyecto");
        Proyecto proyecto = new Proyecto(nombreProyecto);
        Proyecto proyectoRes = new ProyectoDAO().selectOne(proyecto);
        request.setAttribute("proyectoRes", proyectoRes);
        request.getRequestDispatcher("editar_proyecto.jsp").forward(request, response);
    }

    private void editarProyecto (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String proyectoOld = request.getParameter("proyecto");
        String nombreProyecto = request.getParameter("nombreProyecto");
        String inicioStr = request.getParameter("inicio");
        String finStr = request.getParameter("fin");
        Date inicio = parseDate(inicioStr);
        Date fin = parseDate(finStr);
        Proyecto proyecto = new Proyecto(nombreProyecto, inicio, fin);
        int registros = new ProyectoDAO().update(proyecto, proyectoOld);
        System.out.println(proyecto.getNombreProyecto());
        System.out.println(proyecto.getInicio());
        System.out.println(proyecto.getFin());
        System.out.println("Se modificó " + registros + " proyecto");
        mostrarMisProyectos(request, response);
    }

    private void eliminarProyecto (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreProyecto = request.getParameter("nombreProyecto");
        Proyecto proyecto = new Proyecto(nombreProyecto);
        int registros = new ProyectoDAO().delete(proyecto);
        System.out.println("Registros eliminados " + registros);
        mostrarMisProyectos(request, response);
    }

    private static long calcularDiasRestantes(Proyecto proyecto) {
        LocalDate fechaHoy = LocalDate.now();
        LocalDate fechaProyecto = proyecto.getFin().toLocalDate();
        return ChronoUnit.DAYS.between(fechaHoy, fechaProyecto);
    }

    private Date parseDate (String date) {
        LocalDate localDate = LocalDate.parse(date);
        return Date.valueOf(localDate);
    }
}