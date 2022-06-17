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
        response.sendRedirect("login.jsp");
    }
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="optionalStyle" value="css/reporte.css"/>
</jsp:include>
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
<style>
    .encabezado {
        background: url('https://i.pinimg.com/originals/d5/15/50/d51550aec996b3107df3d6c46cc8eabd.jpg');
        text-align: center;
        color: #2c3034;
        width: 100%;
        height: auto;
        background-size: cover;
        background-attachment: fixed;
        position: relative;
        overflow: hidden;
        border-radius: 0 0 85% 85% / 30%;
    }
</style>
<header class="encabezado">
    <div class="overlay">
        <br>
        <br>
        <h2 style="font-family: 'Pacifico', cursive; text-align: center; color: #2c3034; size: 18px">Reporte general</h2>
        <br>
    </div>
</header>
<div class="row" style="margin-left: 10%; margin-right: 10%; text-align: center">
    <div class="col-lg-4" style="align-content: center">
        <h2>${persona.getNombre()} ${persona.getApellidos()}</h2>
        <img src="./assets/user.png" alt="avatar"
             class="rounded-circle img-fluid" style="width: 100px;">
        <div style="margin-top: 10px">
            <a class="btn btn-primary" style="border-color: #ca0867; background-color: #FFFFFF; color: #2c3034; border-radius: 15%" href="ServletPersona?accion=generarReporte&nombrePersona=${persona.getEmail()}" target="_blank">
                Generar reporte en PDF
            </a>
        </div>
    </div>
    <div class="col-lg-4">
        <h2>Proyectos actuales</h2>
        <table class="table">
            <tbody>
            <c:forEach var="proyecto" items="${proyectos}">
                <tr>
                    <div>${proyecto.getNombreProyecto()}</div>
                </tr>
                <hr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-lg-4">
        <h2>Tareas pendientes</h2>
        <table class="table">
            <tbody>
                <c:forEach var="tarea" items="${tareas}">
                <tr>
                    <c:choose>
                        <c:when test="${tarea.isCompletada()}">
                            <p  style="text-align: left" class="small mb-0">${tarea.getNombreTarea()} (completa)</p>
                            <p class="fw-bold mb-0"><span class="material-symbols-outlined" style="text-align: right; color: #198754">
                                                        task_alt
                                                        </span>
                            </p>
                        </c:when>
                        <c:otherwise>
                            <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
                            <p style="text-align: left" class="small mb-0">${tarea.getNombreTarea()} (incompleta)</p>
                            <p class="fw-bold mb-0"><span class="material-symbols-outlined" style="text-align: right; color: #ffc107">
                                                        fmd_bad
                                                        </span>
                            </p>
                        </c:otherwise>
                    </c:choose>
                </tr>
                <hr>
                </c:forEach>
    </div>
</div>
<div class="row" style="margin-left: 10%; margin-right: 10%; text-align: center">
    <div class="col-lg-12">
        <div id="chartContainer"></div>
    </div>
</div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>