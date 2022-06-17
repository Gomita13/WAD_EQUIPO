<%--
  Created by IntelliJ IDEA.
  User: gamma
  Date: 07/05/2022
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%
    String email = (String) session.getAttribute("email");
    if(email == null) {
        response.sendRedirect("login.jsp");
    }
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
    <script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap/bootstrap.bundle.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        :root {
            --clr-lightgreen: linear-gradient(to right, #84ffc9 0%, #aab2ff 100%);
            --clr-dark-green: linear-gradient(to right, #ca0867 0%, #0a3431 100%);
            --clr-light-pink: linear-gradient(to right, #7c65a9 0%, #ff8847 100%);
            --clr-light-lime: linear-gradient(to right, #ef32d9 0%, #ffd689 100%);
        }
    </style>
    <link rel="stylesheet" href="css/progressBar/progressBar.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="css/nav.css">
    <link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Pacifico&family=Roboto+Condensed:wght@300&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Pacifico&family=Roboto+Condensed:wght@300&family=Ruluko&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/proyectos.css">
</head>
<body style="background-color: #eee; overflow-x: hidden;">
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
            <h2 style="font-family: 'Pacifico', cursive; text-align: center; color: #2c3034; size: 18px">Mis proyectos</h2>
            <br>
            <br>
        </div>
    </header>
<div class="container">
    <c:forEach var="proyecto" items="${misProyectos}">
        <div class="card">
            <div class="card-header">
                <a style="color: #2c3034" href="ServletProyecto?accion=detalles&nombreProyecto=${proyecto.getNombreProyecto()}">
                        ${proyecto.getNombreProyecto()}
                </a>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col"><p class="date">Desde ${proyecto.getInicio()} hasta ${proyecto.getFin()}</p></div>
                    <c:choose>
                        <c:when test="${usuario.equals(proyecto.getAdministrador())}">
                            <div class="col">
                                <c:choose>
                                    <c:when test="${Double.isNaN(proyecto.getProgreso())}">
                                        <div class="ab-progress" data-progress data-value="0" data-fill="var(--clr-light-pink)" data-height="35"></div>
                                        <a class="btn btn-primary" style="border-color: #50b02a; background-color:#ffffff; color: #2c3034; width: 30%; margin-right: 15px; margin-top: 8px" href="ServletProyecto?accion=editar&nombreProyecto=${proyecto.getNombreProyecto()}">
                                            Editar proyecto
                                        </a>
                                        <a class="btn btn-primary" style="border-color: #b02a37; background-color:#ffffff; color: #2c3034; width: 30%; margin-top: 8px" href="ServletProyecto?accion=eliminar&nombreProyecto=${proyecto.getNombreProyecto()}">
                                            Eliminar proyecto
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="ab-progress" data-progress data-value="${proyecto.getProgreso()}" data-fill="var(--clr-light-pink)" data-height="35"></div>
                                        <a class="btn btn-primary" style="border-color: #50b02a; background-color:#ffffff; color: #2c3034; width: 30%; margin-right: 15px; margin-top: 8px" href="ServletProyecto?accion=editar&nombreProyecto=${proyecto.getNombreProyecto()}">
                                            Editar proyecto
                                        </a>
                                        <a class="btn btn-primary" style="border-color: #b02a37; background-color:#ffffff; color: #2c3034; width: 30%; margin-top: 8px" href="ServletProyecto?accion=eliminar&nombreProyecto=${proyecto.getNombreProyecto()}">
                                            Eliminar proyecto
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col">
                                <c:choose>
                                    <c:when test="${Double.isNaN(proyecto.getProgreso())}">
                                        <div class="ab-progress" data-progress data-value="0" data-fill="var(--clr-light-pink)" data-height="35"></div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="ab-progress" data-progress data-value="${proyecto.getProgreso()}" data-fill="var(--clr-light-pink)" data-height="35"></div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </c:forEach>
    <br>
    <a class="btn btn-primary" style="border-color: #a30095; background-color:#ca0867; color: #ffffff" href="nuevo_proyecto.jsp"> Nuevo proyecto </a>
    <script src="js/progressBar/jquery-3.6.0.min.js"></script>
    <script src="js/progressBar/jquery.easing.min.js"></script>
    <script src="js/progressBar/noframework.waypoints.min.js"></script>
    <script src="js/progressBar/progressBar.min.js"></script>
    <script src="js/progressBar/script.js"></script>
</div>
</body>
</html>