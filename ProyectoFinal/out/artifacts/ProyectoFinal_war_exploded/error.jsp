<%--
  Created by IntelliJ IDEA.
  User: gamma
  Date: 09/05/2022
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%
    String email = (String) session.getAttribute("email");
    if(email == null) {
        response.sendRedirect("login.jsp");
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="css/404.css">
    <title>404</title>
</head>

<body>
<div id="image">
    <div>404</div>
    <div>
        <img src="assets/Scarecrow.png" alt="Scarecrow">
    </div>
</div>
<div id="text">
    <div>¡Vaya, ha ocurrido un error!</div>
    <div>
        No sabemos si ha sido radiación cósmica o fluctuaciones cuánticas, pero seguramente ha sido un error de programación...
    </div>
</div>
<footer>Web Application Development</footer>
</body>

</html>