<%--
  Created by IntelliJ IDEA.
  User: sadaga
  Date: 04/06/2022
  Time: 07:31 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reporte</title>
</head>
<body>
<jsp:include page="nav.jsp"/>
<h1>${persona.getNombre()} ${persona.getApellidos()}</h1>
<section>
    <h2>Proyectos actuales</h2>
    <c:forEach var="proyecto" items="${proyectos}">
        <div>${proyecto.getNombreProyecto()}</div>
    </c:forEach>
</section>
<section>
    <h2>Tareas pendientes</h2>
    <c:forEach var="tarea" items="${tareas}">
        <div>${tarea.getNombreTarea()}</div>
    </c:forEach>
</section>
</body>
</html>
