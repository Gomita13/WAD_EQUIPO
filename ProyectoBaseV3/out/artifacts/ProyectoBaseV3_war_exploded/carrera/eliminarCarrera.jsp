<%--
  Created by IntelliJ IDEA.
  User: sadaga
  Date: 24/03/2022
  Time: 07:16 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.ipn.mx.modelo.dao.CarreraDAO" %>
<%@page import="java.sql.SQLException" %>
<%@page import="java.util.List" %>
<%@page import="com.ipn.mx.modelo.dto.CarreraDTO" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<h1>Hello World!</h1>
<%
    CarreraDAO dao = new CarreraDAO();
    CarreraDTO dto = new CarreraDTO();
    dto.getEntidad().setIdCarrera(Long.parseLong(request.getParameter("id")));
    try {
        dto = dao.read(dto);
        out.println(dto);

    } catch (SQLException ex) {

    }
%>
</body>
</html>