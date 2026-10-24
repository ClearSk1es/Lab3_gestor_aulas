package login.sistema.gestion.aulas;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;

import login.sistema.gestion.aulas.entidades.Aula;
import login.sistema.gestion.aulas.entidades.Aula.TipoAula;
import login.sistema.gestion.aulas.entidades.Aula.Estado;
import login.sistema.gestion.aulas.dao.AulaDAO;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import login.sistema.gestion.aulas.entidades.Usuario;
import login.sistema.gestion.aulas.util.SesionUsuario;
/**
 * Controlador principal del sistema de gestión de aulas.
 *
 * Administra los campos del formulario, valida los datos
 * ingresados por el usuario y registra nuevas aulas
 * en la base de datos mediante AulaDAO.
 *
 * También permite limpiar el formulario y cerrar sesión.
 */

public class HomeController {

// Componentes vinculados con la interfaz home.fxml.
// Permiten obtener los datos ingresados por el usuario.

    @FXML
    private Label lblBienvenida;

    @FXML
private TextField txtNombre;

@FXML
private TextField txtEdificio;

@FXML
private ComboBox<Integer> cmbPlanta;

@FXML
private TextField txtCapacidad;

@FXML
private ComboBox<TipoAula> cmbTipoAula;

@FXML
private TextField txtResponsable;

@FXML
private ComboBox<Estado> cmbEstado;

/**
 * Inicializa el formulario al cargar la pantalla.
 * Muestra el nombre del usuario que inició sesión
 * y carga las opciones de tipo de aula y estado.
 */

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

    // Carga los tipos de aula y estados disponibles
// en los menús desplegables del formulario.

            cmbTipoAula.getItems().setAll(TipoAula.values());
            cmbEstado.getItems().setAll(Estado.values());

    }
    /**
 * Cierra la sesión del usuario actual
 * y regresa a la pantalla de inicio de sesión.
 */

    @FXML
    private void cerrarSesion() throws IOException {
        SesionUsuario.cerrar();
        App.setRoot("login");
    }

    /**
 * Registra una nueva aula en la base de datos.
 *
 * Obtiene los datos del formulario, verifica que
 * todos los campos obligatorios estén completos
 * y valida que la capacidad sea un número entero positivo.
 *
 * Si los datos son válidos, crea un objeto Aula
 * y utiliza AulaDAO para guardar el registro.
 */

    @FXML
private void guardar() {

    // Obtiene los datos ingresados en el formulario
// y elimina los espacios innecesarios.

    String nombre = txtNombre.getText().trim();
String edificio = txtEdificio.getText().trim();
String capacidadTexto = txtCapacidad.getText().trim();
String responsable = txtResponsable.getText().trim();

// Verifica que todos los campos obligatorios estén completos.
// Si falta información, muestra una alerta y detiene el registro.

if (nombre.isEmpty() || edificio.isEmpty()
        || capacidadTexto.isEmpty() || responsable.isEmpty()
        || cmbPlanta.getValue() == null
        || cmbTipoAula.getValue() == null
        || cmbEstado.getValue() == null) {

    Alert alerta = new Alert(Alert.AlertType.WARNING);
    alerta.setTitle("Campos incompletos");
    alerta.setHeaderText(null);
    alerta.setContentText("Por favor, complete todos los campos del aula.");
    alerta.showAndWait();
    return;
}
// Convierte la capacidad ingresada a un número entero.
// Rechaza valores menores o iguales a cero y entradas no numéricas.

int capacidad;

try {
    capacidad = Integer.parseInt(capacidadTexto);

    if (capacidad <= 0) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Capacidad inválida");
        alerta.setHeaderText(null);
        alerta.setContentText("La capacidad debe ser mayor que cero.");
        alerta.showAndWait();
        return;
    }

} catch (NumberFormatException e) {
    Alert alerta = new Alert(Alert.AlertType.ERROR);
    alerta.setTitle("Capacidad inválida");
    alerta.setHeaderText(null);
    alerta.setContentText("Ingrese un número entero válido para la capacidad.");
    alerta.showAndWait();
    return;
}
// Crea un objeto Aula y le asigna los datos
// ingresados y validados del formulario.
Aula aula = new Aula();

aula.setNombre(nombre);
aula.setEdificio(edificio);
aula.setPlanta(cmbPlanta.getValue());
aula.setCapacidad(capacidad);
aula.setTipoAula(cmbTipoAula.getValue());
aula.setResponsable(responsable);
aula.setEstado(cmbEstado.getValue());

// Prepara el acceso a la base de datos mediante AulaDAO.
// Guarda el aula y obtiene el resultado de la operación.

AulaDAO aulaDAO = new AulaDAO();

boolean guardado = aulaDAO.guardar(aula);

// Muestra un mensaje de éxito si el aula fue registrada.
// En caso contrario, informa que ocurrió un error.

if (guardado) {
    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    alerta.setTitle("Registro exitoso");
    alerta.setHeaderText(null);
    alerta.setContentText("El aula se registró correctamente.");
    alerta.showAndWait();
} else {
    Alert alerta = new Alert(Alert.AlertType.ERROR);
    alerta.setTitle("Error al registrar");
    alerta.setHeaderText(null);
    alerta.setContentText("No se pudo registrar el aula.");
    alerta.showAndWait();
}
}
/**
 * Limpia los campos del formulario.
 * Borra los textos ingresados y restablece
 * las selecciones de los ComboBox.
 */

@FXML
private void limpiar() {
    txtNombre.clear();
    txtEdificio.clear();
    cmbPlanta.getSelectionModel().clearSelection();
    txtCapacidad.clear();
    cmbTipoAula.getSelectionModel().clearSelection();
    txtResponsable.clear();
    cmbEstado.getSelectionModel().clearSelection();
}
}
