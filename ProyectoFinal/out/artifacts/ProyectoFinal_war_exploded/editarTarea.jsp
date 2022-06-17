<%--
  Created by IntelliJ IDEA.
  User: sadaga
  Date: 02/06/2022
  Time: 08:54 p. m.
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
    <jsp:param name="optionalStyle" value="none"/>
</jsp:include>
<head>
    <title>Editar tarea</title>
</head>
<body style="background-image: url('https://img.wallpapersafari.com/desktop/1920/1080/18/98/5PS2iK.jpg')">
<jsp:include page="nav.jsp"/>
<div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col col-md-9 col-lg-7 col-xl-5">
            <div class="card" style="border-radius: 15px; background-color: #ffffff; box-shadow: #2c3034;">
                <div class="card-body p-4 text-black">
                    <div>
                        <h2 style="font-family: 'Pacifico', cursive; text-align: center">Editar ${tareaRes.getNombreTarea()}</h2>
                        <div class="d-flex align-items-center justify-content-between mb-3">
                            <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
                            <p class="small mb-0"><span class="material-symbols-outlined">
                                edit
                            </span>Edita lo que consideres necesario :)</p>
                        </div>
                    </div>
                    <form action="ServletTarea?accion=editar&tarea=${tareaRes.getNombreTarea()}" method="post">
                        <label class="form-label" for="nombreTarea">Nombre de la tarea</label>
                        <input class="form-control" type="text" name="nombreTarea" id="nombreTarea" value="${tareaRes.getNombreTarea()}">
                        <label class="form-label" for="nombreProyecto">Nombre del proyecto</label>
                        <input class="form-control" type="text" name="nombreProyecto" id="nombreProyecto" value="${tareaRes.getNombreProyecto()}" readonly>
                        <label class="form-label" for="encargado">Encargado</label>
                        <select class="form-select" name="encargado" id="encargado">
                            <c:forEach var="encargado" items="${personas}">
                                <option value="${encargado.getEmail()}">${encargado.getNombre()} ${encargado.getApellidos()}</option>
                            </c:forEach>
                        </select>
                        <label class="form-label" for="descripcion">Descripción</label>
                        <textarea class="form-control" name="descripcion" id="descripcion" cols="30" rows="10">${tareaRes.getDescripcion()}</textarea>
                        <hr>
                        <input class="btn btn-primary" style="border-radius: 10px; background-color: #FFFFFF; border-color: #ca0867; color: #2c3034" type="submit" value="Editar">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>