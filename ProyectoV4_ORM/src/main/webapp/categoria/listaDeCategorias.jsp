<%@ page import="com.ipn.mx.modelo.entidades.Categoria" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sadaga
  Date: 08/04/2022
  Time: 01:29 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listado Categorías</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" ></script>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="../index.jsp">Navbar</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="../index.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="CategoriaServlet?accion=listaDeCategorias">Listado de Categorías</a>
                    </li>

                </ul>
            </div>
        </div>
    </nav>

    <div class="mb-3"></div>
    <div class="card">
        <div class="card-header">
            <h1 class="card-title text-primary text-center">
                Listado de Categorías
            </h1>
            <a href="categoria/categoriaForm.jsp" class="btn btn-outline-primary">Nuevo</a>
        </div>
        <div class="card-body">
            <table class="table table-striped">
                <tr>
                    <th>Clave Categor&iacute;a</th>
                    <th>Nombre Categor&iacute;a</th>
                    <th>Descripci&oacute;n Categoria</th>
                    <th>Eliminar</th>
                    <th>Actualizar</th>
                </tr>
                <%
                    List<Categoria> listado = (List<Categoria>) request.getAttribute("listado");
                    for(Categoria cat : listado){%>
                        <tr>
                            <td>
                                <a href="CategoriaServlet?accion=ver&id=<%=cat.getIdcategoria()%>" class="btn btn-outline-success"><%=cat.getIdcategoria()%></a>
                            </td>
                            <td>
                                <%=cat.getNombrecategoria()%>
                            </td>
                            <td>
                                <%=cat.getDescripcioncategoria()%>
                            </td>
                            <td>
                                <a href="CategoriaServlet?accion=eliminar&id=<%=cat.getIdcategoria()%>" class="btn btn-outline-danger">Eliminar</a>
                            </td>
                            <td>
                                <a href="CategoriaServlet?accion=actualizar&id=<%=cat.getIdcategoria()%>" class="btn btn-outline-info">Actualizar</a>
                            </td>
                        </tr>
                <%}%>
            </table>
        </div>
    </div>
</div>
</body>
</html>