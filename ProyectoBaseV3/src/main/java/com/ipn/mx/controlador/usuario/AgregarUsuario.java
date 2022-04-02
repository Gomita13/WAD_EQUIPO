package com.ipn.mx.controlador.usuario;

import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.dto.UsuarioDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name = "AgregarUsuario", value = "/AgregarUsuario")
public class AgregarUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UsuarioDTO dto = new UsuarioDTO();
        UsuarioDAO dao = new UsuarioDAO();

        try {
            Date hoy = new Date();
            java.sql.Date sqlDate = new java.sql.Date(hoy.getTime());
            dto.getEntidad().setNombre(request.getParameter("nombre"));
            dto.getEntidad().setPaterno(request.getParameter("paterno"));
            dto.getEntidad().setMaterno(request.getParameter("materno"));
            dto.getEntidad().setEmail(request.getParameter("email"));
            dto.getEntidad().setNombreUsuario(request.getParameter("nombreUsuario"));
            dto.getEntidad().setClaveUsuario(request.getParameter("claveUsuario"));
            dto.getEntidad().setFechaDeCreacion(sqlDate);

            dao.insert(dto);
            response.sendRedirect("index.jsp");

        } catch (SQLException e) {
            response.sendRedirect("inicio.jsp?msg=1");
        }

    }
}
