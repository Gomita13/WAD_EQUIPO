package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.PersonaDAO;
import com.ipn.mx.modelo.dao.TareaDAO;
import com.ipn.mx.modelo.entidades.Persona;
import com.ipn.mx.modelo.entidades.Tarea;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
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
        request.setAttribute("tareas", tareas);
        for (Tarea tarea :
                tareas) {
            System.out.println("Estas son tus tareas");
            System.out.println(tarea.getNombreTarea());
            System.out.println(tarea.getDescripcion());
            System.out.println(tarea.getNombreProyecto());
            System.out.println(tarea.isCompletada());
        }
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
