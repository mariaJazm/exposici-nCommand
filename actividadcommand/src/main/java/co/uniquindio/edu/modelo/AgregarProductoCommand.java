package co.uniquindio.edu.modelo;

public class AgregarProductoCommand implements Command {
    
    private Inventario inventario;
    private Producto producto;

    public AgregarProductoCommand(Inventario inventario, Producto producto) {
        this.inventario = inventario;
        this.producto = producto;
    }

    @Override
    public void execute() {
        inventario.agregarProducto(producto);
    }

}
