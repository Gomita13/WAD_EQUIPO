<%--
  Created by IntelliJ IDEA.
  User: WebOS
  Date: 07/05/2022
  Time: 17:40
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
<jsp:include page="head.jsp">
    <jsp:param name="optionalStyle" value="css/dashboard.css"/>
</jsp:include>
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
        <h2 style="font-family: 'Pacifico', cursive; text-align: center; color: #2c3034; size: 18px">Dashboard</h2>
        <br>
    </div>
</header>
<br>
<section>
        <div class="row">
            <h2 class="col-lg-6">Tareas generadas</h2>
            <h2 class="col-lg-6">Proyectos para las próximas 2 semanas</h2>
        </div>
    <div class="row">
            <div class="col-lg-6">
                        <br>
                        <c:forEach var="tarea" items="${tareas}">
                            <div class="card border-warning mb-3" style="max-width: 98%;">
                                <div class="card-header"><b>${tarea.getNombreProyecto()}</b></div>
                                    <div class="card-body">
                                        <c:choose>
                                            <c:when test="${tarea.isCompletada()}">
                                                <div class="d-flex align-items-center justify-content-between mb-3">
                                                    <p class="small mb-0">${tarea.getNombreTarea()} (completa)</p>
                                                    <p class="fw-bold mb-0"><span class="material-symbols-outlined" style="text-align: right; color: #198754">
                                                        task_alt
                                                        </span>
                                                    </p>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
                                                <div class="d-flex align-items-center justify-content-between mb-3">
                                                    <p class="small mb-0">${tarea.getNombreTarea()} (incompleta)</p>
                                                    <p class="fw-bold mb-0"><span class="material-symbols-outlined" style="text-align: right; color: #ffc107">
                                                        fmd_bad
                                                        </span>
                                                    </p>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                </div>
                            </div>
                        </c:forEach>
            </div>
            <div class="col-lg-6">
                        <br>
                        <c:forEach var="proyecto" items="${proximosProyectos}">
                            <div class="card border-danger mb-3" style="max-width: 98%; margin-right: 3%">
                                <div class="card-header"><b>${proyecto.getNombreProyecto()}</b></div>
                                <div class="card-body">
                                    <p>${proyecto.getInicio()} / ${proyecto.getFin()}</p>
                                </div>
                            </div>
                        </c:forEach>
            </div>
        </div>
</section>
<section>

</section>
</body>
</html>
