package co.uniquindio.edu.modelo;

public class EliminarProductoCommand implements Command{
    
    private Inventario inventario;
    private Producto producto;

    public EliminarProductoCommand(Inventario inventario, Producto producto) {
        this.inventario = inventario;
        this.producto = producto;
    }

    @Override
    public void execute() {
        inventario.eliminarProducto(producto);
    }

}



