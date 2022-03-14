<%@ page import="modelo.dao.CarreraDAO" %>
<%@ page import="modelo.dto.CarreraDTO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: gamma
  Date: 11/03/2022
  Time: 16:39
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
        <a class="navbar-brand" href="index.jsp">Carrera Web</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" href="agregarCarrera.jsp">Agregar</a>
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
<div class="row">
    <div class="w-50 mx-auto">
        <header>
            <h1>Listado de Carreras</h1>
        </header>
        <main>
            <%
                CarreraDAO dao = new CarreraDAO();
                ArrayList<CarreraDTO> carreras = null;
                try{
                    carreras = dao.readAll();
                }catch (Exception e){
                    e.printStackTrace();
                }

                if(carreras != null){
            %>
            <table class="table table-striped table-responsive">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">idCarrera</th>
                    <th scope="col">Nombre de la carrera</th>
                    <th scope="col">Descripcion de la carrera</th>
                    <th scope="col">Acciones</th>
                    <th scope="col">Acciones</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for(CarreraDTO carrera: carreras){
                %>
                <tr scope="row">
                    <td><%= carrera.getEntidad().getIdCarrera()%></td>
                    <td><%= carrera.getEntidad().getNombreCarrera()%></td>
                    <td><%= carrera.getEntidad().getDescripcionCarrera()%></td>
                    <td>
                        <a href="editarCarrera.jsp?id=<%= carrera.getEntidad().getIdCarrera()%>" class="btn btn-secondary btn-edit">Editar</a>
                    </td>
                    <td>
                        <a href="borrarCarrera.jsp?id=<%=carrera.getEntidad().getIdCarrera()%>" class="btn btn-secondary btn-delete">Eliminar</a>
                    </td>
                </tr>
                <%
                    }
                %></tbody></table><%
        }else{
        %>
            <h3>No hay registros</h3>
            <%
                }
            %>

        </main>
        <footer>

        </footer>
    </div>
</div>
</body>
</html>
