<%-- 
    Document   : index
    Created on : 1/11/2022, 11:09:37
    Author     : nacho
--%>
<%@page import="Modelo.Producto"%>
<%@page import="Modelo.Proveedor" %>
<%@page import="Modelo.Compras" %>
<%@page import="java.util.HashMap" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page import="java.time.LocalDate" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compras</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    </head>
    <body>
    <h1>Formulario Compras</h1>
    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modalAgregar" onclick="limpiar()">Nuevo</button>
    <button name="btn_proveedores" id="btn_proveedores" class="btn btn-warning btn-lg" onclick="javascript:location.href='proveedores.jsp'">CRUD Proveedores</button>

        <div class="container">
       
    <div class="modal fade" id="modalAgregar" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-body">
           <form action="sr_compra" method="post" class="form-group">
                    
                    <label for="lbl_id" class=" col-form-label-lg"><b>ID Compra:</b></label>
                    <input type="number" name="txt_id" id="txt_id" class="form-control"  value="0" readonly>
                    
                    <label for="lbl_no_orden_compra" class=" col-form-label-lg"><b>No. Orden de Compra:</b></label>
                    <input type="number" name="txt_no_orden_compra" id="txt_no_orden_compra" class="form-control" value="<% Compras valueCompra = new Compras(); out.println(valueCompra.getLastOrder());%>" placeholder="01" required>
                    
                    <label for="lbl_proveedor" class=" col-form-label-lg"><b>Proveedor:</b></label>
                    <select name="drop_proveedor" id="drop_proveedor" class="form-control" >
                     <%
                    Proveedor proveedor = new Proveedor();
                    HashMap<String, String> drop = proveedor.drop_proveedor();
                    for (String i: drop.keySet()){
                      out.println(" <option value='"+ i +"'>"+drop.get(i)+"</option>");
                        }
                    %>
                    </select>
                      
                    <label for="lbl_producto" class=" col-form-label-lg"><b>Producto:</b></label>
                    <select name="drop_producto" id="drop_producto" class="form-control" >
                     <%
                    Producto producto = new Producto();
                    HashMap<String, String> drops = producto.drop_producto();
                    for (String i: drops.keySet()){
                      out.println(" <option value='"+ i +"'>"+drops.get(i)+"</option>");
                        }
                    %>
                    </select>
                   
                    <label for="lbl_cantidad" class=" col-form-label-lg"><b>Cantidad:</b></label>
                    <input type="text" name="txt_cantidad" id="txt_cantidad" class="form-control" placeholder="000000" required>
                                          
                    <label for="lbl_precio_costo_unitario" class=" col-form-label-lg" ><b>Precio (Costo Unitario):</b></label>
                    <input type="text" name="txt_precio_costo_unitario" id="txt_precio_costo_unitario" class="form-control" required>
                    
                    <label for="lbl_fecha_orden" class=" col-form-label-lg"><b>Fecha de Orden de Compra:</b></label>
                    <input type="date" name="txt_fecha_orden" id="txt_fecha_orden" class="form-control" required>
                    
                   <label for="lbl_fecha_ingreso" class=" col-form-label-lg"><b>Fecha de Ingreso:</b></label>
                    <input type="date" name="txt_fecha_ingreso" id="txt_fecha_ingreso" class="form-control" required>                  

                <br>
                 <button name="btn_agregar" id="btn_agregar"  value="agregar" class="btn btn-primary btn-lg">Agregar</button>
                                               
            </form>
            </div>
      </div>  
    </div>
  </div>
            
            
             <table class="table table-striped">
                  <thead>
                    <tr>
                        <th>No. Orden de Compra</th>
                        <th>Proveedor</th>
                        <th>Producto</th>
                        <th>Cantidad</th>
                        <th>Precio (Costo Unitario)</th>
                        <th>Fecha Orden de Compra</th>   
                        <th>Fecha Ingreso</th>
                         <!--<th>Acciones</th>-->
                    </tr>
                </thead>
                
                <tbody id="tbl_compras" >
                    <%
                        Compras compras = new Compras();
                        DefaultTableModel tabla = new DefaultTableModel();
                        tabla = compras.leer();
                        for (int t = 0; t < tabla.getRowCount(); t++) {
                            out.println("<tr data-id='" + tabla.getValueAt(t, 0) + "' data-id_pv='" + tabla.getValueAt(t, 1) + "' data-id_pd='" + tabla.getValueAt(t, 2)+ "' data-id_cd='" + tabla.getValueAt(t, 3) + "'>");
                            out.println("<td>" + tabla.getValueAt(t, 0) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 5) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 6) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 7) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 8) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 9) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 10) + "</td>");
                            //out.println("<td> <a   class='btn btn-danger eliminar' data-id='"+tabla.getValueAt(t, 0)+"' type='button' >Eliminar</button></td>");
                            out.println("</tr>");
                        }
                    %>

                </tbody>
            </table>

        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
        <script type="text/javascript">
            
  
                                 function limpiar() {
                                 $("#txt_id").val(0);
                                 $("#txt_no_orden_compra").val('');
                                 $("#drop_proveedor").val(1);
                                 $("#drop_producto").val(1);
                                 $("#txt_cantidad").val('');
                                 $("#txt_precio_costo_unitario").val('');
                                 $("#txt_fecha_orden").val('');
                                 $("#txt_fecha_ingreso").val('');     
                                  $("#btn_agregar").text("Guardar");
                                 }

                                 
                                $('#tbl_compras').on('click', 'tr td', function(evt) {
                                    var target, id, id_proveedor, id_producto, no_orden_compra, cantidad, precio_unitario, fecha_orden, fecha_ingreso;
                                    target = $(event.target);
                                    id = target.parent().data('id');
                                    id_proveedor = target.parent().data('id_pv');
                                    id_producto = target.parent().data('id_pd');
                                    no_orden_compra = target.parent("tr").find("td").eq(0).html();
                                    cantidad = target.parent("tr").find("td").eq(3).html();
                                    precio_unitario = target.parent("tr").find("td").eq(4).html();
                                    fecha_orden = target.parent("tr").find("td").eq(5).html();
                                    fecha_ingreso = target.parent("tr").find("td").eq(6).html().split(" ");
                                     $("#btn_agregar").text("Actualizar");
                                    
                                    $("#txt_id").val(id);
                                    $("#txt_no_orden_compra").val(no_orden_compra);
                                    $("#drop_proveedor").val(id_proveedor);
                                    $("#drop_producto").val(id_producto);
                                    $("#txt_cantidad").val(cantidad);
                                    $("#txt_precio_costo_unitario").val(precio_unitario);
                                    $("#txt_fecha_orden").val(fecha_orden);
                                    $("#txt_fecha_ingreso").val(fecha_ingreso[0]);
                                    $("#modalAgregar").modal('show');
                                });

        </script>
    </body>
</html>
