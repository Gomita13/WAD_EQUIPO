package com.ipn.mx.carrerascrud;

import modelo.dao.CarreraDAO;
import modelo.dto.CarreraDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ActualizarServlet", value = "/ActualizarServlet")
public class ActualizarCarrera extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt((String) request.getParameter("idCarrera"));
        String nombre = (String) request.getParameter("nombreCarrera");
        String descripcion = (String) request.getParameter("descripcionCarrera");
        CarreraDAO dao = new CarreraDAO();
        CarreraDTO dto = new CarreraDTO();
        dto.getEntidad().setIdCarrera(id);
        dto.getEntidad().setDescripcionCarrera(descripcion);
        dto.getEntidad().setNombreCarrera(nombre);
        try{
            if(dao.update(dto) > 0){
                response.sendRedirect("mensaje.jsp?msg=1&op=2");
            }else{
                response.sendRedirect("mensaje.jsp?msg=2&op=2");
            }
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("mensaje.jsp?msg=2&op=2");
        }
    }
}
