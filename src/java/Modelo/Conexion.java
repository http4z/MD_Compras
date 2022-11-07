/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nacho
 */
public class Conexion {
    public Connection conexionDB;
    
    public final String db = "db_compras";
    public final String urlConexion = String.format ("jdbc:mysql://localhost:3306/%s", db);
    public final String usuario = "root";
    public final String contra = "root";
    public final String jdbc = "com.mysql.cj.jdbc.Driver";
    
    public void abrir_conexion(){
    try{
        Class.forName(jdbc);
        conexionDB = DriverManager.getConnection(urlConexion, usuario, contra);
       
    } catch(HeadlessException | ClassNotFoundException | SQLException ex) {
        System.out.println("No se pudo conectar a la base de datos..." + ex.getMessage());
    }
}
    public void cerrar_conexion(){
         try{
       conexionDB.close();
    } catch(HeadlessException | SQLException ex) {
        System.out.println("No se pudo cerrar la conexion a la base de datos..." + ex.getMessage());
    }
    }
   
}
