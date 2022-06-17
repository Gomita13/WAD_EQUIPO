<%--
  Created by IntelliJ IDEA.
  User: sadaga
  Date: 02/06/2022
  Time: 04:48 p. m.
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
    <jsp:param name="optionalStyle" value="css/nuevo_proyecto.css"/>
</jsp:include>
<head>
    <title>Nueva tarea</title>
</head>
<body style="overflow-x: hidden;">
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
        <h2 style="font-family: 'Pacifico', cursive; text-align: center; color: #2c3034; size: 18px">Agregar nueva tarea</h2>
        <br>
    </div>
</header>
<div class="row">
    <div class="col-lg-3"></div>
    <div class="col-lg-6">
        <br>
        <p>Porfavor ingrese los datos solicitados: </p>
        <hr>
        <form action="ServletTarea?accion=nuevaTarea" method="post">
            <label class="form-label" for="nombreTarea">Nombre de la tarea</label>
            <input class="form-control" type="text" name="nombreTarea" id="nombreTarea">
            <label class="form-label" for="nombreProyecto">Nombre del proyecto</label>
            <input class="form-control" type="text" name="nombreProyecto" id="nombreProyecto" value="${nombreProyecto}" readonly>
            <label class="form-label" for="encargado">Encargado</label>
            <select class="form-select" name="encargado" id="encargado">
                <c:forEach var="encargado" items="${personas}">
                    <option value="${encargado.getEmail()}">${encargado.getNombre()} ${encargado.getApellidos()}</option>
                </c:forEach>
            </select>
            <label class="form-label" for="descripcion">Descripción</label>
            <textarea class="form-control" name="descripcion" id="descripcion" cols="30" rows="10"></textarea>
            <hr>
            <input type="submit"  class="btn btn-primary" style="border-radius: 10px; background-color: #FFFFFF; border-color: #ca0867; color: #2c3034; text-align: center" value="Agregar">
        </form>
    </div>
    <div class="col-lg-3"></div>
</div>
</body>
</html>
