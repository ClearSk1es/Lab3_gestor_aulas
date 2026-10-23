package login.sistema.gestion.aulas;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import login.sistema.gestion.aulas.entidades.Usuario;
import login.sistema.gestion.aulas.util.SesionUsuario;

public class HomeController {

    @FXML
    private Label lblBienvenida;

    @FXML
    private void initialize() {
        Usuario usuario = SesionUsuario.getUsuarioActual();
        if (usuario != null) {
            lblBienvenida.setText(
                "Bienvenido, "
                + usuario.getNombre()
                + " "
                + usuario.getApellido()
            );
        }
    }

    @FXML
    private void cerrarSesion() throws IOException {
        SesionUsuario.cerrar();
        App.setRoot("login");
    }
}
