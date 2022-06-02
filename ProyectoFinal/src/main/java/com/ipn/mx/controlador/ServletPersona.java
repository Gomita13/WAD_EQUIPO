package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.PersonaDAO;
import com.ipn.mx.modelo.entidades.Persona;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

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
}
