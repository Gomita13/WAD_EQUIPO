<%@ page import="modelo.dto.CarreraDTO" %>
<%@ page import="modelo.dao.CarreraDAO" %><%--
  Created by IntelliJ IDEA.
  User: gamma
  Date: 12/03/2022
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD de carreras</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/global.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="../index.jsp">Carrera Web</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" href="agregar.jsp">Agregar</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="carreras.jsp">Listar Carreras</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="alumnos.jsp">Listar Alumnos</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <%
        int id = Integer.parseInt(request.getParameter("id"));
        CarreraDAO dao = new CarreraDAO();
        CarreraDTO aux = new CarreraDTO();
        aux.getEntidad().setIdCarrera(id);
        aux = dao.read(aux);
        String nombre = aux.getEntidad().getNombreCarrera();
    %>
    <div class="container">
        <div class="w-50 mx-auto">
            <header>
                <h1>¿Desea eliminar la carrera <%=nombre%>?</h1>
            </header>
            <main>
                <form action="BorrarServlet" method="post">
                    <input type="hidden" name="idCarrera" value="<%=id%>">
                    <input type="submit" name="send" value="Confirmar">
                    <a href="carreras.jsp"><button>Cancelar     </button></a>
                </form>
            </main>
            <footer>
                Backyardigans (R) 2022.
            </footer>
        </div>
    </div>
</body>
</html>
