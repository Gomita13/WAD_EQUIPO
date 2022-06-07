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
        response.sendRedirect("index.jsp");
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
            --clr-dark-green: linear-gradient(to right, #4a9b7f 0%, #0a3431 100%);
            --clr-light-pink: linear-gradient(to right, #7c65a9 0%, #96d4ca 100%);
            --clr-light-lime: linear-gradient(to right, #8de9d5 0%, #32c4c0 100%);
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
<body>
<jsp:include page="nav.jsp"/>
<div class="container">
    <h2 class="page-title">Mis proyectos</h2>
    <c:forEach var="proyecto" items="${misProyectos}">
        <div class="card">
            <div class="card-header">
                <a href="ServletProyecto?accion=detalles&nombreProyecto=${proyecto.getNombreProyecto()}">
                        ${proyecto.getNombreProyecto()}
                </a>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col"><p class="date">Desde ${proyecto.getInicio()} hasta ${proyecto.getFin()}</p></div>
                    <div class="col">
                        <a href="ServletProyecto?accion=editar&nombreProyecto=${proyecto.getNombreProyecto()}">
                            Editar proyecto
                        </a>
                    </div>
                    <div class="col">
                        <a href="ServletProyecto?accion=eliminar&nombreProyecto=${proyecto.getNombreProyecto()}">
                            Eliminar proyecto
                        </a>
                    </div>
                    <div class="col">
                        <div class="ab-progress" data-progress data-value="70" data-fill="var(--clr-lightgreen)" data-height="35" data-title="HTML5"></div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
    <a href="nuevo_proyecto.jsp"> Nuevo proyecto </a>
    <script src="js/progressBar/jquery-3.6.0.min.js"></script>
    <script src="js/progressBar/jquery.easing.min.js"></script>
    <script src="js/progressBar/noframework.waypoints.min.js"></script>
    <script src="js/progressBar/progressBar.min.js"></script>
    <script src="js/progressBar/script.js"></script>
</div>
</body>
</html>