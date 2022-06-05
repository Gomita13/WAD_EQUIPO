<%--
  Created by IntelliJ IDEA.
  User: gamma
  Date: 10/05/2022
  Time: 00:00
  To change this template use File | Settings | File Templates.
--%>
<%
    String email = (String) session.getAttribute("email");
    if(email == null) {
        response.sendRedirect("index.jsp");
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="optionalStyle" value="none"/>
</jsp:include>
<body>
<jsp:include page="nav.jsp"/>
<h1>Hola <%=session.getAttribute("nombre")%></h1>
<h2>Datos de la cuenta</h2>
<form action="ServletPersona?accion=cuenta&emailPersona=${persona.getEmail()}" method="post">
    <label for="email">Email</label>
    <input type="email" name="email" id="email" value="${persona.getEmail()}">
    <label for="nombre">Nombre</label>
    <input type="text" name="nombre" id="nombre" value="${persona.getNombre()}">
    <label for="apellidos">Apellidos</label>
    <input type="text" name="apellidos" id="apellidos" value="${persona.getApellidos()}">
    <label for="password">Nueva contraseña</label>
    <input type="password" name="password" id="password">
    <input type="submit" value="Actualizar cuenta">
</form>
</body>
</html>
