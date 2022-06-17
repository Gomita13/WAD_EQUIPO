<%--
  Created by IntelliJ IDEA.
  User: gamma
  Date: 10/05/2022
  Time: 00:00
  To change this template use File | Settings | File Templates.
--%>
<%
    String email = (String) session.getAttribute("email");
    if(email == null) {
        response.sendRedirect("login.jsp");
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="optionalStyle" value="css/perfil.css"/>
</jsp:include>
<jsp:include page="nav.jsp"/>
<body style="overflow-x: hidden;">
    <section style="background-image: url('https://i.pinimg.com/originals/a2/a4/f8/a2a4f856632dbdc7c13eb2c97d62ab79.jpg')">
        <div class="container py-5">
            <div class="row">
                <h2 class="page-title">Perfil del usuario</h2>
                <br>
                <div class="col-lg-4">
                    <div class="card mb-4 carta">
                        <div class="card-body text-center carta">
                            <img src="./assets/user.png" alt="avatar"
                                 class="rounded-circle img-fluid" style="width: 155px;">
                            <h1 class="my-3">Hola <%=session.getAttribute("nombre")%></h1>
                            <p class="text-muted mb-1">Este es tu perfil de usuario :)</p>
                            <p class="text-muted mb-4">Puedes editar tu información si lo requieres.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8">
                    <div class="card mb-4">
                        <div class="card-body">
                            <form action="ServletPersona?accion=cuenta&emailPersona=${persona.getEmail()}" method="post">
                            <div class="row">
                                <div class="col-sm-3">
                                    <label class="mb-0" for="email">Email: </label>
                                </div>
                                <div class="col-sm-9">
                                    <input class="text-muted mb-0 form-control" type="email" name="email" id="email" value="${persona.getEmail()}">
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <label class="mb-0" for="nombre">Nombre(s): </label>
                                </div>
                                <div class="col-sm-9">
                                    <input class="text-muted mb-0 form-control" type="text" name="nombre" id="nombre" value="${persona.getNombre()}"></p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <label class="mb-0" for="apellidos">Apellidos: </label>
                                </div>
                                <div class="col-sm-9">
                                    <input class="text-muted mb-0 form-control" type="text" name="apellidos" id="apellidos" value="${persona.getApellidos()}">
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <label class="mb-0" for="password">Nueva contraseña: </label>
                                </div>
                                <div class="col-sm-9">
                                    <input class="text-muted mb-0 form-control" type="password" name="password" id="password">
                                </div>
                            </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-2">
                                    </div>
                                    <div class="col-sm-8">
                                        <input class="text-muted mb-0 btnn btnn-outline-danger" type="submit" value="Actualizar cuenta">
                                    </div>
                                    <div class="col-sm-2">
                                    </div>
                                </div>
                            <hr>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
