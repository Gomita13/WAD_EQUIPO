<%--
  Created by IntelliJ IDEA.
  User: sadaga
  Date: 02/06/2022
  Time: 03:43 p. m.
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
    <jsp:param name="optionalStyle" value="css/project_details.css"/>
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
        <h2 class="page-title">${proyectoRes.getNombreProyecto()}</h2>
        <br>
        <br>
    </div>
</header>
<br><br>
<section style="background-color: #ffffff; overflow-x: hidden;">
    <div class="container py-5">
        <p>Fecha de inicio: ${proyectoRes.getInicio()}, Fecha de finalización: ${proyectoRes.getFin()}</p>
        <c:choose>
            <c:when test="${diasRestantes == 0}">
                <p>Hoy es el último día para entregar el proyecto</p>
            </c:when>
            <c:when test="${diasRestantes < 0}">
                <p>La fecha de entrega del proyecto ha expirado</p>
            </c:when>
            <c:otherwise>
                <p>Días restantes: ${diasRestantes}</p>
            </c:otherwise>
        </c:choose>
        <div class="row">
            <div class="col-lg-12">
                <div class="card mb-4">
                    <div class="card-body">
                        <br>
                        <h3 style="font-family: 'Pacifico', cursive; text-align: left; margin-left: 10%">Tareas pendientes</h3>
                        <br>
                        <hr>
                        <c:forEach var="tarea" items="${tareasNoCompletadas}">
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0" style="margin-left: 2%"><strong>${tarea.getNombreTarea()}</strong></p>
                                    <p class="small mb-0"><i class="fas fa-star fa-lg text-warning"></i> <span class="mx-2">|</span>
                                        Asignada a: ${tarea.getEncargado()}
                                    </p>
                                </div>
                                <div class="col-sm-3" style="text-align: center">
                                    <p class="text-muted mb-0">${tarea.getDescripcion()}</p>
                                </div>
                                <c:choose>
                                    <c:when test="${usuario.equals(proyectoRes.getAdministrador())}">
                                        <div class="col-sm-2">
                                            <a class="btn btn-primary" style="width: 80%; border-color: #50b02a; background-color:#ffffff; color: #2c3034;" href="ServletTarea?accion=completar&nombre=${tarea.getNombreTarea()}&proyecto=${tarea.getNombreProyecto()}">
                                                Completar
                                            </a>
                                        </div>
                                        <div class="col-sm-2" style="align-content: center; text-align: center">
                                            <a class="btn btn-primary" style="width: 80%; border-color: #ffa600; background-color:#ffffff; color: #2c3034;" href="ServletTarea?accion=editar&nombre=${tarea.getNombreTarea()}&proyecto=${tarea.getNombreProyecto()}">
                                                Editar
                                            </a>
                                        </div>
                                        <div class="col-sm-2">
                                            <a class="btn btn-primary" style="width: 80%; border-color: #de4646; background-color:#ffffff; color: #2c3034;" href="ServletTarea?accion=eliminar&nombre=${tarea.getNombreTarea()}&proyecto=${tarea.getNombreProyecto()}">
                                                Eliminar
                                            </a>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </div>
                            <hr>
                        </c:forEach>
                        <hr class="my-4">
                        <div class="d-flex justify-content-start align-items-center">
                            <p class="mb-0 text-uppercase"><i class="fas fa-cog me-2"></i> <span
                                    class="text-muted small">| Agregar una nueva tarea: </span></p>
                            <c:choose>
                                <c:when test="${usuario.equals(proyectoRes.getAdministrador())}">
                            <div class="col-sm-2" style="align-content: center; text-align: center">
                                <a class="btn btn-primary" style="width: 80%; border-color: #ca0867; background-color:#ffffff; color: #2c3034; border-radius: 10px" href="ServletTarea?accion=agregarTarea&nombreProyecto=${proyectoRes.getNombreProyecto()}">
                                        Agregar tarea
                                </a>
                            </div>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card mb-4 mb-md-0">
                            <div class="card-body" style="text-align: center">
                                <br>
                                <h3 style="font-family: 'Pacifico', cursive; text-align: left; margin-left: 10%">Mis tareas</h3>
                                <br>
                                <hr>
                                <c:choose>
                                    <c:when test="${misTareas.size() == 0}">
                                        <p>No tienes tareas asignadas para este proyecto aún</p>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="tarea" items="${misTareas}">
                                            <div class="row">
                                                <div class="col-sm-4">
                                                    <p class="mb-0"><p>${tarea.getNombreTarea()}</p>
                                                </div>
                                                <div class="col-sm-4">
                                                    <p class="text-muted mb-0">${tarea.getDescripcion()}</p>
                                                </div>
                                                <c:choose>
                                                    <c:when test="${tarea.isCompletada() == false}">
                                                        <div class="col-sm-4">
                                                            <a class="btn btn-primary" style="width: 80%; border-color: #50b02a; background-color:#ffffff; color: #2c3034;" href="ServletTarea?accion=completar&nombre=${tarea.getNombreTarea()}&proyecto=${tarea.getNombreProyecto()}">
                                                                Completar
                                                            </a>
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="col-sm-4">
                                                            <a class="btn btn-primary" style="width: 80%; border-color: #ffa600; background-color:#ffffff; color: #2c3034;" href="ServletTarea?accion=completar&nombre=${tarea.getNombreTarea()}&proyecto=${tarea.getNombreProyecto()}">
                                                                Pendiente
                                                            </a>
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            <hr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="card" style="margin-top: 30px">
                            <div class="card-body">
                                <br>
                                <h3 style="font-family: 'Pacifico', cursive; text-align: left; margin-left: 10%">Tareas completadas</h3>
                                <br>
                                <hr>
                                <c:forEach var="tarea" items="${tareasCompletadas}">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />                                            <p class="mb-0" style="margin-left: 2%"><strong>${tarea.getNombreTarea()}</strong></p>
                                            <p class="small mb-0"><span class="material-symbols-outlined">
                                                check_circle
                                                </span><span class="mx-2">|</span>
                                                Asignada a: ${tarea.getEncargado()}
                                            </p>
                                        </div>
                                        <div class="col-sm-2" style="text-align: center">
                                            <p class="text-muted mb-0">${tarea.getDescripcion()}</p>
                                        </div>
                                        <c:choose>
                                            <c:when test="${usuario.equals(proyectoRes.getAdministrador())}">
                                                <div class="col-sm-2">
                                                    <a class="btn btn-primary" style="width: 80%; border-color: #50b02a; background-color:#ffffff; color: #2c3034;" href="ServletTarea?accion=completar&nombre=${tarea.getNombreTarea()}&proyecto=${tarea.getNombreProyecto()}">
                                                       Pendiente
                                                    </a>
                                                </div>
                                                <div class="col-sm-2">
                                                    <a class="btn btn-primary" style="width: 80%; border-color: #ffa600; background-color:#ffffff; color: #2c3034;" href="ServletTarea?accion=editar&nombre=${tarea.getNombreTarea()}&proyecto=${tarea.getNombreProyecto()}">
                                                        Editar
                                                    </a>
                                                </div>
                                                <div class="col-sm-2">
                                                    <a class="btn btn-primary" style="width: 80%; border-color: #de4646; background-color:#ffffff; color: #2c3034;" href="ServletTarea?accion=eliminar&nombre=${tarea.getNombreTarea()}&proyecto=${tarea.getNombreProyecto()}">
                                                        Eliminar
                                                    </a>
                                                </div>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <hr>
                                </c:forEach>
                            </div>
                    </div>
                </div>
                <br>
                <div class="card mb-4">
                    <div class="card-body">
                        <br>
                        <h3 style="font-family: 'Pacifico', cursive; text-align: left; margin-left: 10%">Integrantes</h3>
                        <br>
                        <hr>
                        <table class="table align-middle mb-0 bg-white">
                            <thead class="bg-light">
                            <tr>
                                <th>Nombre de usuario</th>
                                <th>Rol</th>
                                <th>Eliminar</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="colaborador" items="${colaboradores}">
                            <tr>
                                <td>
                                    <div class="d-flex align-items-center">
                                        <img
                                                src="./assets/user1.png"
                                                alt=""
                                                style="width: 45px; height: 45px"
                                                class="rounded-circle"
                                        />
                                        <div class="ms-3">
                                            <p class="fw-bold mb-1">${colaborador.getNombre()}</p>
                                            <p class="text-muted mb-0">${colaborador.getEmail()}</p>
                                        </div>
                                    </div>
                                </td>
                                <c:choose>
                                    <c:when test="${colaborador.getEmail().equals(proyectoRes.getAdministrador())}">
                                        <td>Creador</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Colaborador</td>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${usuario.equals(proyectoRes.getAdministrador())}">
                                        <td>
                                            <a type="button" class="btn btn-link btn-sm btn-rounded" href="ServletProyecto?accion=eliminarColaborador&nombreProyecto=${proyectoRes.getNombreProyecto()}&emailColaborador=${colaborador.getEmail()}">
                                                <span class="material-symbols-outlined">
                                                    delete
                                                </span>
                                            </a>
                                        </td>
                                    </c:when>
                                </c:choose>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <hr class="my-4">
                        <div class="d-flex justify-content-start align-items-center">
                            <p class="mb-0 text-uppercase"><i class="fas fa-cog me-2"></i> <span
                                    class="text-muted small">Agregar colaborador </span></p>
                            <p class="mb-0 text-uppercase"><i class="fas fa-link ms-4 me-2"></i> <span
                                    class="text-muted small">| </span></p>
                            <p class="mb-0 text-uppercase"><i class="fas fa-link ms-4 me-2"></i> <span
                                    class="text-muted small"> Selecciona al usuario que deseas agregar al proyecto: </span></p>
                            <c:choose>
                                <c:when test="${usuario.equals(proyectoRes.getAdministrador())}">
                                    <form action="ServletProyecto?accion=agregarColaborador&nombreProyecto=${proyectoRes.getNombreProyecto()}" method="post">
                                        <label for="nuevoColaborador"></label>
                                        <select name="nuevoColaborador" id="nuevoColaborador" style="border-radius: 10px; border-color: #ca0867">
                                            <c:forEach var="persona" items="${personas}">
                                                <option value="${persona.getEmail()}">
                                                        ${persona.getNombre()} ${persona.getApellidos()}
                                                </option>
                                            </c:forEach>
                                        </select>
                                        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />                                        <button type="submit" class="btn btn-outline-dark btn-sm btn-floating">
                                            <span class="material-symbols-outlined">
                                                how_to_reg
                                            </span>
                                        </button>
                                    </form>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>