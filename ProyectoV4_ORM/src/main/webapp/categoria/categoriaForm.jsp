<%@ page import="com.ipn.mx.modelo.entidades.Categoria" %><%--
  Created by IntelliJ IDEA.
  User: sadaga
  Date: 08/04/2022
  Time: 01:28 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Categoría Form</title>
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
    <div class="card text-primary">
        <div class="card-header">
            <h1 class="card-title  text-center">
                Formulario para Datos de la Categoría
            </h1>
        </div>
        <div class="card-body">
            <% Categoria cat = (Categoria) request.getAttribute("categoria");%>
            <form method="post" action="<%= cat == null ? "../CategoriaServlet" : "CategoriaServlet"%>">
                <input type="hidden" name="accion" value="guardar">
                <div class="mb-3">
                    <label for="txtId" class="form-label">Clave Categor&iacute;a</label>
                    <input type="text" class="form-control" id="txtId" name="id"
                           value="<%=cat != null ? cat.getIdcategoria() : ""%>"
                           placeholder="Nombre de la Categoría"

                    />
                </div>

                <div class="mb-3">
                    <label for="txtNombre" class="form-label">Nombre Categor&iacute;a</label>
                    <input type="text" class="form-control" id="txtNombre" name="nombre"
                           value="<%=cat != null ? cat.getNombrecategoria() : ""%>"
                           placeholder="Nombre de la Categoría"
                           required
                    />
                </div>

                <div class="mb-3">
                    <label for="txtDescripcion" class="form-label"> Descripción de la Categoría</label>
                    <input type="text" name="descripcion" id="txtDescripcion"
                           class="form-control"
                           value="<%=cat != null ? cat.getDescripcioncategoria() : ""%>"
                           placeholder="Descripción de la Categoría"
                           required/>
                </div>
                <div class="mb-3">

                    <input type="submit" name="btnEnviar" id="btnEnviar"
                           class="btn-outline-primary"
                           value="Registrar"
                    />
                </div>

            </form>
        </div>
    </div>

</div>
</body>
</html>