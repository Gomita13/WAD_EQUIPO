<%@ page import="com.ipn.mx.modelo.dto.UsuarioDTO" %>
<%@ page import="com.ipn.mx.modelo.dao.UsuarioDAO" %>

<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.ParseException" %><%--
  Created by IntelliJ IDEA.
  User: gamma
  Date: 28/03/2022
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/register.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/popper.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap/bootstrap.min.js"></script>
</head>
<body class="text-center" style="background-image: url('https://fondosmil.com/fondo/54336.png')">
<div class="container h-100" id="container">
    <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-lg-12 col-xl-11">
            <div class="card text-black" style="border-radius: 25px;">
                <div class="card-body p-md-5">
                    <div class="row justify-content-center">
                        <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                            <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Crear una cuenta</p>
                            <form class="mx-1 mx-md-4" method="post" action="../AgregarUsuario">
                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input name="nombre" type="text" id="form3Example1c" class="form-control" placeholder="Nombre"/>
                                    </div>
                                </div>
                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input name="paterno" type="text" class="form-control" placeholder="Paterno"/>
                                    </div>
                                </div>
                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input name="materno" type="text" class="form-control" placeholder="Materno"/>
                                    </div>
                                </div>
                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input name="email" type="email" id="form3Example3c" class="form-control" placeholder="Email"/>
                                    </div>
                                </div>
                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input name="nombreUsuario" type="text" id="form3Example4c" class="form-control" placeholder="Contraseña"/>
                                    </div>
                                </div>
                                <div class="d-flex flex-row align-items-center mb-4">
                                    <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                                    <div class="form-outline flex-fill mb-0">
                                        <input name="claveUsuario" type="password" id="form3Example4cd" class="form-control" placeholder="Confirma contraseña" />
                                    </div>
                                </div>
                                <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                    <input type="submit" class="btn btn-primary btn-lg" value="Registrar"/>
                                </div>
                            </form>
                            <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                <span>¿Ya tienes cuenta? <a href="../index.jsp">Inicia sesión</a></span>
                            </div>

                        </div>
                        <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>