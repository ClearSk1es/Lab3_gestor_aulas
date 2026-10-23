package login.sistema.gestion.aulas;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import login.sistema.gestion.aulas.dao.UsuarioDAO;
import login.sistema.gestion.aulas.entidades.Usuario;
import login.sistema.gestion.aulas.util.SesionUsuario;
import login.sistema.gestion.aulas.util.HashUtil;

public class LoginController {

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtClave;

    @FXML
    private Label lblMensaje;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void ingresar() {

        String correo = txtCorreo.getText().trim();
        String clave = txtClave.getText();

        if (correo.isEmpty() || clave.isEmpty()) {
            lblMensaje.setText("Complete todos los campos.");
            return;
        }

        try {
            //Convirtiendo la contraseña escrita por el usuario a MD5
            String claveHash = HashUtil.md5(clave);
            
            //Enviando el hash al DAO, no la contraseña original
            Usuario usuario = usuarioDAO.autenticar(correo, claveHash);

            if (usuario == null) {
                lblMensaje.setText("Correo o contraseña incorrectos.");
                txtClave.clear();
                return;
            }

            SesionUsuario.iniciar(usuario);
            App.setRoot("home");

        } catch (Exception e) {
            lblMensaje.setText("No se pudo conectar con la base de datos.");
            e.printStackTrace();
        }
    }
}