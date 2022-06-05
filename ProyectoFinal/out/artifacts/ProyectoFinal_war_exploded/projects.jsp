<%--
  Created by IntelliJ IDEA.
  User: gamma
  Date: 07/05/2022
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="optionalStyle" value="css/proyectos.css"/>
</jsp:include>
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
                    <div class="col col-lg-2 status-bar"></div>
                    <div class="col-md-auto"><span class="percent">12%</span></div>
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
                </div>
            </div>
        </div>
    </c:forEach>
    <a href="nuevo_proyecto.jsp"> Nuevo proyecto </a>
</div>
</body>
</html>