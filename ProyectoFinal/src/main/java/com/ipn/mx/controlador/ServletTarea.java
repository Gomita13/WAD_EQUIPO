package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.PersonaDAO;
import com.ipn.mx.modelo.dao.ProyectoDAO;
import com.ipn.mx.modelo.entidades.Persona;
import com.ipn.mx.modelo.entidades.Proyecto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletTarea", value = "/ServletTarea")
public class ServletTarea extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "agregarTarea":
                    this.formAgregarTarea(request, response);
                    break;
                default:
                    System.out.println("Hola");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "nuevaTarea":
                    System.out.println("Agregar tarea");
                    break;
                default:
                    System.out.println("Hola");
            }
        }
    }

    private void formAgregarTarea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreProyecto = request.getParameter("nombreProyecto");
        if(nombreProyecto != null) {
            Proyecto proyecto = new Proyecto(nombreProyecto);
            Proyecto proyectoRes = new ProyectoDAO().selectOne(proyecto);
            String proyectoNombre = proyectoRes.getNombreProyecto();
            List<Persona> personas = new PersonaDAO().selectAll();
            request.setAttribute("nombreProyecto", proyectoNombre);
            request.setAttribute("personas", personas);
            request.getRequestDispatcher("agregar_tarea_proyecto.jsp").forward(request, response);
        } else {
            String proyectoNombre = "";
            List<Persona> personas = new PersonaDAO().selectAll();
            List<Proyecto> proyectos = new ProyectoDAO().selectAllProjects();
            request.setAttribute("proyectos", proyectos);
            request.setAttribute("personas", personas);
            request.getRequestDispatcher("agregar_tarea_vacia.jsp").forward(request, response);
        }
    }
}