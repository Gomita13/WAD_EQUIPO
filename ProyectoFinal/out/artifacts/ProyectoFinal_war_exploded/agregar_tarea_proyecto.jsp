<%--
  Created by IntelliJ IDEA.
  User: sadaga
  Date: 02/06/2022
  Time: 04:48 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Colaboradores</title>
</head>
<body>
<jsp:include page="nav.jsp"/>
<header>
    <h2>Agregar tarea</h2>
</header>
<form action="ServletTarea?accion=nuevaTarea" method="post">
    <label for="nombreTarea">Nombre de la tarea</label>
    <input type="text" name="nombreTarea" id="nombreTarea">
    <label for="nombreProyecto">Nombre del proyecto</label>
    <input type="text" name="nombreProyecto" id="nombreProyecto" value="${nombreProyecto}" readonly>
    <label for="encargado">Encargado</label>
    <select name="encargado" id="encargado">
        <c:forEach var="encargado" items="${personas}">
            <option value="${encargado.getEmail()}">${encargado.getNombre()} ${encargado.getApellidos()}</option>
        </c:forEach>
    </select>
    <label for="descripcion">Descripción</label>
    <textarea name="descripcion" id="descripcion" cols="30" rows="10"></textarea>
    <input type="submit" value="Agregar">
</form>
</body>
</html>
