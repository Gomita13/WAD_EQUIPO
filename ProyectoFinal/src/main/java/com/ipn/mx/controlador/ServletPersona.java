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

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletPersona", value = "/ServletPersona")
public class ServletPersona extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
            }
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

    private void mostrarDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String nombre = (String) session.getAttribute("nombre");
        String apellidos = (String) session.getAttribute("apellidos");
        Persona persona = new Persona(email, nombre, apellidos);
        List<Tarea> tareas = new TareaDAO().selectTareasEncargado(persona);
        List<Proyecto> proyectos = new ProyectoDAO().selectAll(persona);
        List<Proyecto> proximosProyectos = this.calcularProyectos(proyectos);
        request.setAttribute("tareas", tareas);
        request.setAttribute("proximosProyectos", proximosProyectos);
        System.out.println("Estos son tus proximos proyectos");
        for(Proyecto proyectoProx: proximosProyectos) {
            System.out.println(proyectoProx.getNombreProyecto());
            System.out.println(proyectoProx.getFin());
        }
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }

    private List<Proyecto> calcularProyectos(List<Proyecto> proyectos) {
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
}