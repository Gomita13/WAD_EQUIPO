package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.PersonaDAO;
import com.ipn.mx.modelo.dao.ProyectoDAO;
import com.ipn.mx.modelo.dao.TareaDAO;
import com.ipn.mx.modelo.entidades.Persona;
import com.ipn.mx.modelo.entidades.Proyecto;
import com.ipn.mx.modelo.entidades.Tarea;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletPersona", value = "/ServletPersona")
public class ServletPersona extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "reporte":
                    mostrarReporte(request, response);
                    break;
                case "cuenta":
                    formCuenta(request, response);
                    break;
                case "logout":
                    request.getSession().invalidate();
                    response.sendRedirect("index.jsp");
                    break;
                default:
                    mostrarDashboard(request, response);
            }
        } else {
            mostrarDashboard(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("accion" != null) {
            switch (accion) {
                case "insertar":
                    this.insertarPersona(request, response);
                    break;
                case "login":
                    this.login(request, response);
                    break;
                case "cuenta":
                    actualizarCuenta(request, response);
                    break;
                default:
                    mostrarDashboard(request, response);
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private void insertarPersona(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String password = request.getParameter("password");
        Persona persona = new Persona(email, nombre, apellidos, password);
        // Insertamos en la base de datos
        int registrosModificados = new PersonaDAO().insert(persona);
        System.out.println("Registros modificados " + registrosModificados);
        response.sendRedirect("index.jsp");
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Persona persona = new Persona(email, password);
        PersonaDAO personaDAO = new PersonaDAO();
        if(personaDAO.login(persona)) {
            Persona personaRes = personaDAO.selectOne(persona);
            HttpSession session = request.getSession(true);
            session.setAttribute("nombre", personaRes.getNombre());
            session.setAttribute("apellidos", personaRes.getApellidos());
            session.setAttribute("email", personaRes.getEmail());
            System.out.println("Felicidades " + personaRes.getNombre() + " iniciaste sesión kjaja");
            this.mostrarDashboard(request, response);
        } else {
            System.out.println("mamaste");
            response.sendRedirect("index.jsp");
        }
    }

    public static void mostrarDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String nombre = (String) session.getAttribute("nombre");
        String apellidos = (String) session.getAttribute("apellidos");
        Persona persona = new Persona(email, nombre, apellidos);
        List<Tarea> tareas = new TareaDAO().selectTareasEncargado(persona);
        List<Proyecto> proyectos = new ProyectoDAO().selectAll(persona);
        List<Proyecto> proximosProyectos = calcularProyectos(proyectos);
        request.setAttribute("tareas", tareas);
        request.setAttribute("proximosProyectos", proximosProyectos);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }

    private static void mostrarReporte (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Persona persona = new PersonaDAO().selectOne(new Persona(email));
        List<Tarea> tareas = new TareaDAO().selectTareasEncargado(persona);
        List<Proyecto> proyectos = new ProyectoDAO().selectAll(persona);
        List<Proyecto> proyectosActuales = proyectosActuales(proyectos);
        request.setAttribute("persona", persona);
        request.setAttribute("tareas", tareas);
        request.setAttribute("proyectos", proyectosActuales);
        request.getRequestDispatcher("reporte.jsp").forward(request, response);
    }

    private static void formCuenta (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Persona persona = new PersonaDAO().selectOne(new Persona(email));
        request.setAttribute("persona", persona);
        request.getRequestDispatcher("account.jsp").forward(request, response);
    }

    private static void actualizarCuenta (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emailOld = request.getParameter("emailPersona");
        String email = request.getParameter("email");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String password = request.getParameter("password");
        int registros = new PersonaDAO().update(new Persona(email, nombre, apellidos, password), emailOld);
        System.out.println("Registros modificados " + registros);
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("nombre", nombre);
        session.setAttribute("apellidos", apellidos);
        formCuenta(request, response);
    }

    private static List<Proyecto> calcularProyectos(List<Proyecto> proyectos) {
        List<Proyecto> proximosProyectos = new ArrayList<>();
        LocalDate fechaHoy = LocalDate.now();
        LocalDate fechaProyecto;
        for (Proyecto proyecto: proyectos) {
            fechaProyecto = proyecto.getFin().toLocalDate();
            long diasRestantes = ChronoUnit.DAYS.between(fechaHoy, fechaProyecto);
            if(diasRestantes > 0 && diasRestantes < 14) {
                proximosProyectos.add(proyecto);
            }
        }
        return proximosProyectos;
    }

    public static List<Proyecto> proyectosActuales(List<Proyecto> proyectos) {
        List<Proyecto> proyectosActuales = new ArrayList<>();
        LocalDate fechaHoy = LocalDate.now();
        LocalDate fechaInicio, fechaFin;
        for(Proyecto proyecto: proyectos) {
            fechaInicio = proyecto.getInicio().toLocalDate();
            fechaFin = proyecto.getFin().toLocalDate();
            if((ChronoUnit.DAYS.between(fechaHoy, fechaInicio) < 0) && (ChronoUnit.DAYS.between(fechaHoy, fechaFin) > 0)) {
                proyectosActuales.add(proyecto);
            }
        }
        return proyectosActuales;
    }
}