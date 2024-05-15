package co.uniquindio.edu.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.edu.modelo.AgregarProductoCommand;
import co.uniquindio.edu.modelo.Command;
import co.uniquindio.edu.modelo.EliminarProductoCommand;
import co.uniquindio.edu.modelo.Inventario;
import co.uniquindio.edu.modelo.Invoker;
import co.uniquindio.edu.modelo.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ProductoController {

    private Inventario inventario;
    private Invoker invoker;

    // Constructor
    public ProductoController() {
        this.inventario = new Inventario();
        this.invoker = new Invoker();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bntEliminarProducto;

    @FXML
    private Button bntGuardarProducto;

    @FXML
    private TextField txCantidad;

    @FXML
    private TextField txCantidadEliminar; // Nuevo campo para la cantidad a eliminar

    @FXML
    private TextField txCategoria;

    @FXML
    private TextField txNombre;

    @FXML
    private TextField txPrecio;

    @FXML
    void execute(ActionEvent event) {
        //obtener el elemento de la interfaz de usuario
        Object source = event.getSource();
        if (source == bntGuardarProducto) {
            guardarProducto();
        } else if (source == bntEliminarProducto) {
            eliminarProducto();
        }
    }

    @FXML
    void initialize() {
        // Inicialización si es necesario
    }

    private void guardarProducto() {
        //bloque que se intentará ejecutar
        try {
            String nombre = txNombre.getText();
            String categoria = txCategoria.getText();
            double precio = Double.parseDouble(txPrecio.getText()); // convierte cadena a double
            int cantidad = Integer.parseInt(txCantidad.getText());

            Producto nuevoProducto = new Producto(nombre, categoria, precio, cantidad);
            Command agregarCommand = new AgregarProductoCommand(inventario, nuevoProducto);
            invoker.recibirOperacion(agregarCommand);
            invoker.realizarOperaciones();

            limpiarCampos();
            imprimirProductos();

        } 
        //  mensaje de error indicando que el formato del número ingresado es inválido.
        catch (NumberFormatException e) {
            System.err.println("Error: Formato de número inválido.");
        }
    }

    private void eliminarProducto() {
        try {
            String nombre = txNombre.getText();
            int cantidadEliminar = Integer.parseInt(txCantidadEliminar.getText());
            Producto producto = inventario.buscarProducto(nombre);

            if (producto != null) {
                if (producto.getCantidad() > cantidadEliminar) {
                    producto.setCantidad(producto.getCantidad() - cantidadEliminar);
                } else {
                    Command eliminarCommand = new EliminarProductoCommand(inventario, producto);
                    invoker.recibirOperacion(eliminarCommand);
                    invoker.realizarOperaciones();
                }
            }
            limpiarCampos();
            imprimirProductos();
        } catch (NumberFormatException e) {
            System.err.println("Error: Formato de número inválido.");
        }
    }

    private void imprimirProductos() {
        System.out.println("Productos en el inventario:");
        for (Producto producto : inventario.getProductos()) {
            System.out.println(producto);
        }
    }

    private void limpiarCampos() {
        txNombre.setText("");
        txCategoria.setText("");
        txPrecio.setText("");
        txCantidad.setText("");
        txCantidadEliminar.setText(""); // Limpiar el nuevo campo de cantidad a eliminar
    }
}
