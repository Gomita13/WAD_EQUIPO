<%--
  Created by IntelliJ IDEA.
  User: sadaga
  Date: 04/06/2022
  Time: 07:31 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%
    String email = (String) session.getAttribute("email");
    if (email == null) {
        response.sendRedirect("index.jsp");
    }
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reporte</title>
    <script>
        window.onload = function () {
            var chart = new CanvasJS.Chart("chartContainer", {
                animationEnabled: true,
                title: {
                    text: "Desempeño en tus tareas"
                },
                axisY: {
                    includeZero: true
                },
                axisX: {

                },
                data: [{
                    type: "column",
                    yValueFormatString: "#,##0\"\"",
                    dataPoints: ${datosGrafica}
                }]
            });
            chart.render();

        }
    </script>
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
        <c:choose>
            <c:when test="${tarea.isCompletada()}">
                <div>${tarea.getNombreTarea()} (Completada)</div>
            </c:when>
            <c:otherwise>
                <div>${tarea.getNombreTarea()} (Incompleta)</div>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <a href="ServletPersona?accion=generarReporte&nombrePersona=${persona.getEmail()}" target="_blank">
        Generar reporte en PDF
    </a>
</section>
<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>