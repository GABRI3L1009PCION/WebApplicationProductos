/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author
 */
public class ProductoController {

    private ConexionBaseDeDatos conectorBD;
    private Connection conexion;
    private PreparedStatement statement = null;
    private ResultSet result = null;

    public void abrirConexion(){
        conectorBD= new ConexionBaseDeDatos();
        conexion = conectorBD.conectar();
    }       
    
    public String guardarProducto(Producto producto){        
        String sql = "INSERT INTO producto(codigo_producto, nombre_producto, precio, existencia, id_marca, id_categoria)";
             sql += " VALUES(?,?,?,?,?,?)";              
       try{     
            abrirConexion();
            statement = conexion.prepareStatement(sql); 
            statement.setInt(1, producto.getCodigo());
            statement.setString(2, producto.getNombre());
            statement.setInt(3, producto.getPrecio());
            statement.setInt(4, producto.getExistencia());
            statement.setInt(5, producto.getMarca());
            statement.setInt(6, producto.getCategoria());
                int resultado = statement.executeUpdate(); 
                if(resultado > 0){
                    return String.valueOf(resultado);
                }else{
                    return String.valueOf(resultado);
                }
        }catch(SQLException e){ 
            return e.getMessage();
        }
    }
    
    public void getProductos(StringBuffer respuesta){   
        String sql="SELECT producto.codigo_producto, producto.nombre_producto, producto.precio, producto.existencia, marca.descripcion, categoria.descripcion FROM producto, marca, categoria WHERE producto.id_marca = marca.id_marca AND producto.id_categoria = categoria.id_categoria";
        try{
        abrirConexion();
        statement= conexion.prepareStatement(sql);                        
        result = statement.executeQuery();            
            if(result!=null){
                while (result.next()){
                respuesta.append("<tr>");//tr crea la fila y la etiqueta td son las columnas
                respuesta.append("<td >").append(result.getString("codigo_producto")).append("</td>");
                respuesta.append("<td >").append(result.getString("nombre_producto")).append("</td>");
                respuesta.append("<td >").append("Q ").append(result.getString("precio")).append("</td>");
                respuesta.append("<td >").append(result.getString("existencia")).append("</td>");
                respuesta.append("<td >").append(result.getString("descripcion")).append("</td>");
                respuesta.append("<td >").append(result.getString("categoria.descripcion")).append("</td>");
                respuesta.append("<td id=\"").append(result.getString("codigo_producto"))
                        .append("\"  onclick=\"eliminarProducto(this.id);\">")
                        .append(" <a class=\"btn btn-warning\"'><i class=\"fas fa-edit\"></i>  </a>"
                                +" <a class=\"btn btn-danger\"'> <i class=\"fas fa-trash-alt\"></i> </a>"
                                + " <td></tr>");
                }
            }else{
                respuesta.append("error al consultar");
            }           
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public String eliminarProducto(int codigo){        
        String sql = "DELETE FROM producto WHERE codigo_producto = " + codigo;              
       try{     
            abrirConexion();
            statement = conexion.prepareStatement(sql); 
            int resultado = statement.executeUpdate();
            if(resultado > 0){
                return String.valueOf(resultado);
            }else{
                return String.valueOf(resultado);
            }
        }catch(SQLException e){ 
            return e.getMessage();
        }
    }
    
}


