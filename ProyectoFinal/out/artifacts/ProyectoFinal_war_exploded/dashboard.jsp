<%--
  Created by IntelliJ IDEA.
  User: WebOS
  Date: 07/05/2022
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="optionalStyle" value="css/dashboard.css"/>
</jsp:include>
<body>
<jsp:include page="nav.jsp"/>
<section>
    <h1>Tareas pendientes</h1>
    <c:forEach var="tarea" items="${tareas}">
        <div>
            <p>${tarea.getNombreProyecto()}</p>
            <p>${tarea.getNombreTarea()}</p>
        </div>
    </c:forEach>
</section>
<section>
    <h1>Proyectos para las próximas 2 semanas</h1>
    <c:forEach var="proyecto" items="${proximosProyectos}">
        <div>
            <p>${proyecto.getNombreProyecto()}</p>
            <p>${proyecto.getInicio()} / ${proyecto.getFin()}</p>
        </div>
    </c:forEach>
</section>
</body>
</html>
