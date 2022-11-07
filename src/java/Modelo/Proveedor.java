/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
/**
 *
 * @author nacho
 */
public class Proveedor {
    private int id_proveedor;
    private String proveedor;
    private Conexion cn;
    
    public Proveedor(){}
    public Proveedor(int id_proveedor, String proveedor) {
        this.id_proveedor = id_proveedor;
        this.proveedor = proveedor;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
    public HashMap drop_proveedor(){
        HashMap<String, String> drop=new HashMap();
        try{
            cn=new Conexion();
            String query = "SELECT id_proveedor as id, proveedor FROM proveedores;";
             cn.abrir_conexion();
             ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
             while (consulta.next()){
                 drop.put(consulta.getString("id"), consulta.getString("proveedor"));
             }
             cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return drop;
    }  
    
}
