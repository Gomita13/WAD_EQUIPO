package com.ipn.mx.carrerascrud;

import modelo.dao.CarreraDAO;
import modelo.dto.CarreraDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "BorrarServlet", value = "/BorrarServlet")
public class BorrarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt((String) request.getParameter("idCarrera"));
        CarreraDAO dao = new CarreraDAO();
        CarreraDTO dto = new CarreraDTO();
        dto.getEntidad().setIdCarrera(id);
        try{
            if(dao.delete(dto) > 0){
                response.sendRedirect("mensaje.jsp?msg=1&op=3");
            }else{
                response.sendRedirect("mensaje.jsp?msg=2&op=3");
            }
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("mensaje.jsp?msg=2&op=3");
        }
    }

}
