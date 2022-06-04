<%--
  Created by IntelliJ IDEA.
  User: sadaga
  Date: 04/06/2022
  Time: 03:24 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nuevo proyecto</title>
</head>
<body>
<jsp:include page="nav.jsp"/>
<form action="ServletProyecto?accion=nuevo" method="post">
  <label for="nombreProyecto"> Nombre del proyecto </label>
  <input type="text" name="nombreProyecto" id="nombreProyecto">
  <label for="inicio">Fecha de inicio del proyecto</label>
  <input type="date" name="inicio" id="inicio">
  <label for="fin">Fecha de finalización del proyecto</label>
  <input type="date" name="fin" id="fin">
  <input type="submit" value="Crear proyecto">
</form>
</body>
</html>