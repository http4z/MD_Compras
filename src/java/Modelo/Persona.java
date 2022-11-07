
package Modelo;


 abstract public class Persona {
    private int id;
    private String no_orden_compra, cantidad, precio_costo_unitario, fecha_orden, fecha_ingreso;
    
    public Persona(){}
    public Persona(int id, String no_orden_compra, String cantidad, String precio_costo_unitario, String fecha_orden, String fecha_ingreso) {
        this.id = id;

        this.no_orden_compra = no_orden_compra;
        this.cantidad = cantidad;
        this.precio_costo_unitario = precio_costo_unitario;
        this.fecha_orden = fecha_orden;
        this.fecha_ingreso = fecha_ingreso;
    }      

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNo_orden_compra() {
        return no_orden_compra;
    }

    public void setNo_orden_compra(String no_orden_compra) {
        this.no_orden_compra = no_orden_compra;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio_costo_unitario() {
        return precio_costo_unitario;
    }

    public void setPrecio_costo_unitario(String precio_costo_unitario) {
        this.precio_costo_unitario = precio_costo_unitario;
    }

    public String getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(String fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
    
    public int crear(){return 0;}
    public int modificar(){return 0;}
    public int eliminar(){return 0;}
}

