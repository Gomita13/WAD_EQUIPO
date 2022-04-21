<%@ page import="com.ipn.mx.modelo.dto.AlumnoDTO" %>
<%@ page import="com.ipn.mx.modelo.dao.AlumnoDAO" %><%--
  Created by IntelliJ IDEA.
  User: sadaga
  Date: 24/03/2022
  Time: 07:16 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Agregar carrera</title>
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
    <div class="container">
        <header>
            <br><h2>
            Datos del alumno
        </h2><br>
        </header>
        <main>
            <article>
                <form method="post" action="" name="frmDatos">
                    <input type="hidden" name="accion" value="0">
                    <div class="mb-3">
                        <label for="nombreAlumno" class="form-label"> Nombre del alumno:</label>
                        <input type="text" name="nombreAlumno" id="nombreAlumno"
                               placeholder="Nombre del alumno"
                               class="form-control"
                               required
                               maxlength="50"/>
                    </div>
                    <div>
                        <label for="paternoAlumno" class="form-label">Apellido paterno:</label>
                        <input name="paternoAlumno" id="paternoAlumno"
                               placeholder="Apellido paterno"
                               maxlength="100"
                               required
                               class="form-control"
                        />
                    </div>
                    <div class="mb-3">
                        <label for="maternoAlumno" class="form-label">Apellido materno:</label>
                        <input type="text" name="maternoAlumno" id="maternoAlumno"
                               placeholder="Apellido materno"
                               class="form-control"
                               required
                               maxlength="50"/>
                    </div>
                    <div>
                        <label for="emailAlumno" class="form-label">Email del alumno:</label>
                        <input name="emailAlumno" id="emailAlumno"
                               placeholder="Email del alumno"
                               maxlength="100"
                               required
                               class="form-control"
                        />
                    </div>
                    <div class="mb-3">
                        <label for="nombreCarrera" class="form-label"> Nombre Carrera</label>
                        <input type="text" name="nombreCarrera" id="nombreCarrera"
                               placeholder="Nombre de la Carrera"
                               class="form-control"
                               required
                               maxlength="50"/>
                    </div>
                    <div class="mb-3">
                        <input type="submit" name="cmdEnviar" value="Enviar" class="btn btn-outline-primary"/>
                    </div>
                </form>
            </article>
        </main>
        <%
            /*
            * Aquí hacemos un if que checa si se hace una petición de tipo POST a este jsp.
            * Esta página se manda peticiones a sí misma porque en el action del form que está
            * arriba no hay nada, así es como se mandan las peticiones a una misma página. Entonces
            * checa si el método fue post y si fue así hace la inserción de la carrera en la BD con el DAO.
            * El código que está en este bloque es exactamente un copy-paste del servlet del proyecto 2
            * la única diferencia es que aquí no están los out.println()
            * */
            if(request.getMethod().equalsIgnoreCase("Post")) {
                AlumnoDTO dto = new AlumnoDTO();
                dto.getEntidad().setNombreAlumno(request.getParameter("nombreAlumno"));
                dto.getEntidad().setPaternoAlumno(request.getParameter("paternoAlumno"));
                dto.getEntidad().setMaternoAlumno(request.getParameter("maternoAlumno"));
                dto.getEntidad().setEmailAlumno(request.getParameter("emailAlumno"));
                AlumnoDAO dao = new AlumnoDAO();
                try {
                    int idCarrera = dao.selectCarrera(request.getParameter("nombreCarrera"));
                    dto.getEntidad().setIdCarrera(idCarrera);
                    dao.create(dto);
                    // Esta línea lo que hace es redirigir a la página que se pone entre paréntesis una vez terminado el proceso
                    response.sendRedirect("listaDeAlumnos.jsp");
                } catch(Exception e) {
                    System.out.println("ERROR (agregarAlumno.jsp): ");
                    e.printStackTrace();
                    //Si hubiera algún error, redirigimos al usuario a la página que queramos y pasamos el parámetro error por la url
                    response.sendRedirect("listaDeAlumnos.jsp?error=" + e);
                }
            }
        %>
    </div>
</body>
</html>