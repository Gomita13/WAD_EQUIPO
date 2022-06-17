<%--
  Created by IntelliJ IDEA.
  User: sadaga
  Date: 04/06/2022
  Time: 03:24 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%
  String email = (String) session.getAttribute("email");
  if(email == null) {
    response.sendRedirect("login.jsp");
  }
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<html>
<jsp:include page="head.jsp">
  <jsp:param name="optionalStyle" value="css/nuevo_proyecto.css"/>
</jsp:include>
<head>
    <title>Nuevo proyecto</title>
</head>
<body style="overflow-x: hidden;">
<jsp:include page="nav.jsp"/>
<header>
  <div class="overlay">
    <br>
    <br>
    <h2 style="font-family: 'Pacifico', cursive; text-align: center; color: #2c3034; size: 18px">Crear nuevo proyecto</h2>
    <br>
  </div>
</header>
<div class="row">
  <div class="col-lg-3"></div>
  <div class="col-lg-6">
    <br>
    <p>Porfavor ingrese los datos solicitados: </p>
    <hr>
    <form action="ServletProyecto?accion=nuevo" method="post">
      <label class="form-label"  for="nombreProyecto"><b>Nombre del proyecto</b></label>
      <input class="form-control"  type="text" name="nombreProyecto" id="nombreProyecto">
      <label class="form-label"  for="inicio"><b>Fecha de inicio del proyecto</b></label>
      <input class="form-control"  type="date" name="inicio" id="inicio">
      <label class="form-label"  for="fin"><b>Fecha de finalización del proyecto</b></label>
      <input class="form-control"  type="date" name="fin" id="fin">
      <hr>
      <input type="submit" class="btn btn-primary" style="border-radius: 10px; background-color: #FFFFFF; border-color: #ca0867; color: #2c3034; text-align: center" value="Crear proyecto">
    </form>
  </div>
  <div class="col-lg-3"></div>
</div>
</body>
</html>