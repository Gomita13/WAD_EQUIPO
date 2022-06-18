package com.ipn.mx.controlador;

import com.ipn.mx.modelo.entidades.Categoria;

import javax.persistence.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CategoriaServlet", value = "/CategoriaServlet")
public class CategoriaServlet extends HttpServlet {

    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    EntityTransaction transaction;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if(accion.equals("listadoCategorias")){
            listar(request,response);
        }else if (accion.equals("eliminar")){
            eliminar(request,response,Integer.parseInt(request.getParameter("id")));
        }else if (accion.equals("ver")){
            ver(request,response,Integer.parseInt(request.getParameter("id")));
        }else if (accion.equals("actualizar")){
            preactualizar(request,response,Integer.parseInt(request.getParameter("id")));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        int id = strId.equals("") ? 0 : Integer.parseInt(strId);
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String accion = request.getParameter("accion");
        if (id == 0){
            guardar(request,response,nombre,descripcion);
        }else{
            actualizar(request,response,id,nombre,descripcion);
        }
    }

    private void inicializarPersistencia(){
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    private void listar(HttpServletRequest request, HttpServletResponse response){
        inicializarPersistencia();
        try{
            transaction.begin();
            TypedQuery<Categoria> categorias = entityManager.createNamedQuery("Categoria.readAll",Categoria.class);
            request.setAttribute("listado",categorias.getResultList());
            transaction.commit();
            request.getRequestDispatcher("/categoria/listaDeCategorias.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response, int id, String nombre, String descripcion){
        inicializarPersistencia();
        try{
            transaction.begin();
            Categoria cat = entityManager.find(Categoria.class,id);
            cat.setNombrecategoria(nombre);
            cat.setDescripcioncategoria(descripcion);
            transaction.commit();
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response, int id){
        inicializarPersistencia();
        try{
            transaction.begin();
            Categoria cat = entityManager.find(Categoria.class,id);
            entityManager.remove(cat);
            transaction.commit();
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    private void ver(HttpServletRequest request, HttpServletResponse response, int id) {
        inicializarPersistencia();
        try{
            transaction.begin();
            Categoria cat = entityManager.find(Categoria.class,id);
            request.setAttribute("categoria",cat);
            transaction.commit();
            request.getRequestDispatcher("/categoria/verCategorias.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response, String nombre, String descripcion) {
        inicializarPersistencia();
        try{
            transaction.begin();
            Categoria cat = new Categoria();
            cat.setDescripcioncategoria(descripcion);
            cat.setNombrecategoria(nombre);
            entityManager.persist(cat);
            transaction.commit();
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    private void preactualizar(HttpServletRequest request, HttpServletResponse response, int id) {
        inicializarPersistencia();
        try{
            transaction.begin();
            Categoria cat = entityManager.find(Categoria.class,id);
            request.setAttribute("categoria",cat);
            transaction.commit();
            request.getRequestDispatcher("/categoria/categoriaForm.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
