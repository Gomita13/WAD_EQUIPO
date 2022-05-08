<%--
  Created by IntelliJ IDEA.
  User: gamma
  Date: 07/05/2022
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="optionalStyle" value="css/proyectos.css"/>
</jsp:include>
<body>
    <jsp:include page="nav.jsp"/>
    <div class="container">
        <h2 class="page-title">Mis proyectos</h2>
        <div class="card">
            <div class="card-header">Prototipo de Bellas Artes</div>
            <div class="card-body">
                <div class="row">
                    <div class="col"><p class="date">Mayo 1, 2022 - Mayo 29, 2022</p></div>
                    <div class="col col-lg-2 status-bar"> </div>
                    <div class="col-md-auto"><span class="percent">100%</span></div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

