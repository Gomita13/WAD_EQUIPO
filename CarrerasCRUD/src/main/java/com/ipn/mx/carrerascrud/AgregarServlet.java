package com.ipn.mx.carrerascrud;

import modelo.dao.CarreraDAO;
import modelo.dto.CarreraDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AgregarServlet", value = "/AgregarServlet")
public class AgregarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = (String) request.getParameter("nombreCarrera");
        String descripcion = (String) request.getParameter("descripcionCarrera");
        System.out.println(nombre);
        System.out.println(descripcion);
        try{
            CarreraDAO dao = new CarreraDAO();
            CarreraDTO dto = new CarreraDTO();
            dto.getEntidad().setNombreCarrera(nombre);
            dto.getEntidad().setDescripcionCarrera(descripcion);
            if(dao.create(dto) > 0){
                response.sendRedirect("mensaje.jsp?msg=1&op=1");
            }else{
                response.sendRedirect("mensaje.jsp?msg=2&op=1");
            }
        }catch(Exception e){
            e.printStackTrace();
            response.sendRedirect("mensaje.jsp?msg=2&op=1");
        }
    }
}
