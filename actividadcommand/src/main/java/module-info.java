module co.uniquindio.edu {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.uniquindio.edu to javafx.fxml;
    exports co.uniquindio.edu;

    opens co.uniquindio.edu.controlador;
    exports co.uniquindio.edu.controlador;
}
