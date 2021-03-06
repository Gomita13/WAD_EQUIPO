<%--
  Created by IntelliJ IDEA.
  User: gamma
  Date: 07/05/2022
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="optionalStyle" value="css/sign-up.css"/>
</jsp:include>
<body>
    <section class="h-100 gradient-form" style="background-color: #eee;">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-xl-10">
                    <div class="card rounded-3 text-black">
                        <div class="row g-0">
                            <div class="col-lg-6 d-flex align-items-center">
                                <img src="assets/sign-up.png" width="512" height="512">
                            </div>
                            <div class="col-lg-6">
                                <div class="card-body p-md-5 mx-md-4">
                                    <h1>WebOS</h1>
                                    <form>
                                        <p>Crear una cuenta</p>
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="txtEmail">Email:</label>
                                            <input type="email" id="txtEmail" class="form-control"
                                                   placeholder="Phone number or email address" />
                                        </div>
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="txtName">Nombre:</label>
                                            <input type="password" id="txtName" class="form-control" />
                                        </div>
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="txtSurname">Apellidos:</label>
                                            <input type="email" id="txtSurname" class="form-control"
                                                   placeholder="Phone number or email address" />
                                        </div>
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="txtPassword">Constrase??a:</label>
                                            <input type="password" id="txtPassword" class="form-control" />
                                        </div>
                                        <div class="text-center pt-1 mb-5 pb-1">
                                            <a href="dashboard.jsp"><button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="button">Crear cuenta</button></a>
                                        </div>
                                        <div class="d-flex align-items-center justify-content-center pb-4">
                                            <p class="mb-0 me-2">??Ya tienes cuenta?</p>
                                            <a href="index.jsp"><button type="button" class="btn btn-outline-danger">Inicia sesi??n</button></a>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
