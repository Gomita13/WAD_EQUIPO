package com.ipn.mx.utils;

public class HTMLUtils {

    public static String HTML_HEAD = "<head>\n" +
            "<title>CRUD de carreras</title>\n" +
            "<meta charset=\"UTF-8\">\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
            "<link href=\"./styles.css\" rel=\"stylesheet\">\n" +
            "<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\n" +
            "<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>\n" +
            "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>\n" +
            "<link href=\\\"https://fonts.googleapis.com/icon?family=Material+Icons\\\"\\n\" + \"rel=\\\"stylesheet\\\">"+
            "<!-- Google fonts-->\n" +
            "    <link href=\"https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\" />\n" +
            "    <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800\" rel=\"stylesheet\" type=\"text/css\" />\n" +
            "    <link href=\"./styles.css\" rel=\"stylesheet\" />\n" +
            "</head>";

    public static String HTML_NAV = "<nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\n" +
            "<div class=\"container px-4 px-lg-5\">\n" +
            "<a class=\"navbar-brand\" href=\"index.html\">Proyecto Base</a>\n" +
            "<button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
            "<span class=\"navbar-toggler-icon\"></span>\n" +
            "</button>\n" +
            "<div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
            "<ul class=\"navbar-nav ms-auto py-4 py-lg-0\">\n" +
            "<li class=\"nav-item\">\n" +
            "<a class=\"nav-link px-lg-3 py-3 py-lg-4\" href=\"./carrera/nuevo.html\">Carrera</a>\n" +
            "</li>\n" +
            "<li class=\"nav-item\">\n" +
            "<a class=\"nav-link px-lg-3 py-3 py-lg-4\" href=\"CarreraServlet?accion=1\">Lista de Carreras</a>\n" +
            "</li>\n" +
            "<li class=\"nav-item\">\n" +
            "<a class=\"nav-link px-lg-3 py-3 py-lg-4\" href=\"./alumno/nuevo.html\">Alumno</a>\n" +
            "</li>\n" +
            "<li class=\"nav-item\">\n" +
            "<a class=\"nav-link px-lg-3 py-3 py-lg-4\" href=\"AlumnoServlet?accion=1\">Lista de Alumnos</a>\n" +
            "</li>\n" +
            "</ul>\n" +
            "</div>\n" +
            "</div>\n" +
            "</nav>";

}
