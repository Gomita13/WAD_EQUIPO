<%--
  Created by IntelliJ IDEA.
  User: gamma
  Date: 10/05/2022
  Time: 00:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="optionalStyle" value="none"/>
</jsp:include>
<body>
    <jsp:include page="nav.jsp"/>
    <h1>Hola <%=session.getAttribute("nombre")%>%></h1>
</body>
</html>
