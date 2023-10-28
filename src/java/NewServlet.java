/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Clases.Producto;
import Clases.ProductoController;
import Clases.ConexionBaseDeDatos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 
 */
@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {
    Producto producto;
    ProductoController registroProducto;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try ( PrintWriter respuesta = response.getWriter()) {    
            respuesta.write("");
           registroProducto = new ProductoController();
           String control = request.getParameter("control");
           StringBuffer objetoRespuesta = new StringBuffer();
           if(control.toUpperCase().equals("GUARDAR")){
            //se crea el objeto alumnos con los datos recibidos del navegador a traves de la petición HTTP
               producto = new Producto(
                Integer.parseInt(request.getParameter("codigo")),
                request.getParameter("nombre"),
                Integer.parseInt(request.getParameter("precio")),
                Integer.parseInt(request.getParameter("existencia")),
                Integer.parseInt(request.getParameter("marca")),
                Integer.parseInt(request.getParameter("categoria")));                
                registroProducto.guardarProducto(producto);//almacenarlo en BD                 
           }else if(control.toUpperCase().equals("ELIMINAR")){
               int codigoEliminar= Integer.parseInt(request.getParameter("codigo_producto"));
               registroProducto.eliminarProducto(codigoEliminar);
           }
                  
           registroProducto.getProductos(objetoRespuesta);//consultar alumnos en la BD
           respuesta.write(objetoRespuesta.toString());             
             
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
