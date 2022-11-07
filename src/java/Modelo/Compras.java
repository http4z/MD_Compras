package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

public class Compras extends Persona {
    private int id_proveedor;
    private int id_producto;
    Conexion cn;
  
    public Compras() {
    }

    public Compras(int id, int id_proveedor, int id_producto, String no_orden_compra, String cantidad, String precio_costo_unitario, String fecha_orden, String fecha_ingreso){
        super(id, no_orden_compra,cantidad, precio_costo_unitario,fecha_orden,fecha_ingreso);
        this.id_producto = id_producto;
        this.id_proveedor = id_proveedor;
    }

       public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
    
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT c.id_compra as id, pv.id_proveedor, cd.id_producto, cd.id_compra_detalle, c.no_orden_compra, pv.proveedor, pd.producto, cd.cantidad, cd.precio_costo_unitario, c.fecha_orden, c.fecha_ingreso\n" +
                " FROM db_compras.compras as c \n" +
                " left join db_compras.compras_detalle as cd on c.id_compra = cd.id_compra \n" +
                " left join db_compras.proveedores as pv on c.id_proveedor = pv.id_proveedor \n" +
                " left join db_compras.productos as pd on cd.id_producto = pd.id_producto;"; 
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            String encabezado[] = {"id", "id_proveedor", "id_producto", "id_compra_detalle", "no_orden_compra", "proveedor", "producto", "cantidad", "precio_costo_unitario", "fecha_orden", "fecha_ingreso"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[11];
            
            while (consulta.next()) {
                
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("id_proveedor");
                datos[2] = consulta.getString("id_producto");
                datos[3] = consulta.getString("id_compra_detalle"); 
                datos[4] = consulta.getString("no_orden_compra");
                datos[5] = consulta.getString("proveedor");
                datos[6] = consulta.getString("producto");
                datos[7] = consulta.getString("cantidad");
                datos[8] = consulta.getString("precio_costo_unitario");
                datos[9] = consulta.getString("fecha_orden");
                datos[10] = consulta.getString("fecha_ingreso");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tabla;
    }
    
    public int getLastOrder(){
        int value=0;
        try{
            String query = "SELECT COUNT(*) as contador FROM db_compras.compras;";
            cn = new Conexion();
            cn.abrir_conexion();
            ResultSet result = cn.conexionDB.createStatement().executeQuery(query);
            if(result.next()){
                value=result.getInt("contador")+1;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        
        return value;
    }

    public int agregar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            PreparedStatement parametroDetalle;
             cn = new Conexion();
             cn.abrir_conexion();
             System.out.println("ID "+getId());
            if(getId()!=0){              
                String query ="UPDATE db_compras.compras set id_proveedor=? , fecha_orden=? , fecha_ingreso=? where id_compra=?;";
                parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
                parametro.setInt(1, getId_proveedor());
                parametro.setString(2, getFecha_orden());
                parametro.setString(3, getFecha_ingreso());   
                parametro.setInt(4, getId());  
                retorno = parametro.executeUpdate();
                String queryDetail="UPDATE db_compras.compras_detalle set id_producto=? , cantidad=?, precio_costo_unitario=? where id_compra=?;";
                parametroDetalle = (PreparedStatement) cn.conexionDB.prepareStatement(queryDetail);
                parametroDetalle.setInt(1, getId_producto());
                parametroDetalle.setString(2, getCantidad());
                parametroDetalle.setString(3, getPrecio_costo_unitario());
                parametroDetalle.setInt(4, getId());  
               retorno = parametroDetalle.executeUpdate();
            }
            else{
                 String query = "INSERT INTO db_compras.compras(id_proveedor, no_orden_compra, fecha_orden, fecha_ingreso) values(?,?,?,?);"; 
                String querys = "INSERT INTO db_compras.compras_detalle(id_compra, id_producto, cantidad, precio_costo_unitario) values(?,?,?,?);";

                parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query,new String[] {"id_compra"} );
                parametro.setInt(1, getId_proveedor());
                parametro.setString(2, getNo_orden_compra());
                parametro.setString(3, getFecha_orden());
                parametro.setString(4, getFecha_ingreso());                 
               
                retorno = parametro.executeUpdate();
                ResultSet rs = parametro.getGeneratedKeys();
                if (rs.next()) {
                   int orden = rs.getInt(1);
                    setId(orden);
                }
                
                System.out.println("Id de orden "+retorno);
                
                parametroDetalle = (PreparedStatement) cn.conexionDB.prepareStatement(querys);
                parametroDetalle.setInt(1,getId());
                parametroDetalle.setInt(2, getId_producto());
                parametroDetalle.setString(3, getCantidad());
                parametroDetalle.setString(4, getPrecio_costo_unitario());
                 retorno = parametroDetalle.executeUpdate();
            }
           
           
             
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        finally{
            cn.cerrar_conexion();
        }
        return retorno;
    }   

}
