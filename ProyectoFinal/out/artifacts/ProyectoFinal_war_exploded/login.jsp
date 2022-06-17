<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="optionalStyle" value="css/login.css"/>
</jsp:include>
<body>
    <section class="h-100 gradient-form" style="background-color: #eee;">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-xl-10">
                    <div class="card rounded-3 text-black">
                        <div class="row g-0">
                            <div class="col-lg-6">
                                <div class="card-body p-md-5 mx-md-4">
                                    <h1>Task manager</h1>
                                    <form action="ServletPersona?accion=login" method="post">
                                        <p>Introduce tus datos</p>
<%--                                        <input type="hidden" name="accion" value="1">--%>
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="txtEmail">Email:</label>
                                            <input name="email" type="email" id="txtEmail" class="form-control"
                                                   placeholder="Phone number or email address" />
                                        </div>
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="txtPassword">Contraseña:</label>
                                            <input name="password" type="password" id="txtPassword" class="form-control" />
                                        </div>
                                        <div class="mb-5">
                                            <input type="submit" class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-1 login" value="Ingresar">
                                            <a class="text-muted" href="#!">¿Olvidaste tu contraseña?</a>
                                        </div>
                                        <div class="d-flex align-items-center justify-content-center pb-4">
                                            <p class="mb-0 me-2">¿No tienes una cuenta?</p>
                                            <a href="sign-up.jsp"><button type="button" class="btn btn-outline-danger">Crear cuenta</button></a>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="col-lg-6 d-flex align-items-center">
                                <img src="assets/lading.png" width="400" height="400">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>