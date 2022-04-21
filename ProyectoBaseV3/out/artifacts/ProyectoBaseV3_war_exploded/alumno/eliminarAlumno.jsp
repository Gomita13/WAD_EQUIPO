<%--
  Created by IntelliJ IDEA.
  User: sadaga
  Date: 24/03/2022
  Time: 07:16 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.ipn.mx.modelo.dao.AlumnoDAO" %>
<%@page import="java.sql.SQLException" %>
<%@page import="java.util.List" %>
<%@page import="com.ipn.mx.modelo.dto.AlumnoDTO" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Eliminar alumno</title>
</head>
<body>
<%
    /*
    * Esto es un copy-paste del servlet del proyecto 2 solo que sin los out.println()
    * */
    AlumnoDAO dao = new AlumnoDAO();
    AlumnoDTO dto = new AlumnoDTO();
    dto.getEntidad().setIdAlumno(Long.parseLong(request.getParameter("idAlumno")));

    try {
        dao.delete(dto);
        response.sendRedirect("listaDeAlumnos.jsp");
    } catch (Exception ex) {
        System.out.println("ERROR (eliminarAlumno.jsp): ");
        ex.printStackTrace();
        response.sendRedirect("listaDeAlumnos.jsp?error=" + ex);
    }
%>
</body>
</html>