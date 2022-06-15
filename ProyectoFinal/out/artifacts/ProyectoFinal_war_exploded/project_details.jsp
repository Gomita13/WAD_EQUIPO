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
        response.sendRedirect("index.jsp");
    }
%>
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
</header>
<section>
    <h2>Tareas pendientes</h2>
    <c:forEach var="tarea" items="${tareasNoCompletadas}">
        <p>${tarea.getNombreTarea()}</p>
        <p>${tarea.getDescripcion()}</p>
        <p>Asignada a: ${tarea.getEncargado()}</p>
        <c:choose>
            <c:when test="${usuario.equals(proyectoRes.getAdministrador())}">
                <a href="ServletTarea?accion=completar&nombre=${tarea.getNombreTarea()}&proyecto=${tarea.getNombreProyecto()}">
                    Completar tarea
                </a>
                <a href="ServletTarea?accion=editar&nombre=${tarea.getNombreTarea()}&proyecto=${tarea.getNombreProyecto()}">
                    Editar tarea
                </a>
                <a href="ServletTarea?accion=eliminar&nombre=${tarea.getNombreTarea()}&proyecto=${tarea.getNombreProyecto()}">
                    Eliminar tarea
                </a>
            </c:when>
        </c:choose>
    </c:forEach>
<c:choose>
    <c:when test="${usuario.equals(proyectoRes.getAdministrador())}">
        <a href="ServletTarea?accion=agregarTarea&nombreProyecto=${proyectoRes.getNombreProyecto()}">
            Agregar tarea
        </a>
    </c:when>
</c:choose>
</section>
<section>
    <h2>Tareas completadas</h2>
    <c:forEach var="tarea" items="${tareasCompletadas}">
        <p>${tarea.getNombreTarea()}</p>
        <p>${tarea.getDescripcion()}</p>
        <p>Asignada a: ${tarea.getEncargado()}</p>
        <c:choose>
            <c:when test="${usuario.equals(proyectoRes.getAdministrador())}">
                <a href="ServletTarea?accion=completar&nombre=${tarea.getNombreTarea()}&proyecto=${tarea.getNombreProyecto()}">
                    Marcar como pendiente
                </a>
                <a href="ServletTarea?accion=editar&nombre=${tarea.getNombreTarea()}&proyecto=${tarea.getNombreProyecto()}">
                    Editar tarea
                </a>
                <a href="ServletTarea?accion=eliminar&nombre=${tarea.getNombreTarea()}&proyecto=${tarea.getNombreProyecto()}">
                    Eliminar tarea
                </a>
            </c:when>
        </c:choose>
    </c:forEach>
</section>
<section>
    <h2>Mis tareas</h2>
    <c:choose>
        <c:when test="${misTareas.size() == 0}">
            <p>No tienes tareas asignadas para este proyecto aún</p>
        </c:when>
        <c:otherwise>
            <c:forEach var="tarea" items="${misTareas}">
                <p>${tarea.getNombreTarea()}</p>
                <p>${tarea.getDescripcion()}</p>
                <c:choose>
                    <c:when test="${tarea.isCompletada() == false}">
                        <a href="ServletTarea?accion=completar&nombre=${tarea.getNombreTarea()}&proyecto=${tarea.getNombreProyecto()}">
                            Completar tarea
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="ServletTarea?accion=completar&nombre=${tarea.getNombreTarea()}&proyecto=${tarea.getNombreProyecto()}">
                            Marcar como pendiente
                        </a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</section>
<section>
    <h2>Integrantes</h2>
    <table>
        <c:forEach var="colaborador" items="${colaboradores}">
            <tr>
                <td>${colaborador.getNombre()}</td>
                <c:choose>
                    <c:when test="${colaborador.getEmail().equals(proyectoRes.getAdministrador())}">
                        <td>Creador</td>
                    </c:when>
                    <c:otherwise>
                        <td>Colaborador</td>
                    </c:otherwise>
                </c:choose>
                <td>${colaborador.getEmail()}</td>
                <c:choose>
                    <c:when test="${usuario.equals(proyectoRes.getAdministrador())}">
                        <td>
                            <a href="ServletProyecto?accion=eliminarColaborador&nombreProyecto=${proyectoRes.getNombreProyecto()}&emailColaborador=${colaborador.getEmail()}">
                                Eliminar
                            </a>
                        </td>
                    </c:when>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
    <c:choose>
        <c:when test="${usuario.equals(proyectoRes.getAdministrador())}">
            <form action="ServletProyecto?accion=agregarColaborador&nombreProyecto=${proyectoRes.getNombreProyecto()}" method="post">
                <label for="nuevoColaborador"></label>
                <select name="nuevoColaborador" id="nuevoColaborador">
                    <c:forEach var="persona" items="${personas}">
                        <option value="${persona.getEmail()}">
                                ${persona.getNombre()} ${persona.getApellidos()}
                        </option>
                    </c:forEach>
                </select>
                <input type="submit" value="Agregar colaborador">
            </form>
        </c:when>
    </c:choose>
</section>
</body>
</html>