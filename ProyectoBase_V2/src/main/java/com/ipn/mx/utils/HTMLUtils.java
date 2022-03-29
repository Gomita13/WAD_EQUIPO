package com.ipn.mx.utils;

public class HTMLUtils {

    public static String HTML_HEAD = "<head>\n" +
            "<title>CRUD de carreras</title>\n" +
            "<meta charset=\"UTF-8\">\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
            "<link href=\"./css/global.css\" rel=\"stylesheet\">\n" +
            "<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\n" +
            "<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>\n" +
            "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>\n" +
            "</head>";

    public static String HTML_NAV = "<nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\n" +
            "<div class=\"container-fluid\">\n" +
            "<a class=\"navbar-brand\" href=\"index.html\">Carrera Web</a>\n" +
            "<button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
            "<span class=\"navbar-toggler-icon\"></span>\n" +
            "</button>\n" +
            "<div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n" +
            "<ul class=\"navbar-nav me-auto mb-2 mb-lg-0\">\n" +
            "<li class=\"nav-item\">\n" +
            "<a class=\"nav-link active\" href=\"./carrera/nuevo.html\">Agregar Carrera</a>\n" +
            "</li>\n" +
            "<li class=\"nav-item\">\n" +
            "<a class=\"nav-link active\" href=\"CarreraServlet?accion=1\">Listar Carreras</a>\n" +
            "</li>\n" +
            "<li class=\"nav-item\">\n" +
            "<a class=\"nav-link active\" href=\"./alumno/nuevo.html\">Agregar Alumno</a>\n" +
            "</li>\n" +
            "<li class=\"nav-item\">\n" +
            "<a class=\"nav-link active\" href=\"ListadoAlumnoServlet\">Listar Alumnos</a>\n" +
            "</li>\n" +
            "</ul>\n" +
            "</div>\n" +
            "</div>\n" +
            "</nav>";

}
