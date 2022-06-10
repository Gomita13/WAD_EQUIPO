package com.ipn.mx.controlador;

import com.google.gson.Gson;
import com.ipn.mx.modelo.dao.PersonaDAO;
import com.ipn.mx.modelo.dao.ProyectoDAO;
import com.ipn.mx.modelo.dao.TareaDAO;
import com.ipn.mx.modelo.entidades.Persona;
import com.ipn.mx.modelo.entidades.Proyecto;
import com.ipn.mx.modelo.entidades.Tarea;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ServletPersona", value = "/ServletPersona")
public class ServletPersona extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "reporte":
                    mostrarReporte(request, response);
                    break;
                case "cuenta":
                    formCuenta(request, response);
                    break;
                case "logout":
                    request.getSession().invalidate();
                    response.sendRedirect("index.jsp");
                    break;
                case "generarReporte":
                    generarReporte(request, response);
                    break;
                default:
                    mostrarDashboard(request, response);
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("accion" != null) {
            switch (accion) {
                case "insertar":
                    this.insertarPersona(request, response);
                    break;
                case "login":
                    this.login(request, response);
                    break;
                case "cuenta":
                    actualizarCuenta(request, response);
                    break;
                default:
                    mostrarDashboard(request, response);
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private void insertarPersona(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String password = request.getParameter("password");
        Persona persona = new Persona(email, nombre, apellidos, password);
        // Insertamos en la base de datos
        int registrosModificados = new PersonaDAO().insert(persona);
        System.out.println("Registros modificados " + registrosModificados);
        response.sendRedirect("index.jsp");
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Persona persona = new Persona(email, password);
        PersonaDAO personaDAO = new PersonaDAO();
        if(personaDAO.login(persona)) {
            Persona personaRes = personaDAO.selectOne(persona);
            HttpSession session = request.getSession(true);
            session.setAttribute("nombre", personaRes.getNombre());
            session.setAttribute("apellidos", personaRes.getApellidos());
            session.setAttribute("email", personaRes.getEmail());
            System.out.println("Felicidades " + personaRes.getNombre() + " iniciaste sesión kjaja");
            this.mostrarDashboard(request, response);
        } else {
            System.out.println("mamaste");
            response.sendRedirect("index.jsp");
        }
    }

    public static void mostrarDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String nombre = (String) session.getAttribute("nombre");
        String apellidos = (String) session.getAttribute("apellidos");
        Persona persona = new Persona(email, nombre, apellidos);
        List<Tarea> tareas = new TareaDAO().selectTareasEncargado(persona);
        List<Proyecto> proyectos = new ProyectoDAO().selectAll(persona);
        List<Proyecto> proximosProyectos = ServletProyecto.calcularProyectos(proyectos);
        request.setAttribute("tareas", tareas);
        request.setAttribute("proximosProyectos", proximosProyectos);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }

    private static void mostrarReporte (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Persona persona = new PersonaDAO().selectOne(new Persona(email));
        List<Tarea> tareas = new TareaDAO().selectTareasEncargado(persona);
        List<Proyecto> proyectos = new ProyectoDAO().selectAll(persona);
        List<Proyecto> proyectosActuales = ServletProyecto.proyectosActuales(proyectos);
        String datosGrafica = generarDatosGrafica(tareas);
        request.setAttribute("persona", persona);
        request.setAttribute("tareas", tareas);
        request.setAttribute("proyectos", proyectosActuales);
        request.setAttribute("datosGrafica", datosGrafica);
        request.getRequestDispatcher("reporte.jsp").forward(request, response);
    }

    private static String generarDatosGrafica(List<Tarea> tareas) {
        Gson gsonObj = new Gson();
        Map<Object,Object> map = null;
        List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
        List<Tarea> tareasCompletadas = ServletTarea.getTareasCompletadas(tareas);
        List<Tarea> tareasIncompletas = ServletTarea.getTareasIncompletas(tareas);
        map = new HashMap<Object,Object>(); map.put("label", "Asignadas"); map.put("y", tareas.size()); list.add(map);
        map = new HashMap<Object,Object>(); map.put("label", "Completadas"); map.put("y", tareasCompletadas.size()); list.add(map);
        map = new HashMap<Object,Object>(); map.put("label", "Incompletas"); map.put("y", tareasIncompletas.size()); list.add(map);
        return gsonObj.toJson(list);
    }

    private static void formCuenta (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        Persona persona = new PersonaDAO().selectOne(new Persona(email));
        request.setAttribute("persona", persona);
        request.getRequestDispatcher("account.jsp").forward(request, response);
    }

    private static void actualizarCuenta (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emailOld = request.getParameter("emailPersona");
        String email = request.getParameter("email");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String password = request.getParameter("password");
        int registros = new PersonaDAO().update(new Persona(email, nombre, apellidos, password), emailOld);
        System.out.println("Registros modificados " + registros);
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("nombre", nombre);
        session.setAttribute("apellidos", apellidos);
        formCuenta(request, response);
    }

    private void generarReporte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        if(email == null) {
            response.sendRedirect("index.jsp");
        }
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        String emailPersona = request.getParameter("nombrePersona");
        Persona persona = new PersonaDAO().selectOne(new Persona(emailPersona));
        try {
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, out);
                document.open();
                document.add(tituloReporte(persona));
                com.itextpdf.text.List proyectosActualesPdf = proyectosActualesPdf(document, persona);
                document.add(proyectosActualesPdf);
                document.add(new Phrase(Chunk.NEWLINE));
                com.itextpdf.text.List proyectosProximosPdf = proyectosProximosPdf(document, persona);
                document.add(proyectosProximosPdf);
                document.add(new Phrase(Chunk.NEWLINE));
                com.itextpdf.text.List proyectosVencidosPdf = proyectosVencidosPdf(document, persona);
                document.add(proyectosVencidosPdf);
                document.add(new Phrase(Chunk.NEWLINE));
                com.itextpdf.text.List tareasPendientesPdf = tareasPendientesPdf(document, persona);
                document.add(tareasPendientesPdf);
                document.add(new Phrase(Chunk.NEWLINE));
                com.itextpdf.text.List tareasCompletadasPdf = tareasCompletadasPdf(document, persona);
                document.add(tareasCompletadasPdf);
                document.add(new Phrase(Chunk.NEWLINE));
                document.close();
            } catch (Exception ex) {
                ex.getMessage();
            }
        } finally {
            out.close();
        }
    }

    private Paragraph tituloReporte(Persona persona) {
        Paragraph titulo = new Paragraph();
        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
        titulo.add(new Phrase("Reporte de " + persona.getNombre() + " " + persona.getApellidos(), fontTitulo));
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.add(new Phrase(Chunk.NEWLINE));
        titulo.add(new Phrase(Chunk.NEWLINE));
        return titulo;
    }

    private com.itextpdf.text.List proyectosActualesPdf(Document document, Persona persona) throws DocumentException {
        com.itextpdf.text.List proyectosActualesPdf = new com.itextpdf.text.List();
        Paragraph titulo = new Paragraph();
        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
        titulo.add(new Phrase("Proyectos actuales", fontTitulo));
        titulo.add(new Phrase(Chunk.NEWLINE));
        document.add(titulo);
        List<Proyecto> proyectos = new ProyectoDAO().selectAll(persona);
        List<Proyecto> proyectosActuales = ServletProyecto.proyectosActuales(proyectos);
        for(Proyecto proyecto: proyectosActuales) {
            String infoProyecto;
            if(Double.isNaN(proyecto.getProgreso())) {
                infoProyecto = proyecto.getNombreProyecto() + " Inicio " + proyecto.getInicio() + ", fin " +
                        proyecto.getFin() + ", progreso: 0%";
            } else {
                infoProyecto = proyecto.getNombreProyecto() + " Inicio " + proyecto.getInicio() + ", fin " +
                        proyecto.getFin() + ", progreso: " + proyecto.getProgreso() + "%";
            }
            proyectosActualesPdf.add(infoProyecto);
        }
        return proyectosActualesPdf;
    }

    private com.itextpdf.text.List proyectosProximosPdf(Document document, Persona persona) throws DocumentException {
        com.itextpdf.text.List proyectosProximosPdf = new com.itextpdf.text.List();
        Paragraph titulo = new Paragraph();
        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
        titulo.add(new Phrase("Próximos proyectos", fontTitulo));
        titulo.add(new Phrase(Chunk.NEWLINE));
        document.add(titulo);
        List<Proyecto> proyectos = new ProyectoDAO().selectAll(persona);
        List<Proyecto> proyectosProximos = ServletProyecto.proximosProyectos(proyectos);
        for(Proyecto proyecto: proyectosProximos) {
            String infoProyecto = proyecto.getNombreProyecto() + " Inicia el " + proyecto.getInicio();
            proyectosProximosPdf.add(infoProyecto);
        }
        return proyectosProximosPdf;
    }

    private com.itextpdf.text.List proyectosVencidosPdf(Document document, Persona persona) throws DocumentException {
        com.itextpdf.text.List proyectosVencidosPdf = new com.itextpdf.text.List();
        Paragraph titulo = new Paragraph();
        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
        titulo.add(new Phrase("Proyectos vencidos", fontTitulo));
        titulo.add(new Phrase(Chunk.NEWLINE));
        document.add(titulo);
        List<Proyecto> proyectos = new ProyectoDAO().selectAll(persona);
        List<Proyecto> proyectosVencidos = ServletProyecto.proyectosVencidos(proyectos);
        for(Proyecto proyecto: proyectosVencidos) {
            String infoProyecto = proyecto.getNombreProyecto() + " venció el " + proyecto.getFin();
            proyectosVencidosPdf.add(infoProyecto);
        }
        return proyectosVencidosPdf;
    }

    private com.itextpdf.text.List tareasPendientesPdf(Document document, Persona persona) throws DocumentException {
        com.itextpdf.text.List tareasPendientesPdf = new com.itextpdf.text.List();
        Paragraph titulo = new Paragraph();
        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
        titulo.add(new Phrase("Tareas pendientes", fontTitulo));
        titulo.add(new Phrase(Chunk.NEWLINE));
        document.add(titulo);
        List<Tarea> tareas = new TareaDAO().selectTareasEncargado(persona);
        List<Tarea> tareasPendientes = ServletTarea.getTareasIncompletas(tareas);
        for(Tarea tarea: tareasPendientes) {
            tareasPendientesPdf.add(tarea.getNombreTarea() + "\n" + tarea.getDescripcion());
        }
        return tareasPendientesPdf;
    }

    private com.itextpdf.text.List tareasCompletadasPdf(Document document, Persona persona) throws DocumentException {
        com.itextpdf.text.List tareasCompletadasPdf = new com.itextpdf.text.List();
        Paragraph titulo = new Paragraph();
        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
        titulo.add(new Phrase("Tareas completadas", fontTitulo));
        titulo.add(new Phrase(Chunk.NEWLINE));
        document.add(titulo);
        List<Tarea> tareas = new TareaDAO().selectTareasEncargado(persona);
        List<Tarea> tareasCompletadas = ServletTarea.getTareasIncompletas(tareas);
        for(Tarea tarea: tareasCompletadas) {
            tareasCompletadasPdf.add(tarea.getNombreTarea() + "\n" + tarea.getDescripcion());
        }
        return tareasCompletadasPdf;
    }
}