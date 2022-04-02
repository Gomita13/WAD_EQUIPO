<%@ page import="com.ipn.mx.modelo.dao.UsuarioDAO" %>
<%@ page import="com.ipn.mx.modelo.dto.UsuarioDTO" %>
<%@ page import="jakarta.persistence.criteria.CriteriaBuilder" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="./css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script type="text/javascript" src="./js/jquery.min.js"></script>
    <script type="text/javascript" src="./js/popper.min.js"></script>
    <script type="text/javascript" src="./js/bootstrap/bootstrap.min.js"></script>
</head>
<body class="text-center">
    <div class="card w-25">
        <form class="form-signin" method="post" action="">
            <h1 class="h3 mb-3 font-weight-normal">Iniciar sesión</h1>
            <label for="inputUsername" class="sr-only">Email</label>
            <input name="nombreUsuario" type="text" id="inputUsername" class="form-control" placeholder="Nombre de usuario" required autofocus>
            <label for="inputPassword" class="sr-only">Contraseña</label>
            <input name="claveUsuario" type="password" id="inputPassword" class="form-control" placeholder="Contraseña" required>
            <div class="checkbox mb-3">
                <label>
                    ¿No tienes cuenta? <a href="./usuario/registro.jsp">Crear una</a>
                </label>
            </div>
            <button class="btn btn-lg btn-block" type="submit">Sign in</button>
            <p class="mt-5 mb-3 text-muted">&copy; 2022. No sé cómo se llama esta equipo. ESCOM</p>
        </form>
    <%
        if(request.getMethod().equalsIgnoreCase("POST")){ //Login
            System.out.println("ANDAMOS ENTRANDO ACA");
            String nombreUsuario = request.getParameter("nombreUsuario");
            String claveUsuario = request.getParameter("claveUsuario");
            UsuarioDTO dto = new UsuarioDTO();
            UsuarioDAO dao = new UsuarioDAO();
            dto.getEntidad().setNombreUsuario(nombreUsuario);
            dto.getEntidad().setClaveUsuario(claveUsuario);
            System.out.println(dto.toString());
            try{
                UsuarioDTO exists = dao.login(dto);
                if(exists!= null) {
                    session.setAttribute("nombreUsuario",exists.getEntidad().getNombreUsuario());
                    session.setAttribute("nombre",exists.getEntidad().getNombre());
                    session.setAttribute("paterno",exists.getEntidad().getPaterno());
                    session.setAttribute("materno",exists.getEntidad().getMaterno());
                    response.sendRedirect("usuario/inicio.jsp");
                }else{
                    response.sendRedirect("index.jsp");
                }
            }catch(Exception e){
                System.out.println("ERROR (index.jsp): ");
                e.printStackTrace();
            }
        }else{
            if(request.getParameter("err")!= null){%>
                <div class="alert alert-danger" role="alert">
                    Usuario o contraseña incorrectos
                </div>
         <%}
        }%>
    </div>
</body>
</html>