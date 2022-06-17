package com.example.controlador;

import java.io.*;

import com.example.modelos.dao.PersonaDAO;
import com.example.modelos.dto.PersonaDTO;
import com.example.modelos.entidades.Persona;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "personaServlet", value = "/persona-servlet")

public class PersonaServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        int accion = Integer.parseInt((String) request.getParameter("accion"));
        try{
            switch (accion){
                case 0: //cerrar sesion
                    request.getSession().invalidate();
                    response.sendRedirect("login.jsp");
                    break;
                case 1: //eliminar una persona
                    break;
            }
        }catch (Exception e){
            response.sendRedirect("error.jsp");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        int accion = Integer.parseInt((String) request.getParameter("accion"));
        try{
            switch (accion){
                case 0: //insertar una persona
                    String email = (String) request.getParameter("email");
                    String nombre = (String) request.getParameter("nombre");
                    String apellidos = (String) request.getParameter("apellidos");
                    String password = (String) request.getParameter("password");
                    registrarPersona(email,nombre,apellidos,password,response);
                    break;
                case 1: //loggear persona
                    email = (String) request.getParameter("email");
                    password = (String) request.getParameter("password");
                    login(email,password,request,response);
                    break;
                case 2:
                    break;
            }
        }catch (Exception e){
            response.sendRedirect("error.jsp");
        }
    }

    private void registrarPersona(String email, String nombre, String apellidos, String password, HttpServletResponse response) throws Exception {
        PersonaDTO dto = new PersonaDTO();
        dto.getEntidad().setEmail(email);
        dto.getEntidad().setNombre(nombre);
        dto.getEntidad().setApellidos(apellidos);
        dto.getEntidad().setPassword(password);
        PersonaDAO dao = new PersonaDAO();

        if(dao.insert(dto)){
            response.sendRedirect("login.jsp");
        }else{
            response.sendRedirect("error.jsp");
        }
    }

    public void login(String email, String password, HttpServletRequest request, HttpServletResponse response) throws Exception{
        PersonaDTO dto = new PersonaDTO();
        dto.getEntidad().setPassword(password);
        dto.getEntidad().setEmail(email);
        PersonaDAO dao = new PersonaDAO();
        PersonaDTO res = dao.select(dto);
        if(res.getEntidad().getPassword().equals(password)){
            HttpSession iniciada = request.getSession(true);
            iniciada.setAttribute("nombre",res.getEntidad().getNombre());
            iniciada.setAttribute("apellidos",res.getEntidad().getApellidos());
            iniciada.setAttribute("email",res.getEntidad().getEmail());
            response.sendRedirect("dashboard.jsp");
        }else{
            response.sendRedirect("login.jsp");
        }
    }
}