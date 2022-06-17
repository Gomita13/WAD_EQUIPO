<%--
  Created by IntelliJ IDEA.
  User: gamma
  Date: 07/05/2022
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="text-center"><a class="navbar-brand" href="#"><h1>Task Manager</h1></a></div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav" style="margin-left: 60%">
            <li class="nav-item">
                <a class="nav-link" href="ServletPersona?accion=dashboard">Dashboard</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="ServletProyecto?accion=proyectos">Mis proyectos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="ServletPersona?accion=reporte">Reporte</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="ServletPersona?accion=cuenta">Cuenta</a>
            </li>
        </ul>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
        <a href="ServletPersona?accion=logout" style="margin-left: 5%"><span class="material-symbols-outlined">
            logout
            </span>
        </a>
    </div>
</nav>