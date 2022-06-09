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
                case "editar":
                    this.formEditarTarea(request, response);
                    break;
                case "completar":
                    this.completarTarea(request, response);
                    break;
                case "eliminar":
                    this.eliminarTarea(request, response);
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
                    this.nuevaTarea(request, response);
                    break;
                case "editar":
                    this.editarTarea(request, response);
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
            List<Persona> personas = new PersonaDAO().selectByProject(proyectoRes);
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

    private void formEditarTarea (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreTarea = request.getParameter("nombre");
        String nombreProyecto = request.getParameter("proyecto");
        Tarea tarea = new Tarea(nombreTarea, nombreProyecto);
        Tarea tareaRes = new TareaDAO().selectOne(tarea);
        List<Persona> personas = new PersonaDAO().selectByProject(new Proyecto(nombreProyecto));
        request.setAttribute("tareaRes", tareaRes);
        request.setAttribute("personas", personas);
        request.getRequestDispatcher("editarTarea.jsp").forward(request, response);
    }

    private void nuevaTarea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreTarea = request.getParameter("nombreTarea");
        String nombreProyecto = request.getParameter("nombreProyecto");
        String encargado = request.getParameter("encargado");
        String descripcion = request.getParameter("descripcion");
        boolean completada = false;
        Tarea tarea = new Tarea(nombreTarea, nombreProyecto, encargado, descripcion, completada);
        int registrosModificados = new TareaDAO().insert(tarea);
        System.out.println("Registros modificados " + registrosModificados);
        ServletProyecto.detallesProyecto(request, response);
    }

    private void editarTarea (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tareaOld = request.getParameter("tarea");
        String nombreTarea = request.getParameter("nombreTarea");
        String nombreProyecto = request.getParameter("nombreProyecto");
        String encargado = request.getParameter("encargado");
        String descripcion = request.getParameter("descripcion");
        boolean completada = false;
        Tarea tarea = new Tarea(nombreTarea, nombreProyecto, encargado, descripcion, completada);
        int registrosModificados = new TareaDAO().update(tarea, tareaOld);
        System.out.println("Registros actualizados " + registrosModificados);
        ServletProyecto.detallesProyecto(request, response);
    }

    private void completarTarea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreTarea = request.getParameter("nombre");
        String nombreProyecto = request.getParameter("proyecto");
        Tarea tarea = new TareaDAO().selectOne(new Tarea(nombreTarea, nombreProyecto));
        tarea.setCompletada(!tarea.isCompletada());
        int registros = new TareaDAO().completarTarea(tarea);
        System.out.println("Registros modificados " + registros);
        request.setAttribute("nombreProyecto", nombreProyecto);
        getServletContext().getRequestDispatcher("/ServletProyecto?accion=detalles").forward(request, response);
    }

    private void eliminarTarea (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreTarea = request.getParameter("nombre");
        String nombreProyecto = request.getParameter("proyecto");
        Tarea tarea = new Tarea(nombreTarea, nombreProyecto);
        int registrosModificados = new TareaDAO().delete(tarea);
        System.out.println("Registros modificados " + registrosModificados);
        request.setAttribute("nombreProyecto", nombreProyecto);
        getServletContext().getRequestDispatcher("/ServletProyecto?accion=detalles").forward(request, response);
    }
}