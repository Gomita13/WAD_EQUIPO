<%--
  Created by IntelliJ IDEA.
  User: eflor
  Date: 12/06/2022
  Time: 02:41 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>ProyectoFinal</title>
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/indexHome.css" rel="stylesheet" />
</head>
<body>
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark navbar-custom fixed-top">
    <div class="container px-5">
      <a class="navbar-brand" href="#page-top">Task Manager</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ms-auto">
          <li class="nav-item"><a class="nav-link" href="login.jsp">Iniciar sesión</a></li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- Header-->
  <header style="height: 500px" class="masthead text-center text-white">
    <div class="masthead-content">
      <div class="container px-5">
        <h1 class="masthead-heading mb-0" style="margin-top: -70px">Task manager</h1>
        <h2 class="masthead-subheading mb-0">Web application development</h2>
        <a class="btn btn-primary btn-xl rounded-pill mt-5" href="login.jsp">Ingresar</a>
      </div>
    </div>
    <div class="bg-circle-1 bg-circle"></div>
    <div class="bg-circle-2 bg-circle"></div>
    <div class="bg-circle-3 bg-circle"></div>
    <div class="bg-circle-4 bg-circle"></div>
  </header>
  <!-- Content section 1-->
  <section id="scroll">
    <div class="container px-5">
      <div class="row gx-5 align-items-center">
        <div class="col-lg-6 order-lg-2">
          <div class="p-5"><img class="img-fluid rounded-circle" src="https://cdn-icons-png.flaticon.com/512/1935/1935533.png" alt="..." /></div>
        </div>
        <div class="col-lg-6 order-lg-1">
          <div class="p-5">
            <h2 class="display-4">Realiza un seguimiento de tus prioridades</h2>
            <p>Da seguimiento a todas tus tareas en un mismo espacio de trabajo para tener un panorama claro de cómo será tu día, tu semana y tu mes.</p>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- Content section 2-->
  <section>
    <div class="container px-5">
      <div class="row gx-5 align-items-center">
        <div class="col-lg-6">
          <div class="p-5"><img class="img-fluid rounded-circle" src="https://cdn-icons-png.flaticon.com/512/4576/4576262.png" alt="..." /></div>
        </div>
        <div class="col-lg-6">
          <div class="p-5">
            <h2 class="display-4">Organiza tu trabajo de la manera en que quieras.</h2>
            <p>Desde listas hasta tableros, y mucho más, organiza el trabajo en la vista que mejor se adapte a ti.</p>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- Content section 3-->
  <section>
    <div class="container px-5">
      <div class="row gx-5 align-items-center">
        <div class="col-lg-6 order-lg-2">
          <div class="p-5"><img class="img-fluid rounded-circle" src="https://cdn-icons-png.flaticon.com/512/1933/1933934.png" alt="..." /></div>
        </div>
        <div class="col-lg-6 order-lg-1">
          <div class="p-5">
            <h2 class="display-4">Conocenos</h2>
            <p>Es una aplicación muy sencilla, cómoda y fácil de usar.</p>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- Footer-->
  <footer class="py-5 bg-black">
    <div class="container px-5"><p class="m-0 text-center text-white small">Copyright &copy; TaskManager 2022</p></div>
  </footer>
  <!-- Bootstrap core JS-->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  <!-- Core theme JS-->
  <script src="js/scripts.js"></script>
</body>
</html>
