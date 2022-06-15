<%--
  Created by IntelliJ IDEA.
  User: sadaga
  Date: 04/06/2022
  Time: 05:06 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%
    String email = (String) session.getAttribute("email");
    if(email == null) {
        response.sendRedirect("index.jsp");
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar ${proyectoRes.getNombreProyecto()}</title>
</head>
<body>
<jsp:include page="nav.jsp"/>
<form action="ServletProyecto?accion=editarP&proyecto=${proyectoRes.getNombreProyecto()}" method="post">
    <label for="nombreProyecto"> Nombre del proyecto </label>
    <input type="text" name="nombreProyecto" id="nombreProyecto" value="${proyectoRes.getNombreProyecto()}">
    <label for="inicio">Fecha de inicio del proyecto</label>
    <input type="date" name="inicio" id="inicio">
    <label for="fin">Fecha de finalización del proyecto</label>
    <input type="date" name="fin" id="fin">
    <input type="submit" value="Editar proyecto">
</form>
</body>
</html>