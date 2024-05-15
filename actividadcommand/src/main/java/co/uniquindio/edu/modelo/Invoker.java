package co.uniquindio.edu.modelo;

import java.util.ArrayList;
import java.util.List;

public class Invoker {
    private List<Command> operaciones = new ArrayList<>();

    public void recibirOperacion(Command command) {
        operaciones.add(command);
    }

    public void realizarOperaciones() {
        for (Command command : operaciones) {
            command.execute();
        }
        limpiarOperaciones();
    }

    public void limpiarOperaciones() {
        operaciones.clear();
    }
}

