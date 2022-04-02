<%@ page import="com.ipn.mx.modelo.dto.UsuarioDTO" %>
<%@ page import="com.ipn.mx.modelo.dao.UsuarioDAO" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="jakarta.mail.internet.ParseException" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: gamma
  Date: 28/03/2022
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
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

        response.sendRedirect("../index.jsp");

    } catch (SQLException e) {
        out.println("<h1>Error al insertar usuario</h1>");
        e.printStackTrace();
    } catch (Exception se){
        se.printStackTrace();
    }

%>
</body>
</html>
