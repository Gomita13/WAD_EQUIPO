<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Principal Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />

    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="./carrera/css/styles.css" rel="stylesheet" />

</head>

<body>
<div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container px-4 px-lg-5">
            <a class="navbar-brand" href="#">Proyecto Base </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto py-4 py-lg-0">
                    <li class="nav-item">
                        <a class="nav-link px-lg-3 py-3 py-lg-4" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link px-lg-3 py-3 py-lg-4" href="./carrera/nuevo.html">Carrera</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link px-lg-3 py-3 py-lg-4" href="ListadoServlet">Lista de Carreras</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link px-lg-3 py-3 py-lg-4" href="./alumno/nuevo.html">Alumno</a>
                        <!-- <a class="nav-link" href="./CarreraServlet">Carrera</a>-->
                    </li>
                    <li class="nav-item">
                        <a class="nav-link px-lg-3 py-3 py-lg-4" href="ListadoAlumnoServlet">Lista de Alumnos</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<!-- Page Header-->
<header class="masthead" style="background-image: url('https://fondosmil.com/fondo/54336.png')">
    <div class="container position-relative px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <div class="site-heading">
                    <h1>PRACTICA 1</h1>
                    <span class="subheading">Escuela Web</span>
                </div>
            </div>
        </div>
    </div>
</header>
<h3 style="text-align: center">Integrantes del equipo</h3>

<div class="container">
    <div class="row">
        <div class="col" style="margin-left: 4%">
            <br>
            <div class="card" style="width: 18rem;">
                <img class="card-img-top" src="https://cdn-icons-png.flaticon.com/512/7127/7127352.png" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">Saúl García Medina</h5>
                    <p class="card-text">Alumno del grupo 3CM12 de la materia de Web Application Development</p>
                </div>
            </div>
        </div>
        <div class="col" style="margin-left: 4%">
            <br>
            <div class="card" style="width: 18rem;">
                <img class="card-img-top" src="https://cdn-icons-png.flaticon.com/512/7127/7127325.png" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">Emillie Pérez Flores</h5>
                    <p class="card-text">Alumna del grupo 3CM12 de la materia de Web Application Development</p>
                </div>
            </div>
        </div>
        <div class="col" style="margin-left: 4%">
            <br>
            <div class="card" style="width: 18rem;">
                <img class="card-img-top" src="https://cdn-icons-png.flaticon.com/512/7127/7127521.png" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">Aron</h5>
                    <p class="card-text">Alumno del grupo 3CM12 de la materia de Web Application Development</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>