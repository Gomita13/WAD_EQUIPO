<%--
  Created by IntelliJ IDEA.
  User: sadaga
  Date: 02/06/2022
  Time: 03:43 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="optionalStyle" value="css/proyectos.css"/>
</jsp:include>
<body>
<jsp:include page="nav.jsp"/>
<header>
    <p>${proyectoRes.getNombreProyecto()}</p>
    <p>Fecha de inicio: ${proyectoRes.getInicio()}, Fecha de finalización: ${proyectoRes.getFin()}</p>
    <p>Días restantes: ${diasRestantes}</p>

    <section>
        <h2>Tareas pendientes</h2>
        <c:forEach var="tarea" items="${tareasNoCompletadas}">
            <p>${tarea.getNombreTarea()}</p>
            <p>${tarea.getDescripcion()}</p>
            <p>Asignada a: ${tarea.getEncargado()}</p>
        </c:forEach>
    </section>
    <section>
        <h2>Tareas completadas</h2>
        <c:forEach var="tarea" items="${tareasCompletadas}">
            <p>${tarea.getNombreTarea()}</p>
            <p>${tarea.getDescripcion()}</p>
            <p>Asignada a: ${tarea.getEncargado()}</p>
        </c:forEach>
    </section>
    <section>
        <h2>Mis tareas</h2>
        <c:forEach var="tarea" items="${misTareas}">
            <p>${tarea.getNombreTarea()}</p>
            <p>${tarea.getDescripcion()}</p>
        </c:forEach>
    </section>
</header>
</body>
</html>
