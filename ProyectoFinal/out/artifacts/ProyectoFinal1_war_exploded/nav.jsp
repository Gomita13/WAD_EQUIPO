<%--
  Created by IntelliJ IDEA.
  User: gamma
  Date: 07/05/2022
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="text-center"><a class="navbar-brand" href="#"><h1>WebOS</h1></a></div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="#">Dashboard</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="projects.jsp">Mis proyectos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Mis tareas</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="account.jsp">Cuenta</a>
            </li>
        </ul>
        <a href="persona-servlet?accion=0">Cerrar sesión</a>
    </div>
</nav>