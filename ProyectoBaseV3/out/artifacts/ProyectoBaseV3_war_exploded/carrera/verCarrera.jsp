<%--
  Created by IntelliJ IDEA.
  User: sadaga
  Date: 24/03/2022
  Time: 07:18 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ipn.mx.modelo.dao.CarreraDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ipn.mx.modelo.dto.CarreraDTO" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css">
    <script src="../js/bootstrap/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
    <title>Actualizar carrera</title>
</head>
<body>
<%-- INICIO DE LA NAV --%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="../usuario/inicio.jsp">Proyecto Base </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto py-4 py-lg-0">
                <li class="nav-item">
                    <a class="nav-link px-lg-3 py-3 py-lg-4" aria-current="page" href="../usuario/inicio.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link px-lg-3 py-3 py-lg-4" href="../carrera/agregarCarrera.jsp">Carreras</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link px-lg-3 py-3 py-lg-4" href="../carrera/listaDeCarreras.jsp">Listar Carreras</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link px-lg-3 py-3 py-lg-4" href="./alumno/nuevo.html">Alumno</a>
                    <!-- <a class="nav-link" href="./CarreraServlet">Carrera</a>-->
                </li>
                <li class="nav-item">
                    <a class="nav-link px-lg-3 py-3 py-lg-4" href="ListadoAlumnoServlet">Lista de Alumnos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link px-lg-3 py-3 py-lg-4" href="inicio.jsp?close=1">Cerrar sesión</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<%-- FIN DE LA NAV --%>
<div class="container">
    <%
        /*
        * Acá obtenemos el id que obtenemos gracias a que listaDeCarreras.jsp
        * nos pasa el id por la url en la etiqueta <a> que corresponde al vínculo para el jsp de verCarrera.jsp
        * */
        int id = Integer.parseInt(request.getParameter("id"));
        CarreraDAO dao = new CarreraDAO();
        CarreraDTO dto = new CarreraDTO();
        String errors = "";
    %>
    <h1>Datos de la carrera</h1>
    <%
        /*
        * Todo esto es un copy-paste del servlet del proyecto 2 solo que en lugar de usar los out.println()
        * ya podemos usar HTML normalito siempre y cuando no estén dentro de los bloques jsp < % % >
        * recordemos que dentro de estos bloques solo se puede escribir código java, lo que esté fuera es código HTML
        * MUCHO OJO: Cuando queramos poner un valor que esté guardado en una variable de java dentro de los bloques jsp,
        * por ejemplo, dentro de un atributo value de las etiquetas o dentro de las mismas etiquetas. Ej:
        * Tenemos una variable String nombre = "mi nombre" y eso lo queremos meter en el value de un input o en una
        * etiqueta <p> debemos hacerlo de la siguiente manera:
        * <input type='text' value='< % = nombre % >'> (Véase el código html de abajo)
        * Démonos cuenta que no es como un bloque jsp normal el cual es < % % > (Es sin espacios pero si le quito
        * los espacios deja de ponerlo como comentario xD) sino que es < % = variable % > CON EL SIGNO =
        * Los bloques < % % > normales son para cuando queramos escribir código java como tal.
        * */
        try{
         dto.getEntidad().setIdCarrera((long)id);
         CarreraDTO resultado = dao.read(dto);
    %>
    <form action='' method='post'>
        <div class='mb-3'>
            <label for='nombreCarrera' class='form-label'> Nombre Carrera</label>
            <input type='text' name='nombreCarrera' id='nombreCarrera' value='<%=resultado.getEntidad().getNombreCarrera() %>' class='form-control form-control-plaintext' required maxlength='50' readonly/>
        </div>
        <div class='mb-3'>
            <label for='descripcionCarrera' class='form-label'> Descripcion Carrera</label>
            <input type='text' name='descripcionCarrera' id='descripcionCarrera' value='<%=resultado.getEntidad().getDescripcionCarrera() %>' class='form-control form-control-plaintext' required maxlength='50' readonly/>
            <input type='hidden' name='idCarrera' id='idCarrera' value='<%=resultado.getEntidad().getIdCarrera() %>' class='form-control' required maxlength='50'/>
        </div>
        <div class='text-center'>
            <a href='listaDeCarreras.jsp' class='btn btn-primary'>Cancelar</a>
        </div>
    </form>
    <%
        } catch (SQLException e) {
            System.out.println("ERROR (verCarrera.jsp): ");
            e.printStackTrace();
        }
    %>
</div>
</body>
</html>