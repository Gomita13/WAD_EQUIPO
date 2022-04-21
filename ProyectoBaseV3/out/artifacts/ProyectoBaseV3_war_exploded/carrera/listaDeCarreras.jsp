<%--
  Created by IntelliJ IDEA.
  User: sadaga
  Date: 24/03/2022
  Time: 07:17 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.ipn.mx.modelo.dao.CarreraDAO" %>
<%@page import="java.sql.SQLException" %>
<%@page import="java.util.List" %>
<%@page import="com.ipn.mx.modelo.dto.CarreraDTO" %>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Listado Carrera</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" ></script>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
  <!-- Google fonts-->
  <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
  <!-- Core theme CSS (includes Bootstrap)-->
  <link href="../css/styles.css" rel="stylesheet" />
</head>
<body>
<div class="container">
  <div class="mb-3"></div>
  <%-- INICIO DE LA NAV --%>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand text-primary" href="../usuario/inicio.jsp">Escuela Web </a>
      <button class="navbar-toggler text-primary" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon text-primary"></span>
      </button>
      <div class="collapse navbar-collapse text-primary" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link px-lg-3 py-3 py-lg-4" aria-current="page" href="../usuario/inicio.jsp">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link px-lg-3 py-3 py-lg-4" href="agregarCarrera.jsp">Carrera</a>
          </li>
          <li class="nav-item">
            <a class="nav-link px-lg-3 py-3 py-lg-4" href="listaDeCarreras.jsp">Listar Carreras</a>
          </li>
          <li class="nav-item">
            <a class="nav-link px-lg-3 py-3 py-lg-4" href="../alumno/agregarAlumno.jsp">Alumno</a>
            <!-- <a class="nav-link" href="./CarreraServlet">Carrera</a>-->
          </li>
          <li class="nav-item">
            <a class="nav-link px-lg-3 py-3 py-lg-4" href="../alumno/listaDeAlumnos.jsp">Lista de Alumnos</a>
          </li>
          <li class="nav-item">
            <a class="nav-link px-lg-3 py-3 py-lg-4" href="inicio.jsp?close=1">Cerrar sesión</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <%-- FIN DE LA NAV --%>
  <div class="mb-3"></div>
  <div class="card border-primary">
    <div class="card-header text-primary">
      <h1 class="text-center">
        Listado de Carreras
      </h1>
    </div>
    <div class="card-body text-primary">
      <table class="table table-striped">
        <tr>
          <th>Clave de la Carrera</th>
          <th>Nombre de la Carrera</th>
          <th>Descripción de la Carrera</th>
          <th>Eliminar</th>
          <th>Actualizar</th>
        </tr>
        <%
          //Aqui llamamos al dao para que lea todo lo que hay en la tabla de carrera
          CarreraDAO dao = new CarreraDAO();
          List lista = dao.readAll();
          //Este if revisa si la tabla de carreras NO está vacía
          if(lista != null){
            /**
             * Aquí comenzamos un ciclo for el cual va a encerrar todas las etiquetas HTML
             * de la tabla, mucho ojo porque las llaves del for cierran hasta el otro bloque
             * jsp. Este for va a recorrer todos los registros y los irá poniendo en la tabla
             * */
            for(int i =0; i < lista.size(); i++){
              CarreraDTO dto = (CarreraDTO) lista.get(i);
        %>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <tr>
          <td>
            <a href="verCarrera.jsp?id=<%= dto.getEntidad().getIdCarrera() %>" class="btn btn-outline-info">
              <%= dto.getEntidad().getIdCarrera() %>
            </a>
          </td>
          <td>
            <%= dto.getEntidad().getNombreCarrera() %>
          </td>
          <td>
            <%= dto.getEntidad().getDescripcionCarrera() %>
          </td>
          <td>
            <a href="eliminarCarrera.jsp?id=<%= dto.getEntidad().getIdCarrera() %>" class="btn btn-danger">
              <span class='material-icons'> delete_outline </span>
            </a>
          </td>
          <td>
            <a href="actualizarCarrera.jsp?id=<%= dto.getEntidad().getIdCarrera() %>" class="btn btn-outline-success">
              <span class='material-icons'> update </span>
            </a>
          </td>
        </tr>
        <%
            }
            // Si la tabla carreras está vacía manda un td para decir que no hay registros
          }else{
            out.println("<td colspan=5> No hay registros a mostrar </td>");
          }
        %>
      </table>
      <a href="agregarCarrera.jsp" class="btn btn-outline-primary align-items-center">Nueva carrera</a>
    </div>
  </div>
  <%
    /*
    * Este if checa si recibe un parámetro llamado error. Este parámetro se manda desde los demás jsp
    * de la misma forma que en el proyecto 2 se mandaba un parámetro llamado accion y sirve para mostrar
    * los errores si es que los demás jsp's mandaran alguno.
    * */
    if (request.getParameter("error") != null) {
      /*
      Aquí cerramos el bloque jsp porque en caso de que haya error vamos a mostrar un div con el error
      en caso de que no haya errores este div no se muestra
      */
  %>
  <div class="alert alert-danger">
    <p>No se pudo procesar tu petición <%= request.getParameter("error") %></p>
  </div>
  <%
  }
  %>
</div>
</body>
</html>