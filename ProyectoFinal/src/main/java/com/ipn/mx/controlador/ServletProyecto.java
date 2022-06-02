package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.ProyectoDAO;
import com.ipn.mx.modelo.entidades.Persona;
import com.ipn.mx.modelo.entidades.Proyecto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
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
                    System.out.println("Muestra los detalles del proyecto");
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

    }
}